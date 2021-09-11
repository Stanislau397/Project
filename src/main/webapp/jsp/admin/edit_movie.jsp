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
    <script src="${pageContext.request.contextPath}/js/validation.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css"
          rel="stylesheet"/>
    <script src="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/js/bootstrap-multiselect.js"
            type="text/javascript"></script>
    <link href="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/css/bootstrap-multiselect.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="top">
    <div class="text1">
        <c:choose>
            <c:when test="${requestScope.movie_info != null}">
                <h2><fmt:message key="label.edit"/> (<c:out value="${requestScope.movie_info.title}"/>)</h2>
            </c:when>
            <c:when test="${requestScope.movie_info == null}">
                <h2><fmt:message key="label.edit"/> (<c:out value="${requestScope.title}"/>)</h2>
            </c:when>
        </c:choose>
    </div>
</div>
<c:choose>
    <c:when test="${sessionScope.changed_data != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><fmt:message key="label.edit_movie_success"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="changed_data" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.changed_picture != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><fmt:message key="label.edit_picture_success"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="changed_picture" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.actor_removed != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><fmt:message key="label.remove_actor_success"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="actor_removed" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.director_successfully_removed != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><fmt:message key="label.remove_director_success"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="director_successfully_removed" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.changed_trailer != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><fmt:message key="label.edit_trailer_success"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="changed_trailer" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.genre_successfully_added != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><fmt:message key="label.add_genre_to_movie"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="genre_successfully_added" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.genre_removed != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3><fmt:message key="label.genre_removed_from_movie"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="genre_removed" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.error != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3><fmt:message key="label.genre_removed_error_msg"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="error" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.trailer_error != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3><fmt:message key="label.trailer_error"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="trailer_error" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.picture_error != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3><fmt:message key="label.edit_picture_error"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="picture_error" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.genre_already_exists != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3><fmt:message key="label.genre_exists_for_movie"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="genre_already_exists" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.delete_genre_error != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3><fmt:message key="label.add_genre_to_movie"/></h3>
            <a class="close">
                <i class="fa fa-close"
                   style="margin-top: 3px">
                </i></a>
            <c:remove var="genre_successfully_added" scope="session"/>
        </div>
    </c:when>
