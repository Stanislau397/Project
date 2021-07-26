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
    <style><jsp:include page="/css/register.css"/></style>
</head>
<body>
<div class="form-box">
    <div class="login-box">
        <span id="error" style="color: red">
            ${requestScope.sign_in_error}
        </span>
        <h1><fmt:message key="login.label.authorize"/></h1>
        <form id="login" class="input-group" action="${pageContext.request.contextPath}/controller" method="post">
            <input hidden name="command" value="sign_in">
            <div class="text-box">
                <i class="fa fa-envelope-square" aria-hidden="true"></i>
                <input type="text" name="email" placeholder="<fmt:message key="label.email"/>"/>
            </div>

            <div class="text-box">
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" name="password" placeholder="<fmt:message key="label.password"/>"/>
            </div>

            <input class="btn" type="submit" value="<fmt:message key="label.login"/>"/>
        </form>
        <div>
            <a href="${pageContext.request.contextPath}/jsp/register.jsp" style="color: #1a191f"><fmt:message key="label.register"/> </a>
        </div>
    </div>
</div>
</body>
</html>

