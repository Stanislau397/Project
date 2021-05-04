<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>all_users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all_users.css">
</head>
<header>
    <jsp:include page="admin_cabinet.jsp"/>
</header>
<body>
<div class="main-content">
    <div class="users">
        <c:forEach items="${requestScope.user_list}" var="users" varStatus="counter">
            <div class="picture">
                <img src="${pageContext.request.contextPath}/css/image/default_avatar.png">
            </div>
            <div class="user-name">
                <p>${counter.count}. <c:out value="${users.userName}"/></p>
                <p class="role"><c:out value="${users.role}"/></p>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
