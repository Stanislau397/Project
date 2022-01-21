<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.edit_user"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/edit_user.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.edit_user"/></h2>
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
    <c:when test="${sessionScope.changed_role != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3>
                <fmt:message key="label.change_role_success">
                    <fmt:param value="${sessionScope.changed_role}"/>
                </fmt:message>
            </h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="changed_role" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.comment_removed != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><c:out value="${sessionScope.unblocked_user}"/>
                <fmt:message key="label.comment_removed"/>
            </h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="comment_removed" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.comment_edited != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><c:out value="${sessionScope.unblocked_user}"/>
                <fmt:message key="label.comment_update"/>
            </h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="comment_edited" scope="session"/>
        </div>
    </c:when>
</c:choose>
<div class="main-content" id="blur">
    <div class="head">
        <div class="picture">
            <img src="${requestScope.user.avatar}">
        </div>
        <div class="user-info">
            <ul>
                <li style="color: black"><c:out value="${requestScope.user.userName}"/></li>
                <c:choose>
                    <c:when test="${requestScope.user.status}">
                        <li style="color: red; font-size: 13px">(<fmt:message key="label.banned"/>)</li>
                    </c:when>
                    <c:when test="${requestScope.user.status == false}">
                        <li style="color: #009879; font-size: 13px">(<fmt:message key="label.approved"/>)</li>
                    </c:when>
                </c:choose>
            </ul>
            <p style="color: black">
                <fmt:message key="label.rights"/>
                <c:choose>
                <c:when test="${requestScope.user.role == 'ADMIN'}">
                    <fmt:message key="label.admin"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="label.user_role"/>
                </c:otherwise>
                </c:choose>
        </div>
        <div class="navigation">
            <ul>
                <li>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="to_edit_user">
                        <input type="hidden" name="user_name" value="${requestScope.user.userName}">
                        <button type="submit"><fmt:message key="label.profile"/></button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_user_comments">
                        <input type="hidden" name="user_name" value="${requestScope.user.userName}">
                        <button type="submit"><fmt:message key="label.comment"/></button>
                    </form>
                </li>
            </ul>
        </div>
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
                            <c:when test="${requestScope.user.status}">
                                <div class="change-status">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="unblock_user">
                                        <input type="hidden" name="user_id" value="${requestScope.user.userId}">
                                        <button type="submit" style="background-color: rgb(211,211,211)"><fmt:message
                                                key="label.change"/></button>
                                    </form>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="change-status">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="block_user">
                                        <input type="hidden" name="user_id" value="${requestScope.user.userId}">
                                        <button type="submit" style="background-color: rgb(211,211,211)"><fmt:message
                                                key="label.change"/></button>
                                    </form>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <a class="close"
                           style="margin-top: -20px; margin-left: 5px"
                           href="#">
                            <i class="fa fa-close"
                               style="margin-top: 1px; color: black">
                            </i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${requestScope.movie_list == null}">
            <div class="edit-user-info">
                <h2><fmt:message key="label.profile_details"/></h2>
                <p style="color: white">${requestScope.error}</p>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="change_user_role">
                    <input type="hidden" name="user_id" value="${requestScope.user.userId}">
                    <input type="hidden" name="user_name" value="${requestScope.user.userName}">
                    <ul style="display: inline-block">
                        <li>
                            <label for="user_name"><fmt:message key="label.username"/></label>
                            <p id="user_name" class="info"><c:out value="${requestScope.user.userName}"/></p>
                        </li>
                        <li>
                            <label for="email"><fmt:message key="label.email"/></label>
                            <p id="email" class="info"><c:out value="${requestScope.user.email}"/></p>
                        </li>
                        <li>
                            <label for="rights" style="margin-top: 2px"><fmt:message key="label.rights"/></label>
                            <select id="rights" name="role">
                                <c:choose>
                                    <c:when test="${requestScope.user.role == 'ADMIN'}">
                                        <option selected value="ADMIN">
                                            <fmt:message key="label.admin"/></option>
                                        <option value="USER"><fmt:message key="label.user_role"/></option>
                                    </c:when>
                                    <c:when test="${requestScope.user.role != 'ADMIN'}">
                                        <option selected value="USER">
                                            <fmt:message key="label.user_role"/>
                                        </option>
                                        <option value="ADMIN"><fmt:message key="label.admin"/></option>
                                    </c:when>
                                </c:choose>
                            </select>
                        </li>
                        <li>
                        </li>
                    </ul>
                    <button type="submit"><fmt:message key="label.save"/></button>
                </form>
            </div>
        </c:when>
    </c:choose>
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
