<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form1.css">
</head>
<header>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</header>
<body>
<form action="/controller" method="post">
    <input hidden name="command" value="change_password">
    <div>
        <input type="text" name="password" id="password">
        <label for="password"><fmt:message key="label.password"/></label>
        <div class="requirements"></div>
    </div>

    <div>
        <input type="text" name="new_password" id="new_password">
        <label for="new_password"><fmt:message key="label.new_password"/> </label>
        <div class="requirements"></div>
    </div>
    <button type="submit"><fmt:message key="label.save"/> </button>
</form>
</body>
</html>
