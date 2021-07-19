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
            <img src="${pageContext.request.contextPath}${requestScope.user.avatar}">
        </div>
        <div class="user-info">
            <h2><c:out value="${requestScope.user.userName}"/></h2>
        </div>
        <c:if test="${requestScope.user.userName == sessionScope.user_name || requestScope.user.userName == null}">
            <div class="edit-settings">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="to_user_settings">
                    <input type="hidden" name="user_name" value="${requestScope.user.userName}">
                    <button type="submit"> Edit settings
                    </button>
                </form>

            </div>
        </c:if>
    </div>
    <h2><fmt:message key="label.score_history"/></h2>
    <c:forEach items="${requestScope.rated_movies_list}" var="ratedMovies" varStatus="counter">
        <div class="history">
            <div class="counter">
                <p id="counter"><c:out value="${counter.count}."/></p>
            </div>
            <div class="movie-poster">
                <img src="${pageContext.request.contextPath}${ratedMovies.picture}">
            </div>
            <div class="user-score">
                <c:if test="${ratedMovies.rating.score >= 70}">
                    <p style="background-color: #6c3"><c:out value="${ratedMovies.rating.score}"/></p>
                </c:if>
                <c:if test="${ratedMovies.rating.score < 70 && ratedMovies.rating.score >= 40}">
                    <p style="background-color: #fc3"><c:out value="${ratedMovies.rating.score}"/></p>
                </c:if>
                <c:if test="${ratedMovies.rating.score < 40}">
                    <p style="background-color: #f00"><c:out value="${ratedMovies.rating.score}"/></p>
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
</div>
</body>
</html>
