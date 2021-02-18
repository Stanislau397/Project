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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header1.css">
</head>
<body>
<div class="header">
    <div class="inner-header">
        <div class="logo-container">
            <h1>MOVIE<span>APP</span></h1>
        </div>

        <ul class="navigation">
            <a href="${pageContext.request.contextPath}/jsp/home.jsp">
                <li><fmt:message key="label.main"/></li>
            </a>
            <a>
                <li style="color: white">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="show_all_movies">
                        <button type="submit" class="btn"><fmt:message key="label.movies"/></button>
                    </form>
                </li>
            </a>

            <c:if test="${sessionScope.admin == null && sessionScope.user == null}">
                <a href="${pageContext.request.contextPath}/jsp/login.jsp">
                    <li><fmt:message key="label.login"/></li>
                </a>
            </c:if>

            <c:choose>
                <c:when test="${sessionScope.admin != null}">
                    <a href="${pageContext.request.contextPath}/jsp/admin/admin_cabinet.jsp">
                        <li><fmt:message key="label.cabinet"/></li>
                    </a>
                </c:when>
                <c:when test="${sessionScope.user != null}">
                    <a>
                        <li style="color: white">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="show_user_profile">
                                <button type="submit" class="btn"><fmt:message key="label.profile"/></button>
                            </form>
                        </li>
                    </a>
                </c:when>
            </c:choose>

            <c:if test="${sessionScope.admin != null || sessionScope.user != null}">
                <a>
                    <li style="color: white">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="sign_out">
                            <button type="submit" class="btn"><fmt:message key="label.logout"/></button>
                        </form>
                    </li>
                </a>
            </c:if>

            <!--<a>
                <li style="color: white">
                    <form action="${pageContext.request.contextPath}/controller"
                          method="post">
                        <input type="hidden" name="command" value="change_locale"/>
                        <div class="dropdown">
                            <button class="lal" type="button" id="language"
                                    data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                <fmt:message key="label.language"/>
                            </button>
                            <div aria-labelledby="change_language">
                                <button class="tlsl" type="submit" name="language" value="en"><fmt:message key="label.en"/></button>
                                <button class=";s;" type="submit" name="language" value="ru"><fmt:message key="label.ru"/> </button>
                            </div>
                        </div>
                    </form>
                </li>
            </a>-->
        </ul>
    </div>
</div>
</body>
</html>
