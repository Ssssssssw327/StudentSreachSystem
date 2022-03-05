<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<script src="https://code.jquery.com/jquery-1.8.3.min.js"></script>
<!--  <script type="text/javascript" src="js/jquery.js"></script>-->
    <script type="text/javascript">
    	function check(){ // return true 表单正常提交 false 表单终止提交
    		var id = $("#id").val();
    		var name = $("#name").val();
    		var phone = $("#phone").val();
    		var qq = $("#qq").val();
    		var email = $("#email").val();
    		if(!(id>0 && id<300000000)){
    			alert("学号有误！");
    			return false;
    		}
    		if(!(name.length>1 && name.length<5)){
    			alert("姓名长度有误！必须是2-4位");
    			return false;
    		}
    		return true;
    	}	  
    	$(document).ready(function(){	
    	});
    </script>
<title>增加数据</title>
</head>
<body>
	<%
		//int count = 0;
		//session.setAttribute("AddCount",count);
	%>
	<div class="container">
	<form action="${pageContext.request.contextPath}/addServlet" method="post" onsubmit = "return check()">
	<h4 style="text-align:center">填写数据</h4>
    <h4 style="text-align:center">id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id" id="id" required></h4>
   <h4 style="text-align:center">姓名&nbsp;&nbsp;<input type="text" name="name" id="name"></h4>	
    <h4 style="text-align:center">电话&nbsp;&nbsp;<input type="text" name="phone" id="phone"></h4>
    <h4 style="text-align:center">qq&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="qq" id="qq"></h4>
	<h4 style="text-align:center">邮箱&nbsp;&nbsp;<input type="text" name="email" id="email"></h4>
    <h4 style="text-align:center"><button type="submit">确认</button></h4>
	</form>
		<div align="center">
		<a href="${pageContext.request.contextPath}/menu.jsp"><button style="float: center">返回上一级</button></a>
		</div>
	</div>
	<%	int getcount = 0;
		try {
			getcount = (int)session.getAttribute("AddCount");
		} catch (NullPointerException e) {  //弥补上IOException
			e.printStackTrace();			
		}
		if(getcount==-1){
			%>
				<div><h3 style="text-align:center">该学号/教工号已存在与数据库中，请确认后重新填写</h3></div>
			<%
			getcount = 0;
			session.setAttribute("AddCount",getcount);
		}else if(getcount == 1){
			%>
				<div><h3 style="text-align:center">已成功添加<%=getcount %>条数据</h3></div>
			<%
			getcount = 0;
			session.setAttribute("AddCount",getcount);
		}
	%>
</body>
</html>