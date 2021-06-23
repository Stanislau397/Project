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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<nav>
    <div class="navigation">
        <div class="dropdown">
            <h1>MOVIE<span>APP</span></h1>

                <form class="search-movie" action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="search_movie">
                    <input type="text" name="key_word" placeholder="<fmt:message key="label.search_movie"/>">
                    <button class="icon" type="submit"><i class="fa fa-search"></i></button>
                </form>

            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="display_all_actors">
                <button type="submit">Test</button>
            </form>

            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="open_home_page">
                <button type="submit"><fmt:message key="text.label.main"/></button>
            </form>

            <div class="movies">
                <button><fmt:message key="label.movies"/></button>
                <ul>
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=newest_movies"><fmt:message key="label.new_movies"/></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=show_all_movies"><fmt:message key="label.all_movies"/></a>
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
                </c:when>
                <c:when test="${sessionScope.admin != null}">
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="to_admin_cabinet">
                            <button type="submit"><fmt:message key="label.cabinet"/></button>
                        </form>
                </c:when>
                <c:when test="${sessionScope.user != null}">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_user_profile">
                        <button type="submit"><fmt:message key="label.profile"/></button>
                    </form>
                </c:when>
            </c:choose>

            <div class="language">
                <button><fmt:message key="label.language"/></button>
                <ul>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="change_locale">
                            <button class="btn" type="submit" name="language" value="en"><fmt:message
                                    key="label.en"/></button>
                        </form>
                    </li>
                    <li>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="change_locale">
                            <button class="btn" type="submit" name="language" value="ru"><fmt:message
                                    key="label.ru"/></button>
                        </form>
                    </li>
                </ul>
            </div>

            <c:if test="${sessionScope.admin != null || sessionScope.user != null}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="sign_out">
                    <button type="submit"><fmt:message key="label.logout"/></button>
                </form>
            </c:if>
        </div>
    </div>
</nav>
</body>
</html>
