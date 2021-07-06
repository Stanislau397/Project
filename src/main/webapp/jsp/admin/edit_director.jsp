<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.edit_director"/></title>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/edit_actor.css">
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.edit_actor"/> (<c:out value="${requestScope.director.firstName}"/> <c:out value="${requestScope.director.lastName}"/>)</h2>
    </div>
</div>
<div class="main-content">
    <div class="edit-picture">
        <form action="${pageContext.request.contextPath}/UploadServlet" method="post"
              enctype="multipart/form-data">
            <input type="hidden" name="command" value="change_director_picture">
            <input type="hidden" name="director_id" value="${requestScope.director.directorId}">
            <div class="left">
                <div class="image-preview" id="imagePreview">
                    <c:choose>
                        <c:when test="${requestScope.director.picture != null}">
                            <img src="${pageContext.request.contextPath}${requestScope.director.picture}"
                                 alt="Image"
                                 style="margin-left: -2px"
                                 class="image-preview__image">
                            <span class="image-preview__default__text"></span>
                        </c:when>
                        <c:when test="${requestScope.director.picture == null}">
                            <img src="${pageContext.request.contextPath}/css/image/default-avatar-1276x1920.jpg"
                                 alt="Image"
                                 class="image-preview__image" style="margin-left: -2px">
                            <span class="image-preview__default__text"></span>
                        </c:when>
                    </c:choose>
                </div>
                <input type="file" name="file" id="inpFile" class="inputFile"
                       value="${requestScope.movie_info.picture}" required>
                <label for="inpFile"><fmt:message key="label.choose"/></label>
            </div>
            <div class="right">
                <button type="submit"><fmt:message key="label.change"/></button>
            </div>
        </form>
    </div>
    <div class="edit-info">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="edit_director">
            <input type="hidden" name="director_id" value="${requestScope.director.directorId}">
            <label for="first_name"><fmt:message key="label.first_name"/> </label>
            <input type="text" id="first_name" name="first_name"
                   pattern="^.{1,80}$"
                   title="До 80 символов" required
                   value="${requestScope.director.firstName}">
            <label for="last_name"><fmt:message key="label.last_name"/></label>
            <input type="text" id="last_name" name="last_name"
                   required
                   value="${requestScope.director.lastName}">
            <label for="height"><fmt:message key="label.height"/></label>
            <c:choose>
                <c:when test="${requestScope.director.height == 0}">
                    <input type="text" id="height" name="height" required>
                </c:when>
                <c:when test="${requestScope.director.height != 0}">
                    <input type="text" id="height" name="height" value="${requestScope.director.height}"
                           required>
                </c:when>
            </c:choose>
            <label for="age"><fmt:message key="label.birth_date"/></label>
            <c:choose>
                <c:when test="${requestScope.director.birthDate == null}">
                    <input type="text" id="age" name="birth_date"
                           required>
                </c:when>
                <c:when test="${requestScope.director.birthDate != null}">
                    <input type="text" id="age" name="birth_date"
                           required
                           value="${requestScope.director.birthDate}">
                </c:when>
            </c:choose>
            <button type="submit"><fmt:message key="label.save"/></button>
        </form>
    </div>
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

