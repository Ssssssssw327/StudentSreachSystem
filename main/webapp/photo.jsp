<%@page import="com.service.studentDataSearch"%>
<%@page import="com.entity.studentData"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id = request.getParameter("id");
	if(id == null){
		id = (String)request.getAttribute("id");
	}
	if(id != null)
	{
		studentData temp = studentDataSearch.selectStudentID(id);
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%> 
		<div style="text-align:center;vertical-align:middle;">
			<img name="pic" style="width:240px;height:320px" src="<%=basePath+"DownloadServlet?id="+id%>"/>
			<h3>学号:<%=id %></h3>
			<h3>姓名:<%=temp.getName() %></h3>
			<br/><a href = "menu.jsp"><button>继续查询</button></a>
		</div>
	<%
	}else{
		%>
		<div style="text-align:center;vertical-align:middle;">
			<h3>未查询到相关人员</h3>
			<br/><a href = "menu.jsp"><button>继续查询</button></a>
		</div>
		<%
	}
	%>
</body>
</html>