<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Вход</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/morris.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-index.css" />
<head>
<body>
<div>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="/" target="_blank">Product Madness - A1QA</a>
        </div>
    </nav>

    <div class="loginBlock">
        <div class="container-fluid">
            <h1>Введите личную информацию</h1>
            <c:url var="addAction" value="/login"/>
            <form:form action="${addAction}" commandName="employee" data-toggle="validator" role="form">
                <div class="form-group">
                    <label for="login">Логин jira.a1qa</label>
                    <input class="form-control" placeholder="e.example" id="login" required="1"/>
                    <label for="password">Пароль jira.a1qa</label>
                    <input type="password" class="form-control" id="password" required="1"/>
                        <label for="inputEmail">Электронный адрес</label>
                        <input type="email" class="form-control" name="email" placeholder="address.example@productmadness.com" id="inputEmail" data-error ="Введите допустимый адрес электронной почты" required/>
                        <div class="help-block with-errors"></div>
                    <label for="project">Проект</label>
                    <div>
                        <select class="form-control" id="project">
                            <option value ="Mobile">Mobile</option>
                            <option value ="Social">Social</option>
                            <option value ="Server">Server</option>
                            <option value ="Other">Other project(-s)</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="submit" class="btn btn-default">Сохранить</button>
                </div>

            </form:form>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="${pageContext.request.contextPath}/js/plugins/morris/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/morris/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/morris/morris-data.js"></script>
<script src="${pageContext.request.contextPath}/js/indexPage.js"></script>
</body>

</html>
