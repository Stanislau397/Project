<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>admin_cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_cabinet.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
</head>
<header>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</header>
<body>
<div class="top">
    <div class="text1">
        <h2>Dashboard</h2>
    </div>
</div>
<div class="main-content">
    <div class="content">
        <div class="head">
            <div class="icon">
                <i class="fa fa-trophy"></i>
            </div>
            <div class="name">
                <p>Top movies</p>
            </div>
            <div class="button">
                <button>Больше</button>
            </div>
        </div>
        <table class="content-table">
            <thead>
            <tr>
                <th><fmt:message key="label.id"/></th>
                <th><fmt:message key="label.title"/></th>
                <th><fmt:message key="label.release_date"/></th>
                <th><fmt:message key="label.rating1"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.most_rated_movies_list}" var="topMovies" varStatus="counter">
                <c:if test="${counter.count <= 5}">
                    <tr>
                        <td><c:out value="${topMovies.movieId}"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="show_movie_details">
                                <input type="hidden" name="movie_id" value="${topMovies.movieId}">
                                <button type="submit"><c:out value="${topMovies.title}"/></button>
                            </form>
                        </td>
                        <td><c:out value="${topMovies.releaseDate}"/></td>
                        <td><i class="fa fa-star"></i> <c:out value="${topMovies.rating.score}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="content">
        <div class="head">
            <div class="icon">
                <i class="fa fa-film"></i>
            </div>
            <div class="name">
                <p>Latest movies</p>
            </div>
            <div class="button">
                <button>Больше</button>
            </div>
        </div>
        <table class="content-table">
            <thead>
            <tr>
                <th><fmt:message key="label.id"/></th>
                <th><fmt:message key="label.title"/></th>
                <th><fmt:message key="label.release_date"/></th>
                <th><fmt:message key="label.genre"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.latest_movies_list}" var="latestMovies" varStatus="counter">
                <c:if test="${counter.count <= 5}">
                    <tr>
                        <td><c:out value="${latestMovies.movieId}"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="show_movie_details">
                                <input type="hidden" name="movie_id" value="${latestMovies.movieId}">
                                <button type="submit"><c:out value="${latestMovies.title}"/></button>
                            </form>
                        </td>
                        <td><c:out value="${latestMovies.releaseDate}"/></td>
                        <td><c:out value="${latestMovies.genre.genreTitle}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="content">
        <div class="head">
            <div class="icon">
                <i class="fa fa-users"></i>
            </div>
            <div class="name">
                <p>Latest users</p>
            </div>
            <div class="button">
                <button>Больше</button>
            </div>
        </div>
        <table class="content-table">
            <thead>
            <tr>
                <th><fmt:message key="label.id"/></th>
                <th><fmt:message key="label.username"/></th>
                <th><fmt:message key="label.email"/></th>
                <th><fmt:message key="label.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.latest_users_list}" var="latestUsers" varStatus="counter">
                <c:if test="${counter.count <= 5}">
                    <tr>
                        <td><c:out value="${latestUsers.userId}"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="show_user_profile">
                                <input type="hidden" name="user_name" value="${latestUsers.userName}">
                                <button type="submit"><c:out value="${latestUsers.userName}"/></button>
                            </form>
                        </td>
                        <td><c:out value="${latestUsers.email}"/></td>
                        <c:choose>
                            <c:when test="${latestUsers.blocked}">
                                <td>Banned</td>
                            </c:when>
                            <c:when test="${latestUsers.blocked == false}">
                                <td>Approved</td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="content">
        <div class="head">
            <div class="icon">
                <i class="fa fa-star"></i>
            </div>
            <div class="name">
                <p>Latest reviews</p>
            </div>
            <div class="button">
                <button>Больше</button>
            </div>
        </div>
        <table class="content-table">
            <thead>
            <tr>
                <th><fmt:message key="label.id"/></th>
                <th><fmt:message key="label.title"/></th>
                <th><fmt:message key="label.username"/></th>
                <th><fmt:message key="label.rating1"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.latest_reviewed_movies_list}" var="latestReviewedMovies" varStatus="counter">
                <c:if test="${counter.count <= 5}">
                    <tr>
                        <td><c:out value="${latestReviewedMovies.movieId}"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="show_movie_details">
                                <input type="hidden" name="movie_id" value="${latestReviewedMovies.movieId}">
                                <button type="submit"><c:out value="${latestReviewedMovies.title}"/></button>
                            </form>
                        </td>
                        <td>dal</td>
                        <td><c:out value="${latestReviewedMovies.rating.score}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
