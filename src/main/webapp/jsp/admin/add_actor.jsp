<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="label.add_actor"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add_actors.css">
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</head>
<body>
<h1>Popup/Modal Windows without JavaScript</h1>
<div class="box">
    <a class="button" href="#popup1">Let me Pop up</a>
</div>

<div id="popup1" class="overlay">
    <div class="popup">
        <textarea>adsas</textarea>
        <a class="close" href="#">&times;</a>
        <div class="content">
            Thank to pop me out of that button, but now i'm done so you can close this window.
        </div>
    </div>
</div>
</body>
</html>
