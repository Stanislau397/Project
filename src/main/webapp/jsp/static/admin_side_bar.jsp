<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="label.admin_side_bar"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_side_bar.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'>
    </script>
</head>
<body>
<div class="header">

</div>
<nav class="sidebar">
    <div class="text">Admin Panel</div>
    <ul class="drop">
        <li class="active"><a href="${pageContext.request.contextPath}/jsp/admin/admin_cabinet.jsp">Dashboard</a></li>
        <li class="active"><a href="${pageContext.request.contextPath}/controller?command=open_home_page"><fmt:message
                key="text.label.main"/></a></li>
        <li class="active">
            <a href="#" class="feat-btn"><fmt:message key="label.movies"/>
                <span class="fa fa-caret-down first"></span>
            </a>
            <ul class="feat-show">
                <li><a href="${pageContext.request.contextPath}/controller?command=to_upload_movie"><fmt:message
                        key="label.upload_movie"/></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=all_movies"><fmt:message
                        key="label.edit"/></a></li>
            </ul>
        </li>

        <li class="active">
            <a href="#" class="actors-btn"><fmt:message key="label.actors"/>
                <span class="fa fa-caret-down third"></span>
            </a>
            <ul class="actors-show">
                <li><a href="${pageContext.request.contextPath}/jsp/admin/add_actor.jsp"><fmt:message
                        key="label.add_actor"/></a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/admin/add_director.jsp"><fmt:message
                        key="label.add_director"/></a></li>
            </ul>
        </li>

        <li class="active">
            <a href="#" class="serv-btn"><fmt:message key="label.user"/>
                <span class="fa fa-caret-down second"></span>
            </a>
            <ul class="serv-show">
                <li><a href="${pageContext.request.contextPath}/controller?command=all_users"><fmt:message
                        key="label.all_users"/></a></li>
            </ul>
        </li>
        <li class="active"><a
                href="${pageContext.request.contextPath}/controller?command=show_user_profile"><fmt:message
                key="label.profile"/></a></li>
        <li class="active"><a href="${pageContext.request.contextPath}/controller?command=sign_out"><fmt:message
                key="label.logout"/></a></li>
    </ul>
</nav>
</body>
<script type="text/javascript">
    $('.feat-btn').click(function () {
        $('nav ul .feat-show').toggleClass("show");
        $('nav ul .first').toggleClass("rotate");
    });
    $('.serv-btn').click(function () {
        $('nav ul .serv-show').toggleClass("show1");
        $('nav ul .second').toggleClass("rotate");
    });
    $('.actors-btn').click(function () {
        $('nav ul .actors-show').toggleClass("show2");
        $('nav ul .third').toggleClass("rotate");
    });
</script>
</body>
</html>