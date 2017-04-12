<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="BASE" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户</title>
</head>
<body>
	<h1>客户列表</h1>
	<table>
		<tr>
			<th>客户名称</th>
			<th>联系人</th>
			<th>电话号码</th>
			<th>邮箱</th>
			<th>操作</th>
		</tr>
		<c:forEach var="customer" items="${customers}">
			<tr>
				<td>${customer.name}</td>
				<td>${customer.contact}</td>
				<td>${customer.telephone}</td>
				<td>${customer.email}</td>
				<td>
					<a href="${BASE}/customer_edit?id=${customer.id}">编辑</a> 
					<a href="${BASE}/customer_delete?id=${customer.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>