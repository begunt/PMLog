<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Log In</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/morris.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-index.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login-style.css">
<head>
<body>
  <div class="login">
      <h1>Test</h1>
      <c:url var="addAction" value="/login"/>
      <form:form action="${addAction}" commandName="report" data-toggle="validator" role="form">
         <c:if test="${not empty wrongPass}">
            <label class="control-label" for="inputLogin">
                Sorry, your username and password are incorrect - please try again.</label>
        </c:if>
        <form:input  path="person" type="text"
                     placeholder="e.example" id="inputEmail"
                     required="true"/>
        <button class="btn btn-primary btn-block btn-large"  type="submit">Login</button>
    </form:form>
  </div>
  <footer class="navbar-fixed-bottom">
      <div class="container-fluid">
          <span>Product Madness - A1QA</span>
      </div>
  </footer>
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
