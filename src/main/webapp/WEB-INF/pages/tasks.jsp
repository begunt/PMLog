<%@ page import="by.bsuir.bugTrackingSystem.model.Employee" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Employee employeeSession = (Employee) session.getAttribute("employeeSession");%>

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

<head>
<body>
<div id="wrapper">
    <c:import url="template.jsp"></c:import>

    <div id="page-wrapper">
        <div class="container-fluid">
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Добавить</button>

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
                                <h4 class="modal-title" id="myModalLabel">Создание активнсоти</h4>
                            <%--</c:if>--%>
                            <%--<c:if test="${!empty task.name}">
                                <h4 class="modal-title" id="myModalLabel">Редактирование активнсоти</h4>
                            </c:if>--%>
                        </div>
                        <div class="modal-body">
                            <%--<c:url var="addAction" value="/taskController/tasks/add"/>
                            <form:form action="${addAction}" commandName="task" class="form-horizontal">--%>
                            <form commandName="task" class="form-horizontal">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="selectSubProject" class="col-sm-2 control-label">Проект</label>
                                        <div class="col-sm-10">
                                            <select style="margin-left: 60px;" class="form-control" id="selectSubProject">
                                                <option value="Cashman">Cashman</option>
                                                <option value="Cashman">HoV</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="selectActivity" class="col-sm-2 control-label">Активность</label>
                                        <div class="col-sm-10">
                                            <select style="margin-left: 60px;" class="form-control" id="selectActivity">
                                                <option value="Communication">Communication</option>
                                                <option value="Regression">Regression</option>
                                                <option value="Testing activity">Testing activity</option>
                                                <option value="Issue investigation">Issue investigation</option>
                                                <option value="Test documentation creation">Test documentation creation</option>
                                                <option value="Review">Review</option>
                                                <option value="Project Management">Project Management</option>
                                                <option value="Other">Другое (доделать инпут)</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  for="inputBuild" class="col-sm-2 control-label">Билд</label>
                                        <div class="col-sm-10">
                                            <input style="margin-left: 60px;" class="form-control" id="inputBuild"
                                                        placeholder="1.1.111" required="1"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  for="inputDevices" class="col-sm-2 control-label">Девайсы</label>
                                        <div class="col-sm-10">
                                            <input style="margin-left: 60px;" class="form-control" id="inputDevices"
                                                   placeholder="Samsung Galaxy S7 Android 6.0.1" required="1"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="selectEnvironment" class="col-sm-2 control-label">Окружение</label>
                                        <div class="col-sm-10">
                                            <select style="margin-left: 60px;" class="form-control" id="selectEnvironment">
                                                <option value="Prod">Prod</option>
                                                <option value="UAT">UAT</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  for="inputTime" class="col-sm-2 control-label">Время</label>
                                        <div class="col-sm-10">
                                            <input style="margin-left: 60px;" class="form-control" id="inputTime" type="number"
                                                   placeholder="60" required="1"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  for="textareaСomment" class="col-sm-2 control-label">Комментарии</label>
                                        <div class="col-sm-10">
                                            <textarea style="margin-left: 60px;" class="form-control" id="textareaСomment"
                                                   placeholder="Подробное описание активности" required="1"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть
                                        </button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Создать
                                        </button>
                                        <%--<c:if test="${empty task.name}">
                                            <input type="submit" class="btn btn-primary" value="Создать"/>
                                        </c:if>
                                        <c:if test="${!empty task.name}">
                                            <input type="submit" class="btn btn-primary" value="Редактировать"/>
                                        </c:if>--%>
                                    </div>
                                </div>
                            </form>
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
<script src="${pageContext.request.contextPath}/js/loadPage.js"></script>


</body>
</html>