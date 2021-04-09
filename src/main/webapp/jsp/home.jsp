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
                    <img src="${pageContext.request.contextPath}${newestMovies.picture}"/>
                    <c:if test="${newestMovies.rating.score != 0}">
                        <div class="movie-rating">${newestMovies.rating.score}</div>
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
                    <img src="${pageContext.request.contextPath}${mostRatedMovies.picture}"/>
                    <c:if test="${mostRatedMovies.rating.score != 0}">
                        <div class="movie-rating1">${mostRatedMovies.rating.score}</div>
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
</body>
<jsp:include page="static/footer.jsp"/>
</html>
