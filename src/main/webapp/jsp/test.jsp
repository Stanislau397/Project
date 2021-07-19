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
        <style>
            .more {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                padding: 12px 16px;
                z-index: 1;
            }

            .more:hover .dropdown-content {
                display: block;
            }
            </style>
    </head>
<div class="more">
    <span><i class="fa fa-ellipsis-v"></i></span>
    <div class="dropdown-content">
        <p>Удалить</p>
        <p>Редактировать</p>
    </div>
</div>
</body>
</html>
