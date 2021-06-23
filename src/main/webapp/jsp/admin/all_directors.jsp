<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.directors"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.directors"/></h2>
    </div>
    <div class="counter">
        <c:choose>
            <c:when test="${requestScope.directors_list != null}">
                <p>(<c:out value="${requestScope.directors_list.size()}"/>)</p>
            </c:when>
            <c:when test="${requestScope.directors_by_key_words_list != null}">
                <p>(<c:out value="${requestScope.directors_by_key_words_list.size()}"/>)</p>
            </c:when>
        </c:choose>
    </div>
    <div class="search">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="search_directors">
            <input type="text" name="key_word" placeholder="<fmt:message key="label.find_director"/>">
            <input type="submit" value="<fmt:message key="label.search"/>">
        </form>
    </div>
</div>
<div class="main-content">
    <div class="add">
        <a class="button" href="#pop">
            <button class="add-btn"><fmt:message key="label.add_director"/></button>
        </a>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <div id="pop" class="overlay2">
                <div class="pop">
                    <input type="hidden" name="command" value="add_director">
                    <div class="text">
                        <h2 style="color: white"><fmt:message key="label.add_director"/></h2>
                        <input type="text" name="first_name"
                               placeholder="<fmt:message key="label.first_name"/>">
                        <input type="text" name="last_name"
                               placeholder="<fmt:message key="label.last_name"/>">
                    </div>
                    <div class="buttons">
                        <div class="remove">
                            <button type="submit" style="background-color: #1a191f"><fmt:message
                                    key="label.add"/></button>
                        </div>
                        <div class="dismiss">
                            <button style="background-color: #1a191f; margin-left: 14px"><a class="close"
                                                                                            href="#"><fmt:message
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
            <th><fmt:message key="label.first_name"/></th>
            <th><fmt:message key="label.last_name"/></th>
            <th><fmt:message key="label.id"/></th>
            <th><fmt:message key="label.operation"/></th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${requestScope.directors_list != null}">
                <c:forEach items="${requestScope.directors_list}" var="allDirectors" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td><c:out value="${allDirectors.firstName}"/></td>
                        <td><c:out value="${allDirectors.lastName}"/></td>
                        <td><c:out value="${allDirectors.directorId}"/></td>
                        <td>
                            <div class="edit-actor">
                                <a class="button" href="#pop${counter.count}">
                                    <button class="edit-btn"><i class="fa fa-edit"></i></button>
                                </a>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <div id="pop${counter.count}" class="overlay1">
                                        <div class="pop">
                                            <input type="hidden" name="command" value="edit_director">
                                            <input type="hidden" name="director_id" value="${allDirectors.directorId}">
                                            <div class="text">
                                                <h2 style="color: white"><fmt:message key="label.edit_director"/></h2>
                                                <input type="text" name="first_name"
                                                       value="${allDirectors.firstName}">
                                                <input type="text" name="last_name"
                                                       value="${allDirectors.lastName}">
                                            </div>
                                            <div class="buttons">
                                                <div class="remove">
                                                    <button type="submit" style="background-color: #1a191f"><fmt:message
                                                            key="label.change"/></button>
                                                </div>
                                                <div class="dismiss">
                                                    <button style="background-color: #1a191f; margin-left: 14px"><a
                                                            class="close" href="#"><fmt:message
                                                            key="label.close"/></a>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="remove-actor">
                                <a class="button" href="#popup${counter.count}">
                                    <button class="remove-btn"><i class="fa fa-trash"></i></button>
                                </a>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <div id="popup${counter.count}" class="overlay3">
                                        <div class="pop">
                                            <input type="hidden" name="command" value="remove_director">
                                            <input type="hidden" name="director_id" value="${allDirectors.directorId}">
                                            <div class="text">
                                                <h2 style="color: white"><fmt:message key="label.delete_director"/></h2>
                                                <p class="text"><fmt:message key="label.delete_director_msg"/></p>
                                            </div>
                                            <div class="buttons">
                                                <div class="remove">
                                                    <button type="submit" style="background-color: #1a191f"><fmt:message
                                                            key="label.remove"/></button>
                                                </div>
                                                <div class="dismiss">
                                                    <button style="background-color: #1a191f; margin-left: 14px"><a
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
            </c:when>
            <c:when test="${requestScope.directors_by_key_words_list != null}">
                <c:forEach items="${requestScope.directors_by_key_words_list}" var="directorsByKeyWords" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td><c:out value="${directorsByKeyWords.firstName}"/></td>
                        <td><c:out value="${directorsByKeyWords.lastName}"/></td>
                        <td><c:out value="${directorsByKeyWords.directorId}"/></td>
                        <td>
                            <div class="edit-actor">
                                <a class="button" href="#pop${counter.count}">
                                    <button class="edit-btn"><i class="fa fa-edit"></i></button>
                                </a>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <div id="pop${counter.count}" class="overlay1">
                                        <div class="pop">
                                            <input type="hidden" name="command" value="edit_director">
                                            <input type="hidden" name="director_id" value="${directorsByKeyWords.directorId}">
                                            <div class="text">
                                                <h2 style="color: white"><fmt:message key="label.edit_director"/></h2>
                                                <input type="text" name="first_name"
                                                       value="${directorsByKeyWords.firstName}">
                                                <input type="text" name="last_name"
                                                       value="${directorsByKeyWords.lastName}">
                                            </div>
                                            <div class="buttons">
                                                <div class="remove">
                                                    <button type="submit" style="background-color: #1a191f"><fmt:message
                                                            key="label.change"/></button>
                                                </div>
                                                <div class="dismiss">
                                                    <button style="background-color: #1a191f; margin-left: 14px"><a
                                                            class="close" href="#"><fmt:message
                                                            key="label.close"/></a>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="remove-actor">
                                <a class="button" href="#popup${counter.count}">
                                    <button class="remove-btn"><i class="fa fa-trash"></i></button>
                                </a>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <div id="popup${counter.count}" class="overlay3">
                                        <div class="pop">
                                            <input type="hidden" name="command" value="remove_director">
                                            <input type="hidden" name="director_id" value="${directorsByKeyWords.directorId}">
                                            <div class="text">
                                                <h2 style="color: white"><fmt:message key="label.delete_director"/></h2>
                                                <p class="text"><fmt:message key="label.delete_director_msg"/></p>
                                            </div>
                                            <div class="buttons">
                                                <div class="remove">
                                                    <button type="submit" style="background-color: #1a191f"><fmt:message
                                                            key="label.remove"/></button>
                                                </div>
                                                <div class="dismiss">
                                                    <button style="background-color: #1a191f; margin-left: 14px"><a
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
            </c:when>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
