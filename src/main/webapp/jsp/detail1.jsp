<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="today" value="<%=new Date()%>"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><c:out value="${requestScope.movie_info.title}"/></title>
    <jsp:include page="static/header.jsp"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail1.css">
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'>
    </script>
</head>
<body>
<div class="top">
    <c:choose>
        <c:when test="${sessionScope.comment_removed != null}">
            <div class="message">
                <button class="exit-btn"><i class="fa fa-remove"></i></button>
                <p><fmt:message key="label.comment_removed"/></p>
                <c:remove var="comment_removed" scope="session"/>
            </div>
        </c:when>
        <c:when test="${sessionScope.comment_edited != null}">
            <div class="message">
                <button class="exit-btn"><i class="fa fa-remove"></i></button>
                <p><fmt:message key="label.comment_update"/></p>
                <c:remove var="comment_edited" scope="session"/>
            </div>
        </c:when>
    </c:choose>
    <div class="left">
        <div class="title">
            <h1><c:out value="${requestScope.movie_info.title}"/><p><fmt:formatDate
                    value="${requestScope.movie_info.releaseDate}" pattern="yyyy"/></p></h1>
        </div>
        <hr>
        <div class="user-score-container">
            <div class="user-score-title">
                <h2><fmt:message key="label.user_score"/></h2>
            </div>
            <c:choose>
                <c:when test="${requestScope.movie_info.rating.score == 0}">
                    <div class="score">
                        <p class="no-rating">tbd</p>
                    </div>
                </c:when>
                <c:when test="${requestScope.movie_info.rating.score == 100}">
                    <div class="score" style="background-color: #66cc33;">
                        <p class="value">10</p>
                    </div>
                </c:when>
                <c:when test="${requestScope.movie_info.rating.score >= 70}">
                    <div class="score" style="background-color: #66cc33;">
                        <p class="value"><c:out value="${requestScope.movie_info.rating.score / 10}"/></p>
                    </div>
                </c:when>
                <c:when test="${requestScope.movie_info.rating.score < 70 && requestScope.movie_info.rating.score >= 40}">

                    <div class="score" style="background-color: #f9c22a;">
                        <p class="value"><c:out value="${requestScope.movie_info.rating.score / 10}"/></p>
                    </div>
                </c:when>
                <c:when test="${requestScope.movie_info.rating.score < 40 && requestScope.movie_info.rating.score > 0}">
                    <div class="score" style="background-color: red;">
                        <p class="value"><c:out value="${requestScope.movie_info.rating.score / 10}"/></p>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <hr>
        <c:choose>
            <c:when test="${requestScope.movie_info.releaseDate.after(today)}">
                <div class="my-score">
                    <div class="my-score-title">
                        <h2><fmt:message key="label.review_msg"/> <c:out
                                value="${requestScope.movie_info.releaseDate}"/></h2>
                    </div>
                </div>
            </c:when>
            <c:when test="${sessionScope.user_name != null && requestScope.user_score == 0 && requestScope.movie_info.releaseDate.before(today)
             || sessionScope.admin != null && requestScope.user_score == 0 && requestScope.movie_info.releaseDate.before(today)}">
                <div class="rate-movie">
                    <button class="rate-btn"><fmt:message key="label.rate"/>
                        <i class="fa fa-arrow-right " style="margin-left: 3px"></i></button>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="rate_movie">
                        <input type="hidden" name="movie_id" value="${movie_info.movieId}">
                        <input type="submit" name="star" id="star1" value="100">
                        <label class="star10" for="star1"></label>
                        <input type="submit" name="star" id="star2" value="90">
                        <label class="star9" for="star2"></label>
                        <input type="submit" name="star" id="star3" value="80">
                        <label class="star8" for="star3"></label>
                        <input type="submit" name="star" id="star4" value="70">
                        <label class="star7" for="star4"></label>
                        <input type="submit" name="star" id="star5" value="60">
                        <label class="star6" for="star5"></label>
                        <input type="submit" name="star" id="star6" value="50">
                        <label class="star5" for="star6"></label>
                        <input type="submit" name="star" id="star7" value="40">
                        <label class="star4" for="star7"></label>
                        <input type="submit" name="star" id="star8" value="30">
                        <label class="star3" for="star8"></label>
                        <input type="submit" name="star" id="star9" value="20">
                        <label class="star2" for="star9"></label>
                        <input type="submit" name="star" id="star10" value="10">
                        <label class="star1" for="star10"></label>
                    </form>
                </div>
                <div class="dynamic">
                    <p></p>
                </div>
            </c:when>
            <c:when test="${sessionScope.user_name != null && requestScope.user_score != 0 || sessionScope.admin != null && requestScope.user_score != 0}">
                <div class="my-score">
                    <div class="my-score-title">
                        <h2><fmt:message key="label.my_score"/>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="remove_rating">
                                <input type="hidden" name="movie_id" value="${requestScope.movie_info.movieId}">
                                <button class="remove-rating" type="submit"><fmt:message key="label.remove"/></button>
                            </form>
                        </h2>
                    </div>
                    <c:choose>
                        <c:when test="${requestScope.user_score >= 70}">
                            <div class="my-score-value" style="background-color: #66cc33;">
                                <p class="value"><c:out value="${requestScope.user_score / 10}"/></p>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.user_score < 70 && requestScope.user_score >= 40}">
                            <div class="my-score-value" style="background-color: #f9c22a;">
                                <p class="value"><c:out value="${requestScope.user_score / 10}"/></p>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.user_score < 40}">
                            <div class="my-score-value" style="background-color: red;">
                                <p class="value"><c:out value="${requestScope.user_score / 10}"/></p>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </c:when>
        </c:choose>
        <div class="date">
            <c:choose>
                <c:when test="${requestScope.movie_info.releaseDate.month == 0}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.january"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 1}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.february"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 2}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.march"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 3}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.april"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 4}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.may"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 5}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.june"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 6}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.july"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 7}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.august"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 8}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.september"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 9}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.october"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 10}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.november"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
                <c:when test="${requestScope.movie_info.releaseDate.month == 11}">
                    <h2><fmt:message key="label.release_date"/><p><fmt:message key="label.december"/> <fmt:formatDate
                            value="${requestScope.movie_info.releaseDate}" pattern="dd, yyyy"/></p></p></h2>
                </c:when>
            </c:choose>
        </div>
    </div>
    <div class="right">
        <c:if test="${requestScope.movie_info.trailer != null}">
            <video controls autoplay muted
                   src="http://${requestScope.movie_info.trailer}"></video>
        </c:if>
    </div>
