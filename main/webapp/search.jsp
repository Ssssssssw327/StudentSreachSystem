<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
  <head>
    <title>欢迎使用找人系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  	-->
  </head>
  <body>
 	<%!int permission = -1; 
 		String uploadSuccess = null;
 	%> 
	<%
	try {
		permission = (int)session.getAttribute("permission");
		uploadSuccess = (String)request.getAttribute("upLoadSuccess");
	} catch (NullPointerException e) {  //弥补上IOException
		e.printStackTrace();			
	}
	%>
	<div class="container" style="padding-top: 100px">
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <div class="card">
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/searchServlet" method="post">
                        <div class="form-row">
                            <div class="col-md-3 mb-3">
                            	<h1 style="text-align:center">软院找人</h1>
                                <h4 style="text-align:center"><label for="option">搜索</label>
                                <select class="custom-select" id="option" name="option" required>
                                    <option selected disabled value="">选择查询选项</option>
                                    <option value="name">姓名</option>
                                    <option value="id">学号/教工号</option>
                                    <option value="qq">QQ</option>
                                    <option value="email">邮箱</option>
                                    <option value="phone">电话号码</option>
                                </select></h4>
                            </div>
                            <div class="form-group col-md-6"><h4 style="text-align:center">
                                <label for="param">&nbsp;</label>
                                <input type="text" class="form-control" name="param" id="param"></h4>
                            </div>
                            <div class="col-md-3 mb-3"><h4 style="text-align:center">
                                <label for="count">记录个数</label>
                                <input type="text" size="7" class="form-control" id="count" name="count" required></h4>
                            </div>
                        </div>
                        <div align="center"><button type="submit" class="btn btn-primary" style="float: center">查询</button></div>
                    </form>
                    <%
                    	if(permission == 1){
                    		%>
                    		<div align="center"><br/>
                    		---------------------------------------------------------
                    		<br/><br/><a href="${pageContext.request.contextPath}/add.jsp"><button style="float: center">增加数据</button></a>
                			</div>
                			<form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype = "multipart/form-data">
							<div><h4 style="text-align:center">学号：&nbsp;<input type="text" name="id" id="id"></h4></div>
							<h4 style="text-align:center">上传照片：<input type="file" name="spicture"/></h4>
							<div style="text-align:center;vertical-align:middle;">
								<input type = "submit" value = "提交" >
							</div>
							</form>
                    		<%
                    	}
                    %>
                    <%
                    	if(permission !=1){
                    		%>
                    		<div align="center">
                    		---------------------------------------------------------
                    		<br/></div>
                    		<form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype = "multipart/form-data">							
							<h4 style="text-align:center">上传照片：<input type="file" name="spicture"/></h4>
							<div style="text-align:center;vertical-align:middle;">
								<input type = "submit" value = "提交" >
							</div>
							</form>                   		
                    		<%
                    	}
                    %>                   
                    <%
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
                    
                    %>
                    <br/>
                    <div style="text-align:center;vertical-align:middle;">
                    <a href = http://127.0.0.1:8089/demo ><button style="float: center">人脸识别</button></a>
                    </div>
                    <form action="${pageContext.request.contextPath}/FaceCompareServlet" method="post" enctype = "multipart/form-data">
							<h4 style="text-align:center">人脸搜索：<input type="file" name="spicture"/></h4>
							<div style="text-align:center;vertical-align:middle;">
								<input type = "submit" value = "提交" >
							</div>
					</form>
					<form action="${pageContext.request.contextPath}/websocketChatroom.jsp" method="post" >	
							<div style="text-align:center;vertical-align:middle;">
								<input type = "submit" value = "聊天" >
							</div>
					</form>
                </div>
            </div>
        </div>
        <div class="col-2"></div>
    </div>
</div>
  </body>
</html>