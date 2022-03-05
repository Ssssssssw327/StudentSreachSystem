<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <title>欢迎使用找人系统</title>
    <script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
	$(function(){
		//点击事件
		$("#search").click(function(){
			$("#content").load(
					"searchMenu.jsp"
			);
		});
		$("#add").click(function(){
			$("#content").load(
					"add.jsp"
			);
		});
		$("#upload").click(function(){
			$("#content").load(
					"uploadMenu.jsp"
			);
		});
		$("#faceSearch").click(function(){
			$("#content").load(
					"faceSearch.jsp"
			);
		});
	});
	</script>
</head>
  <body>
  <%!int permission = -1; 
 	%> 
	<%
	try {
		permission = (int)session.getAttribute("permission");
	} catch (NullPointerException e) {  //弥补上IOException
		e.printStackTrace();			
	}
	%>
  <div id="container" style="width:1000px">
	<div id="header" >
		<h1 style="margin-bottom:0;text-align:center;">软院找人</h1>
	</div>
 
	<div id="menu" style="height:350px;width:200px;float:left;" align="center">
		<b>菜单</b><br><br/>
		<button class="search" type="submit" name="search" id="search" style="float: center">信息查找</button><br><br/>
		<%if(permission == 1){
			%>
			<button class="add" type="submit" name="add" id="add" style="float: center">增加数据</button><br><br/>
			<%
		}%>
		<button class="upload" type="submit" name="upload" id="upload" style="float: center">上传照片</button><br><br/>
		<button class="faceSearch" type="submit" name="faceSearch" id="faceSearch" style="float: center">人脸搜索</button><br><br/>
		<form action="${pageContext.request.contextPath}/websocketChatroom.jsp" method="post" >	
			<button class="chat" type="submit" name="chat" id="chat" style="float: center">聊天</button><br><br/>
		</form>
	</div>
 	<div id="content" style="background-color:#EEEEEE;height:350px;width:800px;float:left;">
		
	</div>
 
	<div id="footer" style="background-color:#FFA500;clear:both;text-align:center;">
	版权 © lsw
	</div>
 
</div>
  </body>
</html>