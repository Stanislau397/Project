<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="text.label.main"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
</head>
<header>
    <jsp:include page="static/header.jsp"/>
</header>
<body>
<h2><fmt:message key="label.new_movies"/></h2>
<div class="new-movie-container">
    <div class="newest-movies">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="newest_movies">
            <input type="submit" class="btn" value="<fmt:message key="label.see_more"/>">
        </form>
    </div>
    <c:forEach items="${requestScope.newest_movies_list}" var="newestMovies" varStatus="x">
        <c:if test="${x.count < 5}">
            <div class="movie-info">
                <div class="picture-score">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${newestMovies.movieId}"
                       style="margin-left: -1.5px">
                        <img src="${pageContext.request.contextPath}${newestMovies.picture}"/>
                    </a>
                    <c:if test="${newestMovies.rating.score != 0}">
                        <c:if test="${newestMovies.rating.score >= 70}">
                            <div class="movie-rating" style="background-color: #6c3">${newestMovies.rating.score}</div>
                        </c:if>
                        <c:if test="${newestMovies.rating.score < 70 && newestMovies.rating.score > 40}">
                            <div class="movie-rating" style="background-color: #fc3">${newestMovies.rating.score}</div>
                        </c:if>
                        <c:if test="${newestMovies.rating.score < 40}">
                            <div class="movie-rating" style="background-color: red">${newestMovies.rating.score}</div>
                        </c:if>
                    </c:if>
                </div>
                <div class="title">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${newestMovies.movieId}">
                            ${newestMovies.title}</a>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>

<h2><fmt:message key="label.most_rated"/></h2>
<div class="new-movie-container">
    <div class="newest-movies">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="most_rated_movies">
            <input type="submit" class="btn" value="<fmt:message key="label.see_more"/>">
        </form>
    </div>
    <c:forEach items="${requestScope.most_rated_movies_list}" var="mostRatedMovies" varStatus="x">
        <c:if test="${x.count < 5}">
            <div class="movie-info">
                <div class="picture-score">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${mostRatedMovies.movieId}"
                       style="margin-left: -1.5px">
                        <img src="${pageContext.request.contextPath}${mostRatedMovies.picture}"/>
                    </a>
                    <c:if test="${mostRatedMovies.rating.score != 0}">
                        <c:if test="${mostRatedMovies.rating.score > 70}">
                            <div class="movie-rating1"
                                 style="background-color: #6c3">${mostRatedMovies.rating.score}</div>
                        </c:if>
                        <c:if test="${mostRatedMovies.rating.score < 70 && mostRatedMovies.rating.score > 40}">
                            <div class="movie-rating1"
                                 style="background-color: #fc3">${mostRatedMovies.rating.score}</div>
                        </c:if>
                        <c:if test="${mostRatedMovies.rating.score < 40}">
                            <div class="movie-rating1"
                                 style="background-color: red">${mostRatedMovies.rating.score}</div>
                        </c:if>
                    </c:if>
                </div>
                <div class="title">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${mostRatedMovies.movieId}">
                            ${mostRatedMovies.title}</a>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>

<h2><fmt:message key="label.coming_soon"/></h2>
<div class="new-movie-container">
    <div class="newest-movies">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="upcoming_movies">
            <input type="submit" class="btn" value="<fmt:message key="label.see_more"/>">
        </form>
    </div>
    <c:forEach items="${requestScope.upcoming_movies_list}" var="upcomingMovies" varStatus="x">
        <c:if test="${x.count < 5}">
            <div class="movie-info">
                <div class="picture-score">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${upcomingMovies.movieId}"
                       style="margin-left: -1.5px">
                        <img src="${pageContext.request.contextPath}${upcomingMovies.picture}"/>
                    </a>
                    <c:if test="${upcomingMovies.rating.score != 0}">
                        <c:if test="${upcomingMovies.rating.score >= 70}">
                            <div class="movie-rating"
                                 style="background-color: #6c3">${upcomingMovies.rating.score}</div>
                        </c:if>
                        <c:if test="${upcomingMovies.rating.score < 70 && upcomingMovies.rating.score > 40}">
                            <div class="movie-rating"
                                 style="background-color: #fc3">${upcomingMovies.rating.score}</div>
                        </c:if>
                        <c:if test="${upcomingMovies.rating.score < 40}">
                            <div class="movie-rating" style="background-color: red">${upcomingMovies.rating.score}</div>
                        </c:if>
                    </c:if>
                </div>
                <div class="title">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${upcomingMovies.movieId}">
                            ${upcomingMovies.title}</a>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>
