<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.movies"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/movie.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<header>
    <jsp:include page="static/header.jsp"/>
</header>
<body>
<div class="filter">
    <h4><fmt:message key="label.filter"/></h4>
    <select id="selector" class="selector">
        <option selected><fmt:message key="label.choose"/></option>
        <option><fmt:message key="label.by_year"/></option>
        <option><fmt:message key="label.by_genre"/></option>
    </select>
    <div class="year-container" id="year-container">
        <button><fmt:message key="label.year"/></button>
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="movies_by_year">
                    <c:forEach items="${requestScope.movie_years_list}" var="years">
                        <input class="btn" type="submit" name="movie_year" value="${years}">
                    </c:forEach>
                </form>
            </li>
        </ul>
    </div>
    <div class="genre-container" id="genre-container">
        <button><fmt:message key="label.genre"/></button>
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="movies_by_genre">
                    <c:forEach items="${requestScope.genres_list}" var="genres">
                        <input class="btn" type="submit" name="genre_title" value="${genres.title}">
                    </c:forEach>
                </form>
            </li>
        </ul>
    </div>
</div>
<hr class="hr1">
<c:choose>
    <c:when test="${requestScope.movie_list != null}">
        <div class="center-content">
            <c:forEach items="${movie_list}" var="movies" varStatus="counter">
                <div class="side">
                    <img class="image" src="${pageContext.request.contextPath}${movies.picture}">
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" id="form1" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${movies.movieId}">
                        <p><button type="submit">${counter.count}. ${movies.title}</button></p>
                    </form>
                    <c:if test="${movies.rating.score != 0}">
                        <c:if test="${movies.rating.score < 70 && movies.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${movies.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${movies.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${movies.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${movies.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${movies.rating.score}"/>
                            </p>
                        </c:if>
                    </c:if>
                    <c:if test="${movies.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${movies.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${movies.description}"/>
                    </p>
                </div>
                <hr>
            </c:forEach>
        </div>
    </c:when>
    <c:when test="${requestScope.movies_by_key_word_list != null}">
        <div class="center-content">
            <c:forEach items="${movies_by_key_word_list}" var="moviesByKeyWord" varStatus="counter">
                <div class="side">
                    <img class="image" src="${pageContext.request.contextPath}${moviesByKeyWord.picture}">
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${moviesByKeyWord.movieId}">
                        <button type="submit">${counter.count}. ${moviesByKeyWord.title}</button>
                    </form>
                    <c:if test="${moviesByKeyWord.rating.score != 0}">
                        <p class="score">
                        <c:if test="${moviesByKeyWord.rating.score < 70 && moviesByKeyWord.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${moviesByKeyWord.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${moviesByKeyWord.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${moviesByKeyWord.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${moviesByKeyWord.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${moviesByKeyWord.rating.score}"/>
                            </p>
                        </c:if>
                        </p>
                    </c:if>
                    <c:if test="${moviesByKeyWord.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${moviesByKeyWord.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${moviesByKeyWord.description}"/>
                    </p>
                </div>
                <hr>
            </c:forEach>
        </div>
    </c:when>
    <c:when test="${requestScope.movies_by_genre_list != null}">
        <div class="center-content">
            <c:forEach items="${movies_by_genre_list}" var="moviesByGenre" varStatus="counter">
                <div class="side">
                    <img class="image" src="${pageContext.request.contextPath}${moviesByGenre.picture}">
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${moviesByGenre.movieId}">
                        <button type="submit">${counter.count}. ${moviesByGenre.title}</button>
                    </form>
                    <c:if test="${moviesByGenre.rating.score != 0}">
                        <c:if test="${moviesByGenre.rating.score < 70 && moviesByGenre.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${moviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${moviesByGenre.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${moviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${moviesByGenre.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${moviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                    </c:if>
                    <c:if test="${moviesByGenre.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${moviesByGenre.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${moviesByGenre.description}"/>
                    </p>
                </div>
                <hr>
            </c:forEach>
        </div>
    </c:when>
    <c:when test="${requestScope.movies_by_current_year_list != null}">
        <div class="center-content">
            <c:forEach items="${movies_by_current_year_list}" var="currentYearMovies" varStatus="counter">
                <div class="side">
                    <img class="image" src="${pageContext.request.contextPath}${currentYearMovies.picture}">
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${currentYearMovies.movieId}">
                        <button type="submit">${counter.count}. ${currentYearMovies.title}</button>
                    </form>
                    <c:if test="${currentYearMovies.rating.score != 0}">
                        <p class="score">
                            <c:out value="${currentYearMovies.rating.score}"/>
                        </p>
                    </c:if>
                    <c:if test="${currentYearMovies.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${currentYearMovies.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${currentYearMovies.description}"/>
                    </p>
                </div>
                <hr>
            </c:forEach>
        </div>
    </c:when>
    <c:when test="${requestScope.movies_by_year_list != null}">
        <div class="center-content">
            <c:forEach items="${movies_by_year_list}" var="moviesByYear" varStatus="counter">
                <div class="side">
                    <img class="image" src="${pageContext.request.contextPath}${moviesByYear.picture}">
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${moviesByYear.movieId}">
                        <button type="submit">${counter.count}. ${moviesByYear.title}</button>
                    </form>
                    <c:if test="${moviesByYear.rating.score != 0}">
                        <p class="score">
                        <c:if test="${moviesByYear.rating.score < 70 && moviesByYear.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${moviesByYear.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${moviesByYear.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${moviesByYear.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${moviesByYear.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${moviesByYear.rating.score}"/>
                            </p>
                        </c:if>
                        </p>
                    </c:if>
                    <c:if test="${moviesByYear.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${moviesByYear.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${moviesByYear.description}"/>
                    </p>
                </div>
                <hr>
            </c:forEach>
        </div>
    </c:when>
    <c:when test="${requestScope.newest_movies_list != null}">
        <div class="center-content">
            <c:forEach items="${newest_movies_list}" var="newestMovies" varStatus="counter">
                <div class="side">
                    <img class="image" src="${pageContext.request.contextPath}${newestMovies.picture}">
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${newestMovies.movieId}">
                        <button type="submit">${counter.count}. ${newestMovies.title}</button>
                    </form>
                    <c:if test="${newestMovies.rating.score != 0}">
                        <p class="score">
                        <c:if test="${newestMovies.rating.score < 70 && newestMovies.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${newestMovies.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${newestMovies.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${newestMovies.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${newestMovies.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${newestMovies.rating.score}"/>
                            </p>
                        </c:if>
                        </p>
                    </c:if>
                    <c:if test="${newestMovies.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${newestMovies.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${newestMovies.description}"/>
                    </p>
                </div>
                <hr>
            </c:forEach>
        </div>
    </c:when>
    <c:when test="${requestScope.most_rated_movies_list != null}">
        <div class="center-content">
            <c:forEach items="${most_rated_movies_list}" var="mostRatedMovies" varStatus="counter">
                <div class="side">
                    <img class="image" src="${pageContext.request.contextPath}${mostRatedMovies.picture}">
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${mostRatedMovies.movieId}">
                        <button type="submit">${counter.count}. ${mostRatedMovies.title}</button>
                    </form>
                    <c:if test="${mostRatedMovies.rating.score != 0}">
                        <p class="score">
                        <c:if test="${mostRatedMovies.rating.score < 70 && mostRatedMovies.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${mostRatedMovies.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${mostRatedMovies.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${mostRatedMovies.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${mostRatedMovies.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${mostRatedMovies.rating.score}"/>
                            </p>
                        </c:if>
                        </p>
                    </c:if>
                    <c:if test="${mostRatedMovies.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${mostRatedMovies.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${mostRatedMovies.description}"/>
                    </p>
                </div>
                <hr>
            </c:forEach>
        </div>
    </c:when>
</c:choose>
</body>
<jsp:include page="static/footer.jsp"/>
<script>
    $("#year-container").hide();
    $("#genre-container").hide();
    $("#selector").change(function () {
        if ($(this).val() === "По Году" || $(this).val() === "By Year") {
            $("#year-container").show();
        } else {
            $("#year-container").hide();

        }
        if ($(this).val() === "By Genre" || $(this).val() === "По Жанру") {
            $("#genre-container").show();
        } else {
            $("#genre-container").hide();
        }
    })
    ;
</script>
</html>
