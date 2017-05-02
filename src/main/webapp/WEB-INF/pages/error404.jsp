<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="utf-8">
  <title>Error 404</title>
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/plugins/morris.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css" rel="stylesheet"
        type="text/css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-index.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-info.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/component.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table-style.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/modal.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
  <style type="text/css">
    @-moz-document url-prefix(){
      html {
        height: 100%;
      }
    }
  </style>
</head>
<body>
<div>
  <div class="loginBlock">
    <div class="container-fluid">
      <div class="list-div">
        <h1>Error 404</h1>
        <h1>Page not found:(</h1><br>
        <h1><a href="<c:url value="/"/>">Return to the app</a></h1>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>


</body>

</html>
