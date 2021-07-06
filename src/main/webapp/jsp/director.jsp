<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.director"/></title>
    <jsp:include page="/jsp/static/header.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/actor.css">
</head>
<body>
<div class="main">
    <div class="head">
        <div class="actor-img">
            <c:choose>
                <c:when test="${requestScope.director.picture == null}">
                    <div class="img">
                        <p><fmt:message key="label.no_photo"/></p>
                    </div>
                </c:when>
                <c:when test="${requestScope.director.picture != null}">
                    <div class="img">
                        <img src="${pageContext.request.contextPath}${requestScope.director.picture}"/>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <div class="actor-info">
            <h3><c:out value="${requestScope.director.firstName}"/> <c:out value="${requestScope.director.lastName}"/></h3>
            <h2><fmt:message key="label.about_person"/></h2>
            <div class="info">
                <ul>
                    <li class="name"><fmt:message key="label.age"/></li>
                    <li>
                        <c:choose>
                            <c:when test="${requestScope.director.age != 0}">
                                <c:out value="${requestScope.director.age}"/>
                            </c:when>
                            <c:when test="${requestScope.director.age == 0}">
                                -
                            </c:when>
                        </c:choose>
                    </li>
                    <li class="name"><fmt:message key="label.birth_date"/></li>
                    <li>
                        <c:choose>
                            <c:when test="${requestScope.director.birthDate == null}">
                                -
                            </c:when>
                            <c:when test="${requestScope.director.birthDate != null}">
                                <c:out value="${requestScope.director.birthDate}"/>
                            </c:when>
                        </c:choose>
                    </li>
                    <li class="name"><fmt:message key="label.height"/></li>
                    <li>
                        <c:choose>
                            <c:when test="${requestScope.director.height != 0}">
                                <c:out value="${requestScope.director.height} м"/>
                            </c:when>
                            <c:when test="${requestScope.director.height == 0}">
                                -
                            </c:when>
                        </c:choose>
                    </li>
                    <li class="name"><fmt:message key="label.total_movies"/></li>
                    <li>${requestScope.movies_for_director_list.size()}</li>
                </ul>
            </div>
            <c:if test="${requestScope.best_movies_for_director_list.size() > 0}">
                <h2><fmt:message key="label.best_movies"/></h2>
            </c:if>
            <div id="wrapper">
                <div id="carousel">
                    <div id="content">
                        <c:forEach items="${requestScope.best_movies_for_director_list}" var="bestMoviesForDirector">
                            <a class="movie-picture" href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${bestMoviesForDirector.movieId}"
                               style="margin-left: -1.5px">
                                <c:choose>
                                    <c:when test="${bestMoviesForDirector.rating.score >= 70}">
                                        <p class="score" style="background-color: #66cc33"><c:out value="${bestMoviesForDirector.rating.score}"/></p>
                                    </c:when>
                                    <c:when test="${bestMoviesForDirector.rating.score < 70}">
                                        <p class="score" style="background-color: #f9c22a"><c:out value="${bestMoviesForDirector.rating.score}"/></p>
                                    </c:when>
                                </c:choose>
                                <img src="${pageContext.request.contextPath}${bestMoviesForDirector.picture}"
                                     class="item"/>
                                <form action="${pageContext.request.contextPath}/controller" method="get">
                                    <input type="hidden" name="command" value="show_movie_details">
                                    <input type="hidden" name="movie_id" value="${bestMoviesForDirector.movieId}">
                                    <button type="submit" class="title"><c:out value="${bestMoviesForDirector.title}"/></button>
                                </form>
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${requestScope.best_movies_for_director_list.size() > 2}">
                        <button id="prev">
                            <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    width="20"
                                    height="20"
                                    viewBox="0 0 24 24"
                            >
                                <path fill="none" d="M0 0h24v24H0V0z"/>
                                <path d="M15.61 7.41L14.2 6l-6 6 6 6 1.41-1.41L11.03 12l4.58-4.59z"/>
                            </svg>
                        </button>
                        <button id="next">
                            <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    width="20"
                                    height="20"
                                    viewBox="0 0 24 24"
                            >
                                <path fill="none" d="M0 0h24v24H0V0z"/>
                                <path d="M10.02 6L8.61 7.41 13.19 12l-4.58 4.59L10.02 18l6-6-6-6z"/>
                            </svg>
                        </button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="films">
        <h3><fmt:message key="label.all_filmography"/></h3>
        <c:forEach items="${requestScope.movies_for_director_list}" var="moviesForDirector">
            <div class="container">
                <div class="pic">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${moviesForDirector.movieId}">
                        <img src="${pageContext.request.contextPath}${moviesForDirector.picture}">
                    </a>
                </div>
                <div class="movie-title">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_movie_details">
                        <input type="hidden" name="movie_id" value="${moviesForDirector.movieId}">
                        <button type="submit" class="movie-title-btn"><c:out value="${moviesForDirector.title}"/></button>
                    </form>
                </div>
                <div class="movie-year">
                    <p><c:out value="${moviesForDirector.releaseDate}"/></p>
                </div>
                <div class="movie-score">
                    <c:choose>
                        <c:when test="${moviesForDirector.rating.score >= 70}">
                            <p style="background-color: #66cc33"><c:out value="${moviesForDirector.rating.score}"/></p>
                        </c:when>
                        <c:when test="${moviesForDirector.rating.score < 70 && moviesForDirector.rating.score >= 40}">
                            <p style="background-color: #f9c22a"><c:out value="${moviesForDirector.rating.score}"/></p>
                        </c:when>
                        <c:when test="${moviesForDirector.rating.score < 40 && moviesForDirector.rating.score > 0}">
                            <p style="background-color: red"><c:out value="${moviesForDirector.rating.score}"/></p>
                        </c:when>
                        <c:when test="${moviesForDirector.rating.score == 0}">
                            <fmt:message key="label.rating"/>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script type="text/javascript">
    const gap = 20;

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
</html>