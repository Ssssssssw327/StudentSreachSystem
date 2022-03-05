<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>聊天窗口</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	//对象
   var ws;
	//访问后台路径
   var ws_url="ws://localhost:8080/HomeByself1/chatroom";
   //让函数在加载完页面之后再进行操作
   $(function(){
	   
	   ws_connect();
	   
	   //点击事件
	   $("#send").click(function(){
			ws_sendMsg();
		});
	   $("#disconnect").click(function(){
			ws_close();
		});
	   $("#uploadImg").click(function(){
			 
		    ws_sendImg();
		});
   });
   function ws_connect(){
	  var loginUser="${sessionScope.studentId}"
	  if ('WebSocket' in window) {
		  ws = new WebSocket(ws_url+"?loginName="+loginUser);
      } else if ('MozWebSocket' in window) {
          ws = new MozWebSocket(ws_url);
      } else {
          console.log('Error: WebSocket is not supported by this browser.');
          return;
      }

      ws.onopen = function () {
          console.log('Info: WebSocket connection opened.');
          
      };

      ws.onclose = function () {
        
          console.log('Info: WebSocket closed.');
      };

      ws.onmessage = function (message) {
    	  if("string"==typeof(message.data)){
    		  console.log(message.data);
    		  //console.log("@@@"+message.data);
				var receiveMsg=message.data;
          		var obj=JSON.parse(receiveMsg);
          		if(obj.type=="s"){
        	  		$("#record").append("<div>"+obj.msgDateStr+""+obj.msgInfo+"</div>");
        	  		var userHtml="";
        	  		var userList=obj.userList;
        	  		for(var i=0;i<userList.length;i++){
        		  		userHtml=userHtml+userList[i]+"<br/><br/>";
        		  		}
        	  		$("#userList").html(userHtml);
		  		}else if(obj.type=="p"){
			  		$("#record").append("<div>"+obj.msgSender+":&nbsp;"+obj.msgDateStr+"</div><div>"+obj.msgInfo+"</div>");
          		}else if(obj.type=="img"){
				 		picp="<div>"+obj.msgSender+":&nbsp;"+obj.msgDateStr+"</div>";
		  		}else{
				 		//未来可以用于扩展功能
		  		}
    		  
    	  }else{
    		  var reader=new FileReader();
		      reader.readAsDataURL(message.data);
		      reader.onload=function(evt){
		          if(evt.target.readyState==FileReader.DONE){
		        	  //意味着传输结束，传输过程中没有出现异常
		             var url=evt.target.result;
		           $("#record").append(picp+"<div><img src='"+url+"' style='max-height:150px; max-width:150px; vertical-align: middle; align: center;'/></div>");
		          }
		      }
    	  }
          
      };
      
    	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function () {
          ws_close();
      }
  };
  function ws_sendMsg(){
	  //发送信息之前需要先获取信息
	  var msg=$("#msg").val();
	  ws.send(msg);
	  msg= $("#msg").val("");
	  
  };
  function ws_sendImg(){
	  var fObj=$("#img")[0].files[0];
      if(fObj){
		var reader=new FileReader();
        reader.readAsArrayBuffer(fObj);
		reader.onload=function(evt){
			ws.send(evt.target.result);
		}
		$("#img").val("");
      }else{
        $("#img").val("");
      }
  };
	//关闭WebSocket连接
  function ws_close() {
      ws.close();
  }
</script>
</head>
<body>
********************聊天窗口******************************
 <table style="border: 1px solid #00F;">
     <tbody>
       <tr>
          <td colspan="2" align="center">
             <h3>welcome 【${sessionScope.studentId}】 to use this system!</h3>
          </td>
       </tr>
       <tr>
         <td width="500px" height="300px" style="border: 1px solid #00F; vertical-align: top;" id="content"
          name="content">
           <div style="background-color:white;">
              <table id="tbRecord">
                <tbody id="record" style="display:block;height:300px; width:500px; overflow:auto;"/>
              </table>
           </div>
         </td>
         <td width="100px" style="border:1px solid #00F; vertical-align:top;">
           <div style="overflow:auto;">
              <table id="tbuserList">
                 <tbody id="userList" style="display:block; height:300px;overflow:auto;"/>
              </table>
           </div>
         </td>
       </tr>
     </tbody>
     <tfoot>
        <tr>
          <td colspan="2" align="center">
          <input id="msg" name="msg" style="width:100%;" placeholder="信息输入"/>
          </td>
       </tr>
       <tr>
         <td colspan="2" align="center">
           <button style="margin:0 30px 0 30px" id="send" name="send">send</button>
           <input type="file" id="img" style="width:200px; height:30px"/>
           <button id="uploadImg" name="uploadImg">uploadImg</button>
           <!--  <form action="${pageContext.request.contextPath}/search.jsp" method="post" >-->
			<a href = "menu.jsp"><button style="margin:0 30px 0 30px" id="disconnect" name="disconnect">Disconnect</button></a>		
			<!--</form>    -->  
         </td>
       </tr>
     </tfoot>
 </table>
</body>
</html>