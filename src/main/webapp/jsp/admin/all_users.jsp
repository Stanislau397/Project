<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>all_users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table1.css">
</head>
<header>
    <jsp:include page="admin_cabinet.jsp"/>
</header>
<body>
<table class="content-table">
    <thead>
    <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Email</th>
    <th>Role</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${user_list}" var="users">
    <tr>
        <td>${users.userId}</td>
        <td>${users.userName}</td>
        <td>${users.email}</td>
        <td>${users.role}</td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
