<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.edit"/></title>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/edit_movie.css">
</head>
<body>
<div class="main-content">
    <div class="navigation">
        <ul>
            <li><form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="display_movie_poster">
                <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                <button type="submit"><fmt:message key="label.poster"/></button>
            </form></li>
            <li><form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="display_movie_actors">
                <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                <button type="submit"><fmt:message key="label.actors"/></button>
            </form></li>
            <li><form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="display_movie_directors">
                <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                <button type="submit"><fmt:message key="label.directors"/></button>
            </form></li>
            <li><form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="display_movie_genres">
                <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                <button type="submit"><fmt:message key="label.genres"/></button>
            </form></li>
            <li><fmt:message key="label.country"/></li>
        </ul>
    </div>
    <div class="edit">
        <c:choose>

            <c:when test="${requestScope.movie_info != null}">
                <h2><fmt:message key="label.movies"/></h2>

            </c:when>

            <c:when test="${requestScope.actors_list != null}">
                <h2><fmt:message key="label.actors"/></h2>
                <table class="content-table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="label.first_name"/></th>
                        <th><fmt:message key="label.last_name"/></th>
                        <th><fmt:message key="label.operation"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.actors_list}" var="actors" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td><c:out value="${actors.firstName}"/></td>
                            <td><c:out value="${actors.lastName}"/></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="remove_actor_from_movie">
                                    <input type="hidden" name="actor_id" value="${actors.actorId}">
                                    <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                                    <button type="submit" class="remove-btn"><fmt:message key="label.remove"/></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <h2><fmt:message key="label.add_actor"/></h2>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="find_actor">
                    <input type="text" name="first_name" placeholder="<fmt:message key="label.first_name"/>">
                    <input type="text" name="last_name" placeholder="<fmt:message key="label.last_name"/>">
                    <button type="submit" class="search-btn"><fmt:message key="label.search"/></button>
                </form>
                <c:if test="${sessionScope.actor != null}">
                    <table class="content-table">
                        <tbody style="margin-top: 10px">
                            <tr>
                                <td><c:out value="${sessionScope.actor.firstName}"/></td>
                                <td><c:out value="${sessionScope.actor.lastName}"/></td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="add_actor_to_movie">
                                        <input type="hidden" name="first_name" value="${sessionScope.actor.firstName}">
                                        <input type="hidden" name="last_name" value="${sessionScope.actor.lastName}">
                                        <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                                        <button type="submit" class="remove-btn" style="background-color: #66cc33"><fmt:message key="label.add"/></button>
                                    </form>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </c:if>
            </c:when>

            <c:when test="${requestScope.directors_list != null}">
                <h2><fmt:message key="label.directors"/></h2>
                <table class="content-table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="label.first_name"/></th>
                        <th><fmt:message key="label.last_name"/></th>
                        <th><fmt:message key="label.operation"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.directors_list}" var="directors" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td><c:out value="${directors.firstName}"/></td>
                            <td><c:out value="${directors.lastName}"/></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="remove_director_from_movie">
                                    <input type="hidden" name="director_id" value="${directors.directorId}">
                                    <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                                    <button type="submit" class="remove-btn"><fmt:message key="label.remove"/></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <h2><fmt:message key="label.add_director"/></h2>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="find_director">
                    <input type="text" name="first_name" placeholder="<fmt:message key="label.first_name"/>">
                    <input type="text" name="last_name" placeholder="<fmt:message key="label.last_name"/>">
                    <button type="submit" class="search-btn"><fmt:message key="label.search"/></button>
                </form>
                <c:if test="${sessionScope.director != null}">
                    <table class="content-table">
                        <tbody style="margin-top: 10px">
                        <tr>
                            <td><c:out value="${sessionScope.director.firstName}"/></td>
                            <td><c:out value="${sessionScope.director.lastName}"/></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="add_director_to_movie">
                                    <input type="hidden" name="first_name" value="${sessionScope.director.firstName}">
                                    <input type="hidden" name="last_name" value="${sessionScope.director.lastName}">
                                    <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                                    <button type="submit" class="remove-btn" style="background-color: #66cc33"><fmt:message key="label.add"/></button>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:if>
            </c:when>

            <c:when test="${requestScope.movie_genres_list != null}">
                <h2><fmt:message key="label.genres"/></h2>
                <table class="content-table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="label.name"/></th>
                        <th><fmt:message key="label.operation"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.movie_genres_list}" var="movieGenres" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td><c:out value="${movieGenres.genreTitle}"/></td>
                            <td><form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="remove_genre_from_movie">
                                <input type="hidden" name="genre_id" value="${movieGenres.genreId}">
                                <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                                <button type="submit" class="remove-btn"><fmt:message key="label.remove"/></button>
                            </form></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <h2><fmt:message key="label.add_genre"/></h2>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="add_genre_to_movie">
                    <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                    <table class="content-table">
                        <tbody>
                        <tr>
                            <td><select name="genre_id" style="width: 130px; height: 28px; cursor: pointer">
                                <c:forEach items="${requestScope.genres_list}" var="genres">
                                    <option value="${genres.genreId}"><c:out value="${genres.genreTitle}"/></option>
                                </c:forEach>
                            </select></td>
                            <td><button type="submit" class="remove-btn" style="background-color: #66cc33"><fmt:message key="label.add"/></button></td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </c:when>

            <c:when test="${requestScope.movie_poster != null}">
                <table class="content-table">
                    <thead>
                    <tr>
                        <th><fmt:message key="label.poster"/></th>
                        <th><fmt:message key="label.operation"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <img src="<c:url value="${movie_poster.picture}"/>" alt="capture_test">
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="command" value="change_movie_poster">
                                <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                                <input type="file" name="file">
                                <button type="submit" class="remove-btn"
                                        style="background-color: #66cc33"><fmt:message key="label.change"/></button>
                            </form>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>
