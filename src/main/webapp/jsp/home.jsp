<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
</head>
<header>
    <jsp:include page="static/header.jsp"/>
</header>
<body>
<select id="selector" class="selector">
    <option selected>Filter</option>
    <option>Год</option>
    <option>Year</option>
</select>
<div class="year-container" id="year-container">
    <button>Year</button>
    <ul>
        <li>1995</li>
        <li>2005</li>
    </ul>
</div>
</body>
<script>
    $("#year-container").hide();
    $("#selector").change(function(){
        if($(this).val()==="Год")
        {
            $("#year-container").show();
        } else {
            $("#year-container").hide();
        }
    });
</script>
</html>
