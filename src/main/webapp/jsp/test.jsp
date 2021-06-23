<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <head>
        <title></title>
        <script type="text/javascript" src="js/script.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css" rel="stylesheet" />
        <script src="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/js/bootstrap-multiselect.js"type="text/javascript"></script>
        <link href="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/css/bootstrap-multiselect.css"rel="stylesheet" type="text/css" />
        <script>
            $(function () {
                $('#lstFruits').multiselect({
                    includeSelectAllOption: true
                });
                $('#btnSelected').click(function () {
                    var selected = $("#lstFruits option:selected");
                    var message = "";
                    selected.each(function () {
                        message += $(this).text() + " " + $(this).val() + "\n";
                    });
                });
            });

            $(function () {
                $('#lstActors').multiselect({
                    includeSelectAllOption: true
                });
                $('#btnSelected').click(function () {
                    var selected = $("#lstActors option:selected");
                    var message = "";
                    selected.each(function () {
                        message += $(this).text() + " " + $(this).val() + "\n";
                    });
                });
            });
        </script>
    </head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-body">
                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                        <input type="hidden" name="command" value="test">
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <select class="form-control selectpicker" size="10" name="value" id="select-country lstFruits" multiple="multiple" data-live-search="true">
                                    <option data-tokens="china" value="china">China</option>
                                    <option data-tokens="malayasia" value="maaa">Malayasia</option>
                                    <option data-tokens="singapore" value="s">Singapore</option>
                                </select>
                            </div>
                        </div>
                        <button type="submit">Sub</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
