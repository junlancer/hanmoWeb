<%@ page language="java" import="java.util.*" pageEncoding="GB18030" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    �0�2�0�2
    <meta charset="utf-8">
    �0�2�0�2 <title>��̨��¼</title>
    �0�2�0�2
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    �0�2
    �0�2�0�2
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    �0�2�0�2
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    �0�2�0�2 �0�2

</head>
<body>
�0�2�0�2 �0�2
<div class="container">
    �0�2�0�2�0�2�0�2�0�2�0�2�0�2
    <div class="form row">
        �0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2
        <div class="form-horizontal col-md-offset-3" id="login_form">
            �0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2 <h3 class="form-title">����Ա��¼</h3>
            �0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2
            <div class="col-md-9">
                <form action="<%=request.getContextPath()%>/LoginServlet" method="post">
                    <p>�� ����<input type="text" name="admin" id="admin" placeholder="����������"></p>
                    <p>�� �룺<input type="password" name="passWord" id="passWord" placeholder="����������"></p>
                    <div class="form-group col-md-offset-9">
                        <button type="submit" class="btn btn-success " name="submit">��¼</button>
                        �0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2
                    </div>
                </form>
            </div>
            �0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2�0�2
        </div>
        �0�2�0�2�0�2�0�2�0�2�0�2�0�2
    </div>
    �0�2�0�2�0�2
</div>

</body>
</html>



