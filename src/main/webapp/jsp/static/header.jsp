<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title>header</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;800&display=swap" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<nav>
    <div class="navigation">
        <div class="dropdown" style="position: absolute; z-index: 3">
            <div class="logo">
                <a href="${pageContext.request.contextPath}/controller?command=open_home_page">
                    <h1>MOVIE<span>APP</span></h1>
                </a>
            </div>

            <div class="search-box">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="search_movie">
                    <input type="text" name="key_word">
                    <button type="submit"><i class="fa fa-search"></i></button>
                </form>
            </div>

            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="open_home_page">
                <button type="submit"><fmt:message key="text.label.main"/></button>
            </form>

            <div class="movies">
                <button><fmt:message key="label.movies"/></button>
                <ul style="margin: 0px">
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=newest_movies"><fmt:message key="label.new_movies"/></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=show_all_movies&page=1"><fmt:message key="label.all_movies"/></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=upcoming_movies"><fmt:message key="label.coming_soon"/></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=current_year_movies"><fmt:message key="label.this_year_movies"/></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=most_rated_movies"><fmt:message key="label.most_rated"/></a>
                    </li>
                </ul>
            </div>

            <c:choose>
                <c:when test="${sessionScope.admin == null && sessionScope.user == null}">
                    <button>
                        <a href="${pageContext.request.contextPath}/jsp/login.jsp" style="margin-left: -4px"><fmt:message key="label.login"/></a>
                    </button>
                    <div class="language">
                        <button><fmt:message key="label.language"/></button>
                        <ul>
                            <li>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="change_locale">
                                    <button type="submit" name="language" value="en"><img src="${pageContext.request.contextPath}/css/image/Flag-United-Kingdom.jpg"><h4><fmt:message key="label.en"/></h4></button>
                                </form>
                            </li>
                            <li>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="change_locale">
                                    <button type="submit" name="language" value="ru"><img src="${pageContext.request.contextPath}/css/image/Flag_of_Russia.png"><h4><fmt:message key="label.ru"/></h4></button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </c:when>
            </c:choose>

            <c:if test="${sessionScope.admin == null || sessionScope.user == null}">
            </c:if>

            <c:if test="${sessionScope.admin != null || sessionScope.user != null}">
                <div class="profile-picture">
                    <button><img src="${pageContext.request.contextPath}${sessionScope.user_avatar}"></button>
                    <ul>
                        <li>
                            <div class="profile-info">
                                <div class="profile-img">
                                    <img src="${pageContext.request.contextPath}${sessionScope.user_avatar}">
                                </div>
                                <div class="user-name">
                                    <p><c:out value="${sessionScope.user_name}"/></p>
                                </div>
                            </div>
                        </li>
                        <li>
                            <c:choose>
                                <c:when test="${sessionScope.admin == null && sessionScope.user == null}">
                                    <button>
                                        <a href="${pageContext.request.contextPath}/jsp/login.jsp" style="margin-left: -4px"><fmt:message key="label.login"/></a>
                                    </button>
                                </c:when>
                                <c:when test="${sessionScope.admin != null}">
                                    <form action="${pageContext.request.contextPath}/controller" method="get">
                                        <input type="hidden" name="command" value="to_admin_cabinet">
                                        <button type="submit"><i class="fa fa-folder"></i><h4><fmt:message key="label.cabinet"/></h4></button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/controller" method="get">
                                        <input type="hidden" name="command" value="show_user_profile">
                                        <button type="submit"><i class="fa fa-user"></i><h4><fmt:message key="label.profile"/></h4></button>
                                    </form>
                                </c:when>
                                <c:when test="${sessionScope.user != null}">
                                    <form action="${pageContext.request.contextPath}/controller" method="get">
                                        <input type="hidden" name="command" value="show_user_profile">
                                        <button type="submit"><i class="fa fa-user"></i><h4><fmt:message key="label.profile"/></h4></button>
                                    </form>
                                </c:when>
                            </c:choose>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="to_user_settings">
                                <input type="hidden" name="user_name" value="${sessionScope.user_name}">
                                <button type="submit"><i class="fa fa-cog"></i><h4><fmt:message key="label.account_settings"/></h4></button>
                            </form>
                        </li>
                        <li style="border-bottom: 1px solid silver">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="sign_out">
                            <button type="submit"><i class="fa fa-sign-out"></i><h4><fmt:message key="label.logout"/></h4></button>
                            </form>
                        </li>
                        <li>
                            <button><i class="fa fa-flag"></i><h4><fmt:message key="label.language"/></h4></button>
                            <ul>
                                <li>
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="change_locale">
                                        <button type="submit" name="language" value="ru"><img src="${pageContext.request.contextPath}/css/image/Flag_of_Russia.png"><h4><fmt:message key="label.ru"/></h4></button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="change_locale">
                                        <button type="submit" name="language" value="en">
                                            <img src="${pageContext.request.contextPath}/css/image/Flag-United-Kingdom.jpg"><h4><fmt:message key="label.en"/></h4></button>
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</nav>
</body>
</html>
