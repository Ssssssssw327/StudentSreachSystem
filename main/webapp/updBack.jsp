<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>更改数据</title>
</head>
<body>
	<%	
		
		int updcount = 0;
		String updid = "";
	try{
			updcount = (int)session.getAttribute("updateCount");
			updid = (String)session.getAttribute("updateid");
	}catch(NullPointerException e) {  //弥补上IOException
		e.printStackTrace();			
	}
	
		
	if(updcount != 0){
		%>
	<h2>已经成功更新<%=updcount %>条数据</h2>
	<h2>更新的学号：<%=updid %></h2>
	<a href="menu.jsp">继续查询</a>
	<%	}
	else{%> 
		<h2>操作有误</h2>
		<a href="menu.jsp">继续查询</a> <%
	}
	%>
</body>
</html>