<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user_profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">

</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
</header>
<body>
<div class="wrapper">
    <div class="left">
        <img>
        <h4>Name</h4>
        <p>Role</p>
    </div>
    <div class="right">
        <div class="info">
            <h3>Information</h3>
            <div class="data">
                <h4>Email</h4>
                <p>Kada</p>
                <div class="data">
                    <h4>Date</h4>
                    <p>1995</p>
                </div>
            </div>
            <div class="social_media">
                <ul>
                    <li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
                    <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
