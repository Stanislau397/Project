<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Settings</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/settings.css">
</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
</header>
<body>
<h2>ACCOUNT SETTINGS</h2>
<hr>
<h3>PROFILE IMAGE</h3>
<div>
    <img src="${pageContext.request.contextPath}/css/image/user.jpg"/>
</div>
<hr>
<h3>PASSWORD</h3>
<div class="password">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="change_password">
        <input type="password" name="current_password" placeholder="Current Password"
               pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
        <input type="password" name="new_password" placeholder="New Password"
               pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
        <input type="password" name="verify_password" placeholder="Verify Password"
               pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
        <button type="submit" name="submit">Submit</button>
    </form>
</div>
<hr>
</body>
</html>
