<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.account_settings"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/settings.css">
</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
</header>
<body>
    <h2><fmt:message key="label.account_settings"/></h2>
    <hr>
    <h3><fmt:message key="label.profile_image"/></h3>
    <div>
        <img src="${pageContext.request.contextPath}/css/image/default_avatar.png"/>
    </div>
    <hr>
    <h3><fmt:message key="label.password"/></h3>
    <div class="password">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="change_password">
            <input type="password" name="password" placeholder="Current Password"
                   pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
            <input type="password" name="new_password" placeholder="New Password"
                   pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
            <input type="password" name="verify_password" placeholder="Verify Password"
                   pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
            <button type="submit" name="submit"><fmt:message key="label.change_password"/></button>
        </form>
    </div>
    <hr>
    <p class="message">
        <c:if test="${sessionScope.change_password != null}">
            <c:out value="${sessionScope.change_password}"/>
        </c:if>
    </p>
</body>
</html>
