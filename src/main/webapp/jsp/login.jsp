<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.login"/></title>
    <jsp:include page="static/header.jsp"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style><jsp:include page="/css/register.css"/></style>
</head>
<body>
<c:choose>
    <c:when test="${requestScope.sign_in_error != null}">
        <div class="alert">
            <h3><fmt:message key="label.login_error"/></h3>
            <a class="close"><i style="color: black" class="fa fa-close"></i></a>
        </div>
    </c:when>
    <c:when test="${requestScope.register_success != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><fmt:message key="label.register_success"/></h3>
            <a class="close"><i style="color: black" class="fa fa-close"></i></a>
        </div>
    </c:when>
</c:choose>
<div class="form-box">
    <div class="login-box">
        <h1><fmt:message key="login.label.authorize"/></h1>
        <form id="login" class="input-group" action="${pageContext.request.contextPath}/controller" method="post">
            <input hidden name="command" value="sign_in">
            <div class="text-box">
                <i class="fa fa-envelope-square" aria-hidden="true"></i>
                <input type="text" name="email"
                       placeholder="<fmt:message key="label.email"/>"
                       pattern="^[^@]+@[^@]+\.[^@]+$"
                       required/>
            </div>

            <div class="text-box">
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" name="password"
                       placeholder="<fmt:message key="label.password"/>"
                       pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}"
                       title="<fmt:message key="label.password_error"/>"
                       required/>
            </div>

            <input class="btn" type="submit" value="<fmt:message key="label.login"/>"/>
        </form>
        <div>
            <button class="register-btn"><a href="${pageContext.request.contextPath}/jsp/register.jsp" style="color: #1a191f"><fmt:message key="label.register"/> </a></button>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(".close").click(function() {
        $(this)
            .parent(".alert")
            .fadeOut();
    });
</script>
</body>
</html>