<div id="wrapper">
    <div class="top">
        <ul>
            <li><h2 style=" margin-left: 0"><fmt:message key="label.new_movies"/></h2></li>
            <li style="margin-left: 7px">asdasd</li>
        </ul>
    </div>
    <div id="carousel">
        <div id="content">
            <c:forEach items="${requestScope.newest_movies_list}" var="newestMovies">
                <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${newestMovies.movieId}"
                   style="margin-left: -1.5px">
                    <c:choose>
                        <c:when test="${newestMovies.rating.score == 0}">
                            <p class="score"></p>
                        </c:when>
                        <c:when test="${newestMovies.rating.score >= 70}">
                            <p class="score" style="background-color: #66cc33"><c:out
                                    value="${newestMovies.rating.score}"/></p>
                        </c:when>
                        <c:when test="${newestMovies.rating.score < 70 && newestMovies.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3"><c:out
                                    value="${newestMovies.rating.score}"/></p>
                        </c:when>
                        <c:when test="${newestMovies.rating.score < 40}">
                            <p class="score" style="background-color: red"><c:out
                                    value="${newestMovies.rating.score}"/></p>
                        </c:when>
                    </c:choose>
                    <img src="${pageContext.request.contextPath}${newestMovies.picture}" class="item"/>
                    <p class="movie-title"><c:out value="${newestMovies.title}"/></p>
                </a>
            </c:forEach>
        </div>
    </div>
    <button id="prev">
        <svg
                xmlns="http://www.w3.org/2000/svg"
                width="35"
                height="35"
                viewBox="0 0 24 24"
        >
            <path fill="none" d="M0 0h24v24H0V0z"/>
            <path d="M15.61 7.41L14.2 6l-6 6 6 6 1.41-1.41L11.03 12l4.58-4.59z"/>
        </svg>
    </button>
    <button id="next">
        <svg
                xmlns="http://www.w3.org/2000/svg"
                width="35"
                height="35"
                viewBox="0 0 24 24"
        >
            <path fill="none" d="M0 0h24v24H0V0z"/>
            <path d="M10.02 6L8.61 7.41 13.19 12l-4.58 4.59L10.02 18l6-6-6-6z"/>
        </svg>
    </button>
</div>
<div style="height: 200px"></div>
<script type="text/javascript">
    const gap = 15;

    const carousel = document.getElementById("carousel"),
        content = document.getElementById("content"),
        next = document.getElementById("next"),
        prev = document.getElementById("prev");

    next.addEventListener("click", e => {
        carousel.scrollBy(width + gap, 0);
        if (carousel.scrollWidth !== 0) {
            prev.style.display = "flex";
        }
        if (content.scrollWidth - width - gap <= carousel.scrollLeft + width) {
            next.style.display = "none";
        }
    });
    prev.addEventListener("click", e => {
        carousel.scrollBy(-(width + gap), 0);
        if (carousel.scrollLeft - width - gap <= 0) {
            prev.style.display = "none";
        }
        if (!content.scrollWidth - width - gap <= carousel.scrollLeft + width) {
            next.style.display = "flex";
        }
    });

    let width = carousel.offsetWidth;
    window.addEventListener("resize", e => (width = carousel.offsetWidth));
</script>
</body>
<jsp:include page="static/footer.jsp"/>
</html>
