<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.profile"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
</header>
<body>
<div class="middle-content">
    <div class="user">
        <div class="profile-picture">
            <img
                    src="${requestScope.user.avatar}">
        </div>
        <div class="user-info">
            <h2><c:out value="${requestScope.user.userName}"/></h2>
            <span><fmt:message key="label.scores"/><span id="all_reviews">${requestScope.all_reviews}</span></span>
            <span><fmt:message key="label.comment"/> <c:out value="${requestScope.count_comments}"/></span>
        </div>
        <c:if test="${requestScope.user.userName == sessionScope.user_name || requestScope.user.userName == null}">
            <div class="edit-settings">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="to_user_settings">
                    <input type="hidden" name="user_name" value="${requestScope.user.userName}">
                    <button type="submit">
                        <fmt:message key="label.edit_settings"/>
                    </button>
                </form>
            </div>
        </c:if>
    </div>
    <h2 style="font-size: 22px"><fmt:message key="label.my_scores"/></h2>
    <div class="my-scores">
        <div class="score-distribution">
            <h4><fmt:message key="label.score_distribution"/></h4>
            <div class="positive-score">
                <div class="positive-value"
                     id="positive"
                     style="background-color: #66cc33">

                </div>
            </div>
            <h5><fmt:message key="label.positive"/><span id="positive-number">${requestScope.positive_reviews}</span>
            </h5>

            <div class="mixed-score">
                <div class="mixed-value"
                     id="mixed"
                     style="background-color: #f9c22a">

                </div>
            </div>
            <h5><fmt:message key="label.mixed"/><span id="mixed-number">${requestScope.mixed_reviews}</span></h5>

            <div class="negative-score">
                <div class="negative-value"
                     id="negative"
                     style="background-color: red">

                </div>
            </div>
            <h5><fmt:message key="label.negative"/>
                <span id="negative-number">${requestScope.negative_reviews}</span>
            </h5>
        </div>
        <div class="my-latest-scores">
            <h4><fmt:message key="label.average_movie_score"/>
                <c:choose>
                    <c:when test="${requestScope.average >= 70}">
                        <span style="color: #66cc33"><c:out value="${requestScope.average / 10}"/></span>
                    </c:when>
                    <c:when test="${requestScope.average >= 40 && requestScope.average < 70}">
                        <span style="color: #f9c22a;"><c:out value="${requestScope.average / 10}"/></span>
                    </c:when>
                    <c:when test="${requestScope.average < 40}">
                        <span style="color: red"><c:out value="${requestScope.average / 10}"/></span>
                    </c:when>
                </c:choose>
            </h4>
            <div class="latest-high-score">
                <div class="score" style="background-color: #66cc33">
                    <p><c:out value="${requestScope.latest_high_score_movie.rating.score / 10}"/></p>
                </div>
                <div class="title">
                    <h3><fmt:message key="label.latest_high_score"/></h3>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_movie_details">
                        <input type="hidden" name="movie_id" value="${requestScope.latest_high_score_movie.movieId}">
                        <button type="submit"><c:out value="${requestScope.latest_high_score_movie.title}"/></button>
                    </form>
                </div>
            </div>
            <div class="latest-low-score">
                <div class="score" style="background-color: red">
                    <p><c:out value="${requestScope.latest_low_score_movie.rating.score / 10}"/></p>
                </div>
                <div class="title">
                    <h3><fmt:message key="label.latest_low_score"/></h3>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_movie_details">
                        <input type="hidden" name="movie_id" value="${requestScope.latest_low_score_movie.movieId}">
                        <button type="submit"><c:out value="${requestScope.latest_low_score_movie.title}"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <c:forEach items="${requestScope.rated_movies_list}" var="ratedMovies" varStatus="counter">
        <div class="history">
            <a href="${pageContext.request.contextPath}/controller?command=show_movie_details&movie_id=${ratedMovies.movieId}">
                <div class="movie-poster">
                    <img src="${ratedMovies.picture}">
                </div>
            </a>
            <div class="user-score">
                <c:if test="${ratedMovies.rating.score >= 70}">
                    <p style="background-color: #6c3"><c:out value="${ratedMovies.rating.score / 10}"/></p>
                </c:if>
                <c:if test="${ratedMovies.rating.score < 70 && ratedMovies.rating.score >= 40}">
                    <p style="background-color: #fc3"><c:out value="${ratedMovies.rating.score / 10}"/></p>
                </c:if>
                <c:if test="${ratedMovies.rating.score < 40}">
                    <p style="background-color: #f00"><c:out value="${ratedMovies.rating.score / 10}"/></p>
                </c:if>
            </div>
            <div class="movie-title">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="show_movie_details">
                    <input type="hidden" name="movie_id" value="${ratedMovies.movieId}">
                    <button type="submit"><c:out value="${ratedMovies.title}"/></button>
                </form>
                <div class="date">
                    <c:out value="${ratedMovies.comment.postDate}"/>
                </div>
                <div class="text">
                    <c:out value="${ratedMovies.comment.text}"/>
                </div>
            </div>
        </div>
    </c:forEach>
    <div class="pagination">
        <div class="title">
            <p><fmt:message key="label.page"/></p>
        </div>
        <c:forEach begin="1" end="${requestScope.pages}" varStatus="counter">
            <c:choose>
                <c:when test="${requestScope.page_id == counter.count}">
                    <a href="${pageContext.request.contextPath}/controller?command=show_user_profile&user_name=Stanislau&page=${counter.count}"
                       style="color: #2f80ed">${counter.count}</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/controller?command=show_user_profile&user_name=Stanislau&page=${counter.count}">${counter.count}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
    <br>
    <jsp:include page="/jsp/static/footer.jsp"/>
</div>

<script>
    const positiveReview = document.getElementById('positive-number').innerText;
    const mixedReview = document.getElementById('mixed-number').innerText;
    const negativeReview = document.getElementById('negative-number').innerText;
    const allReviews = document.getElementById('all_reviews').innerText;

    $(document).ready(function () {
        let percentOfPositiveReviews = positiveReview / allReviews * 100;
        document.getElementById('positive').style.width = percentOfPositiveReviews + '%'
        let percentOfMixedReviews = mixedReview / allReviews * 100;
        document.getElementById('mixed').style.width = percentOfMixedReviews + '%'
        let percentOfNegativeReviews = negativeReview / allReviews * 100;
        document.getElementById('negative').style.width = percentOfNegativeReviews + '%'
    })
</script>
</body>
</html>
