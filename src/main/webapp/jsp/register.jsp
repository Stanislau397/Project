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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<c:if test="${requestScope.register_success != null}">
    <div class="alert" style="background-color: #66cc33">
        <h3><fmt:message key="label.register_success"/></h3>
        <a class="close"><i style="color: black" class="fa fa-close"></i></a>
    </div>
</c:if>
<div class="form-box">
    <div class="login-box">
        <h1><fmt:message key="label.register"/></h1>

        <form action="${pageContext.request.contextPath}/controller" id="register" class="input-group" method="post">
            <input hidden name="command" value="register">
            <div class="text-box" id="user-name-text-box">
                <i class="fa fa-user" aria-hidden="true"></i>
                <input type="text" id="user_name" name="user_name"
                       placeholder="<fmt:message key="label.username"/>"
                       required/>
            </div>
            <div class="arrow-5 arrow-5-top" id="user-name-error">
                <fmt:message key="label.user_name_error"/>
            </div>

            <div class="text-box" id="email-text-box">
                <i class="fa fa-envelope-square" aria-hidden="true"></i>
                <input type="email" id="email" name="email" placeholder="<fmt:message key="label.email"/>"
                       required>
            </div>
            <div class="arrow-5 arrow-5-top" id="email-error">
                <fmt:message key="label.email_error"/>
            </div>

            <div class="text-box" id="password-text-box">
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" id="password" name="password"
                       placeholder="<fmt:message key="label.password"/>"
                       required/>
            </div>
            <div class="arrow-5 arrow-5-top" id="password-error">
                <fmt:message key="label.password_error"/>
            </div>

            <div class="text-box" id="confirm-password-text-box">
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" id="confirm_password" name="confirm_password"
                       placeholder="<fmt:message key="label.confirm_password"/>"
                       required/>
            </div>
            <div class="arrow-5 arrow-5-top" id="confirm-password-error">
                <fmt:message key="label.confirm_password_error"/>
            </div>

            <input class="btn" id="submit" type="submit" value="<fmt:message key="register.label.register"/>"/>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(function () {

        $('#user-name-error').hide();
        $('#email-error').hide();
        $('#password-error').hide();
        $('#confirm-password-error').hide();

        $("#user_name").focusout(function () {
            validateUserName();
        });

        $("#email").focusout(function () {
            validateEmail();
        });

        $("#password").focusout(function () {
            validatePassword();
        });

        $('#confirm_password').focusout( function () {
            validateConfirmPassword()
        })

        function validateUserName() {
            var user_name = $('#user_name').val();
            var user_name_regex = '^[a-zA-Z0-9](_(?!(\.|_))|\.(?!(_|\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$'
            if (user_name.match(user_name_regex)) {
                $('#user-name-text-box').css('borderBottom', '1px solid green');
                $('#user-name-error').hide();
            } else if (user_name === '') {
                $('#user-name-text-box').css('borderBottom', '1px solid black');
                $('#user-name-error').hide();
            } else {
                $('#user-name-text-box').css('borderBottom', '1px solid red');
                $('#user-name-error').show();
            }
        }

        function validateEmail() {
            var email = $('#email').val();
            var email_regex = '^[^@]+@[^@]+\.[^@]+$';
            if (email.match(email_regex)) {
                $('#email-text-box').css('borderBottom', '1px solid green');
                $('#email-error').hide();
            } else if (email === '') {
                $('#email-text-box').css('borderBottom', '1px solid black');
                $('#email-error').hide();
            } else {
                $('#email-text-box').css('borderBottom', '1px solid red');
                $('#email-error').show();
            }
        }

        function validatePassword() {
            var password = $('#password').val();
            var password_regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
            if (password.match(password_regex)) {
                $('#password-text-box').css('borderBottom', '1px solid green');
                $('#password-error').hide()
            } else if (password === '') {
                $('#password-text-box').css('borderBottom', '1px solid black');
                $('#password-error').hide()
            } else {
                $('#password-text-box').css('borderBottom', '1px solid red');
                $('#password-error').show();
            }
        }

        function validateConfirmPassword() {
            var password = $('#password').val();
            var confirm_password = $('#confirm_password').val();
            if (password === confirm_password) {
                $('#confirm-password-text-box').css('borderBottom', '1px solid green');
                $('#confirm-password-error').hide();
                $('.btn').prop('disabled', false);
            } else if (confirm_password === '') {
                $('#confirm-password-text-box').css('borderBottom', '1px solid black');
                $('#confirm-password-error').hide();
                $('.btn').prop('disabled', true);
            } else {
                $('#confirm-password-text-box').css('borderBottom', '1px solid red');
                $('#confirm-password-error').show();
                $('.btn').prop('disabled', true);
            }
        }
    })
</script>
</body>
</html>