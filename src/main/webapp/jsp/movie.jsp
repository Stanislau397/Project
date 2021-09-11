<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="today" value="<%=new Date()%>"/>
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
<c:choose>
    <c:when test="${requestScope.movie_list != null}">
        <h2><fmt:message key="label.all_movies"/></h2>
        <div class="filter">
            <h4><fmt:message key="label.filter"/></h4>
            <div class="genre-container" id="genre-container">
                <button><fmt:message key="label.genre"/></button>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="movies_by_genre_and_year">
                            <c:forEach items="${requestScope.genres_list}" var="genres">
                                <input class="btn" type="submit" name="genre_title" value="${genres.genreTitle}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
            <div class="year-container" id="year-container">
                <button><fmt:message key="label.year"/></button>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="movies_by_genre_and_year">
                            <c:forEach items="${requestScope.movie_years_list}" var="years">
                                <input class="btn" type="submit" name="movie_year" value="${years}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <hr class="hr1">
        <div class="center-content">
            <c:forEach items="${movie_list}" var="movies">
                <div class="side">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${movies.movieId}">
                        <img class="image" src="http://${movies.picture}">
                    </a>
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" id="form1" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${movies.movieId}">
                        <p>
                            <button type="submit">${movies.title}</button>
                        </p>
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
            <div class="pagination">
                <div class="title">
                    <p><fmt:message key="label.page"/> </p>
                </div>
                <div class="pagination-number">
                    <c:forEach begin="1" end="${requestScope.pages}" varStatus="loop">
                        <c:choose>
                            <c:when test="${requestScope.page_number == loop.count}">
                                <a style="color: #2f80ed"
                                        href="${pageContext.request.contextPath}/controller?command=show_all_movies&page=${loop.count}">${loop.count}
                                </a>
                            </c:when>
                            <c:when test="${requestScope.page_number != loop.count}">
                                <a href="${pageContext.request.contextPath}/controller?command=show_all_movies&page=${loop.count}">${loop.count}</a>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
        </div>
        <br>
        <br>
    </c:when>
    <c:when test="${requestScope.movies_by_key_word_list != null}">
        <h2><fmt:message key="label.searching_results"/></h2>
        <div class="center-content">
            <c:forEach items="${movies_by_key_word_list}" var="moviesByKeyWord" varStatus="counter">
                <div class="side">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${moviesByKeyWord.movieId}">
                        <img class="image" src="http://${moviesByKeyWord.picture}">
                    </a>
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
        <div class="filter">
            <h4><fmt:message key="label.filter"/></h4>
            <div class="genre-container" id="genre-container">
                <button><c:out value="${requestScope.genre}"/></button>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="movies_by_genre">
                            <c:forEach items="${requestScope.genres_list}" var="genres">
                                <input class="btn" type="submit" name="genre_title" value="${genres.genreTitle}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
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
        </div>
        <hr class="hr1">
        <div class="center-content">
            <c:forEach items="${movies_by_genre_list}" var="moviesByGenre" varStatus="counter">
                <div class="side">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${moviesByGenre.movieId}">
                        <img class="image" src="http://${moviesByGenre.picture}">
                    </a>
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
        <h2><fmt:message key="label.this_year_movies"/></h2>
        <div class="filter">
            <h4><fmt:message key="label.filter"/></h4>
            <div class="genre-container">
                <button><fmt:message key="label.genre"/></button>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="current_year_movies">
                            <input class="btn" type="submit" value="<fmt:message key="label.all_genres"/>">
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="current_year_movies_by_genre">
                            <c:forEach items="${requestScope.genres_list}" var="genres">
                                <input class="btn" type="submit" name="genre_title" value="${genres.genreTitle}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <hr class="hr1">
        <div class="center-content">
            <c:forEach items="${movies_by_current_year_list}" var="currentYearMovies">
                <div class="side">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${currentYearMovies.movieId}">
                        <img class="image" src="http://${currentYearMovies.picture}">
                    </a>
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${currentYearMovies.movieId}">
                        <button type="submit">${currentYearMovies.title}</button>
                    </form>
                    <c:if test="${currentYearMovies.rating.score != 0}">
                        <p class="score">
                        <c:if test="${currentYearMovies.rating.score < 70 && currentYearMovies.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${currentYearMovies.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${currentYearMovies.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${currentYearMovies.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${currentYearMovies.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${currentYearMovies.rating.score}"/>
                            </p>
                        </c:if>
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
    <c:when test="${requestScope.current_year_movies_by_genre_list != null}">
        <h2><fmt:message key="label.this_year_movies"/></h2>
        <div class="filter">
            <h4><fmt:message key="label.filter"/></h4>
            <div class="genre-container">
                <button><fmt:message key="label.genre"/></button>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="current_year_movies">
                            <input class="btn" type="submit" value="<fmt:message key="label.all_genres"/>">
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="current_year_movies_by_genre">
                            <c:forEach items="${requestScope.genres_list}" var="genres">
                                <input class="btn" type="submit" name="genre_title" value="${genres.genreTitle}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <hr class="hr1">
        <div class="center-content">
            <c:forEach items="${requestScope.current_year_movies_by_genre_list}" var="currentYearMoviesByGenre"
                       varStatus="counter">
                <div class="side">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${currentYearMoviesByGenre.movieId}">
                        <img class="image" src="http://${currentYearMoviesByGenre.picture}">
                    </a>
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${currentYearMoviesByGenre.movieId}">
                        <button type="submit">${counter.count}. ${currentYearMoviesByGenre.title}</button>
                    </form>
                    <c:if test="${currentYearMoviesByGenre.rating.score != 0}">
                        <p class="score">
                        <c:if test="${currentYearMoviesByGenre.rating.score < 70 && currentYearMoviesByGenre.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${currentYearMoviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${currentYearMoviesByGenre.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${currentYearMoviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${currentYearMoviesByGenre.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${currentYearMoviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        </p>
                    </c:if>
                    <c:if test="${currentYearMoviesByGenre.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${currentYearMoviesByGenre.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${currentYearMoviesByGenre.description}"/>
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
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${moviesByYear.movieId}">
                        <img class="image" src="http://${moviesByYear.picture}">
                    </a>
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
        <h2><fmt:message key="label.new_movies"/></h2>
        <div class="filter">
            <h4><fmt:message key="label.filter"/></h4>
            <div class="genre-container">
                <button><fmt:message key="label.genre"/></button>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="newest_movies">
                            <input class="btn" type="submit" value="<fmt:message key="label.all_genres"/>">
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="newest_movies_by_genre">
                            <c:forEach items="${requestScope.genres_list}" var="genres">
                                <input class="btn" type="submit" name="genre_title" value="${genres.genreTitle}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <hr class="hr1">
        <div class="center-content">
            <c:forEach items="${newest_movies_list}" var="newestMovies">
                <div class="side">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${newestMovies.movieId}">
                        <img class="image" src="http://${newestMovies.picture}">
                    </a>
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${newestMovies.movieId}">
                        <button type="submit">${newestMovies.title}</button>
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
    <c:when test="${requestScope.newest_movies_by_genre_list != null}">
        <h2><fmt:message key="label.new_movies"/></h2>
        <div class="filter">
            <h4><fmt:message key="label.filter"/></h4>
            <div class="genre-container">
                <button><fmt:message key="label.genre"/></button>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="newest_movies">
                            <input class="btn" type="submit" value="<fmt:message key="label.all_genres"/>">
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="newest_movies_by_genre">
                            <c:forEach items="${requestScope.genres_list}" var="genres">
                                <input class="btn" type="submit" name="genre_title" value="${genres.genreTitle}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <hr class="hr1">
        <div class="center-content">
            <c:forEach items="${requestScope.newest_movies_by_genre_list}" var="newestMoviesByGenre"
                       varStatus="counter">
                <div class="side">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${newestMoviesByGenre.movieId}">
                        <img class="image" src="http://${newestMoviesByGenre.picture}">
                    </a>
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${newestMoviesByGenre.movieId}">
                        <button type="submit">${counter.count}. ${newestMoviesByGenre.title}</button>
                    </form>
                    <c:if test="${newestMoviesByGenre.rating.score != 0}">
                        <p class="score">
                        <c:if test="${newestMoviesByGenre.rating.score < 70 && newestMoviesByGenre.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${newestMoviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${newestMoviesByGenre.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${newestMoviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${newestMoviesByGenre.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${newestMoviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        </p>
                    </c:if>
                    <c:if test="${newestMoviesByGenre.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${newestMoviesByGenre.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${newestMoviesByGenre.description}"/>
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
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${mostRatedMovies.movieId}">
                        <img class="image" src="http://${mostRatedMovies.picture}">
                    </a>
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
    <c:when test="${requestScope.upcoming_movies_list != null}">
        <h2><fmt:message key="label.coming_soon"/></h2>
        <div class="filter">
            <h4><fmt:message key="label.filter"/></h4>
            <div class="genre-container">
                <button><fmt:message key="label.genre"/></button>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="upcoming_movies">
                            <input class="btn" type="submit" value="<fmt:message key="label.all_genres"/>">
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="upcoming_movies_by_genre">
                            <c:forEach items="${requestScope.genres_list}" var="genres">
                                <input class="btn" type="submit" name="genre_title" value="${genres.genreTitle}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <hr class="hr1">
        <div class="center-content">
            <c:forEach items="${requestScope.upcoming_movies_list}" var="upcomingMovies" varStatus="counter">
                <div class="side">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${upcomingMovies.movieId}">
                        <img class="image" src="http://${upcomingMovies.picture}">
                    </a>
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${upcomingMovies.movieId}">
                        <button type="submit">${counter.count}. ${upcomingMovies.title}</button>
                    </form>
                    <c:if test="${upcomingMovies.rating.score != 0}">
                        <p class="score">
                        <c:if test="${upcomingMovies.rating.score < 70 && upcomingMovies.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${upcomingMovies.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${upcomingMovies.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${upcomingMovies.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${upcomingMovies.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${upcomingMovies.rating.score}"/>
                            </p>
                        </c:if>
                        </p>
                    </c:if>
                    <c:if test="${upcomingMovies.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${upcomingMovies.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${upcomingMovies.description}"/>
                    </p>
                </div>
                <hr>
            </c:forEach>
        </div>
    </c:when>
    <c:when test="${requestScope.upcoming_movie_by_genre_list != null}">
        <h2><fmt:message key="label.coming_soon"/></h2>
        <div class="filter">
            <h4><fmt:message key="label.filter"/></h4>
            <div class="genre-container">
                <button><fmt:message key="label.genre"/></button>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="upcoming_movies">
                            <input class="btn" type="submit" value="<fmt:message key="label.all_genres"/>">
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="upcoming_movies_by_genre">
                            <c:forEach items="${requestScope.genres_list}" var="genres">
                                <input class="btn" type="submit" name="genre_title" value="${genres.genreTitle}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <hr class="hr1">
        <div class="center-content">
            <c:forEach items="${requestScope.upcoming_movie_by_genre_list}" var="upcomingMoviesByGenre"
                       varStatus="counter">
                <div class="side">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${upcomingMoviesByGenre.movieId}">
                        <img class="image" src="http://${upcomingMoviesByGenre.picture}">
                    </a>
                </div>
                <div class="middle">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${upcomingMoviesByGenre.movieId}">
                        <button type="submit">${counter.count}. ${upcomingMoviesByGenre.title}</button>
                    </form>
                    <c:if test="${upcomingMoviesByGenre.rating.score != 0}">
                        <p class="score">
                        <c:if test="${upcomingMoviesByGenre.rating.score < 70 && upcomingMoviesByGenre.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3">
                                <c:out value="${upcomingMoviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${upcomingMoviesByGenre.rating.score >= 70}">
                            <p class="score" style="background-color: #6c3">
                                <c:out value="${upcomingMoviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        <c:if test="${upcomingMoviesByGenre.rating.score < 40}">
                            <p class="score" style="background-color: #f00">
                                <c:out value="${upcomingMoviesByGenre.rating.score}"/>
                            </p>
                        </c:if>
                        </p>
                    </c:if>
                    <c:if test="${upcomingMoviesByGenre.rating.score == 0}">
                        <p class="no-rating"><fmt:message key="label.rating"/></p>
                    </c:if>
                    <p class="release-date">
                        <fmt:message key="label.release_date"/>
                        <c:out value="${upcomingMoviesByGenre.releaseDate}"/>
                    </p><br>
                    <p class="content">
                        <c:out value="${upcomingMoviesByGenre.description}"/>
                    </p>
                </div>
                <hr>
            </c:forEach>
        </div>
    </c:when>

    <c:when test="${requestScope.movies_by_genre_and_year_list != null}">
        <h2><fmt:message key="label.all_movies"/></h2>
        <div class="filter">
            <h4><fmt:message key="label.filter"/></h4>
            <div class="genre-container" id="genre-container">
                <c:choose>
                    <c:when test="${requestScope.genre != null}">
                        <button><c:out value="${requestScope.genre}"/></button>
                    </c:when>
                    <c:otherwise><button><fmt:message key="label.all_genres"/></button></c:otherwise>
                </c:choose>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="movies_by_genre_and_year">
                            <c:if test="${requestScope.movie_year != null}">
                                <input type="hidden" name="movie_year" value="${requestScope.movie_year}">
                            </c:if>
                            <input type="submit" class="btn" value="<fmt:message key="label.all_genres"/>">
                            <c:forEach items="${requestScope.genres_list}" var="genres">
                                <input class="btn" type="submit" name="genre_title" value="${genres.genreTitle}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
            <div class="year-container" id="year-container">
                <c:choose>
                    <c:when test="${requestScope.movie_year != null}">
                        <button><c:out value="${requestScope.movie_year}"/></button>
                    </c:when>
                    <c:otherwise><button><fmt:message key="label.all_years"/></button></button></c:otherwise>
                </c:choose>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="movies_by_genre_and_year">
                            <c:if test="${requestScope.genre != null}">
                                <input type="hidden" name="genre_title" value="${requestScope.genre}">
                            </c:if>
                            <input type="submit" class="btn" value="<fmt:message key="label.all_years"/>">
                            <c:forEach items="${requestScope.movie_years_list}" var="years">
                                <input class="btn" type="submit" name="movie_year" value="${years}">
                            </c:forEach>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <hr class="hr1">

        <c:choose>
            <c:when test="${requestScope.movies_by_genre_and_year_list.size() == 0}">
                <div class="nothing-found">
                    <h2><fmt:message key="label.error_404"/></h2>
                </div>
            </c:when>
            <c:otherwise>
                <div class="center-content">
                    <c:forEach items="${requestScope.movies_by_genre_and_year_list}" var="moviesByGenreAndYear">
                        <div class="side">
                            <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${moviesByGenreAndYear.movieId}">
                                <img class="image" src="http://${moviesByGenreAndYear.picture}">
                            </a>
                        </div>
                        <div class="middle">
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input hidden name="command" value="show_movie_details">
                                <input hidden name="movie_id" value="${moviesByGenreAndYear.movieId}">
                                <button type="submit">${moviesByGenreAndYear.title}</button>
                            </form>
                            <c:if test="${moviesByGenreAndYear.rating.score != 0}">
                                <p class="score">
                                <c:if test="${moviesByGenreAndYear.rating.score < 70 && moviesByGenreAndYear.rating.score >= 40}">
                                    <p class="score" style="background-color: #fc3">
                                        <c:out value="${moviesByGenreAndYear.rating.score}"/>
                                    </p>
                                </c:if>
                                <c:if test="${moviesByGenreAndYear.rating.score >= 70}">
                                    <p class="score" style="background-color: #6c3">
                                        <c:out value="${moviesByGenreAndYear.rating.score}"/>
                                    </p>
                                </c:if>
                                <c:if test="${moviesByGenreAndYear.rating.score < 40}">
                                    <p class="score" style="background-color: #f00">
                                        <c:out value="${moviesByGenreAndYear.rating.score}"/>
                                    </p>
                                </c:if>
                                </p>
                            </c:if>
                            <c:if test="${moviesByGenreAndYear.rating.score == 0}">
                                <p class="no-rating"><fmt:message key="label.rating"/></p>
                            </c:if>
                            <p class="release-date">
                                <fmt:message key="label.release_date"/>
                                <c:out value="${moviesByGenreAndYear.releaseDate}"/>
                            </p><br>
                            <p class="content">
                                <c:out value="${moviesByGenreAndYear.description}"/>
                            </p>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </c:when>
</c:choose>
<br>
</body>
<jsp:include page="static/footer.jsp"/>
</html>
