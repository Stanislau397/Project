<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>Error</title>
    <jsp:include page="/jsp/static/header.jsp"/>
</head>
<body>
<c:choose>
    <c:when test="${requestScope.error_404 != null}">
        <h2 style="margin-top: 10px; margin-left: 400px"><fmt:message key="label.error_404"/></h2>
    </c:when>
</c:choose>
</body>
</html>