</c:choose>
<div class="main-content">
    <div class="head">
        <div class="navigation">
            <ul>
                <li>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="to_edit_movie">
                        <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                        <button type="submit"><fmt:message key="label.description"/></button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="display_movie_genres">
                        <input type="hidden" name="title" value="${requestScope.title}">
                        <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                        <button type="submit"><fmt:message key="label.genres"/></button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="display_movie_actors">
                        <input type="hidden" name="title" value="${requestScope.title}">
                        <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                        <button type="submit" name="actors-btn"><fmt:message key="label.actors"/></button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="display_movie_directors">
                        <input type="hidden" name="title" value="${requestScope.title}">
                        <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                        <button type="submit"><fmt:message key="label.directors"/></button>
                    </form>
                </li>
            </ul>
        </div>

        <c:choose>
        <c:when test="${requestScope.movie_info != null}">
            <div class="edit-poster">
                <form action="${pageContext.request.contextPath}/UploadServlet" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" name="command" value="change_movie_poster">
                    <input type="hidden" name="movie_id" value="${requestScope.movie_info.movieId}">
                    <input type="hidden" name="picture_path" value="${requestScope.movie_info.picture}">
                    <div class="left">
                        <div class="image-preview" id="imagePreview">
                            <img src="http://${requestScope.movie_info.picture}"
                                 alt="Image"
                                 class="image-preview__image">
                            <span class="image-preview__default__text"></span>
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
            <div class="bottom">
                <form action="${pageContext.request.contextPath}/UploadServlet" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" name="command" value="update_movie_trailer">
                    <input type="hidden" name="movie_id" value="${requestScope.movie_info.movieId}">
                    <div id="video">
                        <video controls id="trailer"
                               src="http://${requestScope.movie_info.trailer}"></video>
                    </div>
                    <label for="fileupload" id="file" class="label-for_video"><fmt:message key="label.choose"/></label>
                    <input id="fileupload" type="file" name="file" class="inputFile" multiple required>
                    <button type="submit"><fmt:message key="label.change"/></button>
                </form>
                <br>
                <br>
            </div>
            <div class="edit-title-date-time-description">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <div class="info-container">
                        <input type="hidden" name="command" value="update_movie">
                        <input type="hidden" name="movie_id" value="${requestScope.movie_info.movieId}">
                        <label for="title"><fmt:message key="label.title"/> </label>
                        <input type="text" id="title" name="title"
                               required
                               value="${requestScope.movie_info.title}">
                        <div class="arrow-5 arrow-5-top" id="title-error">
                            <fmt:message key="label.title_example"/>
                        </div>
                        <label for="run_time"><fmt:message key="label.runtime"/></label>
                        <input type="text" id="run_time" name="run_time"
                               placeholder="<fmt:message key="label.run_time_example"/>"
                               required
                               value="${requestScope.movie_info.runTime}">
                        <div class="arrow-5 arrow-5-top" id="run-time-error">
                            <fmt:message key="label.run_time_error"/>
                        </div>
                        <label for="release_date"><fmt:message key="label.release_date"/></label>
                        <input type="text" id="release_date" name="release_date"
                               placeholder="<fmt:message key="label.date_example"/>"
                               required
                               value="${requestScope.movie_info.releaseDate}">
                        <div class="arrow-5 arrow-5-top" id="release-date-error">
                            <fmt:message key="label.date_error"/>
                        </div>
                        <label for="description"><fmt:message key="label.summery"/></label>
                        <textarea id="description" name="description"><c:out
                                value="${requestScope.movie_info.description}"/></textarea>
                    </div>
                    <div class="arrow-5 arrow-5-top" id="description-error">
                        <fmt:message key="label.description_example"/>
                    </div>
                    <button type="submit" id="edit-movie-btn"><fmt:message key="label.save"/></button>
                </form>
                <br>
                <br>
            </div>
        </c:when>


        <c:when test="${requestScope.directors_list != null}">
        <div class="add-actor">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="add_director_to_movie">
                <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                <div class="mb-3" style="float: left; width: 80%">
                    <select class="form-control selectpicker" data-dropup-auto="false" id="select-country lstDirectors"
                            multiple="multiple" data-live-search="true"
                            style="background-color: rgb(240,240,240)"
                            required name="director">
                        <c:forEach items="${requestScope.all_directors_list}" var="allDirectors">
                            <option data-tokens="${allDirectors.firstName} ${allDirectors.lastName}"
                                    value="${allDirectors.directorId}"><c:out value="${allDirectors.firstName}"/>
                                <c:out value="${allDirectors.lastName}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="add-actor-right">
                    <button type="submit" class="add-actor-btn"><i class="fa fa-plus"></i></button>
                </div>
            </form>
        </div>
        <table class="content-table">
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message key="label.movie"/></th>
                <th><fmt:message key="label.first_name"/></th>
                <th><fmt:message key="label.last_name"/></th>
                <th><fmt:message key="label.operation"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.directors_list}" var="directors" varStatus="counter">
                <tr>
                    <td><c:out value="${counter.count}"/></td>
                    <td><c:out value="${requestScope.title}"/></td>
                    <td><c:out value="${directors.firstName}"/></td>
                    <td style="max-width: 280px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
                        <c:out value="${directors.lastName}"/></td>
                    <td>
                        <div class="remove">
                            <a class="button" href="#pop${counter.count}">
                                <button class="edit-btn" style="background-color: #eb5757"><i
                                        class="fa fa-trash"></i></button>
                            </a>
                            <div id="pop${counter.count}" class="overlay1">
                                <div class="pop">
                                    <div class="text">
                                        <h2><fmt:message key="label.delete_director"/></h2>
                                        <p><fmt:message key="label.delete_director_msg"/></p>
                                    </div>
                                    <div class="buttons">
                                        <div class="remove">
                                            <form action="${pageContext.request.contextPath}/controller"
                                                  method="post">
                                                <input type="hidden" name="command"
                                                       value="remove_director_from_movie">
                                                <input type="hidden" name="movie_id"
                                                       value="${requestScope.movie_id}">
                                                <input type="hidden" name="director_id"
                                                       value="${directors.directorId}">
                                                <button type="submit"><fmt:message key="label.remove"/></button>
                                            </form>
                                        </div>
                                        <a class="close"
                                           style="margin-top: -20px; margin-left: 5px"
                                           href="#">
                                            <i class="fa fa-close"
                                               style="margin-top: 3px; margin-left: 1px; color: black">
                                            </i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </c:when>


    <c:when test="${requestScope.actors_list != null}">
        <div class="add-actor">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <div class="mb-3" style="float: left; width: 80%">
                    <input type="hidden" name="command" value="add_actor_to_movie">
                    <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                    <select class="form-control selectpicker" data-dropup-auto="false" id="select-country lstActors"
                            multiple="multiple" data-live-search="true"
                            style="background-color: rgb(240,240,240)"
                            required name="actors">
                        <c:forEach items="${requestScope.all_actors_list}" var="allActors">
                            <option
                                    data-tokens="${allActors.firstName} ${allActors.lastName}"
                                    value="${allActors.actorId}"><c:out value="${allActors.firstName}"/>
                                <c:out value="${allActors.lastName}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="add-actor-right">
                    <button type="submit" class="add-actor-btn"><i class="fa fa-plus"></i></button>
                </div>
            </form>
        </div>
        <table class="content-table">
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message key="label.movie"/></th>
                <th><fmt:message key="label.first_name"/></th>
                <th><fmt:message key="label.last_name"/></th>
                <th><fmt:message key="label.operation"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.actors_list}" var="actors" varStatus="counter">
                <tr>
                    <td><c:out value="${counter.count}"/></td>
                    <td><c:out value="${requestScope.title}"/></td>
                    <td><c:out value="${actors.firstName}"/></td>
                    <td style="max-width: 280px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
                        <c:out value="${actors.lastName}"/></td>
                    <td>
                        <div class="remove">
                            <a class="button" href="#pop${counter.count}">
                                <button class="edit-btn" style="background-color: #eb5757"><i
                                        class="fa fa-trash"></i></button>
                            </a>
                            <div id="pop${counter.count}" class="overlay1">
                                <div class="pop">
                                    <div class="text">
                                        <h2 style="color: black"><fmt:message key="label.delete_actor"/></h2>
                                        <p><fmt:message key="label.delete_actor_msg"/></p>
                                    </div>
                                    <div class="buttons">
                                        <div class="remove">
                                            <form action="${pageContext.request.contextPath}/controller"
                                                  method="post">
                                                <input type="hidden" name="command"
                                                       value="remove_actor_from_movie">
                                                <input type="hidden" name="movie_id"
                                                       value="${requestScope.movie_id}">
                                                <input type="hidden" name="actor_id" value="${actors.actorId}">
                                                <button type="submit"><fmt:message key="label.remove"/></button>
                                            </form>
                                        </div>
                                        <a class="close"
                                           style="margin-top: -20px; margin-left: 5px"
                                           href="#">
                                            <i class="fa fa-close"
                                               style="margin-top: 3px; margin-left: 1px; color: black">
                                            </i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>

    <c:when test="${requestScope.movie_genres_list != null}">
        <table class="content-table">
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message key="label.movie"/></th>
                <th><fmt:message key="label.genre"/></th>
                <th><fmt:message key="label.id"/></th>
                <th><fmt:message key="label.operation"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.movie_genres_list}" var="movieGenres" varStatus="counter">
                <tr>
                    <td><c:out value="${counter.count}"/></td>
                    <td><c:out value="${requestScope.title}"/></td>
                    <td><c:out value="${movieGenres.genreTitle}"/></td>
                    <td style="max-width: 280px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
                        <c:out value="${movieGenres.genreId}"/></td>
                    <td>
                        <div class="remove">
                            <a class="button" href="#pop${counter.count}">
                                <button class="edit-btn" style="background-color: #eb5757"><i
                                        class="fa fa-trash"></i></button>
                            </a>
                            <div id="pop${counter.count}" class="overlay1">
                                <div class="pop">
                                    <div class="text">
                                        <h2><fmt:message key="label.delete_genre"/></h2>
                                        <p><fmt:message key="label.delete_genre_msg"/></p>
                                    </div>
                                    <div class="buttons">
                                        <div class="remove">
                                            <form action="${pageContext.request.contextPath}/controller"
                                                  method="post">
                                                <input type="hidden" name="command"
                                                       value="remove_genre_from_movie">
                                                <input type="hidden" name="movie_id"
                                                       value="${requestScope.movie_id}">
                                                <input type="hidden" name="genre_id"
                                                       value="${movieGenres.genreId}">
                                                <button type="submit"><fmt:message key="label.remove"/></button>
                                            </form>
                                        </div>
                                        <a class="close"
                                           style="margin-top: -20px; margin-left: 5px"
                                           href="#">
                                            <i class="fa fa-close"
                                               style="margin-top: 3px; margin-left: 1px; color: black">
                                            </i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="add">
            <a class="button" href="#pop">
                <button class="add-btn"><fmt:message key="label.add_genre"/></button>
            </a>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <div id="pop" class="overlay2">
                    <div class="pop">
                        <input type="hidden" name="command" value="add_genre_to_movie">
                        <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                        <div class="text">
                            <h2 style="color: black"><fmt:message key="label.add_genre"/></h2>
                            <select name="genre_id">
                                <c:forEach items="${requestScope.genres_list}" var="genres">
                                    <option value="${genres.genreId}"><c:out
                                            value="${genres.genreTitle}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="buttons">
                            <div class="remove">
                                <button type="submit" style="background-color: rgb(211,211,211)"><fmt:message
                                        key="label.add"/></button>
                            </div>
                            <div class="dismiss">
                                <a class="close"
                                   style="margin-top: -20px; margin-left: 5px"
                                   href="#">
                                    <i class="fa fa-close"
                                       style="margin-top: 3px; margin-left: 1px; color: black">
                                    </i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </c:when>
    </c:choose>
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

    $("#fileupload").change(function (evt) {
        var fileUpload = $(this).get(0).files;
        readURL(this, "#trailer");
    });

    function readURL(inputFile, imgId) {
        if (inputFile.files && inputFile.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $(imgId).attr('src', e.target.result);
            }
            reader.readAsDataURL(inputFile.files[0]);
        }
    }

    $(function () {
        $('#lstActors').multiselect({
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
</script>
<script type="text/javascript">
    $(".close").click(function () {
        $(this)
            .parent(".alert")
            .fadeOut();
    });
</script>
</div>
<br>
<br>
<br>
<br>
</body>
</html>
