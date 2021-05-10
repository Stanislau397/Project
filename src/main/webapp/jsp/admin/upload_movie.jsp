<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="label.upload_movie"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/upload_movie.css">
</head>
<jsp:include page="/jsp/static/admin_side_bar.jsp"/>
<body>
<div class="main-content">
    <div class="upload-title">
        <h2><fmt:message key="label.upload_movie"/></h2>
    </div>
    <div class="movie-form">
        <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="upload_movie">
            <label for="title"><fmt:message key="label.title"/></label>
            <input type="text" name="title" id="title" required>
            <hr>
            <label for="country"><fmt:message key="label.country"/></label>
            <input type="text" id="country" name="country" required>
            <hr>
            <label for="run_time"><fmt:message key="label.runtime"/></label>
            <input type="text" id="run_time" name="run_time" required>
            <hr>
            <label for="date"><fmt:message key="label.release_date"/></label>
            <input type="text" id="date" name="release_date" required>
            <hr>
            <label for="genre"><fmt:message key="label.genre"/></label>
                <select name="genre_id" id="genre" required>
                    <c:forEach items="${requestScope.genres_list}" var="genres">
                    <option value="${genres.genreId}"><c:out value="${genres.genreTitle}"/></option>
                    </c:forEach>
                </select>
            <hr>
            <label for="director"><fmt:message key="label.director"/></label>
            <input type="text" id="director" name="director" required>
            <hr>
            <label for="description"><fmt:message key="label.summery"/></label>
            <input class="text-area" name="description" id="description" required>
            <hr>
            <label for="picture"><fmt:message key="label.picture"/></label>
            <input type="file" id="picture" name="file" required>
            <hr>
            <button type="submit"><fmt:message key="label.upload"/></button>
        </form>
    </div>
</div>
</body>
</html>
