<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>Comments</title>
</head>
<body>
<c:forEach items="${requestScope.comment_list}" var="comments">
    ${comments.userName}
</c:forEach>
</body>
</html>
