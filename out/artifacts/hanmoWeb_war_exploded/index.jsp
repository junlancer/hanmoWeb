<%@ page language="java" import="java.util.*" pageEncoding="GB18030" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
      
    <meta charset="utf-8">
       <title>后台登录</title>
      
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
     
      
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
      
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        

</head>
<body>
    
<div class="container">
           
    <div class="form row">
                   
        <div class="form-horizontal col-md-offset-3" id="login_form">
                            <h3 class="form-title">管理员登录</h3>
                           
            <div class="col-md-9">
                <form action="<%=request.getContextPath()%>/LoginServlet" method="post">
                    <p>帐 户：<input type="text" name="admin" id="admin" placeholder="请输入姓名"></p>
                    <p>密 码：<input type="password" name="passWord" id="passWord" placeholder="请输入密码"></p>
                    <div class="form-group col-md-offset-9">
                        <button type="submit" class="btn btn-success " name="submit">登录</button>
                                          
                    </div>
                </form>
            </div>
                       
        </div>
               
    </div>
       
</div>

</body>
</html>



