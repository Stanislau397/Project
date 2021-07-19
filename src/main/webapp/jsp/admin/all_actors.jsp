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
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.actors"/></h2>
    </div>
    <div class="counter">
        <c:choose>
            <c:when test="${requestScope.actors_list != null}">
                <p>(<c:out value="${requestScope.actors_list.size()}"/>)</p>
            </c:when>
            <c:when test="${requestScope.actors_by_key_words_list != null}">
                <p>(<c:out value="${requestScope.actors_by_key_words_list.size()}"/>)</p>
            </c:when>
        </c:choose>
    </div>
    <div class="search">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="search_actor">
            <input type="text" name="key_word" placeholder="<fmt:message key="label.find_actor"/>">
            <input type="submit" value="<fmt:message key="label.search"/>">
        </form>
    </div>
</div>
<div class="main-content">
    <div class="add">
        <a class="button" href="${pageContext.request.contextPath}/jsp/admin/add_actor.jsp">
            <button class="add-btn"><fmt:message key="label.add_actor"/></button>
        </a>
    </div>
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
                                <a class="button" href="${pageContext.request.contextPath}/controller?command=to_edit_actor&actor_id=${allActors.actorId}">
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
                                                    <button type="submit" style="background-color: #FFF;color: black"><fmt:message
                                                            key="label.remove"/></button>
                                                </div>
                                                <div class="dismiss">
                                                    <button style="background-color: #FFF; margin-left: 14px"><a
                                                            class="close" href="#"><fmt:message
                                                            key="label.close"/></a>
                                                    </button>
                                                </div>
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
                                <a class="button" href="${pageContext.request.contextPath}/controller?command=to_edit_actor&actor_id=${actorsByKeyWords.actorId}">
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
                                                <h2 style="color: white"><fmt:message key="label.delete_actor"/></h2>
                                                <p class="text"><fmt:message key="label.delete_actor_msg"/></p>
                                            </div>
                                            <div class="buttons">
                                                <div class="remove">
                                                    <button type="submit" style="background-color: #1a191f"><fmt:message
                                                            key="label.remove"/></button>
                                                </div>
                                                <div class="dismiss">
                                                    <button style="background-color: #1a191f; margin-left: 14px"><a
                                                            class="close" href="#"><fmt:message
                                                            key="label.close"/></a>
                                                    </button>
                                                </div>
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
</div>
</body>
</html>
