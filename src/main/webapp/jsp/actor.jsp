<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="today" value="<%=new Date()%>"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                    <li class="name"><fmt:message key="label.birth_date"/></li>
                    <li>
                        <c:choose>
                            <c:when test="${fn:contains(requestScope.actor.birthDate, 'null')}">
                                -
                            </c:when>
                            <c:otherwise>
                                <c:out value="${requestScope.actor.birthDate}"/>
                            </c:otherwise>
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
        </div>
    </div>
    <div class="films">
        <h3 class="filmography"><fmt:message key="label.all_filmography"/></h3>
        <c:forEach items="${requestScope.movies_for_actor_list}" var="moviesForActor">
            <div class="container">
                <div class="movie-year">
                    <p><fmt:formatDate
                            value="${moviesForActor.releaseDate}" pattern="yyyy"/></p>
                </div>
                <div class="pic">
                    <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${moviesForActor.movieId}">
                        <img src="${pageContext.request.contextPath}${moviesForActor.picture}">
                    </a>
                </div>
                <div class="movie-title">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_movie_details">
                        <input type="hidden" name="movie_id" value="${moviesForActor.movieId}">
                        <button type="submit" class="movie-title-btn"><c:out value="${moviesForActor.title}"/></button>
                    </form>
                </div>
                <div class="movie-score">
                    <c:choose>
                        <c:when test="${moviesForActor.rating.score >= 70}">
                            <p style="background-color: #66cc33"><c:out
                                    value="${moviesForActor.rating.score / 10}"/></p>
                        </c:when>
                        <c:when test="${moviesForActor.rating.score < 70 && moviesForActor.rating.score >= 40}">
                            <p style="background-color: #f9c22a"><c:out
                                    value="${moviesForActor.rating.score / 10}"/></p>
                        </c:when>
                        <c:when test="${moviesForActor.rating.score < 40 && moviesForActor.rating.score > 0}">
                            <p style="background-color: red"><c:out value="${moviesForActor.rating.score / 10}"/></p>
                        </c:when>
                        <c:when test="${moviesForActor.rating.score == 0}">
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
