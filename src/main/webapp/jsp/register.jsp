<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="/property/text"/>
<html lang="${language}">
<head>
    <title><fmt:message key="label.register"/></title>
    <link rel="stylesheet" href="../css/register.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
<div class="form-box">
    <div class="login-box">
        <h1><fmt:message key="label.register"/></h1>

        <form action="${pageContext.request.contextPath}/controller" id="register" class="input-group" method="post">
            <input hidden name="command" value="register">
            <div class="text-box">
                <i class="fa fa-user" aria-hidden="true"></i>
                <input type="text" id="user_name" name="user_name"
                       placeholder="<fmt:message key="label.username"/>"/>
            </div>
            <div>
                <small id="name_error"></small>
            </div>

            <div class="text-box">
                <i class="fa fa-envelope-square" aria-hidden="true"></i>
                <input type="text" id="email" name="email" placeholder="<fmt:message key="label.email"/>">
            </div>
            <div>
                <small id="email_error"></small>
            </div>

            <div class="text-box" id="pas">
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" id="password" name="password"
                       placeholder="<fmt:message key="label.password"/>"/>
            </div>
            <div>
                <small id="password_error"></small>
            </div>

            <div class="text-box">
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" id="confirm_password" name="confirm_password"
                       placeholder="<fmt:message key="label.confirm_password"/>"/>
            </div>
            <div>
                <small id="confirm_error"></small>
            </div>

            <input class="btn" id="submit" type="submit" value="<fmt:message key="register.label.register"/>"/>

        </form>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>