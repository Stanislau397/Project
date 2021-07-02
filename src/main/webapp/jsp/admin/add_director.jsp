<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.add_actor"/></title>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add_actor.css">
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.add_director"/></h2>
    </div>
</div>
<div class="main-content">
    <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
        <div class="picture">
            <input type="hidden" name="command" value="add_director">
            <div class="left">
                <div class="image-preview" id="imagePreview">
                    <img src="${pageContext.request.contextPath}/css/image/default-avatar-1276x1920.jpg"
                         alt="Image"
                         class="image-preview__image" style="margin-left: -2px">
                    <span class="image-preview__default__text"></span>
                </div>
                <input type="file" name="file" id="inpFile" class="inputFile"
                       value="${requestScope.movie_info.picture}" required>
                <label for="inpFile"><fmt:message key="label.choose"/></label>
            </div>
        </div>
        <div class="info">
            <label for="first_name"><fmt:message key="label.first_name"/> </label>
            <input type="text" id="first_name" name="first_name"
                   pattern="^.{1,80}$"
                   title="До 80 символов" required
                   value="${requestScope.actor.firstName}">
            <label for="last_name"><fmt:message key="label.last_name"/></label>
            <input type="text" id="last_name" name="last_name"
                   required
                   value="${requestScope.actor.lastName}">
            <label for="age"><fmt:message key="label.birth_date"/></label>
            <input type="text" id="age" name="birth_date"
                   required>
            <button type="submit"><fmt:message key="label.add"/></button>
        </div>
    </form>
</div>
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