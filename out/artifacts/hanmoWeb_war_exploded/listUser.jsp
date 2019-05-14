<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/6
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="bean.User" %>

<table align='center' border='1' cellspacing='1' width="" cellpadding="10" bgcolor="#ffffff">
    <tr>
        <th align="left" width="50">id</th>
        <th align="left" width="150">用户名</th>
        <th align="left" width="150">手机号</th>
        <th align="left" width="150">搜索内容</th>
        <th align="left" width="150">ip</th>
        <th align="left" width="100">0表示未审阅</th>
        <th align="left" width="150">时间</th>
        <th align="left" width="80">操作</th>
        <%--<td>edit</td>
        <td>delete</td>--%>
    </tr>
    <%
        ArrayList<User> list = (ArrayList) request.getAttribute("userList");

    %>
    <% for (int i = 0; i < list.size(); i++) { %>
    <tr>
        <td><%=list.get(i).userId%>
        </td>
        <td><%=list.get(i).userName%>
        </td>
        <td><%=list.get(i).userTel%>
        </td>
        <td><%=list.get(i).userSec%>
        </td>
        <td><%=list.get(i).userIp%>
        </td>
        <td><%=list.get(i).role%>
        </td>
        <td><%=list.get(i).userTime%>
        </td>
        <td><a name="delete" href="<%=request.getContextPath()%>/DeleteUserServlet?userName=<%=list.get(i).userName%>">删除</a>
            <a name="check" href="<%=request.getContextPath()%>/CheckUserApi?userName=<%=list.get(i).userName%>">审阅</a>
        </td>
    </tr>
    <%}%>
    <tr>
        <td colspan="8" align="center">
            <%--<a href="?flag=-1&start=${start}">[上一页]</a>
            <a href="?flag=1&start=${start}">[下一页]</a>--%>
            <a href="<%=request.getContextPath()%>/PagingReadingServlet?admin=admin&flag=0&start=0">[回到首页]</a>
            <a href="<%=request.getContextPath()%>/PagingReadingServlet?admin=admin&flag=-1&start=${start}">[上一页]</a>
            <a href="<%=request.getContextPath()%>/PagingReadingServlet?admin=admin&flag=1&start=${start}">[下一页]</a>
            <a href="<%=request.getContextPath()%>/DownloadExl">[导出Excel]</a>
        </td>
    </tr>
</table>
