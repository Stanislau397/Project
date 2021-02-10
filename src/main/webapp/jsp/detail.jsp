<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="/property/text"/>
<html lang="${language}">
<head>
    <title>ditail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css">
</head>
<header>
    <jsp:include page="static/header.jsp"/>
</header>
<body>
    <table>
        <tr>
            <th colspan="3"></th>
        </tr>

        <tr>
            <th rowspan="9" class="picture">
                <img src="${pageContext.request.contextPath}${picture}"/>
            </th>
        </tr>
        <tr>
            <td class="title">
                <p class="p-title"><fmt:message key="label.movie_detail"/> </p>

            </td>
            <td class="rating">
                <p class="p-rating">${movie_rating}</p>
            </td>
        </tr>
        <tr>
            <td class="starring">
                <ul>
                <li class="li-starring"><fmt:message key="label.starring"/> </li>
                <li class="li-starring-parameter">
                    <c:forEach items="${requestScope.actors}" var="actors">
                        ${actors},
                    </c:forEach>
                </li>
                </ul>
            </td>
        </tr>
        <tr>
            <td class="info">
                <ul>
                    <li class="li-info"><fmt:message key="label.summery"/> </li>
                    <li class="li-info-parameter">${description}</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td class="director">
               <ul>
                   <li class="director-li"><fmt:message key="label.director"/> </li>
                   <li class="director-parameter">
                       <c:forEach items="${requestScope.directors}" var="directors">
                           ${directors},
                       </c:forEach>
                   </li>
               </ul>
                <ul>
                    <li class="run"><fmt:message key="label.runtime"/> </li>
                    <li class="time">${run_time}</li>
                </ul>
                <ul>
                    <li class="country-li"><fmt:message key="label.country"/> </li>
                    <li class="country-parameter">${country}</li>
                </ul>
                <ul>
                    <li class="li-genre"><fmt:message key="label.genre"/> </li>
                    <li class="li-genre-parameter">${genre}</li>
                </ul>
                <ul>
                    <li class="pg-rating"><fmt:message key="label.rating"/> </li>
                    <li class="pg-rating-parameter">R</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td>
                <ul>
                    <li></li>
                    <li></li>
                </ul>
            </td>
        </tr>
    </table>
</body>
</html>
