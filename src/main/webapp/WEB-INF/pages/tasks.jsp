<%@ page import="by.a1qa.model.Project" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!--<%Employee employeeSession = (Employee) session.getAttribute("employeeSession");%>-->--%>

<html>
<head>
    <meta charset="utf-8">
    <title>Активности</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/morris.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-index.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-info.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/component.css"/>

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script>
        /*var projectName = 'Mobile';
        function selectProject() {
            var e = document.getElementById("selectIdProject");
            console.log("selected project = " + projectName);
            document.getElementById("fields_of_project_id_" + projectName).style.cssText = "display: none;";
            var inputsForDisable = document.getElementsByClassName("fields_of_project_id_" + projectName);
            for(var j = 0; j < inputsForDisable.length; j++) {
                inputsForDisable[j].removeAttribute("required");
            }
            projectName = e.options[e.selectedIndex].value;
            document.getElementById("fields_of_project_id_" + projectName).style.cssText = "display: block;";
            var inputs = document.getElementsByClassName("fields_of_project_id_" + projectName);
            for(var i = 0; i < inputs.length; i++) {
                if(inputs[i].style.cssText != "display: none;")
                    inputs[i].setAttribute("required", "");
            }
        }*/
    </script>
<head>
<body>
<div id="wrapper">
    <c:import url="template.jsp"></c:import>

    <div id="page-wrapper">
        <div class="container-fluid">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Добавить</button>

            <div style="display:none">
                <c:if test="${empty report.activity}">
                    <input id="mainObject" value=null />
                </c:if>
                <c:if test="${!empty report.activity}">
                    <input id="mainObject" value='${report.activity}'/>
                </c:if>
            </div>

            <%--<c:if test="${!empty listOfReports}">--%>
                <div id="listTasks"><h1>Список активностей</h1></div>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th width="80">Активность</th>
                            <th width="1000">Описание</th>
                            <th width="60">Время</th>
                            <th width="60" colspan="2"> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listReports}" var="report">
                            <tr>
                                <td>${report.activity}</td>
                                <td>
                                    <%--<c:forEach items="${selectedProject.customFields}" var="field">
                                        <c:if test ="${report.${field.modelFieldName}} != ''">
                                            </br>${field.name}: ${report.${field.modelFieldName}}
                                        </c:if>
                                    </c:forEach>--%>
                                    idReport: ${report.idReport}
                                    </br>timestamp: ${report.timestamp}
                                    </br>Person: ${report.person}
                                    </br> product: ${report.product}
                                    </br>project : ${report.project}
                                    </br>sprint : ${report.sprint}
                                    </br>build : ${report.build}
                                    </br>devices : ${report.devices}
                                    </br>environment : ${report.environment}
                                    </br>time : ${report.time}
                                    </br>comment : ${report.comment}
                                    </br>link : ${report.link}
                                    </br>numberOfCheckedStories : ${report.numberOfCheckedStories}
                                    </br>numberOfReopenedStories : ${report.numberOfReopenedStories}
                                    </br>linkToReopenedStories : ${report.linkToReopenedStories}
                                    </br>numberOfCheckedDefects: ${report.numberOfCheckedDefects}
                                    </br>numberOfReopenedDefects : ${report.numberOfReopenedDefects}
                                    </br>linkToReopenedDefects : ${report.linkToReopenedDefects}
                                    </br> Milestone: ${report.milestone}
                                    </br>Testruns: ${report.testruns}
                                    </br>numberOfCheckedCases : ${report.numberOfCheckedCases}
                                    </br>Comment: ${report.comment}
                                </td>
                                <td>${report.time}</td>
                                <td>
                                    <button type="button" class="btn btn-primary" id="edit" style="margin-left: 0px;" onClick='location.href="<c:url
                                            value="/reportController/edit/${report.idReport}"/>"'>Edit
                                    </button>
                                </td>
                                <td>
                                    <button type="button" id="delete" class="btn btn-primary"
                                            onClick='location.href="<c:url value="/reportController/remove/${report.idReport}"/>"'>Delete
                                    </button>
                                </td>
                        </tr>
                       </c:forEach>
                        </tbody>
                    </table>
                </div>
            <%--</c:if>
            <c:if test="${empty listOfReports}">
                <div id="listTasks"><h1>Список активностей пуст</h1></div>
            </c:if>--%>

            <!-- Попап создания -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <c:if test="${empty report.activity}">
                                <h4 class="modal-title" id="myModalLabel">Создание активноcти</h4>
                            </c:if>
                            <c:if test="${!empty report.activity}">
                                <h4 class="modal-title" id="myModalLabel">Редактирование активнсоти</h4>
                            </c:if>
                        </div>
                        <div class="modal-body">
                            <form:form action="/reportController/reports/add" commandName="report" class="form-horizontal">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <form:input path="person" style="display: none;" value="${report.person}"/>
                                        <form:input path="idReport" style="display: none;" value="${report.idReport}"/>
                                        <label for="selectIdProject" class="col-sm-2 control-label" style="width: 187px; text-align: left; ">Product</label>
                                        <div class="col-sm-10">
                                            <form:select style="width: 270px" path="product" class="form-control" id="selectIdProject" > <%--onchange="selectProject()"--%>
                                                <form:option value="0">Select product</form:option>
                                                <c:forEach items="${listProjects}" var="project">
                                                    <form:option
                                                            value="${project.name}">${project.name}</form:option>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                    </div>
                                    <c:forEach items="${listProjects}" var="selectedProject">
                                        <div id="fields_of_project_id_${selectedProject.name}" style="display: none;">
                                            <c:forEach items="${selectedProject.customFields}" var="field">
                                                <div class="form-group">
                                                    <c:if test="${field.idType == 1}">
                                                        <c:if test="${field.name == 'The number of checked defects' || field.name == 'The number of reopened defects' || field.name == 'Link to the filter with reopened defects'}">
                                                            <label  for="input_${field.name}" class="col-sm-2 control-label bugVerification" style="width: 300px; text-align: left; display:none;">${field.name}</label>
                                                            <div class="col-sm-10">
                                                                <form:input style="width: 270px; display:none" class="form-control fields_of_project_id_${selectedProject.name} bugVerification" id="input_${field.name}"
                                                                        path="${field.modelFieldName}"/> <%--required="true"--%>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${field.name == 'The number of checked stories' || field.name == 'The number of reopened stories' || field.name == 'Link to the filter with reopened stories'}">
                                                            <label  for="input_${field.name}" class="col-sm-2 control-label storyRegression" style="display:none; width: 300px; text-align: left; ">${field.name}</label>
                                                            <div class="col-sm-10">
                                                                <form:input style="width: 270px; display:none" class="form-control storyRegression fields_of_project_id_${selectedProject.name}" id="input_${field.name}"
                                                                        path="${field.modelFieldName}"/> <%--required="true--%>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${field.name == 'The number of checked cases' || field.name == 'Milestone' || field.name == 'Test runs'}">
                                                            <label  for="input_${field.name}" class="col-sm-2 control-label fullPass" style="display:none; width: 300px; text-align: left; ">${field.name}</label>
                                                            <div class="col-sm-10">
                                                                <form:input style="width: 270px; display:none" class="form-control fullPass fields_of_project_id_${selectedProject.name}" id="input_${field.name}"
                                                                        path="${field.modelFieldName}"/> <%--required="true"--%>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${field.name != 'The number of checked defects' && field.name != 'The number of reopened defects' && field.name != 'Link to the filter with reopened defects'
                                                        && field.name != 'The number of checked stories' && field.name != 'The number of reopened stories' && field.name != 'Link to the filter with reopened stories'
                                                        && field.name != 'The number of checked cases' && field.name != 'Milestone' && field.name != 'Test runs'}">
                                                            <label for="input_${field.name}" class="col-sm-2 control-label" style="width: 300px; text-align: left; ">${field.name}</label>
                                                            <div class="col-sm-10">
                                                                <form:input style="width: 270px" class="form-control fields_of_project_id_${selectedProject.name}" id="input_${field.name}"
                                                                         path="${field.modelFieldName}"/> <%--required="true"--%>
                                                            </div>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${field.idType == 2}">
                                                        <label for="select_${field.name}" class="col-sm-2 control-label" style="width: 300px; text-align: left; ">${field.name}</label>
                                                        <div class="col-md-10" >
                                                            <form:select path="${field.modelFieldName}" style="width: 270px" class="form-control fields_of_project_id_${selectedProject.name}" id="select_${field.name}">
                                                                <form:option value="">Select value</form:option>
                                                                <c:forEach items="${listDropdown}" var="dropdownItem">
                                                                    <c:if test="${dropdownItem.idField == field.idField}">
                                                                        <form:option value="${dropdownItem.itemName}">${dropdownItem.itemName}</form:option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </form:select>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${field.idType == 3}">
                                                        <label for="select_${field.name}" class="col-sm-2 control-label" style="width: 300px; text-align: left; ">${field.name}</label>
                                                        <div class="col-md-10" >
                                                            <select style="width: 270px" class="form-control fields_of_project_id_${selectedProject.name}" id="select_${field.name}">
                                                                <option value="">Select value</option>
                                                                <c:forEach items="${listDropdown}" var="dropdownItem">
                                                                    <c:if test="${dropdownItem.idField == field.idField}">
                                                                        <option value="${dropdownItem.itemName}">${dropdownItem.itemName}</option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-1">
                                                            <input class="btn btn-primary" type = "button" value="Add" onclick="getUserChoice('${field.name}')" style="border-left-width: 0px;margin-left: 70px;"/>
                                                        </div>
                                                        <div class="col-md-10">
                                                            </br>
                                                            <form:input path="${field.modelFieldName}" type = "text" id="users_${field.name}" value="" style="width: 470px"  class="form-control fields_of_project_id_${selectedProject.name}"/>
                                                            <%--required="true"--%>
                                                        </div>
                                                    </c:if>


                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                        </button>
                                        <c:if test="${empty report.activity}">
                                            <input type="submit" class="btn btn-primary" value="Add"/>
                                        </c:if>
                                        <c:if test="${!empty report.activity}">
                                            <input type="submit" class="btn btn-primary" value="Edit"/>
                                        </c:if>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
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
<script src="${pageContext.request.contextPath}/js/popupForCreatingActivities.js"></script>


</body>
</html>