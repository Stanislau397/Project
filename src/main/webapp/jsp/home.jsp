<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/rating.css">
</head>
<header>
    <jsp:include page="static/header.jsp"/>
    <form>
        <select class="select" id="language" name="language" onchange="submit()">
            <option value="en" ${language == 'en' ? 'selected' : ''}>en </option>
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>ru </option>
        </select>
    </form>
</header>
<body>

</body>
</html>
