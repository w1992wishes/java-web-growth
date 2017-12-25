<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>客户管理 - 创建客户</title>
</head>
<body>
    <h1>创建客户</h1>

    <table>
    <form id="customer_create"  method="post" action="customer_create">
        <tr>
            <th>客户名称</th>
            <th>联系人</th>
            <th>电话号码</th>
            <th>邮箱地址</th>
        </tr>
        <tr>
            <td><input type="text" name="name"/></td>
            <td><input type="text" name="contact" /></td>
            <td><input type="text" name="telephone" /></td>
            <td><input type="text" name="email" /></td>
        </tr>
        <tr>
            <td colspan="4">
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </form>
    </table>
</body>
</html>
