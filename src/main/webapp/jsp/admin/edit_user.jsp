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
<div class="main-content" id="blur">
    <div class="head">
        <div class="picture">
            <img src="${pageContext.request.contextPath}${requestScope.user.avatar}">
        </div>
        <div class="user-info">
            <ul>
                <li style="color: black"><c:out value="${requestScope.user.userName}"/></li>
                <c:choose>
                    <c:when test="${requestScope.user.blocked}">
                        <li style="color: red; font-size: 13px">(<fmt:message key="label.banned"/>)</li>
                    </c:when>
                    <c:when test="${requestScope.user.blocked == false}">
                        <li style="color: #009879; font-size: 13px">(<fmt:message key="label.approved"/>)</li>
                    </c:when>
                </c:choose>
            </ul>
            <p style="color: black"><fmt:message key="label.rights"/> <c:out value="${requestScope.user.role}"/></p>
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
                <a class="button" href="#pop"><button class="change-role-btn"><i class="fa fa-lock"></i></button></a>
                <div id="pop" class="overlay1">
                    <div class="pop">
                        <div class="text">
                            <h2 style="color:black;"><fmt:message key="label.change_status"/></h2>
                            <p style="color: black;"><fmt:message key="label.change_status_msg"/></p>
                        </div>
                        <div class="buttons">
                            <c:choose>
                                <c:when test="${requestScope.user.blocked}">
                                    <div class="change-status">
                                        <form action="${pageContext.request.contextPath}/controller" method="post">
                                            <input type="hidden" name="command" value="unblock_user">
                                            <input type="hidden" name="user_name" value="${requestScope.user.userName}">
                                            <button type="submit" style="background-color: rgb(211,211,211)"><fmt:message key="label.change"/></button>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${requestScope.user.blocked == false}">
                                    <div class="change-status">
                                        <form action="${pageContext.request.contextPath}/controller" method="post">
                                            <input type="hidden" name="command" value="block_user">
                                            <input type="hidden" name="user_name" value="${requestScope.user.userName}">
                                            <button type="submit" style="background-color: rgb(211,211,211)"><fmt:message key="label.change"/></button>
                                        </form>
                                    </div>
                                </c:when>
                            </c:choose>
                            <div class="dismiss">
                                <button style="background: rgb(211,211,211);"><a class="close" href="#"><fmt:message key="label.close"/></a></button>
                            </div>
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
                    <input type="hidden" name="command" value="update_email_and_role">
                    <input type="hidden" name="user_id" value="${requestScope.user.userId}">
                    <ul style="display: inline-block">
                        <li>
                            <label for="email"><fmt:message key="label.email"/></label>
                            <input type="text" name="email" id="email" value="${requestScope.user.email}"
                                   pattern="^[^@]+@[^@]+\.[^@]+$">
                        </li>
                        <li>
                            <label for="rights" style="margin-top: 20px"><fmt:message key="label.rights"/></label>
                            <select id="rights" name="role">
                                <c:choose>
                                    <c:when test="${requestScope.user.role == 'ADMIN'}">
                                        <option selected value="ADMIN"><c:out
                                                value="${requestScope.user.role}"/></option>
                                        <option value="USER">USER</option>
                                    </c:when>
                                    <c:when test="${requestScope.user.role != 'ADMIN'}">
                                        <option selected value="USER"><c:out
                                                value="${requestScope.user.role}"/></option>
                                        <option value="ADMIN">ADMIN</option>
                                    </c:when>
                                </c:choose>
                            </select>
                        </li>
                        <li>
                        </li>
                    </ul>
                    <button type="submit" style="margin-top: 90px;"><fmt:message key="label.save"/></button>
                </form>
            </div>
        </c:when>
        <c:when test="${requestScope.movie_list != null}">
            <table class="content-table">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="label.movie"/></th>
                    <th><fmt:message key="label.username"/></th>
                    <th><fmt:message key="label.text"/></th>
                    <th><fmt:message key="label.likes_dislikes"/></th>
                    <th><fmt:message key="label.operation"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.movie_list}" var="comments" varStatus="counter">
                    <tr>
                        <td><c:out value="${counter.count}"/></td>
                        <td><c:out value="${comments.title}"/></td>
                        <td><c:out value="${comments.comment.userName}"/></td>
                        <td style="max-width: 280px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
                            <c:out value="${comments.comment.text}"/></td>
                        <td><c:out value="${comments.comment.commentUpVotes}"/> / <c:out
                                value="${comments.comment.commentDownVotes}"/></td>
                        <td>
                            <div class="remove">
                                <a class="button" href="#pop${counter.count}">
                                    <button class="edit-btn" style="background-color: #eb5757"><i
                                            class="fa fa-trash"></i></button>
                                </a>
                                <div id="pop${counter.count}" class="overlay1">
                                    <div class="pop">
                                        <div class="text">
                                            <h2><fmt:message key="label.comment_delete1"/></h2>
                                            <p><fmt:message key="label.comment_delete"/></p>
                                        </div>
                                        <div class="buttons">
                                            <div class="remove">
                                                <form action="${pageContext.request.contextPath}/controller"
                                                      method="post">
                                                    <input type="hidden" name="command" value="remove_comment">
                                                    <input type="hidden" name="user_name"
                                                           value="${requestScope.user.userName}">
                                                    <input type="hidden" name="comment"
                                                           value="${comments.comment.text}">
                                                    <input type="hidden" name="movie_id" value="${comments.movieId}">
                                                    <button type="submit"><fmt:message key="label.remove"/></button>
                                                </form>
                                            </div>
                                            <div class="dismiss">
                                                <button><a class="close" href="#"><fmt:message key="label.close"/></a>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="edit">
                                <a class="button" href="#popup${counter.count}">
                                    <button class="edit-btn"><i class="fa fa-edit" style="margin-left: 2px;
margin-top: 2px"></i></button>
                                </a>
                                <div id="popup${counter.count}" class="overlay">
                                    <div class="popup">
                                        <div class="info">
                                            <div class="picture">
                                                <img src="${pageContext.request.contextPath}/css/image/avatar/default_avatar.png">
                                            </div>
                                            <div class="user-info">
                                                <p><c:out value="${comments.comment.userName}"/></p>
                                                <p><c:out value="${comments.comment.postDate}"/></p>
                                            </div>
                                        </div>
                                        <div class="text">
                                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                                <input type="hidden" name="command" value="edit_comment">
                                                <input type="hidden" name="user_name"
                                                       value="${comments.comment.userName}">
                                                <input type="hidden" name="text" value="${comments.comment.text}">
                                                <textarea name="updated_text"><c:out
                                                        value="${comments.comment.text}"/></textarea>
                                                <button type="submit"><fmt:message key="label.save"/></button>
                                            </form>
                                        </div>
                                        <a class="close" href="#">&times;</a>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
    </c:choose>
</div>
</body>
</html>
