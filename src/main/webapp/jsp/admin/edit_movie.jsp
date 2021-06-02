<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.edit"/></title>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/edit_movie.css">
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.edit"/> (<c:out value="${requestScope.movie_info.title}"/>)</h2>
    </div>
</div>
<div class="main-content">

        <div class="edit-poster">
            <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="change_movie_poster">
                <input type="hidden" name="movie_id" value="${requestScope.movie_info.movieId}">
                <input type="hidden" name="picture_path" value="${requestScope.movie_info.picture}">
                    <div class="left">
                        <div class="image-preview" id="imagePreview">
                            <img src="${pageContext.request.contextPath}${requestScope.movie_info.picture}" alt="Image"
                                 class="image-preview__image">
                            <span class="image-preview__default__text"></span>
                        </div>
                        <input type="file" name="file" id="inpFile" class="inputFile" value="${requestScope.movie_info.picture}" required>
                        <label for="inpFile"><fmt:message key="label.choose"/></label>
                    </div>
                <div class="right">
                    <button type="submit"><fmt:message key="label.change"/></button>
                </div>
            </form>
        </div>
        <div class="edit-title-date-time-description">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="update_movie">
                <input type="hidden" name="movie_id" value="${requestScope.movie_info.movieId}">
                <label for="title"><fmt:message key="label.title"/> </label>
                <input type="text" id="title" name="title"
                       pattern="^.{1,80}$"
                       title="До 80 символов" required
                       value="${requestScope.movie_info.title}">
                <label for="run_time"><fmt:message key="label.runtime"/></label>
                <input type="text" id="run_time" name="run_time"
                       style="margin-left: 85px"
                       pattern="^\d+$"
                       title="123" required
                       value="${requestScope.movie_info.runTime}">
                <label for="date"><fmt:message key="label.release_date"/></label>
                <input type="text" id="date" name="release_date"
                       style="margin-left: 22px"
                       pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))"
                       title="1995-11-19" required
                       value="${requestScope.movie_info.releaseDate}">
                <label for="description"><fmt:message key="label.summery"/></label>
                <textarea id="description" name="description"><c:out value="${requestScope.movie_info.description}"/></textarea>
                <button type="submit"><fmt:message key="label.save"/></button>
            </form>
        </div>
    <div class="edit-genre">
        <h2><fmt:message key="label.genres"/></h2>

    </div>
    <div class="edit-actors">

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
