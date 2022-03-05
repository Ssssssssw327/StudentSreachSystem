<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>照片上传</title>
</head>
<body>
	<%!int permission = -1; 
 		String uploadSuccess = null;
 	%> 
	<%
	try {
		permission = (int)session.getAttribute("permission");
		
	} catch (NullPointerException e) {  //弥补上IOException
		e.printStackTrace();			
	}
	uploadSuccess = (String)request.getAttribute("upLoadSuccess");
	%>
	<%
    if(permission == 1){
    %>
   		<form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype = "multipart/form-data">
			<div><h4 style="text-align:center">学号：&nbsp;<input type="text" name="id" id="id"></h4></div>
			<h4 style="text-align:center">上传照片：<input type="file" name="spicture"/></h4>
			<div style="text-align:center;vertical-align:middle;">
				<input type = "submit" value = "提交" >
			</div>
		</form>
    <%
    }else{
    %>
    	<form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype = "multipart/form-data">							
			<h4 style="text-align:center">上传照片：<input type="file" name="spicture"/></h4>
			<div style="text-align:center;vertical-align:middle;">
				<input type = "submit" value = "提交" >
			</div>
		</form>                   		
    <%
    }%>         
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