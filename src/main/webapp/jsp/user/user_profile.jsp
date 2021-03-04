<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user_profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/asdf.css">
</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
</header>
<body>
<div class="side-box">
    <img src="${pageContext.request.contextPath}/css/image/user.jpg"/>
</div>
<div class="middle-box">
    <ul>
        <li class="name">
            <c:out value="${sessionScope.user_name}"/>
        </li>
        <li class="settings">
            <a href="${pageContext.request.contextPath}/jsp/user/account_settings.jsp">Edit settings</a>
        </li>
    </ul>
    <ul>
        <li class="ratings">
            <c:out value="${requestScope.amount_of_reviews} Ratings"/>
        </li>
        <li class="review">
            <c:out value="${requestScope.comment} Comments"/>
        </li>
    </ul>
</div>
<hr>
<p>My Scores</p>
<div class="distribution">
    <h1>My distribution</h1>
    <ul>
        <li class="name1">Positive:</li>
        <li class="score">
            <c:out value="${requestScope.positive}"/>
        </li>
    </ul>
    <ul>
        <li class="name1">Mixed:</li>
        <li class="2">
            <c:out value="${requestScope.mixed}"/>
        </li>
    </ul>
    <ul>
        <li class="name1">Negative:</li>
        <li class="score">
            <c:out value="${requestScope.negative}"/>
        </li>
    </ul>
</div>
<div class="average">
    <ul>
        <li class="name2">Average movie score:</li>
        <li class="score2">
            <c:out value="${requestScope.average}"/>
        </li>
    </ul>
    <div class="latest-high-score">
        <c:out value="${requestScope.high}"/>
    </div>
    <div class="latest_movie">
        <p class="latest">Latest high score:</p>
        <form class="latest-movie" action="${pageContext.request.contextPath}/controller" method="get">
            <input hidden name="command" value="show_movie_details">
            <input hidden name="movie_id" value="${high_movie_id}">
            <button type="submit">${requestScope.high_score_title}</button>
        </form>
    </div>
    <div class="latest-low-score">
        <c:out value="${requestScope.low}"/>
    </div>
    <div class="latest-low-movie">
        <p class="latest-low-name">Latest low score:</p>
        <form class="latest-low-movie-name" action="${pageContext.request.contextPath}/controller" method="get">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input hidden name="command" value="show_movie_details">
                <input hidden name="movie_id" value="${low_movie_id}">
                <button type="submit">${requestScope.low_score_title}</button>
            </form>
        </form>
    </div>
</div>
<ul class="sort">
    <li>By Date:</li>
</ul>
<c:forEach items="${requestScope.rated_movies_list}" var="ratedMovies">
    <div class="movie_review">
        <img class="image" src="${pageContext.request.contextPath}${ratedMovies.picture}">
    </div>
    <div class="movie-score">
        <c:out value="${ratedMovies.rating.score}"/>
    </div>
    <div class="movie-info">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="show_movie_details">
            <input type="hidden" name="movie_id" value="${ratedMovies.movieId}">
            <button type="submit">
                <c:out value="${ratedMovies.title}"/>
            </button>
        </form>
        <ul>
            <li>Average movie score:</li>
            <li>75</li>
        </ul>
        <li class="release-date">
            <c:out value="${ratedMovies.comment.postDate}"/>
        </li>
        <li class="my-review">
            <c:out value="${ratedMovies.comment.text}"/>
        </li>
        <hr>
        <div class="helpful-score">
            11 of 11 users forund this helpful
        </div>
        <hr>
    </div>
</c:forEach>
</body>
</html>
