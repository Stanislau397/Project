<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.account_settings"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/settings.css">
</head>
<header>
    <jsp:include page="/jsp/static/header.jsp"/>
</header>
<body>
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
        <input type="file" name="file" id="inpFile" class="inputFile">
        <label for="inpFile"><fmt:message key="label.choose"/></label>
        <button type="submit" style="display: block" class="avatar-btn"><fmt:message key="label.change"/></button>
    </form>
    <hr>
    <h3><fmt:message key="label.password"/></h3>
    <div class="password">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="change_password">
            <input type="hidden" name="user_name" value="${requestScope.user.userName}">
            <input type="password" name="password" placeholder="Current Password"
                   pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
            <input type="password" name="new_password" placeholder="New Password"
                   pattern="^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$" required>
            <input type="password" name="verify_password" placeholder="Verify Password"
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

    <script>
        const inpFile = document.getElementById("inpFile");
        const previewContainer = document.getElementById("imagePreview");
        const previewImage = previewContainer.querySelector(".image-preview__image");
        const previewDefaultText = previewContainer.querySelector(".image-preview__default__text");

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
    </script>
</body>
</html>
