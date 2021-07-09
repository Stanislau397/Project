<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <head>
        <title></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/test.css">
        <style>
            .image-preview {
                width: 280px;
                height: 360px;
                border: 2px solid #222028;
                border-radius: 10px;
                margin-top: 10px;
                margin-left: 20px;
                display: flex;
                align-items: center;
                font-weight: bold;
                color: black;
            }

            .image-preview__image {
                min-width: 280px;
                height: 360px;
                object-fit: fill;
                border-radius: 10px;
            }

            .image-preview__default__text {
                margin-left: 100px;
            }
        </style>
    </head>
<body>
<input id="fileupload" type="file" name="files[]" multiple>
<div class="progress">
    <div class="meter" style="width: 0%;"></div>
</div>
<div class="data"></div>
<div id="some-image">
    <video muted autoplay controls width="200px" height="200px" id="uAvata"></video>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
    $("#fileupload").change(function(evt) {
        var fileUpload = $(this).get(0).files;
        readURL(this, "#uAvata");
    });
    //Preview image
    function readURL(inputFile, imgId) {
        if (inputFile.files && inputFile.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $(imgId).attr('src', e.target.result);
            }
            reader.readAsDataURL(inputFile.files[0]);
        }
    }
    // Preview video will read video like: @xxxmatko
</script>
</body>
</html>
