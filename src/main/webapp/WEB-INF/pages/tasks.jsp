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
        var idProject = 1;
        function selectProject() {
            var e = document.getElementById("selectIdProject");
            document.getElementById("fields_of_project_id_" + idProject).style.cssText = "display: none;";
            idProject = e.options[e.selectedIndex].value;
            document.getElementById("fields_of_project_id_" + idProject).style.cssText = "display: block;";
        }
    </script>
<head>
<body>
<div id="wrapper">
    <c:import url="template.jsp"></c:import>

    <div id="page-wrapper">
        <div class="container-fluid">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Добавить</button>

            <div id="listTasks"><h1>Список активностей</h1></div>

            <%--<c:if test="${!empty listTasks}">--%>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th width="80">Активность</th>
                            <th width="160">Описание</th>
                            <th width="60">Время</th>
                            <th width="60" colspan="2"> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--<c:forEach items="${listTasks}" var="task">--%>
                            <tr>
                                <td>Issue investigation</td>
                                <td>Исследование</td>
                                <td>30</td>
                                <td>
                                    <%--<button type="button" class="btn btn-primary" id="edit"
                                            style="margin-left: 0px;" onClick='location.href="<c:url
                                            value="/taskController/edit/${task.idTask}"/>"'>
                                        Редактировать
                                    </button>--%>
                                        <button type="button" class="btn btn-primary" id="edit"
                                                style="margin-left: 0px;">
                                            Редактировать
                                        </button>
                                </td>
                                <td>
                                    <%--<button type="button" id="delete" class="btn btn-primary"
                                            onClick='location.href="<c:url
                                                    value="/taskController/remove/${task.idTask}"/>"'>
                                        Удалить
                                    </button>--%>
                                        <button type="button" id="delete" class="btn btn-primary">
                                            Удалить
                                        </button>
                                </td>
                        </tr>
                       <%-- </c:forEach>--%>
                        </tbody>
                    </table>
                </div>

            <!-- Попап создания -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <%--<c:if test="${empty task.name}">--%>
                                <h4 class="modal-title" id="myModalLabel">Создание активноcти</h4>
                            <%--</c:if>--%>
                            <%--<c:if test="${!empty task.name}">
                                <h4 class="modal-title" id="myModalLabel">Редактирование активнсоти</h4>
                            </c:if>--%>
                        </div>
                        <div class="modal-body">
                            <c:url var="addAction" value="/projectController/projects/add"/>
                            <form:form action="${addAction}" commandName="project" class="form-horizontal">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="selectIdProject" class="col-sm-2 control-label" style="width: 187px; text-align: left; ">Project</label>
                                        <div class="col-sm-10">
                                            <form:select style="width: 270px" path="idProject" class="form-control" id="selectIdProject" onchange="selectProject()">
                                                <form:option value="0">Select project</form:option>
                                                <c:forEach items="${listProjects}" var="project">
                                                    <form:option
                                                            value="${project.idProject}">${project.name}</form:option>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                    </div>
                                    <c:forEach items="${listProjects}" var="selectedProject">
                                        <div id="fields_of_project_id_${selectedProject.idProject}" style="display: none;">
                                            <c:forEach items="${selectedProject.customFields}" var="field">
                                                <div class="form-group">
                                                    <c:if test="${field.idType == 1}">
                                                        <c:if test="${field.name == 'The number of checked defects' || field.name == 'The number of reopened defects' || field.name == 'Link to the filter with reopened defects'}">
                                                            <label  for="input_${field.name}" class="col-sm-2 control-label bugVerification" style="width: 300px; text-align: left; display:none;">${field.name}</label>
                                                            <div class="col-sm-10 bugVerification" style="display:none">
                                                                <input style="width: 270px" class="form-control" id="input_${field.name}"
                                                                       required="1"/>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${field.name == 'The number of checked stories' || field.name == 'The number of reopened stories' || field.name == 'Link to the filter with reopened stories'}">
                                                            <label  for="input_${field.name}" class="col-sm-2 control-label storyRegression" style="display:none; width: 300px; text-align: left; ">${field.name}</label>
                                                            <div class="col-sm-10">
                                                                <input style="width: 270px; display:none" class="form-control storyRegression" id="input_${field.name}"
                                                                       required="1"/>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${field.name == 'The number of checked cases' || field.name == 'Milestone' || field.name == 'Test runs'}">
                                                            <label  for="input_${field.name}" class="col-sm-2 control-label fullPass" style="display:none; width: 300px; text-align: left; ">${field.name}</label>
                                                            <div class="col-sm-10">
                                                                <input style="width: 270px; display:none" class="form-control fullPass" id="input_${field.name}"
                                                                       required="1"/>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${field.name != 'The number of checked defects' && field.name != 'The number of reopened defects' && field.name != 'Link to the filter with reopened defects'
                                                        && field.name != 'The number of checked stories' && field.name != 'The number of reopened stories' && field.name != 'Link to the filter with reopened stories'
                                                        && field.name != 'The number of checked cases' && field.name != 'Milestone' && field.name != 'Test runs'}">
                                                            <label  for="input_${field.name}" class="col-sm-2 control-label" style="width: 300px; text-align: left; ">${field.name}</label>
                                                            <div class="col-sm-10">
                                                                <input style="width: 270px" class="form-control" id="input_${field.name}"
                                                                        required="1"/>
                                                            </div>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${field.idType == 2}">
                                                        <label for="select_${field.name}" class="col-sm-2 control-label" style="width: 300px; text-align: left; ">${field.name}</label>
                                                        <div class="col-md-10" >
                                                            <select style="width: 270px" class="form-control" id="select_${field.name}">
                                                                <c:if test="${field.name == 'Activity'}">
                                                                    <option value="none">Select activity</option>
                                                                </c:if>
                                                                <c:forEach items="${listDropdown}" var="dropdownItem">
                                                                    <c:if test="${dropdownItem.idField == field.idField}">
                                                                        <option value="${dropdownItem.itemName}">${dropdownItem.itemName}</option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>

                                                        </div>
                                                    <c:if test="${field.name == 'Devices'}">
                                                        <div class="col-md-1">
                                                            <input class="btn btn-primary" type = "button" id="addButton" value="Добавить" style="border-left-width: 0px;margin-left: 70px;"/>
                                                        </div>
                                                        <div class="col-md-10">
                                                            </br>
                                                            <input type = "text" id="usersDevices" value="" style="width: 470px" class="form-control"/>
                                                        </div>
                                                    </c:if>

                                                    </c:if>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть
                                        </button>
                                        <button type="submit" class="btn btn-default" >Создать
                                        </button>
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