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
<div id="wrapper">
    <div class="top">
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="newest_movies">
                    <button class="link1"><h2 style=" margin-left: 0"><fmt:message key="label.new_movies"/></h2>
                    </button>
                </form>
            </li>
            <li style="margin-left: 7px">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="newest_movies">
                    <button class="link2"><fmt:message key="label.see_more"/></button>
                </form>
            </li>
        </ul>
    </div>
    <div id="carousel1">
        <div id="content1">
            <c:forEach items="${requestScope.newest_movies_list}" var="newestMovies" varStatus="counter">
                <c:if test="${counter.count <= 12}">
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
                        <img src="http://${newestMovies.picture}" class="item"/>
                        <p class="movie-title"><c:out value="${newestMovies.title}"/></p>
                    </a>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <button id="prev1">
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
    <button id="next1">
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
<div id="wrapper1">
    <div class="top">
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="upcoming_movies">
                    <button class="link1"><h2 style=" margin-left: 0"><fmt:message key="label.coming_soon"/></h2>
                    </button>
                </form>
            </li>
            <li style="margin-left: 7px">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="upcoming_movies">
                    <button class="link2"><fmt:message key="label.see_more"/></button>
                </form>
            </li>
        </ul>
    </div>
    <div id="carousel2">
        <div id="content2">
            <c:forEach items="${requestScope.upcoming_movies_list}" var="upcomingMovies">
                <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${upcomingMovies.movieId}"
                   style="margin-left: -1.5px">
                    <c:choose>
                        <c:when test="${upcomingMovies.rating.score == 0}">
                            <p class="score"></p>
                        </c:when>
                        <c:when test="${upcomingMovies.rating.score >= 70}">
                            <p class="score" style="background-color: #66cc33"><c:out
                                    value="${upcomingMovies.rating.score}"/></p>
                        </c:when>
                        <c:when test="${upcomingMovies.rating.score < 70 && upcomingMovies.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3"><c:out
                                    value="${upcomingMovies.rating.score}"/></p>
                        </c:when>
                        <c:when test="${upcomingMovies.rating.score < 40}">
                            <p class="score" style="background-color: red"><c:out
                                    value="${upcomingMovies.rating.score}"/></p>
                        </c:when>
                    </c:choose>
                    <img src="http://${upcomingMovies.picture}" class="item"/>
                    <p class="movie-title"><c:out value="${upcomingMovies.title}"/></p>
                </a>
            </c:forEach>
        </div>
    </div>
    <button id="prev2">
        <svg
                xmlns="http://www.w3.org/2000/svg"
                width="36"
                height="36"
                viewBox="0 0 24 24"
        >
            <path fill="none" d="M0 0h24v24H0V0z"/>
            <path d="M15.61 7.41L14.2 6l-6 6 6 6 1.41-1.41L11.03 12l4.58-4.59z"/>
        </svg>
    </button>
    <button id="next2">
        <svg
                xmlns="http://www.w3.org/2000/svg"
                width="35"
                height="36"
                viewBox="0 0 24 24"
        >
            <path fill="none" d="M0 0h24v24H0V0z"/>
            <path d="M10.02 6L8.61 7.41 13.19 12l-4.58 4.59L10.02 18l6-6-6-6z"/>
        </svg>
    </button>
</div>
<div id="wrapper2">
    <div class="top">
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="most_rated_movies">
                    <button class="link1"><h2 style=" margin-left: 0"><fmt:message key="label.most_rated"/></h2>
                    </button>
                </form>
            </li>
            <li style="margin-left: 7px">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="most_rated_movies">
                    <button class="link2"><fmt:message key="label.see_more"/></button>
                </form>
            </li>
        </ul>
    </div>
    <div id="carousel3">
        <div id="content3">
            <c:forEach items="${requestScope.most_rated_movies_list}" var="mostRatedMovies">
                <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${mostRatedMovies.movieId}"
                   style="margin-left: -1.5px">
                    <c:choose>
                        <c:when test="${mostRatedMovies.rating.score == 0}">
                            <p class="score"></p>
                        </c:when>
                        <c:when test="${mostRatedMovies.rating.score >= 70}">
                            <p class="score" style="background-color: #66cc33"><c:out
                                    value="${mostRatedMovies.rating.score}"/></p>
                        </c:when>
                        <c:when test="${mostRatedMovies.rating.score < 70 && mostRatedMovies.rating.score >= 40}">
                            <p class="score" style="background-color: #fc3"><c:out
                                    value="${mostRatedMovies.rating.score}"/></p>
                        </c:when>
                        <c:when test="${mostRatedMovies.rating.score < 40}">
                            <p class="score" style="background-color: red"><c:out
                                    value="${mostRatedMovies.rating.score}"/></p>
                        </c:when>
                    </c:choose>
                    <img src="http://${mostRatedMovies.picture}" class="item"/>
                    <div class="movie-title">
                        <p><c:out value="${mostRatedMovies.title}"/></p>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
    <button id="prev3">
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
    <button id="next3">
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
<h2 style="margin-left: 140px; margin-top: 40px"><fmt:message key="label.trailers"/></h2>
<div class="trailers-container">
    <c:forEach items="${requestScope.trailers_list}" var="trailers" varStatus="counter">
        <c:if test="${counter.count == 1}">
            <img class="img1" src="http://${trailers.picture}">
        </c:if>
    </c:forEach>
    <div class="titles">
        <c:forEach items="${requestScope.trailers_list}" var="trailers" varStatus="counter">
            <button class="title-btn"
                    id="http://${trailers.trailer}"
                    value="http://${trailers.picture}">
                <p>${counter.count} <c:out value="${trailers.title}"/></p>
                <c:choose>
                    <c:when test="${trailers.rating.score >= 70}">
                        <span style="background-color: #66cc33"><p>${trailers.rating.score}</p></span>
                    </c:when>
                    <c:when test="${trailers.rating.score < 70 && trailers.rating.score >= 40}">
                        <span style="background-color: #f9c22a"><p>${trailers.rating.score}</p></span>
                    </c:when>
                    <c:when test="${trailers.rating.score < 40 && trailers.rating.score > 0}">
                        <span style="background-color: red"><p>${trailers.rating.score}</p></span>
                    </c:when>
                    <c:otherwise>
                        <span><p>tbd</p></span>
                    </c:otherwise>
                </c:choose>

            </button>
        </c:forEach>
    </div>
    <div class="trailer">
        <c:forEach items="${requestScope.trailers_list}" var="trailers" varStatus="counter">
            <c:if test="${counter.count == 1}">
                <video controls class="movie-trailer"
                       src="http://${trailers.trailer}"></video>
            </c:if>
        </c:forEach>
    </div>
