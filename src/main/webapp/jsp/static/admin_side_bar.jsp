<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/property/text" />
<html lang="${language}">
<head>
    <title>side_bar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bar_side.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
<div class="wrapper">
    <div class="sidenav">
        <h1>
            <fmt:message key="label.admin"/>
        </h1>
        <ul>
            <li><a href="${pageContext.request.contextPath}/jsp/home.jsp"><i class="fa fa-home"></i>
            <fmt:message key="text.label.main"/> </a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/admin/block_user.jsp">
                <i class="fa fa-ban"></i>
                <fmt:message key="label.block"/> </a></li>

            <li><a>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input hidden name="command" value="find_all_users">
                    <i class="fa fa-user"></i>
                    <button class="btn" type="submit" ><fmt:message key="label.all"/> </button>
                </form>
            </a></li>

            <li><a href="${pageContext.request.contextPath}/jsp/admin/change_password.jsp"><i class="fa fa-password"></i>change password</a></li>

            <li><a>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input hidden name="command" value="sign_out">
                    <i class="fa fa-sign-out"></i>
                    <button class="btn" type="submit" ><fmt:message key="label.logout"/> </button>
                </form>
            </a></li>
        </ul>
    </div>
</div>
</body>
</html>
