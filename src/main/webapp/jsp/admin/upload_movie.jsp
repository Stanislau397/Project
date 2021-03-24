<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>Upload movie</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_cabinet.css">
</head>
<header>
    <jsp:include page="/jsp/admin/admin_cabinet.jsp"/>
</header>
<body>
<div class="upload-content">
<form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="upload_movie">
    <label for="title">Title: </label>
    <input type="text" class="movie_title" id="title" name="title">
    <label for="release_date">Release date: </label>
    <input type="text" class="release-date" id="release_date" name="release_date" placeholder="year-month-date">
    <label for="run-time">Run time: </label>
    <input type="text" class="run-time" id="run-time" name="run_time">
    <label for="country">Country: </label>
    <input type="text" class="country" id="country" name="country">
    <label for="poster">Poster: </label>
    <input type="file" class="poster" id="poster" name="file">
    <label for="director">Director: </label>
    <input type="text" name="director" class="director" id="director">
    <label for="genre_id">Choose genre: </label>
    <select id="genre_id" name="genre_id" class="genre">
        <option name="dollar" selected>Choose genre: </option>
        <c:forEach items="${requestScope.genres_list}" var="genres">
            <option value="${genres.genreId}">
                <c:out value="${genres.title}"/>
            </option>
        </c:forEach>
    </select>
    <div class="middle">
        <textarea name="description" id="description" class="summery" placeholder="Summery"></textarea>
        <textarea name="actors" id="actors" class="actors" placeholder="Actors"></textarea>
    </div>
    <input type="submit" value="upload" class="button">
</form>
</div>
</body>
</html>
