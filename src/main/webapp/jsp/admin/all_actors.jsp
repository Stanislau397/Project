<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.actors"/></title>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.actors"/></h2>
    </div>
    <c:if test="${requestScope.counter != null}">
        <div class="counter">
            <p>(<c:out value="${requestScope.counter}"/>)</p>
        </div>
    </c:if>
    <div class="add">
        <a class="button" href="${pageContext.request.contextPath}/jsp/admin/add_actor.jsp">
            <button class="add-btn"><fmt:message key="label.add_actor"/></button>
        </a>
    </div>
    <div class="search">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="search_actor">
            <input type="text" name="key_word" placeholder="<fmt:message key="label.find_actor"/>">
            <input type="submit" value="<fmt:message key="label.search"/>">
        </form>
    </div>
</div>
<c:choose>
    <c:when test="${sessionScope.actor_removed != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><fmt:message key="label.remove_actor_success"/></h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="actor_removed" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.error != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3><fmt:message key="label.remove_actor_error"/></h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="error" scope="session"/>
        </div>
    </c:when>
</c:choose>
<div class="main-content">
    <table class="content-table">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="label.first_name"/></th>
            <th><fmt:message key="label.last_name"/></th>
            <th><fmt:message key="label.id"/></th>
            <th><fmt:message key="label.operation"/></th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${requestScope.actors_list != null}">
                <c:forEach items="${requestScope.actors_list}" var="allActors" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td><c:out value="${allActors.firstName}"/></td>
                        <td><c:out value="${allActors.lastName}"/></td>
                        <td><c:out value="${allActors.actorId}"/></td>
                        <td>
                            <div class="edit-actor">
                                <a class="button"
                                   href="${pageContext.request.contextPath}/controller?command=to_edit_actor&actor_id=${allActors.actorId}">
                                    <button class="edit-btn"><i class="fa fa-edit"></i></button>
                                </a>
                            </div>
                            <div class="remove-actor">
                                <a class="button" href="#popup${counter.count}">
                                    <button class="remove-btn"><i class="fa fa-trash"></i></button>
                                </a>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <div id="popup${counter.count}" class="overlay3">
                                        <div class="pop">
                                            <input type="hidden" name="command" value="remove_actor">
                                            <input type="hidden" name="actor_id" value="${allActors.actorId}">
                                            <div class="text">
                                                <h2 style="color: black"><fmt:message key="label.delete_actor"/></h2>
                                                <p class="text"><fmt:message key="label.delete_actor_msg"/></p>
                                            </div>
                                            <div class="buttons">
                                                <div class="remove">
                                                    <button type="submit" style="background-color: #FFF; color: black"><fmt:message
                                                            key="label.remove"/></button>
                                                </div>
                                                <a class="close"
                                                   style="margin-top: -20px; margin-left: 5px"
                                                   href="#">
                                                    <i class="fa fa-close"
                                                       style="margin-top: 3px; margin-left: 1px; color: black">
                                                    </i>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:when test="${requestScope.actors_by_key_words_list != null}">
                <c:forEach items="${requestScope.actors_by_key_words_list}" var="actorsByKeyWords" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td><c:out value="${actorsByKeyWords.firstName}"/></td>
                        <td><c:out value="${actorsByKeyWords.lastName}"/></td>
                        <td><c:out value="${actorsByKeyWords.actorId}"/></td>
                        <td>
                            <div class="edit-actor">
                                <a class="button"
                                   href="${pageContext.request.contextPath}/controller?command=to_edit_actor&actor_id=${actorsByKeyWords.actorId}">
                                    <button class="edit-btn"><i class="fa fa-edit"></i></button>
                                </a>
                            </div>
                            <div class="remove-actor">
                                <a class="button" href="#popup${counter.count}">
                                    <button class="remove-btn"><i class="fa fa-trash"></i></button>
                                </a>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <div id="popup${counter.count}" class="overlay3">
                                        <div class="pop">
                                            <input type="hidden" name="command" value="remove_actor">
                                            <input type="hidden" name="actor_id" value="${actorsByKeyWords.actorId}">
                                            <div class="text">
                                                <h2 style="color: black"><fmt:message key="label.delete_actor"/></h2>
                                                <p class="text"><fmt:message key="label.delete_actor_msg"/></p>
                                            </div>
                                            <div class="buttons">
                                                <div class="remove">
                                                    <button type="submit" style="background-color: #FFF; color: black"><fmt:message
                                                            key="label.remove"/></button>
                                                </div>
                                                    <a class="close"
                                                       style="margin-top: -20px; margin-left: 5px"
                                                       href="#">
                                                        <i class="fa fa-close"
                                                           style="margin-top: 3px; margin-left: 1px; color: black">
                                                        </i>
                                                    </a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
        </c:choose>
        </tbody>
    </table>
    <div class="pagination">
        <div class="title">
            <p><fmt:message key="label.page"/></p>
        </div>
        <div class="pagination-number">
            <c:forEach begin="1" end="${requestScope.pages}" varStatus="loop">
                <c:choose>
                    <c:when test="${requestScope.page_number == loop.count}">
                        <a style="color: #2f80ed"
                           href="${pageContext.request.contextPath}/controller?command=display_all_actors&page=${loop.count}">${loop.count}
                        </a>
                    </c:when>
                    <c:when test="${requestScope.page_number != loop.count}">
                        <a href="${pageContext.request.contextPath}/controller?command=display_all_actors&page=${loop.count}">${loop.count}</a>
                    </c:when>
                </c:choose>
            </c:forEach>
        </div>
    </div>
</div>
<br>
<br>
<script type="text/javascript">
    $(".close").click(function () {
        $(this)
            .parent(".alert")
            .fadeOut();
    });
</script>
</body>
</html>
