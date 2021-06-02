<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <title><fmt:message key="label.movies"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all_movies.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <jsp:include page="/jsp/static/admin_side_bar.jsp"/>
</head>
<body>
<div class="top">
    <div class="text1">
        <h2><fmt:message key="label.all_movies"/></h2>
    </div>
    <div class="counter">
        <p>(<c:out value="${requestScope.counter}"/>)</p>
    </div>
    <div class="search">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="search_movie_by_key_word">
            <input type="text" name="key_word" placeholder="<fmt:message key="label.search_movie"/>">
            <input type="submit" value="<fmt:message key="label.search"/>">
        </form>
    </div>
</div>
<div class="main-content">
    <table class="content-table">
        <thead>
        <tr>
            <th><fmt:message key="label.id"/></th>
            <th><fmt:message key="label.title"/></th>
            <th><fmt:message key="label.country"/></th>
            <th><fmt:message key="label.runtime"/></th>
            <th><fmt:message key="label.rating1"/></th>
            <th><fmt:message key="label.operation"/></th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${requestScope.movie_list != null}">
                <c:forEach items="${requestScope.movie_list}" var="allMovies">
                    <tr>
                        <td><c:out value="${allMovies.movieId}"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="show_movie_details">
                                <input type="hidden" name="movie_id" value="${allMovies.movieId}">
                                <button type="submit" class="movie-title-btn"><c:out value="${allMovies.title}"/></button>
                            </form>
                        </td>
                        <td><c:out value="${allMovies.country}"/></td>
                        <td><c:out value="${allMovies.runTime}"/></td>
                        <td><i class="fa fa-star" style="margin-right: 3px"></i><c:out value="${allMovies.rating.score}"/></td>
                        <td>
                            <div class="edit-movie">
                                <form action="${pageContext.request.contextPath}/controller" method="get">
                                    <input type="hidden" name="command" value="to_edit_movie">
                                    <input type="hidden" name="movie_id" value="${allMovies.movieId}">
                                    <button type="submit" class="edit-btn"><i class="fa fa-edit"></i></button>
                                </form>
                            </div>
                            <div class="remove-movie">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="delete_movie">
                                    <input type="hidden" name="movie_id" value="${allMovies.movieId}">
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:when test="${requestScope.movies_by_key_word_list != null}">
                <c:forEach items="${requestScope.movies_by_key_word_list}" var="moviesByKeyWord">
                    <tr>
                        <td><c:out value="${moviesByKeyWord.movieId}"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="show_movie_details">
                                <input type="hidden" name="movie_id" value="${moviesByKeyWord.movieId}">
                                <button type="submit" class="movie-title-btn"><c:out value="${moviesByKeyWord.title}"/></button>
                            </form>
                        </td>
                        <td><c:out value="${moviesByKeyWord.country}"/></td>
                        <td><c:out value="${moviesByKeyWord.runTime}"/></td>
                        <td><i class="fa fa-star" style="margin-right: 3px"></i><c:out value="${moviesByKeyWord.rating.score}"/></td>
                        <td>
                            <div class="edit-movie">
                                <form action="${pageContext.request.contextPath}/controller" method="get">
                                    <input type="hidden" name="command" value="to_edit_movie">
                                    <input type="hidden" name="movie_id" value="${moviesByKeyWord.movieId}">
                                    <button type="submit" class="edit-btn"><i class="fa fa-edit"></i></button>
                                </form>
                            </div>
                            <div class="remove-movie">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="delete_movie">
                                    <input type="hidden" name="movie_id" value="${moviesByKeyWord.movieId}">
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
<script>
    var myArray = [
        {'name':'Michael', 'age':'30', 'birthdate':'11/10/1989'},
        {'name':'Mila', 'age':'32', 'birthdate':'10/1/1989'},
        {'name':'Paul', 'age':'29', 'birthdate':'10/14/1990'},
        {'name':'Dennis', 'age':'25', 'birthdate':'11/29/1993'},
        {'name':'Tim', 'age':'27', 'birthdate':'3/12/1991'},
        {'name':'Erik', 'age':'24', 'birthdate':'10/31/1995'},
    ]


    buildTable(myArray)



    $('th').on('click', function(){
        var column = $(this).data('colname')
        var order = $(this).data('order')
        var text = $(this).html()
        text = text.substring(0, text.length - 1);



        if (order == 'desc'){
            myArray = myArray.sort((a, b) => a[column] > b[column] ? 1 : -1)
            $(this).data("order","asc");
            text += '&#9660'
        }else{
            myArray = myArray.sort((a, b) => a[column] < b[column] ? 1 : -1)
            $(this).data("order","desc");
            text += '&#9650'
        }

        $(this).html(text)
        buildTable(myArray)
    })





    function buildTable(data){
        var table = document.getElementById('myTable')
        table.innerHTML = ''
        for (var i = 0; i < data.length; i++){
            var colname = `name-${i}`
            var colage = `age-${i}`
            var colbirth = `birth-${i}`

            var row = `<tr>
                        <td>${data[i].name}</td>
                        <td>${data[i].age}</td>
                        <td>${data[i].birthdate}</td>
                   </tr>`
            table.innerHTML += row
        }
    }

</script>
</body>
</html>
