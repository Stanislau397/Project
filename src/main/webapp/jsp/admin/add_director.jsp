<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="label.add_director"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/upload_movie.css">
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</head>
<body>
<div class="main-content" style="height: 200px; width: 750px; margin-left: 500px; margin-top: 200px">
    <c:choose>
        <c:when test="${sessionScope.add_director_message == true}">
            <p style="color: green;margin-top: 0">Director has been added</p>
        </c:when>
        <c:when test="${sessionScope.add_director_message == false}">
            <p style="color: red;margin-top: 0">This director already exists</p>
        </c:when>
    </c:choose>
    <div class="upload-title">
        <h2><fmt:message key="label.add_director"/></h2>
    </div>
    <div class="movie-form">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="add_director">
            <label for="first_name"><fmt:message key="label.first_name"/></label>
            <input type="text" name="first_name" id="first_name" required>
            <hr>
            <label for="last_name"><fmt:message key="label.last_name"/></label>
            <input type="text" id="last_name" name="last_name" required>
            <hr>
            <button type="submit"><fmt:message key="label.upload"/></button>
        </form>
    </div>
</div>
</body>
</html>
