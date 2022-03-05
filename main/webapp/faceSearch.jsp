<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>人脸搜索</title>
</head>
<body>
	<%! 
 		String uploadSuccess = null;
 	%> 
	<%
		uploadSuccess = (String)request.getAttribute("upLoadSuccess");
	%>
		<form action="${pageContext.request.contextPath}/FaceCompareServlet" method="post" enctype = "multipart/form-data">
				<h4 style="text-align:center">人脸搜索：<input type="file" name="spicture"/></h4>
				<div style="text-align:center;vertical-align:middle;">
						<input type = "submit" value = "提交" >
				</div>
		</form>
		
	<%
		if(uploadSuccess != null){
			if("success".equals(uploadSuccess)){
            %>
              <div><h4 style="text-align:center">照片上传成功</h4></div>
            <% 
            }else if("fail".equals(uploadSuccess)){
            %>
               <div><h4 style="text-align:center;color:red;" >照片上传失败,学号有误</h4></div>
            <%
            }else if("图片类型有误！".equals(uploadSuccess)){
            %>
               <div><h4 style="text-align:center;color:red;" >图片上传失败,图片类型有误！</h4></div>
            <%
            }
		}
                    	
    %>
</body>
</html>