</div>
<div class="main">
    <c:if test="${requestScope.movie_info != null}">
        <div class="left-side">
            <div class="picture">
                <img src="http://${movie_info.picture}"/>
            </div>
        </div>
        <div class="middle">
            <h3><fmt:message key="label.movie_detail"/></h3>
            <ul>
                <li class="name"><fmt:message key="label.country"/></li>
                <li class="value">${movie_info.country}</li>
            </ul>
            <ul>
                <li class="name"><fmt:message key="label.genre"/></li>
                <li class="value">
                    <c:forEach items="${requestScope.movie_genres_list}" var="movieGenres" varStatus="counter">
                        <c:choose>
                            <c:when test="${counter.count != requestScope.movie_genres_list.size()}">
                                <c:out value="${movieGenres.genreTitle},"/>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${movieGenres.genreTitle}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </li>
            </ul>
            <ul>
                <li class="name"><fmt:message key="label.director"/></li>
                <li class="value">
                    <c:forEach items="${requestScope.directors_list}" var="directors" varStatus="counter">
                        <c:choose>
                            <c:when test="${requestScope.directors_list.size() != counter.count}">
                                <form action="${pageContext.request.contextPath}/controller" method="get">
                                    <input type="hidden" name="command" value="display_director_info">
                                    <input type="hidden" name="first_name" value="${directors.firstName}">
                                    <input type="hidden" name="last_name" value="${directors.lastName}">
                                    <input type="hidden" name="director_id" value="${directors.directorId}">
                                    <button type="submit" style="color:#060077;"><c:out
                                            value="${directors.firstName} ${directors.lastName},"/></button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="${pageContext.request.contextPath}/controller" method="get">
                                    <input type="hidden" name="command" value="display_director_info">
                                    <input type="hidden" name="first_name" value="${directors.firstName}">
                                    <input type="hidden" name="last_name" value="${directors.lastName}">
                                    <input type="hidden" name="director_id" value="${directors.directorId}">
                                    <button type="submit" style="color:#060077;"><c:out
                                            value="${directors.firstName} ${directors.lastName}"/></button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </li>
            </ul>
            <ul>
                <li class="name"><fmt:message key="label.runtime"/></li>
                <li class="value">${movie_info.runTime} <fmt:message key="label.min"/></li>
            </ul>
            <ul>
                <li class="name"><fmt:message key="label.summery"/></li>
                <li class="value">${movie_info.description}</li>
            </ul>
        </div>
    </c:if>
    <div class="right-side">
        <div class="actors">
            <h3><fmt:message key="label.starring"/></h3>
            <c:forEach items="${requestScope.actors_list}" var="actors">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="display_actor_info">
                    <input type="hidden" name="actor_id" value="${actors.actorId}">
                    <input type="hidden" name="first_name" value="${actors.firstName}">
                    <input type="hidden" name="last_name" value="${actors.lastName}">
                    <button type="submit"><c:out value="${actors.firstName}"/> <c:out
                            value="${actors.lastName}"/></button>
                </form>
            </c:forEach>
        </div>
    </div>
