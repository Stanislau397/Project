<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>admin_cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_cabinet.css">
</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
</header>
<body>
<div class="dash_board">
    <a href="${pageContext.request.contextPath}/jsp/admin/upload_movie.jsp">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="to_upload_movie">
            <input type="submit" value="Upload Movie">
        </form>
    </a>
</div>
</body>
</html>
