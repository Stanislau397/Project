<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/test.css">
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div>
    <a id="hide1" href="#hide1" class="hide">+ Summary goes here</a>
    <a id="show1" href="#show1" class="show">- Summary goes here</a>
    <div class="details">
        Content goes here.
    </div>
</div>
<div>
    <a id="hide2" href="#hide2" class="hide">+ Summary goes here</a>
    <a id="show2" href="#show2" class="show">- Summary goes here</a>
    <div class="details">
        Content goes here.
    </div>
</div>
</body>
</html>
