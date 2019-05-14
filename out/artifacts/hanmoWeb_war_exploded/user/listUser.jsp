<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/6
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>用户名</td>
        <td>手机号</td>
        <td>ip</td>
        <td>用户角色</td>
        <td>时间</td>
        <%--<td>操作</td>--%>
        <%--<td>edit</td>
        <td>delete</td>--%>
    </tr>
    <c:forEach items="${userList}" var="user" varStatus="st">
        <tr>
            <td>${user.userId}</td>
            <td>${user.userName}</td>
            <td>${user.userTel}</td>
            <td>${user.userSec}</td>
            <td>${user.userIp}</td>
            <td>${user.role}</td>
            <td>${user.userTime}</td>
            <%--<td><a href="editHero?id=${hero.id}">edit</a></td>
            <td><a href="deleteHero?id=${hero.id}">delete</a></td>--%>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="6" align="center">
            <a href="?start=${next}">[下一页]</a>
        </td>
    </tr>
</table>
