<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Result</title>
    <meta charset="utf-8">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">
    	function register(id){
			//var updateid = $("#updateid").val();
			//alert(id);
			$("#content").load(
					//"MoblieServlet",
					"update.jsp",
					"updateid="+id
			);
		}
    	function getphoto(id){
			//var updateid = $("#updateid").val();
			//alert(id);
			$("#content").load(
					//"MoblieServlet",
					"photo.jsp",
					"id="+id
			);
		}
    	$(document).ready(function(){
    		$("tr:odd").css("background-color","lightgray");	
    	});
    </script>
</head>
    <body>
    <div id="container" style="width:1000px">
    <div id="menu" style="height:500px;width:700px;float:left;">
    <table class="table" style="padding-top: 80px">
    <tr>
    	<th>学号</th>
    	<th>姓名</th>
    	<th>电话</th>
    	<th>qq</th>
    	<th>邮箱</th>
    	<th>功能</th>
    </tr>
    <%
    List<String> table = (List<String>)request.getAttribute("table");
    %>
    <%!int permission = -1; %>
    <%
	try {
		permission = (int)session.getAttribute("permission");
	} catch (NullPointerException e) {  //弥补上IOException
		e.printStackTrace();			
	}
	if(permission == 1){ //拥有管理员权限  permission = 1
		for (int i=0;i<(table.size());i=i+5) {
		   %>
		   <tr>
		       <td><%=table.get(i)%></td>
		       <td><%=table.get(i+1)%></td>
		       <td><%=table.get(i+2)%></td>
		       <td><%=table.get(i+3)%></td>
		       <td><%=table.get(i+4)%></td>
		       <td><form action="${pageContext.request.contextPath}/deleteServlet" method="post">
		       			<button class="delete" type="submit" name="deleteid" id ="deleteid" value="<%=table.get(i)%>"style="float: center">删除</button>
		       		</form>
		       </td>
		      
		       <!-- <td><form action="${pageContext.request.contextPath}/update.jsp" method="post">
		       			<button class="update" type="submit" name="updateid" value="<%=table.get(i)%>"style="float: center">修改</button>
		       		</form>
		       </td> -->
		       <td>
		       		<button class="update" type="submit" name="updateid" id="<%=table.get(i) %>" value="<%=table.get(i) %>" onclick = "register(this.id)" style="float: center">修改</button>
		       </td>
		       <!-- <td>
		       		<form action="${pageContext.request.contextPath}/photo.jsp" method="post">
		       			<button class="selectphoto" type="submit" name="id" value="<%=table.get(i)%>"style="float: center">查看照片</button>
		       		</form>        
		       </td> -->
		       <td>
		       		<button class="selectphoto" type="submit" name="id" id="<%=table.get(i) %>" value="<%=table.get(i) %>" onclick = "getphoto(this.id)" style="float: center">照片</button>
		       </td>
		   </tr>
		   
		   <%
		}
	}else{//普通用户权限 permission = 0
		for (int i=0;i<(table.size());i=i+5) {
		   %>
		   <tr>
		       <td><%=table.get(i)%></td>
		       <td><%=table.get(i+1)%></td>
		       <td><%=table.get(i+2)%></td>
		       <td><%=table.get(i+3)%></td>
		       <td><%=table.get(i+4)%></td>
		       <td>
		       		<form action="${pageContext.request.contextPath}/photo.jsp" method="post">
		       			<button class="selectphoto" type="submit" name="id" value="<%=table.get(i)%>"style="float: center">查看照片</button>
		       		</form>        
		       </td> 
		   </tr>
		   <%
		   }
	}%>
    
    <!--<h1>${pageContext.request.queryString}</h1>  -->
	</table>
	<span id = "try"></span>
	  <nav>
			<ul>
				<%
				int currentPage = (int) request.getAttribute("currentPage");
                int totalPage = (int) request.getAttribute("totalpage");
                int displayNumber = (int) request.getAttribute("number");
                if (totalPage != 1){
                	if(currentPage != 1){
                		session.setAttribute("currentPage", currentPage-1);
                %>
                <li><a href="${pageContext.request.contextPath}/resultDispose?page=<%=currentPage-1%>&displayNumber=<%=displayNumber%>&totalPage=<%=totalPage%>" method="post">上一页</a></li>
                 <%
                	}
                    for(int i = 1;i<=totalPage;i++){
                    	if(currentPage != i){
                    		session.setAttribute("currentPage", i);
                        %>
                        <li><a href="${pageContext.request.contextPath}/resultDispose?page=<%=i%>&displayNumber=<%=displayNumber%>&totalPage=<%=totalPage%>" method="post"><%=i%></a></li>
                        <%
                        
                    	}
                        if(currentPage == i){
                        	session.setAttribute("currentPage", currentPage);
                        %>
                        <li><a href="#"><%=i%></a></li>
                        <%
                         }
                    }
					if(currentPage < totalPage){
						session.setAttribute("currentPage", currentPage+1);
                      	%>
                        <li><a href="${pageContext.request.contextPath}/resultDispose?page=<%=currentPage+1%>&displayNumber=<%=displayNumber%>&totalPage=<%=totalPage%>" method="post">下一页</a></li>
                        <%
                        
					}
           		}
                        %>
                    </ul>
		</nav>
    <a href="${pageContext.request.contextPath}/menu.jsp" class="card-link">继续查询</a>
    </div>
    <div id="content" style="background-color:#EEEEEE;height:500px;width:300px;float:left;">
    		
    		<div id = "try"></div>
	</div>
	</div>
    </body>
</html>
