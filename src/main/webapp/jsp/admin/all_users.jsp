<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.all_users"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all_users.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
</head>
<header>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</header>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.all_users"/></h2>
    </div>
    <div class="counter">
        <p>(<c:out value="${requestScope.counter}"/>)</p>
    </div>
    <div class="search">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="find_user">
            <input type="text" name="user_name" placeholder="<fmt:message key="label.username"/>">
            <input type="submit" value="<fmt:message key="label.search"/>">
        </form>
    </div>
</div>
<c:choose>
    <c:when test="${sessionScope.blocked_user != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><c:out value="${sessionScope.blocked_user}"/>
                <fmt:message key="label.block_user_success"/>
            </h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="blocked_user" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.unblocked_user != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><c:out value="${sessionScope.unblocked_user}"/>
                <fmt:message key="label.unblock_user_success"/>
            </h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="unblocked_user" scope="session"/>
        </div>
    </c:when>
</c:choose>
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
            <c:forEach items="${requestScope.user_list}" var="users" varStatus="counter">
                <tr>
                    <td><c:out value="${users.userId}"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="show_user_profile">
                            <input type="hidden" name="user_name" value="${users.userName}">
                            <input type="hidden" name="page" value="1">
                            <button type="submit" class="user_name_btn"><c:out value="${users.userName}"/></button>
                        </form>
                    </td>
                    <td><c:out value="${users.email}"/></td>
                    <td><c:out value="${users.role}"/></td>
                    <c:choose>
                        <c:when test="${users.blocked == false}">
                            <td style="color: green"><fmt:message key="label.active"/></td>
                        </c:when>
                        <c:when test="${users.blocked}">
                            <td style="color:red;"><fmt:message key="label.blocked"/></td>
                        </c:when>
                    </c:choose>
                    <td>
                        <div class="right">
                            <a class="button" href="#pop${counter.count}">
                                <button class="change-role-btn"><i class="fa fa-lock"></i></button>
                            </a>
                            <div id="pop${counter.count}" class="overlay1">
                                <div class="pop">
                                    <div class="text">
                                        <h2 style="color:black;"><fmt:message key="label.change_status"/></h2>
                                        <p style="color: black;"><fmt:message key="label.change_status_msg"/></p>
                                    </div>
                                    <div class="buttons">
                                        <c:choose>
                                            <c:when test="${users.blocked}">
                                                <div class="change-status">
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="post">
                                                        <input type="hidden" name="command" value="unblock_user">
                                                        <input type="hidden" name="user_name" value="${users.userName}">
                                                        <button type="submit" class="change-btn"
                                                                style="background-color: rgb(211,211,211); width: 400px; color: black">
                                                            <fmt:message key="label.change"/></button>
                                                    </form>
                                                </div>
                                            </c:when>
                                            <c:when test="${users.blocked == false}">
                                                <div class="change-status">
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="post">
                                                        <input type="hidden" name="command" value="block_user">
                                                        <input type="hidden" name="user_name" value="${users.userName}">
                                                        <button type="submit" class="chang-btn"
                                                                style="background-color: 	rgb(211,211,211);">
                                                            <fmt:message key="label.change"/></button>
                                                    </form>
                                                </div>
                                            </c:when>
                                        </c:choose>
                                        <a class="close"
                                           style="margin-top: -20px; margin-left: 5px"
                                           href="#">
                                            <i class="fa fa-close"
                                               style="margin-top: 3px; color: black">
                                            </i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="change-role">
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="to_edit_user">
                                <input type="hidden" name="user_name" value="${users.userName}">
                                <button type="submit" class="change-role-btn"><i class="fa fa-edit"
                                                                                 style="color: white; font-size: 20px;"></i>
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </c:if>

        <c:if test="${requestScope.user != null}">
            <tbody>
            <tr>
                <td><c:out value="${user.userId}"/></td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_user_profile">
                        <input type="hidden" name="user_name" value="${user.userName}">
                        <input type="hidden" name="page" value="1">
                        <button type="submit" class="user_name_btn"><c:out value="${user.userName}"/></button>
                    </form>
                </td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.role}"/></td>
                <c:choose>
                    <c:when test="${user.blocked == false}">
                        <td style="color: #66cc33"><fmt:message key="label.active"/></td>
                    </c:when>
                    <c:when test="${user.blocked}">
                        <td style="color: #eb5757"><fmt:message key="label.blocked"/></td>
                    </c:when>
                </c:choose>
                <td>
                    <div class="right">
                        <a class="button" href="#pop">
                            <button class="change-role-btn"><i class="fa fa-lock"></i></button>
                        </a>
                        <div id="pop" class="overlay1">
                            <div class="pop">
                                <div class="text">
                                    <h2 style="color:black;"><fmt:message key="label.change_status"/></h2>
                                    <p style="color: black;"><fmt:message key="label.change_status_msg"/></p>
                                </div>
                                <div class="buttons">
                                    <c:choose>
                                        <c:when test="${user.blocked}">
                                            <div class="change-status">
                                                <form action="${pageContext.request.contextPath}/controller"
                                                      method="post">
                                                    <input type="hidden" name="command" value="unblock_user">
                                                    <input type="hidden" name="user_name" value="${user.userName}">
                                                    <button type="submit" class="change-btn"
                                                            style="background-color: 	rgb(211,211,211);"><fmt:message
                                                            key="label.change"/></button>
                                                </form>
                                            </div>
                                        </c:when>
                                        <c:when test="${user.blocked == false}">
                                            <div class="change-status">
                                                <form action="${pageContext.request.contextPath}/controller"
                                                      method="post">
                                                    <input type="hidden" name="command" value="block_user">
                                                    <input type="hidden" name="user_name" value="${user.userName}">
                                                    <button type="submit" class="chang-btn"
                                                            style="background-color: 	rgb(211,211,211);"><fmt:message
                                                            key="label.change"/></button>
                                                </form>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                    <div class="dismiss">
                                        <button style="background-color: 	rgb(211,211,211);"><a class="close"
                                                                                                   href="#"><fmt:message
                                                key="label.close"/></a></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="change-role">
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="to_edit_user">
                            <input type="hidden" name="user_name" value="${user.userName}">
                            <button type="submit" class="change-role-btn"><i class="fa fa-edit"
                                                                             style="color: white; font-size: 20px;"></i>
                            </button>
                        </form>
                    </div>
                </td>
            </tr>
            </tbody>
        </c:if>
    </table>
</div>
<script type="text/javascript">
    $(".close").click(function () {
        $(this)
            .parent(".alert")
            .fadeOut();
    });
</script>
</body>
</html>
