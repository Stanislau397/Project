<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.add_actor_to_movie"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add_actors.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</head>
<body>
<div class="main-content">
    <div class="title">
        <h2><fmt:message key="label.add_actor"/></h2>
    </div>
    <div class="search">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="find_actor">
            <input type="text" name="first_name" placeholder="<fmt:message key="label.first_name"/>">
            <input type="text" name="last_name" placeholder="<fmt:message key="label.last_name"/>">
            <button type="submit"><fmt:message key="label.search"/></button>
        </form>
    </div>
    <c:if test="${sessionScope.actor != null}">
    <div class="key-word-actor">
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
</body>
</html>
