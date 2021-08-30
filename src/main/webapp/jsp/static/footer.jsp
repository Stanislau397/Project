<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>footer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
</head>
<body>
<footer>
    <div class="top-1">
        <ul>
            <li><a><i class="icon ion-social-instagram"></i></a></li>
            <li><a><i class="icon ion-social-snapchat"></i></a></li>
            <li><a><i class="icon ion-social-twitter"></i></a></li>
            <li><a><i class="icon ion-social-facebook"></i></a></li>
        </ul>
    </div>
    <div class="bottom-1">
        <ul>
            <li><a href="${pageContext.request.contextPath}/controller?command=open_home_page"><fmt:message key="text.label.main"/></a></li>
            <li><a><fmt:message key="label.suggestions"/></a></li>
            <li><a><fmt:message key="label.about_us"/></a></li>
            <li><a><fmt:message key="label.terms"/></a></li>
            <li><a><fmt:message key="label.advertising"/></a></li>
        </ul>
    </div>
</footer>
</body>
</html>
