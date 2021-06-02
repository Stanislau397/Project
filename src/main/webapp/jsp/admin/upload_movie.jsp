<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="label.upload_movie"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/upload_movie.css">
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
</head>
<jsp:include page="/jsp/static/admin_side_bar.jsp"/>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.upload_movie"/></h2>
    </div>
</div>
<div class="main-content">
    <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="upload_movie">
        <div class="left">
            <div class="left">
                <div class="image-preview" id="imagePreview">
                    <img src="" alt="Image" class="image-preview__image">
                    <span class="image-preview__default__text"></span>
                </div>
                <input type="file" name="file" id="inpFile" class="inputFile">
                <label for="inpFile"><fmt:message key="label.picture"/></label>
            </div>
            <div class="info">

                <ul class="block">
                    <li><input type="text" class="text" name="title"
                               placeholder="<fmt:message key="label.title"/>"
                               pattern="^.{1,80}$"
                               title="До 80 символов" required></li>
                    <li><textarea name="description"
                                  placeholder="<fmt:message key="label.summery"/>" required></textarea></li>
                </ul>

                <ul class="inline">
                    <li><input type="text" class="text1" name="run_time"
                               placeholder="<fmt:message key="label.runtime"/>"
                               pattern="^\d+$"
                               title="123" required></li>
                    <li><input type="text" style="margin-left: 12px" class="text1" name="release_date"
                               placeholder="<fmt:message key="label.release_date"/>"
                               pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))"
                               title="1995-11-19" required></li>
                </ul>

                <ul class="inline">
                    <li><input type="text" class="text1" name="country" placeholder="<fmt:message key="label.country"/>" required></li>
                    <li><select name="genre_id">
                        <option selected><fmt:message key="label.genre"/></option>
                        <c:forEach items="${requestScope.genres_list}" var="genres">
                            <option value="${genres.genreId}"><c:out value="${genres.genreTitle}"/></option>
                        </c:forEach>
                    </select></li>
                </ul>

                <ul class="block">
                    <li><input type="text" class="text" name="director" placeholder="<fmt:message key="label.directors"/>" required></li>
                    <li><textarea name="actors" placeholder="<fmt:message key="label.actors"/>" required></textarea></li>
                </ul>
            </div>
            <button type="submit" class="publish-btn"><fmt:message key="label.publish"/></button>
        </div>
    </form>
</div>
<script>
    const inpFile = document.getElementById("inpFile");
    const previewContainer = document.getElementById("imagePreview");
    const previewImage = previewContainer.querySelector(".image-preview__image");
    const previewDefaultText = previewContainer.querySelector(".image-preview__default__text");

    inpFile.addEventListener("change", function () {
       const  file = this.files[0];

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
