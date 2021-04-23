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
            <div class="rate-movie">
                <button><i class="fa fa-star"></i><fmt:message key="label.rate"/></button>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="rate_movie">
                    <input type="hidden" name="movie_id" value="${movie_info.movieId}">
                    <input type="radio" name="star" id="star1" value="100">
                    <label class="star10" for="star1"></label>
                    <input type="radio" name="star" id="star2" value="90">
                    <label class="star9" for="star2"></label>
                    <input type="radio" name="star" id="star3" value="80">
                    <label class="star8" for="star3"></label>
                    <input type="radio" name="star" id="star4" value="70">
                    <label class="star7" for="star4"></label>
                    <input type="radio" name="star" id="star5" value="60">
                    <label class="star6" for="star5"></label>
                    <input type="radio" name="star" id="star6" value="50">
                    <label class="star5" for="star6"></label>
                    <input type="radio" name="star" id="star7" value="40">
                    <label class="star4" for="star7"></label>
                    <input type="radio" name="star" id="star8" value="30">
                    <label class="star3" for="star8"></label>
                    <input type="radio" name="star" id="star9" value="20">
                    <label class="star2" for="star9"></label>
                    <input type="radio" name="star" id="star10" value="10">
                    <label class="star1" for="star10"></label>
                </form>
            </div>
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
                <li class="value">${movie_info.genre}</li>
            </ul>
            <hr>
            <ul>
                <li class="name"><fmt:message key="label.director"/></li>
                <li class="value">
                    <c:forEach items="${requestScope.directors_list}" var="directors">
                        <c:out value="${directors}"/>
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
                <p class="score">${movie_info.rating.score}</p>
            </c:if>
            <c:if test="${movie_info.rating.score == 0}">
                <p class="no-rating"><fmt:message key="label.rating"/></p>
            </c:if>
        </div>
        <div class="actors">
            <h3><fmt:message key="label.starring"/></h3>
            <c:forEach items="${requestScope.actors_list}" var="actors">
                <p>${actors}</p>
            </c:forEach>
        </div>
    </div>
</div>
<div class="comment-section">
    <h3><fmt:message key="label.comments"/></h3>
    <c:if test="${requestScope.comments != null}">
        <c:forEach items="${requestScope.comments}" var="comments">
            <div class="user-comment">
                <div class="user-info">
                    <div class="user-picture">
                        <img src="${pageContext.request.contextPath}/css/image/default_avatar.png">
                    </div>
                    <div class="user-name">
                        <p><c:out value="${comments.userName}"/></p>
                    </div>
                    <div class="upload-date">
                        <p><c:out value="${comments.postDate}"/></p>
                    </div>
                </div>

                <div class="text">
                    <p><c:out value="${comments.text}"/></p>
                </div>

                <div class="comment_votes">
                    <div class="up_vote">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="up_vote_comment">
                            <input type="hidden" name="comment_id" value="${comments.commentId}">
                            <input type="hidden" name="movie_id" value="${movie_info.movieId}">
                            <button type="submit"><i class="fa fa-thumbs-up"></i> Полезно <c:out value="${comments.commentUpVotes}"/></button>
                        </form>
                    </div>
                    <div class="down_vote">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="down_vote_comment">
                            <input type="hidden" name="comment_id" value="${comments.commentId}">
                            <input type="hidden" name="movie_id" value="${movie_info.movieId}">
                            <button type="submit"><i class="fa fa-thumbs-down"></i> Нет <c:out value="${comments.commentDownVotes}"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $('input[name=star]').change(function () {
            $('form').submit();
        });
        $('.star1').mouseover(function () {
            $('.dynamic').fadeIn().html('1').css({marginLeft : '350px', marginTop : '-25px', fontSize : '20px',
            fontFamily : 'sans-serif'});
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
