package com.service;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.dao.*;
import com.dao.FileUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 人脸比对 WebAPI 接口调用示例
 * 运行前：请先填写Appid、APIKey、APISecret以及图片路径
 * 运行方法：直接运行 main() 即可
 * 结果： 控制台输出结果信息
 * 接口文档（必看）：https://www.xfyun.cn/doc/face/xffaceComparisonRecg/API.html
 */

public class WebFaceCompare {

    public static String compareFace(InputStream imagePath1,InputStream imagePath2) throws Exception {
        WebFaceCompare demo = new WebFaceCompare();
        ResponseData respData = demo.faceContrast(imagePath1,imagePath2);
        if (respData!=null && respData.getPayLoad().getFaceCompareResult() != null) {
           String textBase64 = respData.getPayLoad().getFaceCompareResult().getText();
            String text = new String(Base64.getDecoder().decode(textBase64));
            System.out.println("人脸比对结果(text)base64解码后：");
            System.out.println(text);
            return text;
        }
		return null;
    }
   class Property {
        public final static  String requestUrl =  "https://api.xf-yun.com/";
        public final static  String appid ="b"; //请填写控制台获取的APPID,
        public final static  String apiSecret="Y";  //请填写控制台获取的APISecret;
        public final static  String apiKey = "c";  //请填写控制台获取的APIKey
        public final static  String serviceId= "s";
   }

    public String getXParam(String imageBase641, String imageEncoding1, String imageBase642, String imageEncoding2) {
        JsonObject jso = new JsonObject();

        /** header **/
        JsonObject header = new JsonObject();
        header.addProperty("app_id", Property.appid);
        header.addProperty("status", 3);

        jso.add("header", header);

        /** parameter **/
        JsonObject parameter = new JsonObject();
        JsonObject service = new JsonObject();
        service.addProperty("service_kind", "face_compare");

        JsonObject faceCompareResult = new JsonObject();
        faceCompareResult.addProperty("encoding", "utf8");
        faceCompareResult.addProperty("format", "json");
        faceCompareResult.addProperty("compress", "raw");
        service.add("face_compare_result", faceCompareResult);
        parameter.add(Property.serviceId, service);
        jso.add("parameter", parameter);

        /** payload **/
        JsonObject payload = new JsonObject();
        JsonObject inputImage1 = new JsonObject();
        inputImage1.addProperty("encoding", imageEncoding1);
        inputImage1.addProperty("image", imageBase641);
        payload.add("input1", inputImage1);

        JsonObject inputImage2 = new JsonObject();
        inputImage2.addProperty("encoding", imageEncoding2);
        inputImage2.addProperty("image", imageBase642);
        payload.add("input2", inputImage2);
        System.out.println(jso.toString());
        jso.add("payload", payload);
        return jso.toString();
    }


    
  //读取image
    private byte[] readImage(InputStream imagePath) throws IOException {
        //InputStream is = new FileInputStream(imagePath);
        byte[] imageByteArray1 = FileUtil.readInputStream(imagePath);
        //return is.readAllBytes();
        return imageByteArray1;
    }

    
    public ResponseData faceContrast(InputStream imageFirstUrl, InputStream imageSecondUrl) throws Exception {

        String url = assembleRequestUrl(Property.requestUrl, Property.apiKey, Property.apiSecret);

        String imageBase641 = Base64.getEncoder().encodeToString(readImage(imageFirstUrl));
        String imageBase642 = Base64.getEncoder().encodeToString(readImage(imageSecondUrl));
        String imageEncoding1 = "jpg";
        String imageEncoding2 = "jpg";
        return handleFaceContrastRes(url, getXParam(imageBase641, imageEncoding1, imageBase642, imageEncoding2));
    }

    public static final Gson json = new Gson();

    private ResponseData handleFaceContrastRes(String url, String bodyParam) {

        Map<String,String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        String result = HttpUtil.doPost2(url, headers,bodyParam);
        if (result != null) {
            System.out.println("人脸识别接口调用结果：" + result);
            return json.fromJson(result, ResponseData.class);
        } else {
            return null;
        }
    }


    //构建url
    public static String assembleRequestUrl(String requestUrl, String apiKey, String apiSecret) {
        URL url = null;
        // 替换调schema前缀 ，原因是URL库不支持解析包含ws,wss schema的url
        String  httpRequestUrl = requestUrl.replace("ws://", "http://").replace("wss://","https://" );
        try {
            url = new URL(httpRequestUrl);
            //获取当前日期并格式化
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            String date = format.format(new Date());

            String host = url.getHost();
            if (url.getPort()!=80 && url.getPort() !=443){
                host = host +":"+String.valueOf(url.getPort());
            }
            StringBuilder builder = new StringBuilder("host: ").append(host).append("\n").//
                    append("date: ").append(date).append("\n").//
                    append("POST ").append(url.getPath()).append(" HTTP/1.1");
            Charset charset = Charset.forName("UTF-8");
            Mac mac = Mac.getInstance("hmacsha256");
            SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(charset), "hmacsha256");
            mac.init(spec);
            byte[] hexDigits = mac.doFinal(builder.toString().getBytes(charset));
            String sha = java.util.Base64.getEncoder().encodeToString(hexDigits);

            String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
            String authBase = java.util.Base64.getEncoder().encodeToString(authorization.getBytes(charset));
            return String.format("%s?authorization=%s&host=%s&date=%s", requestUrl, URLEncoder.encode(authBase,"utf-8"), URLEncoder.encode(host,"utf-8"), URLEncoder.encode(date,"utf-8"));

        } catch (Exception e) {
            throw new RuntimeException("assemble requestUrl error:"+e.getMessage());
        }
    }

    public static class ResponseData {
        private Header header;
        private PayLoad payload;
        public Header getHeader() {
            return header;
        }
        public PayLoad getPayLoad() {
            return payload;
        }
    }
    public static class Header {
        private int code;
        private String message;
        private String sid;
        public int getCode() {
            return code;
        }
        public String getMessage() {
            return message;
        }
        public String getSid() {
            return sid;
        }
    }
    public static class PayLoad {
        private FaceResult face_compare_result;
        public FaceResult getFaceCompareResult() {
            return face_compare_result;
        }
    }
    public static class FaceResult {
        private String compress;
        private String encoding;
        private String format;
        private String text;
        public String getCompress() {
            return compress;
        }
        public String getEncoding() {
            return encoding;
        }
        public String getFormat() {
            return format;
        }
        public String getText() {
            return text;
        }
    }
}

