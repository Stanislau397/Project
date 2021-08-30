<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.account_settings"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/settings.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
</header>
<body>
<div>
    <c:if test="${sessionScope.avatar_edited != null}">
        <div class="update-message">
            <button class="exit-btn"><i class="fa fa-remove"></i></button>
            <p><fmt:message key="label.avatar_update"/></p>
            <c:set var="user_avatar" scope="session" value="${sessionScope.avatar_edited}"/>
            <c:remove var="avatar_edited" scope="session"/>
        </div>
    </c:if>
    <h2><fmt:message key="label.account_settings"/></h2>
    <hr>
    <h3><fmt:message key="label.profile_image"/></h3>
    <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="change_user_avatar">
        <input type="hidden" name="user_id" value="${requestScope.user.userId}">
        <div class="image-preview" id="imagePreview">
            <img src="${pageContext.request.contextPath}${requestScope.user.avatar}"
                 alt="Image"
                 class="image-preview__image" style="margin-left: -2px">
            <span class="image-preview__default__text"></span>
        </div>
        <input type="file" name="file" id="inpFile" class="inputFile" required>
        <label for="inpFile"><fmt:message key="label.choose"/></label>
        <button type="submit" style="display: block" class="avatar-btn"><fmt:message key="label.change"/></button>
    </form>
    <hr>
    <h3><fmt:message key="label.password"/></h3>
    <div class="password">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="change_password">
            <input type="hidden" name="user_name" value="${requestScope.user.userName}">
            <input type="password" name="password" placeholder="<fmt:message key="label.current_password"/>"
                   pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
            <input type="password" name="new_password" id="new_password"
                   class="new_password"
                   placeholder="<fmt:message key="label.new_password"/>"
                   pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
            <input type="password" name="verify_password" placeholder="<fmt:message key="label.verify_password"/>"
                   pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
            <button type="submit" name="submit"><fmt:message key="label.change_password"/></button>
        </form>
    </div>
    <hr>
    <p class="message">
        <c:if test="${sessionScope.change_password != null}">
            <c:out value="${sessionScope.change_password}"/>
        </c:if>
    </p>
    <br>
    <br>
    <br>
    <jsp:include page="/jsp/static/footer.jsp"/>
</div>

<script>
    const inpFile = document.getElementById("inpFile");
    const previewContainer = document.getElementById("imagePreview");
    const previewImage = previewContainer.querySelector(".image-preview__image");
    const previewDefaultText = previewContainer.querySelector(".image-preview__default__text");
    const newPassword = document.getElementById('new_password').innerText;

    inpFile.addEventListener("change", function () {
        const file = this.files[0];

        if (file) {
            const reader = new FileReader();

            previewDefaultText.style.display = "none";
            previewImage.style.display = "block";

            reader.addEventListener("load", function () {
                previewImage.setAttribute("src", this.result);
            });

            reader.readAsDataURL(file);
        }
    });

    $('.exit-btn').focus(function () {
        $('.update-message').hide().fadeOut('slow');
    });

</script>
</body>
</html>
