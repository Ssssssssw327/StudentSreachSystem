<%@page import="com.entity.studentData"%>
<%@page import="com.service.studentDataSearch"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>更新数据</title>
</head>
<body>
	<%
		String id = request.getParameter("updateid");
		studentData student = studentDataSearch.selectStudentID(id);
	%>
	
	<div class="container" style="padding-top: 80px">
	<form action="${pageContext.request.contextPath}/updateServlet" method="post">
	<h2 style="text-align:center">修改数据</h2>
    <div><h2 style="text-align:center">id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id" id="id" value="<%=id %>" readonly = "readonly"></h2></div>
    <div></div>
	
    <div><h2 style="text-align:center">姓名&nbsp;&nbsp;<input type="text" name="name" id="name" value="<%=student.getName() %>" required></h2></div>
	<div></div>
	
    <div><h2 style="text-align:center">电话&nbsp;&nbsp;<input type="text" name="phone" id="phone" value="<%=student.getPhone() %>" required></h2></div>
	<div></div>

    <div><h2 style="text-align:center">qq&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  name="qq" id="qq" value="<%=student.getQq() %>"required></h2></div>
	<div></div>

    <div><h2 style="text-align:center">邮箱&nbsp;&nbsp;<input type="text" name="email" id="email" value="<%=student.getEmail() %>" required></h2></div>

    <h2 style="text-align:center"><button type="submit">确认</button></h2>
	</form>
	</div>
</body>
</html>