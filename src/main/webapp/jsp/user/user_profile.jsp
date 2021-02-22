<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user_profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user_profile.css">

</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
</header>
<body>
<div class="side-box">
    <img src="${pageContext.request.contextPath}/css/image/image.png"/>
</div>
<div class="middle-box">
    <ul>
        <li class="name">
            <c:out value="${sessionScope.user_name}"/>
        </li>
        <li class="settings">
            <a href="${pageContext.request.contextPath}/jsp/user/account_settings.jsp">Edit settings</a>
        </li>
    </ul>
    <ul>
        <li class="ratings">
            3 Ratings
        </li>
        <li class="review">
            <c:out value="${requestScope.comment} Comments"/>
        </li>
    </ul>
</div>
<hr>
<p>My Scores</p>
</body>
</html>
