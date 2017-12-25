<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>客户编辑</title>
</head>
<body>
<h1>客户信息</h1>

<form id="customer_edit" method="post" action="customer_edit">
    <table>
        <tr>
            <th>编号</th>
            <th>客户名称</th>
            <th>联系人</th>
            <th>电话号码</th>
            <th>邮箱地址</th>
        </tr>
        <tr>
            <td><input type="text" name="id" value="${customer.id}" readonly="readonly"/></td>
            <td><input type="text" name="name" value="${customer.name}"/></td>
            <td><input type="text" name="contact" value="${customer.contact}" /></td>
            <td><input type="text" name="telephone" value="${customer.telephone}" /></td>
            <td><input type="text" name="email" value="${customer.email}" /></td>
        </tr>
        <tr>
            <td colspan="5">
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>

