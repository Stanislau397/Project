<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/movie.css">
</head>
<header>
    <jsp:include page="static/header.jsp"/>
</header>
<body>
<c:forEach items="${movie_list}" var="movies">
    <div class="side">
        <img class="image" src="${pageContext.request.contextPath}${movies.picture}">
    </div>
    <div class="middle">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input hidden name="command" value="show_movie_details">
            <input hidden name="movie_id" value="${movies.movieId}">
            <button type="submit" >${movies.title}</button>
        </form>
        <p class="release-date">Release date: ${movies.releaseDate}</p><br>
        <p class="content">${movies.description}</p>
    </div>
    <hr>
</c:forEach>
</body>
</html>
