<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.register"/></title>
    <jsp:include page="static/header.jsp"/>
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
                       placeholder="<fmt:message key="label.username"/>"
                       pattern="^[a-zA-Z0-9](_(?!(\.|_))|\.(?!(_|\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$" required/>
            </div>
            <div>
                <small id="name_error"></small>
            </div>

            <div class="text-box">
                <i class="fa fa-envelope-square" aria-hidden="true"></i>
                <input type="email" id="email" name="email" placeholder="<fmt:message key="label.email"/>"
                       pattern="^[^@]+@[^@]+\.[^@]+$" required>
            </div>
            <div>
                <small id="email_error"></small>
            </div>

            <div class="text-box" id="pas">
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" id="password" name="password"
                       placeholder="<fmt:message key="label.password"/>"
                       pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required/>
            </div>
            <div>
                <small id="password_error"></small>
            </div>

            <div class="text-box">
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" id="confirm_password" name="confirm_password"
                       placeholder="<fmt:message key="label.confirm_password"/>"
                pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required/>
            </div>
            <div>
                <small id="confirm_error"></small>
            </div>

            <input class="btn" id="submit" type="submit" value="<fmt:message key="register.label.register"/>"/>
        </form>
    </div>
</div>
</body>
</html>