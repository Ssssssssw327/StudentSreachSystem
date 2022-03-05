<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>login</title>
</head>
<body>
	
	<%! String uname; %>	
	<%
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if("name".equals(cookie.getName())){
					uname = cookie.getValue();
				}
			}
		}
		
	%>
	<div id="container" style="width:1300px;">
		<div id="menu" style="height:200px;width:400px;float:left;">
		</div>
		<div id="context" style="height:200px;width:400px;float:left;">
			<h1 style="text-align:center">软院找人</h1>
			<fieldset>
    			<legend>登录窗口</legend>
				<form action="${pageContext.request.contextPath}/LoginServlet" method = "post">
					<h4 style="text-align:center">用户名：<input type = "text" name = "uname" value = <%=(uname==null ? "":uname)%>></h4>
					<h4 style="text-align:center">密码：<input type = "password" name = "upwd"></h4>
					<h4 style="text-align:center"><input type = "submit" value = "登录"></h4>
				</form>   
   			</fieldset>
		</div>
	</div>
   
   
</body>
</html>