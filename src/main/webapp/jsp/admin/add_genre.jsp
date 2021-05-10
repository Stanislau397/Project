<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.edit_delete"/></title>
    <jsp:include page="/jsp/static/header.jsp"/>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/upload_movie.css">
</head>
    <title><fmt:message key="label.edit_delete"/></title>
</head>
<body>
<div class="main-content">
    <div class="upload-title">
        <h2><fmt:message key="label.edit_delete"/></h2>
    </div>
    <div class="movie-form">
        <c:forEach items="${requestScope.genres_list}" var="genres">
            <p style="margin-top: 5px; margin-left: 10px"><c:out value="${genres.title}"/></p>
        </c:forEach>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="add_genre">
            <label for="genre"><fmt:message key="label.genre"/></label>
            <input type="text" id="genre" name="genre_title" value="${requestScope.movie_info.releaseDate}" required>
            <hr>
            <button type="submit"><fmt:message key="label.add_genre"/></button>
        </form>
    </div>
</div>
</body>
</html>
