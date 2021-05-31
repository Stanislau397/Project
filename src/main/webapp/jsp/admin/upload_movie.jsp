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
    <div class="left">
        <div class="image-preview" id="imagePreview">
            <img src="" alt="Image" class="image-preview__image">
            <span class="image-preview__default__text"></span>
        </div>
    </div>
    <div class="info">
        <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="upload_movie">
            <ul class="block">
                <li><input type="file" name="file" id="inpFile" class="inputFile">
                    <label for="inpFile"><fmt:message key="label.picture"/></label>
                </li>
                <li><input type="text" class="text" name="title" placeholder="<fmt:message key="label.title"/>"></li>
                <li><input type="text" class="text" name="release_date" placeholder="<fmt:message key="label.release_date"/>"></li>
                <li><input type="text" class="text" name="run_time" placeholder="<fmt:message key="label.runtime"/>"></li>
                <li><input type="text" class="text" name="country" placeholder="<fmt:message key="label.country"/>"></li>
                <li><select name="genre_id">
                    <option selected><fmt:message key="label.genre"/></option>
                    <c:forEach items="${requestScope.genres_list}" var="genres">
                        <option value="${genres.genreId}"><c:out value="${genres.genreTitle}"/></option>
                    </c:forEach>
                </select></li>
                <li><input type="text" class="text" placeholder="<fmt:message key="label.directors"/>" name="director"></li>
                <li><textarea placeholder="<fmt:message key="label.actors"/>" name="actors"></textarea></li>
                <li><textarea placeholder="<fmt:message key="label.summery"/>" name="description"></textarea></li>
                <li><button type="submit">Submit</button> </li>
            </ul>
        </form>
    </div>
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
