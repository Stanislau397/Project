<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.director"/></title>
    <jsp:include page="/jsp/static/header.jsp"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/actor.css">
</head>
<body>
<div class="main">
    <div class="head">
        <h3><c:out value="${requestScope.first_name}"/> <c:out value="${requestScope.last_name}"/></h3>
    </div>
    <div class="films">
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
            <c:forEach items="${requestScope.movies_for_director_list}" var="moviesForDirector" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="show_movie_details">
                            <input type="hidden" name="movie_id" value="${moviesForDirector.movieId}">
                            <button type="submit"><c:out value="${moviesForDirector.title}"/></button>
                        </form>
                    </td>
                    <td><c:out value="${moviesForDirector.releaseDate}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${moviesForDirector.rating.score > 0}">
                                <i class="fa fa-star"></i>  <c:out value="${moviesForDirector.rating.score}"/>
                            </c:when>
                            <c:when test="${moviesForDirector.rating.score == 0}">
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
</body>
</html>
