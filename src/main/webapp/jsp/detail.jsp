<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>detail</title>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css">
</head>
<header>
    <jsp:include page="static/header.jsp"/>
</header>
<body>
<div class="center">
    <table>
        <tr>
            <th colspan="3"></th>
        </tr>

        <tr>
            <th rowspan="5" class="picture">
                <img src="${pageContext.request.contextPath}${movie_info.picture}"/>
            </th>
        </tr>
        <tr>
            <td class="title">
                <p class="p-title"><fmt:message key="label.movie_detail"/></p>

            </td>
            <td class="rating">
                <c:if test="${requestScope.movie_info != null}">
                    <p class="p-rating">
                        <c:out value="${movie_info.rating.score}"/>
                    </p>
                </c:if>
                <c:if test="${requestScope.movie_info == null}">
                    <p>No rating</p>
                </c:if>
            </td>
        </tr>
        <tr>
            <td class="starring">
                <ul>
                    <li class="li-starring"><fmt:message key="label.starring"/></li>
                    <li class="li-starring-parameter">
                        <c:forEach items="${requestScope.actors_list}" var="actors">
                            <c:out value="${actors}"/>
                        </c:forEach>
                    </li>
                </ul>
            </td>
        </tr>
        <tr>
            <td class="info">
                <ul>
                    <li class="li-info"><fmt:message key="label.summery"/></li>
                    <li class="li-info-parameter">
                        <c:out value="${movie_info.description}"/>
                    </li>
                </ul>
                <div class="info-2">
                    <ul class="one">
                        <li class="director-li"><fmt:message key="label.director"/></li>
                        <li class="director-parameter">
                            <c:forEach items="${requestScope.directors_list}" var="directors">
                                <c:out value="${directors}"/>
                            </c:forEach>
                        </li>
                    </ul>
                    <ul class="two">
                        <li class="run"><fmt:message key="label.runtime"/></li>
                        <li class="time">
                            <c:out value="${movie_info.runTime}"/>
                        </li>
                    </ul>
                    <ul class="three">
                        <li class="country-li"><fmt:message key="label.country"/></li>
                        <li class="country-parameter">
                            <c:out value="${movie_info.country}"/>
                        </li>
                    </ul>
                    <ul class="five">
                        <li class="li-genre"><fmt:message key="label.genre"/></li>
                        <li class="li-genre-parameter">
                            <c:out value="${movie_info.genre.title}"/></li>
                    </ul>
                </div>
            </td>
        </tr>
    </table>
    <c:if test="${requestScope.user_score != null}">
        <p class="your-score">
            Your score is ${user_score}
        </p>
    </c:if>
    <c:if test="${sessionScope.admin != null && requestScope.rated_movie == false|| sessionScope.user != null && requestScope.rated_movie == false}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <div class="container">
                <div class="rating">
                    <input type="hidden" name="command" value="rate_movie">
                    <input type="hidden" name="movie_id" value="${movie_id}">
                    <input type="radio" id="star1" name="point" value="10">
                    <label for="star1" class="fa fa-star"></label>
                    <input type="radio" id="star2" name="point" value="20">
                    <label for="star2" class="fa fa-star"></label>
                    <input type="radio" id="star3" name="point" value="30">
                    <label for="star3" class="fa fa-star"></label>
                    <input type="radio" id="star4" name="point" value="40">
                    <label for="star4" class="fa fa-star"></label>
                    <input type="radio" id="star5" name="point" value="50">
                    <label for="star5" class="fa fa-star"></label>
                    <input type="radio" id="star6" name="point" value="60">
                    <label for="star6" class="fa fa-star"></label>
                    <input type="radio" id="star7" name="point" value="70">
                    <label for="star7" class="fa fa-star"></label>
                    <input type="radio" id="star8" name="point" value="80">
                    <label for="star8" class="fa fa-star"></label>
                    <input type="radio" id="star9" name="point" value="90">
                    <label for="star9" class="fa fa-star"></label>
                    <input type="radio" id="star10" name="point" class="fa fa-star" value="100">
                    <label for="star10" class="fa fa-star"></label>
                    <button type="submit">submit</button>
                </div>
            </div>
        </form>
    </c:if>
    <div class="comment-section">
        <c:if test="${sessionScope.admin != null || sessionScope.user != null}">
            <div class="comment-box">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input hidden name="command" value="leave_comment">
                    <input type="hidden" name="movie_id" value="${movie_id}">
                    <textarea name="comment" placeholder="Type your comment"></textarea><br>
                    <button type="submit">Submit Comment</button>
                </form>
            </div>
        </c:if>
        <c:forEach items="${requestScope.comments}" var="comments">
            <div class="comment-score">
                75
            </div>
            <div class="user-info">
                <p class="user-name">
                    <c:out value="${comments.userName}"/>
                </p>
                <p class="comment-date">
                    <c:out value="${comments.postDate}"/>
                </p>
                <div class="comment-container">
                    <p><c:out value="${comments.text}"/></p>
                </div>
            </div>
        </c:forEach>
    </div>
    <c:if test="${sessionScope.user_name == comments.userName && sessionScope.user != null}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="remove_comment">
            <input type="hidden" name="movie_id" value="${movie_id}">
            <input type="hidden" name="user_name" value="${comments.userName}">
            <input type="hidden" name="comment" value="${comments.text}">
            <input class="remove" type="submit" value="remove">
        </form>
    </c:if>
    <c:if test="${sessionScope.admin != null}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="remove_comment">
            <input type="hidden" name="movie_id" value="${movie_id}">
            <input type="hidden" name="user_name" value="${comments.userName}">
            <input type="hidden" name="comment" value="${comments.text}">
            <input class="remove" type="submit" value="remove">
        </form>
    </c:if>
</div>
</body>
</html>
