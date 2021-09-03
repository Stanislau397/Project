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
<c:choose>
    <c:when test="${sessionScope.changed_data != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><fmt:message key="label.edit_director_success"/></h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="changed_data" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.error != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3><fmt:message key="label.edit_director_error"/></h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="error" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.changed_picture != null}">
        <div class="alert" style="background-color: #66cc33"">
            <h3><fmt:message key="label.edit_picture_success"/></h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="changed_picture" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.picture_error != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3><fmt:message key="label.edit_picture_error"/></h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="picture_error" scope="session"/>
        </div>
    </c:when>
</c:choose>
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
        <div class="input-space">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="edit_director">
                <input type="hidden" name="director_id" value="${requestScope.director.directorId}">
                <label for="first_name"><fmt:message key="label.first_name"/> </label>
                <input type="text" id="first_name" name="first_name"
                       value="${requestScope.director.firstName}"
                required>
                <div class="arrow-5 arrow-5-top" id="first-name-error">
                    <fmt:message key="label.first_name_error"/>
                </div>
                <label for="last_name"><fmt:message key="label.last_name"/></label>
                <input type="text" id="last_name" name="last_name"
                       required
                       value="${requestScope.director.lastName}">
                <div class="arrow-5 arrow-5-top" id="last-name-error">
                    <fmt:message key="label.last_name_error"/>
                </div>
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
                <label for="birth-date"><fmt:message key="label.birth_date"/></label>
                <c:choose>
                    <c:when test="${requestScope.director.birthDate == null}">
                        <input type="text" id="birth-date" name="birth_date"
                               placeholder="<fmt:message key="label.date_example"/>"
                               required>
                    </c:when>
                    <c:when test="${requestScope.director.birthDate != null}">
                        <input type="text" id="birth-date" name="birth_date"
                               placeholder="<fmt:message key="label.date_example"/>"
                               required
                               value="${requestScope.director.birthDate}">
                    </c:when>
                </c:choose>
                <div class="arrow-5 arrow-5-top" id="birth-date-error">
                    <fmt:message key="label.birth_date_error"/>
                </div>
        </div>
        <button type="submit"><fmt:message key="label.save"/></button>
        </form>
    </div>
</div>
<script>
    $(function () {

        const inpFile = document.getElementById("inpFile");
        const previewContainer = document.getElementById("imagePreview");
        const previewImage = previewContainer.querySelector(".image-preview__image");
        const previewDefaultText = previewContainer.querySelector(".image-preview__default__text");
        $('#first-name-error').hide();
        $('#last-name-error').hide();
        $('#birth-date-error').hide();

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

        $(".close").click(function () {
            $(this)
                .parent(".alert")
                .fadeOut();
        });

        $('#first_name').focusout( function () {
            validateFirstName();
        });

        $('#last_name').focusout( function () {
            validateLastName();
        });

        $('#birth-date').focusout( function () {
            validateBirthDate();
        });

        function validateFirstName() {
            var first_name = $('#first_name').val();
            var first_name_regex = '^[A-Za-z]+((\s)?((\'|\-|\.)?([A-Za-z])+))*$';
            if (first_name.match(first_name_regex)) {
                $('#first-name-error').hide();
                $('#first_name').css('border', '2px solid #66cc33');
            } else if (first_name === '') {
                $('#first-name-error').hide();
                $('#first_name').css('border', '1px solid silver');
            } else {
                $('#first-name-error').show();
                $('#first_name').css('border', '2px solid red');
            }
        }

        function validateLastName() {
            var last_name = $('#last_name').val();
            var last_name_regex = '^[A-Za-z]+((\s)?((\'|\-|\.)?([A-Za-z])+))*$';
            if (last_name.match(last_name_regex)) {
                $('#last_name').css('border', '2px solid #66cc33');
                $('#last-name-error').hide();
            } else if(last_name === '') {
                $('#last_name').css('border', '1px solid silver');
                $('#last-name-error').hide();
            } else {
                $('#last_name').css('border', '2px solid red');
                $('#last-name-error').show();
            }
        }

        function validateBirthDate() {
            var birth_date = $('#birth-date').val();
            var birth_date_regex = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;
            if (birth_date.match(birth_date_regex)) {
                $('#birth-date').css('border', '2px solid #66cc33');
                $('#birth-date-error').hide();
                $('#btn').prop('disabled', false);
            } else if (birth_date === '') {
                $('#birth-date').css('border', '1px solid silver');
                $('#birth-date-error').hide();
                $('#btn').prop('disabled', false);
            } else {
                $('#birth-date').css('border', '2px solid red');
                $('#birth-date-error').show();
                $('#btn').prop('disabled', true);
            }
        }
    })
</script>
</body>
</html>

