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
                                <img src="${pageContext.request.contextPath}${requestScope.movie_info.picture}"
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
                        <textarea id="description" name="description"><c:out
                                value="${requestScope.movie_info.description}"/></textarea>
                        <button type="submit"><fmt:message key="label.save"/></button>
                    </form>
                </div>
            </c:when>


            <c:when test="${requestScope.directors_list != null}">
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
                                                        <input type="hidden" name="director_id" value="${directors.directorId}">
                                                        <button type="submit"><fmt:message key="label.remove"/></button>
                                                    </form>
                                                </div>
                                                <div class="dismiss">
                                                    <button><a class="close" href="#"><fmt:message
                                                            key="label.close"/></a>
                                                    </button>
                                                </div>
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
                        <button class="add-btn" style="width: 210px; height: 42px"><fmt:message key="label.add_director"/></button>
                    </a>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                    <div id="pop" class="overlay2">
                        <div class="pop">
                                <input type="hidden" name="command" value="add_director_to_movie">
                                <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                                <div class="text">
                                    <h2 style="color: white"><fmt:message key="label.add_director"/></h2>
                                    <input type="text" name="first_name"
                                           placeholder="<fmt:message key="label.first_name"/>">
                                    <input type="text" name="last_name"
                                           placeholder="<fmt:message key="label.last_name"/>">
                                </div>
                                <div class="buttons">
                                    <div class="remove">
                                        <button type="submit" style="background-color: #1a191f"><fmt:message
                                                key="label.add"/></button>
                                    </div>
                                    <div class="dismiss">
                                        <button style="background-color: #1a191f; margin-left: 14px"><a class="close"
                                                                                                        href="#"><fmt:message
                                                key="label.close"/></a>
                                        </button>
                                    </div>
                                </div>
                        </div>
                    </div>
                    </form>
                </div>
            </c:when>


            <c:when test="${requestScope.actors_list != null}">
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
                                                <h2><fmt:message key="label.delete_actor"/></h2>
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
                                                <div class="dismiss">
                                                    <button><a class="close" href="#"><fmt:message
                                                            key="label.close"/></a>
                                                    </button>
                                                </div>
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
                        <button class="add-btn"><fmt:message key="label.add_actor"/></button>
                    </a>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <div id="pop" class="overlay2">
                            <div class="pop">
                                <input type="hidden" name="command" value="add_actor_to_movie">
                                <input type="hidden" name="movie_id" value="${requestScope.movie_id}">
                                <div class="text">
                                    <h2 style="color: white"><fmt:message key="label.add_actor"/></h2>
                                    <input type="text" name="first_name"
                                           placeholder="<fmt:message key="label.first_name"/>">
                                    <input type="text" name="last_name"
                                           placeholder="<fmt:message key="label.last_name"/>">
                                </div>
                                <div class="buttons">
                                    <div class="remove">
                                        <button type="submit" style="background-color: #1a191f"><fmt:message
                                                key="label.add"/></button>
                                    </div>
                                    <div class="dismiss">
                                        <button style="background-color: #1a191f; margin-left: 14px"><a class="close"
                                                                                                        href="#"><fmt:message
                                                key="label.close"/></a>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
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
                                                        <input type="hidden" name="genre_id" value="${movieGenres.genreId}">
                                                        <button type="submit"><fmt:message key="label.remove"/></button>
                                                    </form>
                                                </div>
                                                <div class="dismiss">
                                                    <button><a class="close" href="#"><fmt:message
                                                            key="label.close"/></a>
                                                    </button>
                                                </div>
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
                                    <h2 style="color: white"><fmt:message key="label.add_genre"/></h2>
                                    <select name="genre_id">
                                        <c:forEach items="${requestScope.genres_list}" var="genres">
                                            <option value="${genres.genreId}"><c:out value="${genres.genreTitle}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="buttons">
                                    <div class="remove">
                                        <button type="submit" style="background-color: #1a191f"><fmt:message
                                                key="label.add"/></button>
                                    </div>
                                    <div class="dismiss">
                                        <button style="background-color: #1a191f; margin-left: 14px"><a class="close"
                                                                                                        href="#"><fmt:message
                                                key="label.close"/></a>
                                        </button>
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
    </script>
</div>
</body>
</html>
