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
<div class="search">
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="">
        <input type="text" name="user_name" placeholder="<fmt:message key="label.search_movie"/>">
        <input type="submit" value="<fmt:message key="label.search"/>">
    </form>
</div>
<div class="main-content">
    <table class="content-table">
        <thead>
        <tr>
            <th><fmt:message key="label.id"/></th>
            <th><fmt:message key="label.poster"/></th>
            <th><fmt:message key="label.title"/></th>
            <th><fmt:message key="label.country"/></th>
            <th><fmt:message key="label.operation"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.movie_list}" var="allMovies">
            <tr>
                <td><c:out value="${allMovies.movieId}"/></td>
                <td><img src="${pageContext.request.contextPath}${allMovies.picture}"/></td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_movie_details">
                        <input type="hidden" name="movie_id" value="${allMovies.movieId}">
                        <button type="submit" class="movie-title-btn"><c:out value="${allMovies.title}"/></button>
                    </form>
                </td>
                <td><c:out value="${allMovies.country}"/></td>
                <td>
                    <div class="edit-movie">
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="to_edit_movie">
                            <input type="hidden" name="movie_id" value="${allMovies.movieId}">
                            <button type="submit" class="edit-btn"><fmt:message key="label.edit"/></button>
                        </form>
                    </div>
                    <div class="remove-movie">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="delete_movie">
                            <input type="hidden" name="movie_id" value="${allMovies.movieId}">
                            <button type="submit" class="remove-btn"><fmt:message key="label.remove"/></button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
