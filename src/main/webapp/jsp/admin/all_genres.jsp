<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.all_genres"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.genres"/></h2>
    </div>
    <div class="counter">
        <p>(<c:out value="${requestScope.genres_list.size()}"/>)</p>
    </div>
</div>
<div class="main-content">
    <div class="add">
        <a class="button" href="#pop">
            <button class="add-btn"><fmt:message key="label.add_genre"/></button>
        </a>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <div id="pop" class="overlay1">
                <div class="pop">
                    <input type="hidden" name="command" value="add_genre">
                    <div class="text">
                        <h2 ><fmt:message key="label.add_genre"/></h2>
                        <input type="text" name="genre_title" placeholder="<fmt:message key="label.name"/>">
                    </div>
                    <div class="buttons">
                        <div class="remove">
                            <button type="submit" style="background-color: #FFF; color: black"><fmt:message
                                    key="label.add"/></button>
                        </div>
                        <div class="dismiss">
                            <button style="background-color: #FFF; margin-left: 14px"><a
                                    class="close" href="#"><fmt:message
                                    key="label.close"/></a>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
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
        <c:forEach items="${requestScope.genres_list}" var="allGenres" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><c:out value="${allGenres.genreTitle}"/></td>
                <td><c:out value="${allGenres.genreId}"/></td>
                <td>
                    <div class="remove-genre">
                        <a class="button" href="#popup${counter.count}">
                            <button class="remove-btn"><i class="fa fa-trash"></i></button>
                        </a>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <div id="popup${counter.count}" class="overlay3">
                                <div class="pop">
                                    <input type="hidden" name="command" value="remove_genre">
                                    <input type="hidden" name="genre_id" value="${allGenres.genreId}">
                                    <div class="text">
                                        <h2><fmt:message key="label.delete_genre"/></h2>
                                        <p class="text"><fmt:message key="label.delete_genre_msg"/></p>
                                    </div>
                                    <div class="buttons">
                                        <div class="remove">
                                            <button type="submit" style="color: black; background-color: #FFF"><fmt:message
                                                    key="label.remove"/></button>
                                        </div>
                                        <div class="dismiss">
                                            <button style=" margin-left: 14px; background-color: #FFF"><a
                                                    class="close" href="#"><fmt:message
                                                    key="label.close"/></a>
                                            </button>
                                        </div>
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
</html>
