<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Log in</title>
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
            <a class="navbar-brand" target="_blank">PM - A1QA WorkLog</a>
        </div>
    </nav>
    <div class="container-fluid root">
        <div class="panel panel-default device-form">
            <div class="panel-heading">
                <div class="panel-title">Log in</div>
            </div>
            <div class="panel-body">
                <c:url var="addAction" value="/sign_in"/>
                <form:form action="${addAction}" commandName="employee">
                    <div class="form-group">

                        <label for="aqa-login">A1QA Login</label>
                        <form:input path="aqaLogin" class="string text-left optional form-control" placeholder="i.ivanov" id="aqa-login"/>
                        <label for="aqa-password">A1QA Password</label>
                        <form:input path="aqaPassword" type="password" class="form-group password optional form-control" id="aqa-password"/>

                        <label for="pm-login">PM Login</label>
                        <form:input path="pmLogin" class="string text-left optional form-control" placeholder="ivan.ivanov" id="pm-login"/>
                        <label for="pm-password">PM Password</label>
                        <form:input path="pmPassword" type="password" class="form-group password optional form-control" id="pm-password"/>

                        <!--<label for="address">Электронный адрес</label>-->
                        <!--<input class="form-control" placeholder="address.example@productmadness.com" id="address"/>-->
                    </div>
                    <!--<div class="checkbox">
                        <input name="remember_me" type="hidden" value="0">
                        <label class="boolean optional" for="remember_me">
                            <input class="boolean optional" id="remember_me" type="checkbox" value="1">
                            Remember me
                        </label>
                    </div>-->
                    <div>
                        <button type="submit" class="btn btn-default">Log in</button>
                    </div>
                </form:form>
            </div>

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
</body>

</html>
