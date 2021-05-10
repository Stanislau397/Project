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
            <li>
                <form>
                    <button type="submit"><fmt:message key="label.poster"/></button>
                </form>
            </li>
            <li>
                <form>
                    <input type="hidden" name="command" value="display_movie_actors">
                    <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                    <button type="submit"><fmt:message key="label.actors"/></button>
                </form>
            </li>
            <li>
            <li>
                <form>
                    <input type="hidden" name="command" value="add_actor_to_movie">
                    <button type="submit"><fmt:message key="label.directors"/></button>
                </form>
            </li>
            </li>
            <li>
                <form>
                    <button type="submit"><fmt:message key="label.genres"/></button>
                </form>
            </li>
        </ul>
    </div>
    <c:if test="${requestScope.actors_list != null}">
        <h2><fmt:message key="label.search_actor"/></h2>
        <c:if test="${sessionScope.actor != null}">
            <div class="content">
            <table>
                <tr>
                    <th><fmt:message key="label.id"/></th>
                    <th><fmt:message key="label.first_name"/></th>
                    <th><fmt:message key="label.last_name"/></th>
                    <th><fmt:message key="label.operation"/></th>
                </tr>
                <tr>
                    <td><c:out value="${sessionScope.actor.actorId}"/></td>
                    <td><c:out value="${sessionScope.actor.firstName}"/></td>
                    <td><c:out value="${sessionScope.actor.lastName}"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="add_actor_to_movie">
                            <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                            <input type="hidden" name="first_name" value="${sessionScope.actor.firstName}">
                            <input type="hidden" name="last_name" value="${sessionScope.actor.lastName}">
                            <button type="submit"><fmt:message key="label.add"/></button>
                        </form>
                    </td>
                </tr>
            </table>
        </c:if>
        <h2><fmt:message key="label.remove_actor"/></h2>
        <c:if test="${requestScope.actors_list != null}">
            <h2><fmt:message key="label.actors"/></h2>
            <table>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="label.first_name"/></th>
                    <th><fmt:message key="label.last_name"/></th>
                    <th><fmt:message key="label.operation"/></th>
                </tr>
                <c:forEach items="${requestScope.actors_list}" var="actors" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td><c:out value="${actors.firstName}"/></td>
                        <td><c:out value="${actors.lastName}"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="remove_actor_from_movie">
                                <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                                <input type="hidden" name="actor_id" value="${actors.actorId}">
                                <button type="submit"><fmt:message key="label.remove"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        </div>
    </c:if>
</div>
</body>
</html>