</div>
<br>
<br>
<script type="text/javascript">
    $(document).ready(function () {
        const gap1 = 10;
        const carousel1 = document.getElementById("carousel1"),
            content1 = document.getElementById("content1"),
            next1 = document.getElementById("next1"),
            prev1 = document.getElementById("prev1");

        next1.addEventListener("click", e => {
            carousel1.scrollBy(width + gap1, 0);
            if (carousel1.scrollWidth !== 0) {
                prev1.style.display = "flex";
            }
            if (content1.scrollWidth - width - gap1 <= carousel1.scrollLeft + width) {
                next1.style.display = "none";
            }
        });
        prev1.addEventListener("click", e => {
            carousel1.scrollBy(-(width + gap1), 0);
            if (carousel1.scrollLeft - width - gap1 <= 0) {
                prev1.style.display = "none";
            }
            if (!content1.scrollWidth - width - gap1 <= carousel1.scrollLeft + width) {
                next1.style.display = "flex";
            }
        });

        let width = carousel1.offsetWidth;
        window.addEventListener("resize", e => (width = carousel1.offsetWidth));
    });

    $(document).ready(function () {
        const gap2 = 10;

        const carousel2 = document.getElementById("carousel2"),
            content2 = document.getElementById("content2"),
            next2 = document.getElementById("next2"),
            prev2 = document.getElementById("prev2");

        next2.addEventListener("click", e => {
            carousel2.scrollBy(width + gap2, 0);
            if (carousel2.scrollWidth !== 0) {
                prev2.style.display = "flex";
            }
            if (content2.scrollWidth - width - gap2 <= carousel2.scrollLeft + width) {
                next2.style.display = "none";
            }
        });
        prev2.addEventListener("click", e => {
            carousel2.scrollBy(-(width + gap2), 0);
            if (carousel2.scrollLeft - width - gap2 <= 0) {
                prev2.style.display = "none";
            }
            if (!content2.scrollWidth - width - gap2 <= carousel2.scrollLeft + width) {
                next2.style.display = "flex";
            }
        });

        let width = carousel2.offsetWidth;
        window.addEventListener("resize", e => (width = carousel2.offsetWidth));
    })

    $(document).ready(function () {
        const gap3 = 10;

        const carousel3 = document.getElementById("carousel3"),
            content3 = document.getElementById("content3"),
            next3 = document.getElementById("next3"),
            prev3 = document.getElementById("prev3");

        next3.addEventListener("click", e => {
            carousel3.scrollBy(width + gap3, 0);
            if (carousel3.scrollWidth !== 0) {
                prev3.style.display = "flex";
            }
            if (content3.scrollWidth - width - gap3 <= carousel3.scrollLeft + width) {
                next3.style.display = "none";
            }
        });
        prev3.addEventListener("click", e => {
            carousel3.scrollBy(-(width + gap3), 0);
            if (carousel3.scrollLeft - width - gap3 <= 0) {
                prev3.style.display = "none";
            }
            if (!content3.scrollWidth - width - gap3 <= carousel3.scrollLeft + width) {
                next3.style.display = "flex";
            }
        });

        let width = carousel3.offsetWidth;
        window.addEventListener("resize", e => (width = carousel3.offsetWidth));
    });

    $(document).ready(function () {
        $('.title-btn').click(function () {
            $('.img1').attr('src', $(this).attr("value"))
            $('.movie-trailer').attr('src', $(this).attr("id"))
        })
    })
</script>
</body>
<jsp:include page="static/footer.jsp"/>
</html>
