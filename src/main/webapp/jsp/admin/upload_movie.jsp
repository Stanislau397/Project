<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.upload_movie"/></title>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/upload_movie.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/validation.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css"
          rel="stylesheet"/>
    <script src="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/js/bootstrap-multiselect.js"
            type="text/javascript"></script>
    <link href="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/css/bootstrap-multiselect.css"
          rel="stylesheet" type="text/css"/>
</head>
<body style="background-color: rgb(240,240,240)">
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.upload_movie"/></h2>
    </div>
</div>
<c:choose>
    <c:when test="${sessionScope.movie_title_attr != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3>
                <fmt:message key="label.add_movie_success">
                    <fmt:param value="${sessionScope.movie_title_attr}"/>
                </fmt:message>
                <span>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input hidden name="command" value="show_movie_details">
                        <input hidden name="movie_id" value="${sessionScope.movie_id_attr}">
                        <button class="see-btn">
                                <fmt:message key="label.see"/>
                                <i class="fa fa-arrow-right"></i>
                    </form>
            </span>
            </h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="movie_title_attr" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.invalid_input != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3><c:out value="${sessionScope.invalid_input}"/></h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="invalid_input" scope="session"/>
        </div>
    </c:when>
</c:choose>
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
                <div class="arrow-5 arrow-5-top"
                     style="width: 220px; margin-left: 10px"
                     id="image-error">
                    <fmt:message key="label.image_error"/>
                </div>
            </div>
            <div class="info">
                <div class="mb-3">
                    <label for="title" class="form-label"><fmt:message key="label.title"/>*</label>
                    <input type="text" class="form-control"
                           id="title"
                           name="title" placeholder="<fmt:message key="label.title_example"/>">
                </div>
                <div class="arrow-5 arrow-5-top" id="title-error">
                    <fmt:message key="label.title_example"/>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label"><fmt:message key="label.summery"/></label>
                    <textarea class="form-control" id="description"
                              name="description"
                              placeholder="<fmt:message key="label.description_example"/>"
                              rows="3"></textarea>
                </div>
                <div class="arrow-5 arrow-5-top" id="description-error">
                    <fmt:message key="label.description_example"/>
                </div>
                <div class="mb-3">
                    <label for="release_date" class="form-label"><fmt:message key="label.release_date"/>*</label>
                    <input type="text" class="form-control"
                           placeholder="<fmt:message key="label.date_example"/>"
                           id="release_date"
                           name="release_date">
                </div>
                <div class="arrow-5 arrow-5-top" id="release-date-error">
                    <fmt:message key="label.date_error"/>
                </div>
                <div class="mb-3">
                    <label for="run_time" class="form-label"><fmt:message key="label.runtime"/></label>
                    <input type="text" class="form-control"
                           placeholder="<fmt:message key="label.run_time_example"/>"
                           id="run_time"
                           name="run_time">
                </div>
                <div class="arrow-5 arrow-5-top" id="run-time-error">
                    <fmt:message key="label.run_time_error"/>
                </div>
            </div>
        </div>
        <div class="bottom">
            <div class="mb-3">
                <label for="select-country lstGenres" class="form-label"><fmt:message key="label.country"/></label>
                <select class="form-control selectpicker" data-dropup-auto="false" id="select-country lstCountries"
                        multiple="multiple" data-live-search="true" name="countries">
                    <c:forEach items="${requestScope.countries_list}" var="allCountries">
                        <option data-tokens="${allCountries.countryName}"
                                value="${allCountries.countryId}"><c:out
                                value="${allCountries.countryName}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="select-country lstGenres" class="form-label"><fmt:message key="label.genres"/></label>
                <select class="form-control selectpicker" data-dropup-auto="false" id="select-country lstGenres"
                        multiple="multiple" data-live-search="true" name="genres">
                    <c:forEach items="${requestScope.genres_list}" var="genres">
                        <option data-tokens="${genres.genreTitle}"
                                value="${genres.genreId}"><c:out value="${genres.genreTitle}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="select-country lstDirectors" class="form-label"><fmt:message
                        key="label.director"/></label>
                <select class="form-control selectpicker" data-dropup-auto="false" id="select-country lstDirectors"
                        multiple="multiple" data-live-search="true"
                        name="director">
                    <c:forEach items="${requestScope.directors_list}" var="allDirectors">
                        <option data-tokens="${allDirectors.firstName} ${allDirectors.lastName}"
                                value="${allDirectors.directorId}"><c:out value="${allDirectors.firstName}"/>
                            <c:out value="${allDirectors.lastName}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="select-country lstActors" class="form-label"><fmt:message key="label.actors"/></label>
                <select class="form-control selectpicker"
                        data-dropup-auto="false"
                        id="select-country lstActors"
                        multiple="multiple"
                        data-live-search="true"
                        name="actors">
                    <c:forEach items="${requestScope.actors_list}" var="allActors">
                        <option style="background-color: rgb(240,240,240)"
                                data-tokens="${allActors.firstName} ${allActors.lastName}"
                                value="${allActors.actorId}"><c:out value="${allActors.firstName}"/>
                            <c:out value="${allActors.lastName}"/></option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button type="submit" class="publish-btn" id="btn"><fmt:message key="label.publish"/></button>
    </form>
    <script type="text/javascript">

        $(document).ready(function () {
            $(function () {
                $('#select-country lstGenres').multiselect({
                    includeSelectAllOption: true
                });
                $('#btnSelected').click(function () {
                    var selected = $("#lstGenres option:selected");
                    var message = "";
                    selected.each(function () {
                        message += $(this).text() + " " + $(this).val() + "\n";
                    });
                });
            });

            $(function () {
                $('#lstActors').multiselect({
                    placeholder: "Actors",
                    includeSelectAllOption: true
                });
                $('#btnSelected').click(function () {
                    var selected = $("#lstActors option:selected");
                    var message = "";
                    selected.each(function () {
                        message += $(this).text() + " " + $(this).val() + "\n";
                    });
                });
            });

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
        })

        $(".close").click(function () {
            $(this)
                .parent(".alert")
                .fadeOut();
        });
    </script>
</div>
<div style="height: 250px"></div>
</body>
</html>