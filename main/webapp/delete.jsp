<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>删除数据</title>
</head>
<body>
	<%	
		int delcount = 0;
		String delid = "";
		
	try{
			delcount = (int)session.getAttribute("deleteCount");			
			delid = (String)session.getAttribute("deleteid");
	}catch(NullPointerException e) {  //弥补上IOException
		e.printStackTrace();			
	}
	if(delcount != 0){
		%>
	<h2>已经成功删除<%=delcount %>条数据</h2>
	<h2>删除的学号：<%=delid %></h2>
	<a href="menu.jsp">继续查询</a>
	<%	}
//		response.sendRedirect("search.jsp");
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	else{%> 
		<h2>操作有误</h2>
		<a href="menu.jsp">继续查询</a> <%
	}
	%>
</body>
</html>