</div>
<div class="comment-section">
    <c:choose>
        <c:when test="${requestScope.comments.size() > 0}">
            <h3><fmt:message key="label.comments"/></h3>
        </c:when>
        <c:when test="${requestScope.movie_info.releaseDate.after(today)}">
            <h3><fmt:message key="label.review_msg"/> <c:out value="${requestScope.movie_info.releaseDate}"/></h3>
        </c:when>
        <c:when test="${requestScope.comments.size() == 0}">
            <h3><fmt:message key="label.no_comments"/></h3>
        </c:when>
    </c:choose>
    <c:if test="${sessionScope.user_name != null && requestScope.movie_info.releaseDate.before(today)}">
        <button class="scroll-down"><i class="fa fa-plus"></i>
            <p><fmt:message key="label.write_comment"/></p></button>
    </c:if>
    <c:choose>
        <c:when test="${requestScope.comments != null}">
            <c:forEach items="${requestScope.comments}" var="comments" varStatus="counter">
                <div class="user-comments">
                    <a href="${pageContext.request.contextPath}/controller?command=show_user_profile&user_name=${comments.userName}&page=1">
                        <div class="user-picture">
                            <img src="http://${comments.userAvatar}">
                        </div>
                    </a>
                    <div class="content">
                        <div class="user-name">
                            <form action="${pageContext.request.contextPath}/controller" name="get">
                                <input type="hidden" name="command" value="show_user_profile">
                                <input type="hidden" name="user_name" value="${comments.userName}">
                                <input type="hidden" name="page" value="1">
                                <button type="submit" class="user-name-btn"><h2><c:out
                                        value="${comments.userName}"/></h2></button>
                            </form>
                        </div>
                        <div class="user-comment">
                            <p><c:out value="${comments.text}"/></p>
                        </div>
                        <div class="comment-vote">
                            <p><c:out value="${comments.commentUpVotes}"/>
                                <fmt:message key="label.of"/>
                                <c:out value="${comments.countComments}"/>
                                <fmt:message key="label.found_helpful"/>
                            </p>
                            <c:choose>
                                <c:when test="${sessionScope.user != null || sessionScope.admin != null}">
                                    <c:if test="${sessionScope.user_name != comments.userName}">
                                        <form action="${pageContext.request.contextPath}/controller" method="post">
                                            <input type="hidden" name="command" value="up_vote_comment">
                                            <input type="hidden" name="movie_id"
                                                   value="${requestScope.movie_info.movieId}">
                                            <input type="hidden" name="comment_id" value="${comments.commentId}">
                                            <button class="thumb-up-btn"><i class="fa fa-thumbs-up"></i>
                                                <c:out value="${comments.commentUpVotes}"/></button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/controller" method="post">
                                            <input type="hidden" name="command" value="down_vote_comment">
                                            <input type="hidden" name="movie_id"
                                                   value="${requestScope.movie_info.movieId}">
                                            <input type="hidden" name="comment_id" value="${comments.commentId}">
                                            <button class="thumb-down-btn"><i class="fa fa-thumbs-down"></i>
                                                <c:out value="${comments.commentDownVotes}"/></button>
                                        </form>
                                    </c:if>
                                    <c:if test="${sessionScope.user_name == comments.userName}">
                                        <button class="thumb-up-btn"><i class="fa fa-thumbs-up"></i>
                                            <c:out value="${comments.commentUpVotes}"/></button>
                                        <button class="thumb-down-btn"><i class="fa fa-thumbs-down"></i>
                                            <c:out value="${comments.commentDownVotes}"/></button>
                                    </c:if>
                                </c:when>
                                <c:when test="${sessionScope.user == null || sessionScope.admin == null}">
                                    <button class="thumb-up-btn"><i class="fa fa-thumbs-up"></i>
                                        <c:out value="${comments.commentUpVotes}"/></button>
                                    <button class="thumb-down-btn"><i class="fa fa-thumbs-down"></i>
                                        <c:out value="${comments.commentDownVotes}"/></button>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <div class="upload-date">
                        <p><c:out value="${comments.postDate}"/></p>
                    </div>
                    <div class="more">
                        <c:if test="${sessionScope.admin != null || sessionScope.user_name == comments.userName}">
                            <span><i class="fa fa-ellipsis-v"></i></span>
                        </c:if>
                        <div class="dropdown-content">
                            <a class="button" href="#pop${counter.count}">
                                <button><fmt:message key="label.remove"/></button>
                            </a>
                            <div id="pop${counter.count}" class="overlay1">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="remove_comment">
                                    <input type="hidden" name="movie_id" value="${requestScope.movie_info.movieId}">
                                    <input type="hidden" name="comment" value="${comments.text}">
                                    <input type="hidden" name="user_name" value="${comments.userName}">
                                    <div class="pop">
                                        <a class="close" href="#"><i class="fa fa-close"></i></a>
                                        <div class="text">
                                            <h2><fmt:message key="label.comment_delete1"/></h2>
                                            <p><fmt:message key="label.comment_delete"/></p>
                                        </div>
                                        <div class="buttons">
                                            <div class="remove">
                                                <button class="remove-comment-btn" type="submit"><fmt:message
                                                        key="label.remove"/></button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <a class="button" href="#pop1${counter.count}">
                                <button><fmt:message key="label.edit1"/></button>
                            </a>
                            <div id="pop1${counter.count}" class="overlay2">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="edit_comment">
                                    <input type="hidden" name="text" value="${comments.text}">
                                    <input type="hidden" name="user_name" value="${comments.userName}">
                                    <div class="pop1">
                                        <a class="close" href="#"><i class="fa fa-close"></i></a>
                                        <div class="text">
                                            <h2><fmt:message key="label.edit_comment"/></h2>
                                        </div>
                                        <textarea name="updated_text"><c:out value="${comments.text}"/></textarea>
                                        <div class="buttons">
                                            <div class="remove">
                                                <button class="edit-comment-btn" type="submit"><fmt:message
                                                        key="label.edit1"/></button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <br>
        </c:when>
    </c:choose>
    <c:if test="${sessionScope.user_name != null && requestScope.movie_info.releaseDate.before(today)}">
        <h3><fmt:message key="label.write_comment"/></h3>
        <div class="leave-comment">
            <div class="user-info">
                <div class="user-picture1">
                    <img src="http://${sessionScope.user_avatar}">
                </div>
                <div class="user-name">
                    <form action="${pageContext.request.contextPath}/controller" name="get">
                        <input type="hidden" name="command" value="show_user_profile">
                        <input type="hidden" name="user_name" value="${sessionScope.user_name}">
                        <input type="hidden" name="page" value="1">
                        <button type="submit" class="user-name-button"><c:out
                                value="${sessionScope.user_name}"/></button>
                    </form>
                    <form action="${pageContext.request.contextPath}/controller" name="get">
                        <input type="hidden" name="command" value="sign_out">
                        <button type="submit" class="log-out-button"><fmt:message key="label.logout"/></button>
                    </form>
                </div>
            </div>
            <div class="comment">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="leave_comment">
                    <input type="hidden" name="movie_id" value="${requestScope.movie_info.movieId}">
                    <textarea name="comment"></textarea>
                    <button type="submit" class="comment-submit-btn"><fmt:message key="label.leave_comment"/></button>
                </form>
            </div>
        </div>
    </c:if>
    <br>
    <br>
    <jsp:include page="static/footer.jsp"/>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $('.scroll-down').click(function () {
            $("html, body").animate({scrollTop: $(document).height()}, "fast");
        });
    });
    $(document).ready(function () {
        $('.btn1').submit();
        $('.star1').mouseover(function () {
            $('.dynamic').fadeIn().html('1.0').css({
                marginLeft: '550px', marginTop: '-30px', fontSize: '35px',
                color: 'red',
                fontFamily: 'sans-serif'
            });
        });
        $('.star1').mouseleave(function () {
            $('.dynamic').fadeOut();
        });
        $('.star2').mouseover(function () {
            $('.dynamic').fadeIn().html('2.0').css({
                marginLeft: '550px', marginTop: '-30px', fontSize: '35px',
                color: 'red',
                fontFamily: 'sans-serif'
            });
        });
        $('.star2').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star3').mouseover(function () {
            $('.dynamic').fadeIn().html('3.0').css({
                marginLeft: '550px', marginTop: '-30px', fontSize: '35px',
                color: 'red',
                fontFamily: 'sans-serif'
            });
        });
        $('.star3').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star4').mouseover(function () {
            $('.dynamic').fadeIn().html('4.0').css({
                marginLeft: '550px', marginTop: '-30px', fontSize: '35px',
                color: '#f9c22a',
                fontFamily: 'sans-serif'
            });
        });
        $('.star4').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star5').mouseover(function () {
            $('.dynamic').fadeIn().html('5.0').css({
                marginLeft: '550px', marginTop: '-30px', fontSize: '35px',
                color: '#f9c22a',
                fontFamily: 'sans-serif'
            });
        });
        $('.star5').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star6').mouseover(function () {
            $('.dynamic').fadeIn().html('6.0').css({
                marginLeft: '550px', marginTop: '-30px', fontSize: '35px',
                color: '#f9c22a',
                fontFamily: 'sans-serif'
            });
        });
        $('.star6').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star7').mouseover(function () {
            $('.dynamic').fadeIn().html('7.0').css({
                marginLeft: '550px', marginTop: '-30px', fontSize: '35px',
                color: '#66cc33',
                fontFamily: 'sans-serif'
            });
        });
        $('.star7').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star8').mouseover(function () {
            $('.dynamic').fadeIn().html('8.0').css({
                marginLeft: '550px', marginTop: '-30px', fontSize: '35px',
                color: '#66cc33',
                fontFamily: 'sans-serif'
            });
        });
        $('.star8').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star9').mouseover(function () {
            $('.dynamic').fadeIn().html('9.0').css({
                marginLeft: '550px', marginTop: '-30px', fontSize: '35px',
                color: '#66cc33',
                fontFamily: 'sans-serif'
            });
        });
        $('.star9').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star10').mouseover(function () {
            $('.dynamic').fadeIn().html('10.0').css({
                marginLeft: '550px', marginTop: '-30px', fontSize: '35px',
                color: '#66cc33',
                fontFamily: 'sans-serif'
            });
        });
        $('.star10').mouseleave(function () {
            $('.dynamic').hide();
        });
    });

    $('.exit-btn').focus(function () {
        $('.message').hide().fadeOut('slow');
    });
</script>
</html>