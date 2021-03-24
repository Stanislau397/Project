<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.login"/></title>
    <jsp:include page="static/header.jsp"/>
    <link rel="stylesheet" href="../css/register.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
<div class="form-box">
    <div class="login-box">
        <h1><fmt:message key="login.label.authorize"/></h1>
        <c:if test="${sessionScope.sign_in_error != null}">
            <span style="color: red"><c:out value="${sign_in_error}">
                ${sign_in_error}
            </c:out></span>
        </c:if>
        <form id="login" class="input-group" action="${pageContext.request.contextPath}/controller" method="post">
            <input hidden name="command" value="sign_in">
            <div class="text-box">
                <i class="fa fa-envelope-square" aria-hidden="true"></i>
                <input type="text" name="email" placeholder="<fmt:message key="label.email"/>"/>
            </div>

            <div class="text-box" >
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" name="password" placeholder="<fmt:message key="label.password"/>"/>
            </div>

            <input class="btn" type="submit"  value="<fmt:message key="label.login"/>"/>
        </form>
        <div>
            <a href="${pageContext.request.contextPath}/jsp/register.jsp"><fmt:message key="label.register"/> </a>
        </div>
    </div>
</div>
</body>
</html>

