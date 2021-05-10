<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.movies"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all_movies.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</head>
<body>
<div class="main-content">
    <div class="search">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="search_movie">
            <input type="text" name="key_word">
            <button type="submit"><fmt:message key="label.search"/></button>
        </form>
    </div>
    <c:forEach items="${requestScope.movie_list}" var="movies">
        <div class="movie">
            <div class="picture">
                <img src="${pageContext.request.contextPath}${movies.picture}">
            </div>
            <div class="title">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="show_movie_details">
                    <input type="hidden" name="movie_id" value="${movies.movieId}">
                    <button type="submit">${movies.title}</button>
                </form>
            </div>
            <div class="actors">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="display_movie_actors">
                    <input type="hidden" name="movie_id" value="${movies.movieId}">
                    <input type="hidden" name="movie_title" value="${movies.title}">
                    <button type="submit"><fmt:message key="label.actors"/></button>
                </form>
            </div>
            <div class="actors">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="display_movie_directors">
                    <input type="hidden" name="movie_id" value="${movies.movieId}">
                    <input type="hidden" name="movie_title" value="${movies.title}">
                    <button type="submit"><fmt:message key="label.director"/></button>
                </form>
            </div>
            <div class="actors">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <button type="submit"><fmt:message key="label.remove"/></button>
                </form>
            </div>
            <div class="edit">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="to_edit_movie">
                    <input type="hidden" name="movie_id" value="${movies.movieId}">
                    <button type="submit"><i class="fa fa-edit"></i></button>
                </form>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
