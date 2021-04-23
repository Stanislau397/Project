<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>admin side bar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_side_bar.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body>
<div class="side-bar-container">
    <div class="content">
        <a href="${pageContext.request.contextPath}/controller?command=to_upload_movie"><i class="fa fa-upload"></i> Upload Movie</a>
    </div>
</div>
</body>
</html>
