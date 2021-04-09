<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>admin_cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_cabinet.css">
</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</header>
<body>
<div class="main-container">
    <h2>Upload Movie</h2>
    <hr>
    <div class="content">
        <form>
            <input type="hidden" name="command" value="upload_movie">
            <label for="title">Title</label>
            <input type="text" id="title">
            <label for="country">Country</label>
            <input title="text" id="country">
        </form>
    </div>
</div>
</body>
</html>
