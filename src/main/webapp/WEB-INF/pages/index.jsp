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
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-index.css"/>
    <link href="https://fonts.googleapis.com/css?family=Nunito+Sans" rel="stylesheet">
<style>
    .container-background{
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
        background: linear-gradient(45deg, rgba(128,0,128,1) 0%, rgba(128,0,128,1) 6%, rgba(6,0,6,1) 67%, rgba(0,0,0,1) 70%);
        min-height: 100%;
    }
    .gradient-bg{
        background: linear-gradient(45deg, rgba(128,0,128,1) 0%, rgba(128,0,128,1) 6%, rgba(6,0,6,1) 67%, rgba(0,0,0,1) 70%);
        color: white!important;
    }
    input[type="email"].form-control::-webkit-input-placeholder {
        color: whitesmoke;
        opacity: 0.5;
    }
    input:focus::-webkit-input-placeholder {
        transition: text-indent 0.5s 0.5s ease;
        text-indent: -100%;
        opacity: 1;
    }
</style>

    <head>
<body>
<div class="container-background" >

    <div class="panel-heading">
        <div class="page-header" style="text-align: center">
            <div style="height: 51px; font-size: 36px; color: white">
                <span class="element"></span>
            </div>

        </div>
    </div>
    <div class="container">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="panel panel-default gradient-bg">
                <div class="panel-heading gradient-bg">
                    <h4>Please enter your personal information</h4>
                </div>
                <div></div>
                <div class="panel-body">
                    <c:url var="addAction" value="/login"/>
                    <form:form action="${addAction}" commandName="report" data-toggle="validator" role="form">
                        <div class="input-group input-group-lg">
                                <%--<label for="login">Логин jira.a1qa</label>
                                <input class="form-control" placeholder="e.example" id="login" required="1"/>
                                <label for="password">Пароль jira.a1qa</label>
                                <input type="password" class="form-control" id="password" required="1"/>--%>

                            <c:if test="${not empty wrongPass}">
                                <label class="control-label" for="inputLogin">
                                    Sorry, your username and password are incorrect - please try again.</label>
                            </c:if>

                            <form:input  path="person" type="email" class="form-control gradient-bg" aria-describedby="basic-addon2"
                                        placeholder="address.example@productmadness.com" id="inputEmail"
                                        required="true"/>
                            <span class="input-group-btn">
                                       <button class="btn btn-default gradient-bg" type="submit">Login</button>
                                     </span>
                            <form:input path="password" type="password" class="form-control" id="inputPassword" required="true"/>

                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<!-- Typed JS -->
<script src="${pageContext.request.contextPath}/js/typed.js-master/js/typed.js"></script>
<script>
    $(function(){
        $(".element").typed({
            strings: ["Product Madness", "A1QA"],
            typeSpeed: 50,
            backSpeed: 50,
            loop: true,
            cursorChar: "|",
            backDelay: 1000

        });
    });
</script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="${pageContext.request.contextPath}/js/plugins/morris/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/morris/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/morris/morris-data.js"></script>
<script src="${pageContext.request.contextPath}/js/indexPage.js"></script>
</body>

</html>
