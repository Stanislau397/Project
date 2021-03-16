<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>movies</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/movie.css">
</head>
<header>
    <jsp:include page="static/header.jsp"/>
</header>
<body>
<c:if test="${requestScope.movies_by_key_word_list == null}">
    <div class="center-content">
        <c:forEach items="${movie_list}" var="movies">
            <div class="side">
                <img class="image" src="${pageContext.request.contextPath}${movies.picture}">
            </div>
            <div class="middle">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input hidden name="command" value="show_movie_details">
                    <input hidden name="movie_id" value="${movies.movieId}">
                    <button type="submit">${movies.title}</button>
                </form>
                <c:if test="${movies.rating.score != 0}">
                    <p class="score">
                        <c:out value="${movies.rating.score}"/>
                    </p>
                </c:if>
                <c:if test="${movies.rating.score == 0}">
                    <p class="no-rating"><c:out value="No rating"/></p>
                </c:if>
                <p class="release-date">
                    <c:out value="Release date: ${movies.releaseDate}"/>
                </p><br>
                <p class="content">
                    <c:out value="${movies.description}"/>
                </p>
            </div>
            <hr>
        </c:forEach>
    </div>
</c:if>

<c:if test="${requestScope.movies_by_key_word_list != null}">
    <div class="center-content">
        <c:forEach items="${movies_by_key_word_list}" var="moviesByKeyWord">
            <div class="side">
                <img class="image" src="${pageContext.request.contextPath}${moviesByKeyWord.picture}">
            </div>
            <div class="middle">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input hidden name="command" value="show_movie_details">
                    <input hidden name="movie_id" value="${moviesByKeyWord.movieId}">
                    <button type="submit">${moviesByKeyWord.title}</button>
                </form>
                <c:if test="${movies.rating.score != 0}">
                    <p class="score">
                        <c:out value="${moviesByKeyWord.rating.score}"/>
                    </p>
                </c:if>
                <c:if test="${moviesByKeyWord.rating.score == 0}">
                    <p class="no-rating"><c:out value="No rating"/></p>
                </c:if>
                <p class="release-date">
                    <c:out value="Release date: ${moviesByKeyWord.releaseDate}"/>
                </p><br>
                <p class="content">
                    <c:out value="${moviesByKeyWord.description}"/>
                </p>
            </div>
            <hr>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
