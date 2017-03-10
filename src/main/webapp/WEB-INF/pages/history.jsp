<%@ page import="by.a1qa.model.Project" %>
<%@ page import="by.a1qa.model.Report" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Report reportSession = (Report) session.getAttribute("reportSession");%>

<html>
<head>
    <meta charset="utf-8">
    <title>Daily report</title>

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

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>


    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

    <!-- for picker -->

    <head>
<body>

<div class="logout">
    <a href="<c:url value="/reportController/history"/>" target="_blank"><i class="fa fa-fw fa-dashboard"></i></i>History</a>
    <a class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${reportSession.person} <b></b></a>
    <a href="<c:url value="/logout"/>"><i class="fa fa-fw fa-power-off"></i>Logout</a>
</div>


<%--<form:form action="/reportController/sent" commandName="listOfReports" class="form-horizontal">--%>
<c:if test="${empty listOfReportsFromHistory}">
    <div class="list-div"><h1>The history is empty</h1></div>
</c:if>
<c:if test="${!empty listOfReportsFromHistory}">

    <div class="list-div"><h1>History of activities</h1></div>
</c:if>

<!-- Пошла таблица -->
<c:if test="${!empty listOfReportsFromHistory}">
<table class="table" id="reportTable"  id="reportTable">

    <thead>
    <tr>
        <th style="width: 30%">Date</th>
        <th>Comment</th>
        <th style="width: 15%">Spent minutes</th>
    </tr>
    </thead>
    <tbody class="tbl-content">
    <c:forEach items="${listOfReportsFromHistory}" var="reportFromHistoty">
        <c:if test="${reportFromHistoty.person == reportSession.person}">
            <tr>
                <td>
                    ${reportFromHistoty.timestamp}
                </td>
                <td>
                    ${reportFromHistoty.comment}
                </td>
                <td>
                    <span class="getTime">${reportFromHistoty.time}</span>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
    </c:if>
</table>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- Morris Charts JavaScript -->
<script src="${pageContext.request.contextPath}/js/plugins/morris/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/morris/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/morris/morris-data.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popupForCreatingActivities.js"></script>

<script>
    $(document).ready(function(){
        $('[data-toogle="tooltip"]').tooltip();
    })
</script>


</body>
</html>