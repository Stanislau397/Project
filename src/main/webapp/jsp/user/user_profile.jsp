<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user_profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile1.css">

</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
</header>
<body>
<div class="side">
    <img class="image" src="${pageContext.request.contextPath}/css/image/image.png">
</div>
<div class="middle">
    <p class="name">${user_name}</p>
    <hr>
    <p class="email">${user_email}</p>
    <hr>
    <p class="settings">
        <a class href="${pageContext.request.contextPath}/jsp/user/account_settings.jsp">Edit settings</a>
    </p>
    <hr>
</div>
</body>
</html>
