<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/property/text" />
<html lang="${language}">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
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
