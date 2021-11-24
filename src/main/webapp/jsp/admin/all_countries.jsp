<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.countries"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.countries"/></h2>
    </div>
    <div class="counter">
        <p>(<c:out value="${requestScope.countries_list.size()}"/>)</p>
    </div>
    <div class="add">
        <a class="button" href="#pop">
            <button class="add-btn"><fmt:message key="label.add_country"/></button>
        </a>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <div id="pop" class="overlay1">
                <div class="pop">
                    <input type="hidden" name="command" value="add_country">
                    <div class="text">
                        <h2><fmt:message key="label.add_country"/></h2>
                        <input type="text" name="country" placeholder="<fmt:message key="label.name"/>">
                    </div>
                    <div class="buttons">
                        <div class="remove">
                            <button type="submit" style="background-color: #FFF; color: black"><fmt:message
                                    key="label.add"/></button>
                        </div>
                        <a class="close"
                           style="margin-top: -20px; margin-left: 5px"
                           href="#">
                            <i class="fa fa-close"
                               style="margin-top: 1px; margin-left: 1px; color: black">
                            </i>
                        </a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<c:choose>
    <c:when test="${sessionScope.country_successfully_added != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3>
                <fmt:message key="label.add_country_success">
                    <fmt:param value="${sessionScope.country_name}"/>
                </fmt:message>
            </h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="country_successfully_added" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.country_removed != null}">
        <div class="alert" style="background-color: #66cc33">
            <h3>
                <fmt:message key="label.remove_country_success"/>
            </h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="country_removed" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.country_already_exists != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3>
                <fmt:message key="label.country_exists">
                    <fmt:param value="${sessionScope.country_name}"/>
                </fmt:message>
            </h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="country_already_exists" scope="session"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.error != null}">
        <div class="alert" style="background-color: #eb5757">
            <h3>
                <fmt:message key="label.remove_country_error"/>
            </h3>
            <a class="close"><i class="fa fa-close"></i></a>
            <c:remove var="error" scope="session"/>
        </div>
    </c:when>
</c:choose>
<div class="main-content">
    <table class="content-table">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="label.name"/></th>
            <th><fmt:message key="label.id"/></th>
            <th><fmt:message key="label.operation"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.countries_list}" var="allCountries" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><c:out value="${allCountries.countryName}"/></td>
                <td><c:out value="${allCountries.countryId}"/></td>
                <td>
                    <div class="remove-genre">
                        <a class="button" href="#popup${counter.count}">
                            <button class="remove-btn"><i class="fa fa-trash"></i></button>
                        </a>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <div id="popup${counter.count}" class="overlay3">
                                <div class="pop">
                                    <input type="hidden" name="command" value="remove_country">
                                    <input type="hidden" name="country_id" value="${allCountries.countryId}">
                                    <div class="text">
                                        <h2><fmt:message key="label.remove_country"/></h2>
                                        <p class="text"><fmt:message key="label.delete_country_msg"/></p>
                                    </div>
                                    <div class="buttons">
                                        <div class="remove">
                                            <button type="submit" style="color: black; background-color: #FFF">
                                                <fmt:message
                                                        key="label.remove"/></button>
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
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<br>
</body>
<script type="text/javascript">
    $(".close").click(function () {
        $(this)
            .parent(".alert")
            .fadeOut();
    });
</script>
</body>
</html>
