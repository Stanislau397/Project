<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="today" value="<%=new Date()%>"/>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
                        <img src="http://${requestScope.director.picture}"/>
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
                            <c:when test="${fn:contains(requestScope.director.birthDate, 'null')}">
                                -
                            </c:when>
                            <c:otherwise>
                                <c:out value="${requestScope.director.birthDate}"/>
                            </c:otherwise>
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
        </div>
        <c:if test="${requestScope.best_movies_for_director_list.size() != 0}">
            <div class="best-movies">
                <h2><fmt:message key="label.best_movies"/></h2>
                <c:forEach items="${requestScope.best_movies_for_director_list}" var="bestMoviesForDirector">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${bestMoviesForDirector.movieId}">
                        <button type="submit"><c:out value="${bestMoviesForDirector.title}"/></button>
                    </form>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <div class="films">
        <h3 class="filmography"><fmt:message key="label.all_filmography"/></h3>
        <c:forEach items="${requestScope.movies_for_director_list}" var="moviesForDirector">
            <div class="container">
                <div class="movie-year">
                    <p><fmt:formatDate
                            value="${moviesForDirector.releaseDate}" pattern="yyyy"/></p>
                </div>
                <div class="pic">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${moviesForDirector.movieId}">
                        <img src="http://${moviesForDirector.picture}">
                    </a>
                </div>
                <div class="movie-title">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_movie_details">
                        <input type="hidden" name="movie_id" value="${moviesForDirector.movieId}">
                        <button type="submit" class="movie-title-btn"><c:out value="${moviesForDirector.title}"/></button>
                    </form>
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
                            <p style="color: white; background-color: lightslategray">tbd</p>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </c:forEach>
    </div>
    <jsp:include page="/jsp/static/footer.jsp"/>
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