<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.admin_side_bar"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_side_bar.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body>
<div class="side-bar-container">
    <div class="inside-container">
        <button class="btn"><fmt:message key="label.movies"/><i class="fa fa-caret-down"></i></button>
        <div class="movie-content">
            <p><a class="a-movie" href="${pageContext.request.contextPath}/controller?command=to_upload_movie"><fmt:message key="label.upload_movie"/></a></p>
            <p><a class="a-movie" href="${pageContext.request.contextPath}/controller?command=all_movies"><fmt:message key="label.edit"/></a></p>
            <p><a class="a-movie" href="${pageContext.request.contextPath}/controller?command=to_genres"><fmt:message key="label.edit_delete"/></a></p>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
        $('.movie-content').hide();
        $('.btn').click( function () {
            $('.movie-content').show();
        });
</script>
</html>
