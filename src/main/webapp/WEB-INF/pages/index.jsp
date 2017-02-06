<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Log In</title>
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
            <a class="navbar-brand" href="/" target="_blank">Product Madness - A1QA (pre-alpha)</a>
        </div>
    </nav>

    <div class="loginBlock">
        <div class="container-fluid">
            <h1>Please enter your personal information</h1>
            <c:url var="addAction" value="/login"/>
            <form:form action="${addAction}" commandName="report" data-toggle="validator" role="form">
                <div class="form-group">
                        <div <c:if test="${not empty wrongPass}">class="form-group has-error has-feedback"</c:if>>
                            <c:if test="${not empty wrongPass}">
                                <label class="control-label" for="inputLogin">
                                    Sorry, your username and password are incorrect - please try again.</label>
                            </c:if>
                            <label for="inputLogin">Login</label>
                            <form:input path="person" class="form-control" placeholder="e.example" id="inputLogin" required="true"/>
                            <label for="inputPassword">Password</label>
                            <form:input path="password" type="password" class="form-control" id="inputPassword" required="true"/>
                        </div>
                </div>
                <div>
                    <button type="submit" class="btn btn-default">Save</button>
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
