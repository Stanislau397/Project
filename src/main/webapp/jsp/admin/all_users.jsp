<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.all_users"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all_users.css">
</head>
<header>
    <jsp:include page="admin_cabinet.jsp"/>
</header>
<body>
<div class="search">
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="find_user">
        <input type="text" name="user_name" placeholder="<fmt:message key="label.username"/>">
        <input type="submit" value="<fmt:message key="label.search"/>">
    </form>
</div>
<div class="main-content">
    <table class="content-table">
        <thead>
        <tr>
            <th><fmt:message key="label.id"/></th>
            <th><fmt:message key="label.username"/></th>
            <th><fmt:message key="label.email"/></th>
            <th><fmt:message key="label.role"/></th>
            <th><fmt:message key="label.status"/></th>
            <th><fmt:message key="label.operation"/></th>
        </tr>
        </thead>
        <c:if test="${requestScope.user_list != null}">
            <tbody>
        <c:forEach items="${requestScope.user_list}" var="users">
            <tr>
                <td><c:out value="${users.userId}"/></td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_user_profile">
                        <input type="hidden" name="user_name" value="${users.userName}">
                        <button type="submit" class="user_name_btn"><c:out value="${users.userName}"/></button>
                    </form>
                </td>
                <td><c:out value="${users.email}"/></td>
                <td><c:out value="${users.role}"/></td>
                <c:choose>
                    <c:when test="${users.blocked == false}">
                        <td><fmt:message key="label.active"/></td>
                    </c:when>
                    <c:when test="${users.blocked}">
                        <td><fmt:message key="label.blocked"/></td>
                    </c:when>
                </c:choose>
                <td>
                    <c:choose>
                        <c:when test="${users.blocked == false}">
                            <div class="block-user">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="block_user">
                                    <input type="hidden" name="user_name" value="${users.userName}">
                                    <button type="submit" class="block-btn"><fmt:message key="label.block"/></button>
                                </form>
                            </div>
                            <div class="change-role">
                                <c:if test="${users.role == 'USER'}">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="change_user_role">
                                        <input type="hidden" name="user_name" value="${users.userName}">
                                        <button type="submit" class="change-role-btn"><fmt:message key="label.make_admin"/></button>
                                    </form>
                                </c:if>
                                <c:if test="${users.role == 'ADMIN'}">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="change_user_role">
                                        <input type="hidden" name="user_name" value="${users.userName}">
                                        <button type="submit" class="change-role-btn"><fmt:message key="label.make_user"/></button>
                                    </form>
                                </c:if>
                            </div>
                        </c:when>
                        <c:when test="${users.blocked}">
                            <div class="unblock-user">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="unblock_user">
                                    <input type="hidden" name="user_name" value="${users.userName}">
                                    <button type="submit" class="unblock-btn" style="background-color: #66cc33"><fmt:message key="label.unblock"/></button>
                                </form>
                            </div>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        </c:if>

        <c:if test="${requestScope.user != null}">
            <tbody>
                <tr>
                    <td><c:out value="${user.userId}"/></td>
                    <td><c:out value="${user.userName}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <c:choose>
                        <c:when test="${user.blocked == false}">
                            <td><fmt:message key="label.active"/></td>
                        </c:when>
                        <c:when test="${user.blocked}">
                            <td><fmt:message key="label.blocked"/></td>
                        </c:when>
                    </c:choose>
                    <td>
                        <c:choose>
                            <c:when test="${user.blocked == false}">
                                <div class="block-user">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="block_user">
                                        <input type="hidden" name="user_name" value="${user.userName}">
                                        <button type="submit" class="block-btn"><fmt:message key="label.block"/></button>
                                    </form>
                                </div>
                                <div class="change-role">
                                    <c:if test="${user.role == 'USER'}">
                                        <form action="${pageContext.request.contextPath}/controller" method="post">
                                            <input type="hidden" name="command" value="change_user_role">
                                            <input type="hidden" name="user_name" value="${user.userName}">
                                            <button type="submit" class="change-role-btn"><fmt:message key="label.make_admin"/></button>
                                        </form>
                                    </c:if>
                                    <c:if test="${user.role == 'ADMIN'}">
                                        <form action="${pageContext.request.contextPath}/controller" method="post">
                                            <input type="hidden" name="command" value="change_user_role">
                                            <input type="hidden" name="user_name" value="${user.userName}">
                                            <button type="submit" class="change-role-btn"><fmt:message key="label.make_user"/></button>
                                        </form>
                                    </c:if>
                                </div>
                            </c:when>
                            <c:when test="${user.blocked}">
                                <div class="unblock-user">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="unblock_user">
                                        <input type="hidden" name="user_name" value="${user.userName}">
                                        <button type="submit" class="unblock-btn" style="background-color: #66cc33"><fmt:message key="label.unblock"/></button>
                                    </form>
                                </div>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </tbody>
        </c:if>
    </table>
</div>
</body>
</html>
