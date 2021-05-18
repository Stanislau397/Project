<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>detail</title>
    <jsp:include page="static/header.jsp"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail1.css">
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'>
    </script>
</head>
<body>
<div class="main">
    <c:if test="${requestScope.movie_info != null}">
        <div class="left-side">
            <div class="picture">
                <img src="${pageContext.request.contextPath}${movie_info.picture}"/>
            </div>
            <div class="rate-movie-button">

            </div>
            <c:if test="${sessionScope.user_name != null && requestScope.user_score == 0 || sessionScope.admin != null && requestScope.user_score == 0}">
                <div class="rate-movie">
                    <button><i class="fa fa-star"></i><fmt:message key="label.rate"/></button>
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
            </c:if>
            <div class="dynamic"></div>
        </div>
        <div class="middle">
            <h1>${movie_info.title}</h1>
            <h3><fmt:message key="label.movie_detail"/></h3>
            <ul>
                <li class="name"><fmt:message key="label.release_date"/></li>
                <li class="value">${movie_info.releaseDate}</li>
            </ul>
            <hr>
            <ul>
                <li class="name"><fmt:message key="label.country"/></li>
                <li class="value">${movie_info.country}</li>
            </ul>
            <hr>
            <ul>
                <li class="name"><fmt:message key="label.genre"/></li>
                <li class="value">${movie_info.genre.genreTitle}</li>
            </ul>
            <hr>
            <ul>
                <li class="name"><fmt:message key="label.director"/></li>
                <li class="value">
                    <c:forEach items="${requestScope.directors_list}" var="directors">
                        <c:out value="${directors.firstName} ${directors.lastName}"/>
                    </c:forEach>
                </li>
            </ul>
            <hr>
            <ul>
                <li class="name"><fmt:message key="label.runtime"/></li>
                <li class="value">${movie_info.runTime} <fmt:message key="label.min"/></li>
            </ul>
            <hr>
            <ul>
                <li class="name"><fmt:message key="label.summery"/></li>
                <li class="value">${movie_info.description}</li>
            </ul>
            <hr>
        </div>
    </c:if>
    <div class="right-side">
        <div class="rating">
            <c:if test="${requestScope.movie_info.rating.score != 0}">
                <c:if test="${requestScope.movie_info.rating.score >= 70}">
                    <p class="score" style="background-color: #6c3">${movie_info.rating.score}</p>
                </c:if>
                <c:if test="${requestScope.movie_info.rating.score < 70 && requestScope.movie_info.rating.score >= 40}">
                    <p class="score" style="background-color: #fc3">${movie_info.rating.score}</p>
                </c:if>
                <c:if test="${requestScope.movie_info.rating.score < 40}">
                    <p class="score" style="background-color: #f00">${movie_info.rating.score}</p>
                </c:if>
            </c:if>
            <c:if test="${movie_info.rating.score == 0}">
                <p class="no-rating"><fmt:message key="label.rating"/></p>
            </c:if>
        </div>
        <c:if test="${sessionScope.user_name != null && requestScope.user_score != 0 || sessionScope.admin != null && requestScope.user_score != 0}">
            <div class="my-score">
                <div class="my-score-text">
                    <p><fmt:message key="label.my_score"/></p>
                </div>
                <div class="inner-score">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="remove_rating">
                        <input type="hidden" name="movie_id" value="${requestScope.movie_info.movieId}">
                        <button class="btn1" type="submit"><i class="fa fa-remove"></i>
                            <p><c:out value="${user_score}"/></p></button>
                    </form>
                </div>
            </div>
        </c:if>
        <div class="actors">
            <h3><fmt:message key="label.starring"/></h3>
            <c:forEach items="${requestScope.actors_list}" var="actors">
                <p><c:out value="${actors.firstName}"/> <c:out value="${actors.lastName}"/></p>
            </c:forEach>
        </div>
    </div>
