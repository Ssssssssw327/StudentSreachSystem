<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>信息查找</title>
</head>
<body>
<div class="container" >
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <div class="card">
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/searchServlet" method="post">
                        <div class="form-row">
                            <div class="col-md-3 mb-3">
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

                </div>
            </div>
        </div>
        <div class="col-2"></div>
    </div>
</div>
</body>
</html>