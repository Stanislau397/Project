<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.actor"/></title>
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
                <c:when test="${requestScope.actor.picture == null}">
                    <div class="img">
                        <p><fmt:message key="label.no_photo"/></p>
                    </div>
                </c:when>
                <c:when test="${requestScope.actor.picture != null}">
                    <div class="img">
                        <img src="${pageContext.request.contextPath}${requestScope.actor.picture}"/>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <div class="actor-info">
            <h3><c:out value="${requestScope.actor.firstName}"/> <c:out value="${requestScope.actor.lastName}"/></h3>
            <h2><fmt:message key="label.about_person"/></h2>
            <div class="info">
                <ul>
                    <li class="name"><fmt:message key="label.age"/></li>
                    <li>
                        <c:choose>
                            <c:when test="${requestScope.actor.age != 0}">
                                <c:out value="${requestScope.actor.age}"/>
                            </c:when>
                            <c:when test="${requestScope.actor.age == 0}">
                                -
                            </c:when>
                        </c:choose>
                    </li>
                    <li class="name"><fmt:message key="label.height"/></li>
                    <li>
                        <c:choose>
                            <c:when test="${requestScope.actor.height != 0}">
                                <c:out value="${requestScope.actor.height} м"/>
                            </c:when>
                            <c:when test="${requestScope.actor.height == 0}">
                                -
                            </c:when>
                        </c:choose>
                    </li>
                    <li class="name"><fmt:message key="label.total_movies"/></li>
                    <li>${requestScope.movies_for_actor_list.size()}</li>
                </ul>
            </div>
            <c:if test="${requestScope.best_movies_for_actor_list.size() > 0}">
                <h2><fmt:message key="label.best_movies"/></h2>
            </c:if>
            <div id="wrapper">
                <div id="carousel">
                    <div id="content">
                        <c:forEach items="${requestScope.best_movies_for_actor_list}" var="bestMoviesForActor">
                            <a class="movie-picture" href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${bestMoviesForActor.movieId}"
                               style="margin-left: -1.5px">
                                <c:choose>
                                    <c:when test="${bestMoviesForActor.rating.score >= 70}">
                                        <p class="score" style="background-color: #66cc33"><c:out value="${bestMoviesForActor.rating.score}"/></p>
                                    </c:when>
                                    <c:when test="${bestMoviesForActor.rating.score < 70}">
                                        <p class="score" style="background-color: #f9c22a"><c:out value="${bestMoviesForActor.rating.score}"/></p>
                                    </c:when>
                                </c:choose>
                                <img src="${pageContext.request.contextPath}${bestMoviesForActor.picture}"
                                     class="item"/>
                                <form action="${pageContext.request.contextPath}/controller" method="get">
                                    <input type="hidden" name="command" value="show_movie_details">
                                    <input type="hidden" name="movie_id" value="${bestMoviesForActor.movieId}">
                                    <button type="submit" class="title"><c:out value="${bestMoviesForActor.title}"/></button>
                                </form>
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${requestScope.best_movies_for_actor_list.size() > 2}">
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
        <table class="content-table">
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message key="label.movie"/></th>
                <th><fmt:message key="label.release_date"/></th>
                <th><fmt:message key="label.scores"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.movies_for_actor_list}" var="moviesForActor" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="show_movie_details">
                            <input type="hidden" name="movie_id" value="${moviesForActor.movieId}">
                            <button type="submit"><c:out value="${moviesForActor.title}"/></button>
                        </form>
                    </td>
                    <td><c:out value="${moviesForActor.releaseDate}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${moviesForActor.rating.score > 0}">
                                <i class="fa fa-star"></i> <c:out value="${moviesForActor.rating.score}"/>
                            </c:when>
                            <c:when test="${moviesForActor.rating.score == 0}">
                                <fmt:message key="label.rating"/>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
