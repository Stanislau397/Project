<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <head>
        <title></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/test.css">
        <script>
            $(function () {
                $('#lstFruits').multiselect({
                    includeSelectAllOption: true
                });
                $('#btnSelected').click(function () {
                    var selected = $("#lstFruits option:selected");
                    var message = "";
                    selected.each(function () {
                        message += $(this).text() + " " + $(this).val() + "\n";
                    });
                });
            });

            $(function () {
                $('#lstActors').multiselect({
                    includeSelectAllOption: true
                });
                $('#btnSelected').click(function () {
                    var selected = $("#lstActors option:selected");
                    var message = "";
                    selected.each(function () {
                        message += $(this).text() + " " + $(this).val() + "\n";
                    });
                });
            });
        </script>
    </head>
<body>
<div class="top">
    <div class="left">

    </div>
    <div class="right">
        <video controls width="400px" height="200px" src="${pageContext.request.contextPath}/css/image/SPACE%20JAM%202%20A%20NEW%20LEGACY%20Trailer%20(2021)%20Family%20Movie.mp4"/>
    </div>
</div>
</body>
</html>
