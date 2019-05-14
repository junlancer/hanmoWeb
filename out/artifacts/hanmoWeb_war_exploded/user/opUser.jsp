<%@ page import="bean.User" %>
<%@ page import="utils.JdbcUtil" %>
<%@ page import="dao.UserDaoImpl" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理系统</title>
    <%
        User user = (User) session.getAttribute("user");
        String getRole = "";
        if (user != null && !"".equals(user)) {
    %>
    <script type="text/javascript">
        alert("<%=user.userName%>操作成功");
    </script>


    <%
            if (user.role == -1)
                getRole = "管理员:";
            else
                getRole = "普通用户:";
        }

        String userName = (String) session.getAttribute("userName");
        if (userName != null && !"".equals(userName)) {
    %>
    <script type="text/javascript">
        alert("删除<%=userName%>成功");
    </script>
    <%
        }
    %>
</head>
<body>

<div>

</div>
<br>
<h1 align="center">用户管理系统</h1>
<div align="center">
    <p></p>
    <table width="" border="1" cellpadding="10" cellspacing="1" bgcolor="#ffffff" align="center">
        <tr>
            <th align="left" width="50">id</th>
            <th align="left" width="150">用户名</th>
            <th align="left" width="150">手机号</th>
            <th align="left" width="150">搜索内容</th>
            <th align="left" width="150">ip</th>
            <th align="left" width="100">用户角色</th>
            <th align="left" width="80">操作</th>
        </tr>
        <%
            List<User> allUser = UserDaoImpl.getInstance().read(null);
            for (User u : allUser) {
                String role = "普通用户";
                if (u.role == -1) {
                    role = "管理员";

                }
                if (u.userTel == null || "".equals(u.userTel)) {
                    u.userTel = u.userName;
                }

        %>
        <tr>
            <td align="left" width="50"><%=u.userId%>
            </td>
            <td align="left" width="150"><%=u.userName%>
            </td>
            <td align="left" width="150"><%=u.userTel%>
            </td>
            <td align="left" width="150"><%=u.userSec%>
            </td>
            <td align="left" width="150"><%=u.userIp%>
            </td>
            <td align="left" width="100"><%=role%>
            </td>
            <td align="left" width="80">
                <a name="update" href="user/updateUser.jsp?userName=<%=u.userName%>">修改</a>
                <a name="delete" href="<%=request.getContextPath()%>/DeleteUserServlet?userName=<%=u.userName%>">删除</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
