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
        <div class="alert" style="background-color: #66cc33">
            <h3><c:out value="${sessionScope.actor}"/> <fmt:message key="label.added_to_application"/></h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="actor" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.actor_already_exists != null}">
        <div class="alert" style="background-color: #eb5757">
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
                    <img src="${pageContext.request.contextPath}/image/istockphoto-1016744004-612x612.jpg"
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
            <div class="input-space">
                <label for="first_name"><fmt:message key="label.first_name"/> </label>
                <input type="text" id="first_name" name="first_name"
                       required>
                <div class="arrow-5 arrow-5-top" id="first-name-error">
                    <fmt:message key="label.first_name_error"/>
                </div>
                <label for="last_name"><fmt:message key="label.last_name"/></label>
                <input type="text" id="last_name" name="last_name"
                       required>
                <div class="arrow-5 arrow-5-top" id="last-name-error">
                    <fmt:message key="label.last_name_error"/>
                </div>
                <label><fmt:message key="label.birth_date"/></label>
                <select id="birth_date" name="day_of_month">
                    <option selected>День</option>
                    <c:forEach begin="1" end="31" var="month">
                        <option value="${month}">${month}</option>
                    </c:forEach>
                </select>
                <select name="month">
                    <option selected>Месяц</option>
                    <option value="1"><fmt:message key="label.january"/></option>
                    <option value="2"><fmt:message key="label.february"/></option>
                    <option value="3"><fmt:message key="label.march"/></option>
                    <option value="4"><fmt:message key="label.april"/></option>
                    <option value="5"><fmt:message key="label.may"/></option>
                    <option value="6"><fmt:message key="label.june"/></option>
                    <option value="7"><fmt:message key="label.july"/></option>
                    <option value="8"><fmt:message key="label.august"/></option>
                    <option value="9"><fmt:message key="label.september"/></option>
                    <option value="10"><fmt:message key="label.october"/></option>
                    <option value="11"><fmt:message key="label.november"/></option>
                    <option value="12"><fmt:message key="label.december"/></option>
                </select>
                <select name="year">
                    <option selected><fmt:message key="label.year"/></option>
                    <c:forEach begin="1900" end="2022" var="year">
                        <option value="${year}"><c:out value="${year}"/></option>
                    </c:forEach>
                </select>
                <div class="arrow-5 arrow-5-top" id="birth-date-error">
                    <fmt:message key="label.birth_date_error"/>
                </div>
            </div>
            <button type="submit" id="btn"><fmt:message key="label.add"/></button>
        </div>
    </form>
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