</div>
<div class="comment-section">
    <h3><fmt:message key="label.comments"/></h3>
    <c:if test="${sessionScope.user_name != null}">
    <button class="scroll-down"><i class="fa fa-plus"></i>
        <p><fmt:message key="label.write_comment"/></p></button>
    </c:if>
    <c:if test="${requestScope.comments != null}">
    <c:forEach items="${requestScope.comments}" var="comments">
    <div class="user-comment">
        <div class="user-info">
            <div class="user-picture">
                <img src="${pageContext.request.contextPath}/css/image/default_avatar.png">
            </div>
            <div class="user-name">
                <form action="${pageContext.request.contextPath}/controller" name="get">
                    <input type="hidden" name="command" value="show_user_profile">
                    <input type="hidden" name="user_name" value="${comments.userName}">
                    <button type="submit">${comments.userName}</button>
                </form>
            </div>
            <div class="upload-date">
                <p><c:out value="${comments.postDate}"/></p>
            </div>
            <c:if test="${sessionScope.user_name == comments.userName || sessionScope.admin != null}">
                    <div class="remove-comment">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="remove_comment">
                            <input type="hidden" name="movie_id" value="${movie_info.movieId}">
                            <input type="hidden" name="comment" value="${comments.text}">
                            <input type="hidden" name="user_name" value="${comments.userName}">
                            <button type="submit"><i class="fa fa-remove"></i></button>
                        </form>
                    </div>
            </c:if>
        </div>
        <div class="text">
            <p><c:out value="${comments.text}"/></p>
        </div>

        <c:if test="${comments.userName != sessionScope.user_name && sessionScope.user_name != null}">
            <div class="comment_votes">
                <div class="up_vote">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="up_vote_comment">
                        <input type="hidden" name="comment_id" value="${comments.commentId}">
                        <input type="hidden" name="movie_id" value="${movie_info.movieId}">
                        <button type="submit"><i class="fa fa-thumbs-up"></i> <fmt:message key="label.useful"/> <c:out
                                value="${comments.commentUpVotes}"/></button>
                    </form>
                </div>
                <div class="down_vote">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="down_vote_comment">
                        <input type="hidden" name="comment_id" value="${comments.commentId}">
                        <input type="hidden" name="movie_id" value="${movie_info.movieId}">
                        <button type="submit"><i class="fa fa-thumbs-down"></i> <fmt:message key="label.no"/> <c:out
                                value="${comments.commentDownVotes}"/></button>
                    </form>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.user_name == comments.userName || sessionScope.user_name == null}">
            <div class="comment_votes">
                <div class="up_vote">
                    <button><i class="fa fa-thumbs-up"></i> <fmt:message key="label.useful"/> <c:out
                            value="${comments.commentUpVotes}"/></button>
                </div>
                <div class="down_vote">
                    <button><i class="fa fa-thumbs-down"></i> <fmt:message key="label.no"/> <c:out
                            value="${comments.commentDownVotes}"/></button>
                </div>
            </div>
        </c:if>
    </div>
    </c:forEach>
    </c:if>
    <c:if test="${sessionScope.user_name != null}">
    <h3><fmt:message key="label.write_comment"/></h3>
    <div class="leave-comment">
        <div class="user-info">
            <div class="user-picture">
                <img src="${pageContext.request.contextPath}/css/image/default_avatar.png">
            </div>
            <div class="user-name">
                <form action="${pageContext.request.contextPath}/controller" name="get">
                    <input type="hidden" name="command" value="show_user_profile">
                    <button type="submit">${sessionScope.user_name}</button>
                </form>
            </div>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="leave_comment">
                <input type="hidden" name="movie_id" value="${movie_info.movieId}">
                <textarea placeholder="<fmt:message key="label.text"/>" name="comment" required></textarea>
                <button class="btn-btn" type="submit"><fmt:message key="label.leave_comment"/></button>
            </form>
        </div>
        </c:if>
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
            $('.dynamic').fadeIn().html('1').css({
                marginLeft: '350px', marginTop: '-25px', fontSize: '20px',
                fontFamily: 'sans-serif'
            });
        });
        $('.star1').mouseleave(function () {
            $('.dynamic').fadeOut();
        });
        $('.star2').mouseover(function () {
            $('.dynamic').html('2');
            $('.dynamic').show();
        });
        $('.star2').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star3').mouseover(function () {
            $('.dynamic').html('3');
            $('.dynamic').show();
        });
        $('.star3').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star4').mouseover(function () {
            $('.dynamic').html('4');
            $('.dynamic').show();
        });
        $('.star4').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star5').mouseover(function () {
            $('.dynamic').html('5');
            $('.dynamic').show();
        });
        $('.star5').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star6').mouseover(function () {
            $('.dynamic').html('6');
            $('.dynamic').show();
        });
        $('.star6').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star7').mouseover(function () {
            $('.dynamic').html('7');
            $('.dynamic').show();
        });
        $('.star7').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star8').mouseover(function () {
            $('.dynamic').html('8');
            $('.dynamic').show();
        });
        $('.star8').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star9').mouseover(function () {
            $('.dynamic').html('9');
            $('.dynamic').show();
        });
        $('.star9').mouseleave(function () {
            $('.dynamic').hide();
        });
        $('.star10').mouseover(function () {
            $('.dynamic').html('10');
            $('.dynamic').show();
        });
        $('.star10').mouseleave(function () {
            $('.dynamic').hide();
        });
    });
</script>
</html>
