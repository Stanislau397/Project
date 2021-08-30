<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.add_actor"/></title>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add_actor.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.add_actor"/></h2>
    </div>
</div>
<c:choose>
    <c:when test="${sessionScope.actor != null}">
        <div class="alert">
            <h3><c:out value="${sessionScope.actor}"/> <fmt:message key="label.added_to_application"/></h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="actor" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.actor_already_exists != null}">
        <div class="alert">
            <h3><c:out value="${sessionScope.actor_already_exists}"/> <fmt:message key="label.already_exists"/></h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="actor_already_exists" scope="session"/>
        </div>
    </c:when>
</c:choose>
<div class="main-content">
    <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
    <div class="picture">
            <input type="hidden" name="command" value="add_actor">
            <div class="left">
                <div class="image-preview" id="imagePreview">
                    <img src="${pageContext.request.contextPath}/css/image/default-avatar-1276x1920.jpg"
                         alt="Image"
                         class="image-preview__image" style="margin-left: -2px">
                    <span class="image-preview__default__text"></span>
                </div>
                <input type="file" name="file" id="inpFile" class="inputFile"
                       value="${requestScope.movie_info.picture}" required
                >
                <label for="inpFile"><fmt:message key="label.choose"/></label>
            </div>
    </div>
    <div class="info">
            <label for="first_name"><fmt:message key="label.first_name"/> </label>
            <input type="text" id="first_name" name="first_name"
                   pattern="^.{1,80}$"
                   title="До 80 символов" required>
            <label for="last_name"><fmt:message key="label.last_name"/></label>
            <input type="text" id="last_name" name="last_name"
                   required>
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

    $(".close").click(function() {
        $(this)
            .parent(".alert")
            .fadeOut();
    });
</script>
</body>
</html>
