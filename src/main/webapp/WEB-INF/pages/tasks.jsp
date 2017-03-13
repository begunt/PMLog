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
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0-rc.1/themes/base/jquery-ui.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>


    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

    <!-- for picker -->


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

    <div class="logout">
        <a ><i class="fa fa-user"></i> ${reportSession.person} <b></b></a>
        <a href="<c:url value="/reportController/history"/>" target="_blank"><i class="glyphicon glyphicon-list-alt"></i></i> History</a>
        <a href="<c:url value="/logout"/>"><i class="fa fa-fw fa-power-off"></i>Logout</a>
    </div>
            <div style="display:none">
                <input id="forAddButton" value="${forAddButton}"/>

                <c:if test="${empty report.activity}">
                    <input id="mainObject" value=null/>
                </c:if>
                <c:if test="${!empty report.activity}">
                    <input id="mainObject" value='${report.activity}'/>
                </c:if>
            </div>


                <%--<form:form action="/reportController/sent" commandName="listOfReports" class="form-horizontal">--%>
            <c:if test="${empty listReports and empty fileToDownload}">
                <div class="list-div"><h1>The list of not submitted activities is empty</h1></div>
            </c:if>
            <c:if test="${!empty listReports}">

                <div class="list-div"><h1>List of activities</h1></div>
            </c:if>

            <c:if test="${not empty fileToDownload}">
                <div class="list-div"><h1>The list of activities was sent</h1></div>
            </c:if>
                <!-- Пошла таблица -->
            <c:if test="${!empty listReports}">
                    <table class="table" id="reportTable"  id="reportTable">

                            <thead>
                                <tr>
                                    <th style="width: 30%">Task</th>
                                    <th>Description</th>
                                    <th style="width: 15%">Spent minutes</th>
                                    <th style="width: 10%; text-align:center">Actions</th>
                                </tr>
                            </thead>
                        <tbody class="tbl-content">
                        <c:forEach items="${listReports}" var="report">
                            <c:if test="${report.person == reportSession.person}">
                                <tr>
                                    <td>
                                        <c:if test="${report.product == 'M0bile'}">Mobile - ${report.project} - ${report.activity}</c:if>
                                        <c:if test="${report.product != 'M0bile'}">${report.product} - ${report.project} - ${report.activity}</c:if>
                                    </td>

                                    <td>
                                        <%--<span><b>idReport:</b> ${report.idReport}</span>--%>
                                        <span><b>Timestamp:</b> ${report.timestamp}</span>
                                        <span><b>Person:</b> ${report.person}</span>
                                        <c:if test="${report.product == 'M0bile'}"><span><b>Product:</b> Mobile</span></c:if>
                                            <c:if test="${report.product != 'M0bile'}"><span><b>Product:</b> ${report.product}</span></c:if>
                                        <a href="#rest_${report.idReport} " data-toggle="collapse">More...</a>
                                        <div id="rest_${report.idReport}" class="collapse">
                                        <c:forEach items="${listProjects}" var="projectFromReport">
                                           <c:if test="${report.selectedProject == projectFromReport.idProject}">
                                                <c:forEach items="${projectFromReport.customFields}" var="field">
                                                    <c:choose>
                                                        <%--<c:when test="${field.modelFieldName == 'project'}">
                                                            <c:if test="${not empty report.project}">

                                                            </c:if>
                                                        </c:when>--%>
                                                        <c:when test="${field.modelFieldName == 'sprint'}">
                                                            <c:if test="${not empty report.sprint}">
                                                                <span><b>${field.name}:</b> ${report.sprint}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'build'}">
                                                            <c:if test="${not empty report.build}">
                                                                <span><b>${field.name}:</b> ${report.build}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'devices'}">
                                                            <c:if test="${not empty report.devices}">
                                                                <span><b>${field.name}:</b> ${report.devices}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'environment'}">
                                                            <c:if test="${not empty report.environment}">
                                                                <span><b>${field.name}:</b> ${report.environment}</span>
                                                            </c:if>
                                                        </c:when>

                                                        <%--<c:when test="${field.modelFieldName == 'time'}">
                                                            <c:if test="${not empty report.time}">

                                                            </c:if>
                                                        </c:when>--%>
                                                        <c:when test="${field.modelFieldName == 'comment'}">
                                                            <c:if test="${not empty report.comment}">
                                                                <span><b>${field.name}:</b> ${report.comment}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'link'}">
                                                            <c:if test="${not empty report.link}">
                                                                <span><b>${field.name}:</b> ${report.link}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'numberOfCheckedStories'}">
                                                            <c:if test="${report.numberOfCheckedStories != 0}">
                                                                <span><b>${field.name}:</b> ${report.numberOfCheckedStories}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'numberOfReopenedStories'}">
                                                            <c:if test="${report.numberOfReopenedStories  != 0}">
                                                                <span><b>${field.name}:</b> ${report.numberOfReopenedStories}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'linkToReopenedStories'}">
                                                            <c:if test="${not empty report.linkToReopenedStories}">
                                                                <span><b>${field.name}:</b> ${report.linkToReopenedStories}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'numberOfCheckedDefects'}">
                                                            <c:if test="${report.numberOfCheckedDefects  != 0}">
                                                                <span><b>${field.name}:</b> ${report.numberOfCheckedDefects}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'numberOfReopenedDefects'}">
                                                            <c:if test="${report.numberOfReopenedDefects  != 0}">
                                                                <span><b>${field.name}:</b> ${report.numberOfReopenedDefects}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'linkToReopenedDefects'}">
                                                            <c:if test="${not empty report.linkToReopenedDefects}">
                                                                <span><b>${field.name}:</b> ${report.linkToReopenedDefects}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'milestone'}">
                                                            <c:if test="${not empty report.milestone}">
                                                                <span><b>${field.name}:</b> ${report.milestone}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'testruns'}">
                                                            <c:if test="${not empty report.testruns}">
                                                                <span><b>${field.name}:</b> ${report.testruns}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'numberOfCheckedCases'}">
                                                            <c:if test="${not empty report.numberOfCheckedCases}">
                                                                <c:if test="${report.numberOfCheckedCases != 0}">
                                                                    <span><b>${field.name}:</b> ${report.numberOfCheckedCases}</span>
                                                                </c:if>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'comment'}">
                                                            <c:if test="${not empty report.comment}">
                                                                <span><b>${field.name}:</b> ${report.comment}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${field.modelFieldName == 'linkToTask'}">
                                                            <c:if test="${not empty report.linkToTask}">
                                                                <span><b>${field.name}:</b> ${report.linkToTask}</span>
                                                            </c:if>
                                                        </c:when>
                                                        <c:otherwise>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                        </div>
                                    </td>
                                    <td><span class="getTime">${report.time}</span></td>
                                    <td>
                                        <p>
                                            <button data-toggle="tooltip" title="ID = ${report.idReport}" type="button" class="btn btn-primary" id="edit"
                                                style="margin-left: 0px;"
                                                onClick='location.href="<c:url
                                                        value="/reportController/edit/${report.idReport}"/>"'>
                                                <span class="glyphicon glyphicon-erase gly-margin"></span>Edit
                                            </button>
                                        </p>
                                        <button type="button" id="delete" class="btn btn-danger for-delete"
                                                onClick='location.href="<c:url
                                                        value="/reportController/remove/${report.idReport}"/>"'>
                                            <span class="glyphicon g glyphicon-remove-circle gly-margin"></span>Delete
                                        </button>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                        </c:if>
                    </table>
    <div class="total-time"><span>Total time: </span><span id="totalTime"></span></div>
            <div class="buttons-div">
                <c:if test="${empty report.activity}">
                     <button id="activityAddBtn" type="button" class="btn btn-primary for-add hide-add-btn" data-toggle="modal"
                        data-target="#myModal" data-backdrop="static" >
                         <span class="glyphicon glyphicon-plus gly-margin"></span>Add
                     </button>
                <%--onclick="openPopup()"--%>
                </c:if>

                <c:if test="${!empty report.activity}">
                    <button data-backdrop="static" id="activityUpBtn" type="button" class="btn btn-primary for-add hide-add-btn"
                         onClick='location.href="<c:url value="/reportController/updateAddButton"/>"'>
                        <span class="glyphicon glyphicon-plus gly-margin"></span>Add
                    </button>
                </c:if>

                <c:if test="${!empty listReports}">
                     <button type="button" class="btn btn-success hide-add-btn" id="edit" style="margin-left: 0px;"
                        onClick='location.href="<c:url
                                value="/reportController/sent"/>"'>
                         <span class="glyphicon glyphicon-export gly-margin"></span>Submit
                    </button>
                </c:if>
                <c:if test="${not empty fileToDownload}">
                    <button type="button" class="btn btn-primary download-xls" id="edit" style="margin-left: 0px;"
                            onClick='location.href="<c:url
                                    value="/reportController/files/${fileToDownload}"/>"'>Download .xls
                    </button>
                </c:if>
            </div>



            <!-- Попап создания -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="color: black">
                <div class="modal-dialog" role="document">
                    <div class="modal-content bg-color" style="padding: 10px;">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <c:if test="${empty report.activity}">
                                <h4 class="modal-title" id="myModalLabel">Creation activity</h4>
                            </c:if>
                            <c:if test="${!empty report.activity}">
                                <h4 class="modal-title" id="myModalLabel">Editing activity</h4>
                            </c:if>
                        </div>
                            <form:form action="/reportController/reports/add" id="reportForm" commandName="report"
                                       class="form-horizontal">

                                <!--Dev fields-->
                                <form:input path="person" style="display: none;"
                                            value="${reportSession.person}"/>
                                <form:input path="idReport" style="display: none;" value="${report.idReport}"/>
                                <!--Dev field end-->

                                <!-- Select Product -->
                                <p>
                                    <div class="form-group">
                                        <label data-toggle="tooltip" title="${field.tooltip}" for="selectIdProject" class="col-sm-3 control-label al-center">

                                            Product *
                                            <script>
                                                console.log("${field.required}");
                                            </script>

                                        </label>
                                        <div class="col-sm-9">
                                            <form:select path="product" data-style="forData" cssClass="selectpicker isRequired_True"
                                                     id="selectIdProject" required="required"> <%--onchange="selectProject()"--%>
                                                <option style="display: none"></option>
                                                    <c:forEach items="${listProjects}" var="project">
                                                        <!--K0STYL'-->
                                                        <c:if test="${project.name == 'M0bile'}">
                                                            <form:option
                                                                value="${project.name}">Mobile
                                                            </form:option>
                                                        </c:if>

                                                        <c:if test="${project.name != 'M0bile'}">
                                                            <form:option
                                                                value="${project.name}">${project.name}
                                                            </form:option>
                                                        </c:if>
                                                    </c:forEach>
                                            </form:select>
                                        </div>
                                    </div>
                                </p>
                                <!-- Select Product End -->

                                    <c:forEach items="${listProjects}" var="selectedProject">
                                        <div id="fields_of_project_id_${selectedProject.name}" class="fields_of_project"style="display: none;">
                                        <c:forEach items="${selectedProject.customFields}" var="field">
                                             <div class="${field.dependsOnActivity} form-group">
                                                <c:if test="${field.idType == 1}">

                                                 <%--   <c:if test="${field.name == 'The number of checked defects' || field.name == 'The number of reopened defects' || field.name == 'Link to the filter with reopened defects'}">
                                                          <label data-toggle="tooltip" title="${field.tooltip}" for="input_${field.name}" class="col-sm-3 control-label bugVerification al-center" style="display:none;">${field.name}</label>
                                                          <div class="col-sm-9">
                                                              <form:input class="fields_of_project_id_${selectedProject.name} bugVerification" id="input_${field.name}"
                                                                          path="${field.modelFieldName}"/>
                                                          </div>
                                                      </c:if>
                                                      <c:if test="${field.name == 'The number of checked stories' || field.name == 'The number of reopened stories' || field.name == 'Link to the filter with reopened stories'}">
                                                          <label data-toggle="tooltip" title="${field.tooltip}" for="input_${field.name}" class="col-sm-3 control-label storyRegression al-center" style="display:none;">${field.name}</label>
                                                          <div class="col-sm-9">
                                                              <form:input class="storyRegression fields_of_project_id_${selectedProject.name}" id="input_${field.name}"
                                                                          path="${field.modelFieldName}"/>
                                                          </div>
                                                      </c:if>
                                                      <c:if test="${field.name == 'The number of checked cases' || field.name == 'Milestone' || field.name == 'Test runs'}">
                                                          <label data-toggle="tooltip" title="${field.tooltip}" for="input_${field.name}" class="col-sm-3 control-label fullPass al-center" style="display:none;">${field.name}</label>
                                                          <div class="col-sm-9">
                                                              <form:input class="fullPass fields_of_project_id_${selectedProject.name}" id="input_${field.name}"
                                                                          path="${field.modelFieldName}"/>
                                                          </div>
                                                      </c:if>
                                                    <c:if test="${field.name != 'The number of checked defects' && field.name != 'The number of reopened defects' && field.name != 'Link to the filter with reopened defects'
                                                   && field.name != 'The number of checked stories' && field.name != 'The number of reopened stories' && field.name != 'Link to the filter with reopened stories'
                                                    && field.name != 'The number of checked cases' && field.name != 'Milestone' && field.name != 'Test runs'}">

--%>
                                                    <label data-toggle="tooltip" title="${field.tooltip}" for="input_${field.name}" class="${field.dependsOnActivity} col-sm-3 control-label al-center">${field.name} <c:if test="${field.required == true}"> * </c:if> </label>

                                                    <div class="col-sm-9">

                                                            <form:input class="${field.dependsOnActivity} fields_of_project_id_${selectedProject.name} isRequired_${field.required}" id="input_${field.name}"
                                                                path="${field.modelFieldName}"/>

                                                    </div>
                                                    </c:if>
                                                <%--</c:if>--%>

                                                <c:if test="${field.idType == 2}">
                                                        <label data-toggle="tooltip" title="${field.tooltip}" for="select_${field.name}" class="col-sm-3 control-label al-center">${field.name} <c:if test="${field.required == true}"> * </c:if> </label>

                                                        <div class="col-sm-9">
                                                            <form:select data-select-on-tab="true" path="${field.modelFieldName}" data-style="forData" class="selectpicker fields_of_project_id_${selectedProject.name} isRequired_${field.required}" id="select_${field.name}">
                                                                    <c:forEach items="${listDropdown}" var="dropdownItem">
                                                                        <c:if test="${dropdownItem.idField == field.idField}">
                                                                            <form:option value="${dropdownItem.itemName}">${dropdownItem.itemName}</form:option>
                                                                        </c:if>
                                                                     </c:forEach>
                                                            </form:select>
                                                        </div>
                                                </c:if>

                                                <c:if test="${field.idType == 3}">
                                                        <label data-toggle="tooltip" title="${field.tooltip}" for="select_${field.name}_${selectedProject.name}" class="col-sm-3 control-label al-center">${field.name}  <c:if test="${field.required == true}"> * </c:if> </label>

                                                        <div class="col-sm-9">
                                                            <c:if test="${field.name == 'Devices'}">
                                                                <select id="devices-to-grab" style="display: none;">   <%--<option id="custom-device-Name" title="Other Device">Other Device</option>--%>
                                                                    <c:forEach items="${listDropdown}" var="dropdownItem">
                                                                            <c:if test="${dropdownItem.idField == field.idField}">
                                                                             <option value="${dropdownItem.itemName}">${dropdownItem.itemName}</option>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                </select>
                                                                <form:input path="${field.modelFieldName}" type="text"
                                                                            id="users_${field.name}_${selectedProject.name}"
                                                                            value=""
                                                                            class="fields_of_project_id_${selectedProject.name} isRequired_${field.required}"/>
                                                            </c:if>

                                                            <c:if test="${field.name != 'Devices'}">
                                                                <select data-select-on-tab="true" data-style="forData" class=" selectpicker fields_of_project_id_${selectedProject.name} isRequired_${field.required}" id="select_${field.name}_${selectedProject.name}">
                                                                    <c:forEach items="${listDropdown}" var="dropdownItem">
                                                                        <c:if test="${dropdownItem.idField == field.idField}">
                                                                            <option value="${dropdownItem.itemName}">${dropdownItem.itemName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </select>
                                                                <div class="col-sm-9" style="display: none">
                                                                    </br>
                                                                    <form:input path="${field.modelFieldName}" type="text"
                                                                                id="users_${field.name}_${selectedProject.name}"
                                                                                value=""
                                                                                class="fields_of_project_id_${selectedProject.name} isRequired_${field.required}" cssStyle="display: none"/>

                                                                </div>
                                                            </c:if>
                                                         </div>

                                                       <!-- <div class="col-md-1">
                                                            <input class="btn btn-primary" type="button" value="Add"
                                                                onclick="getUserChoice('${field.name}_${selectedProject.name}')"
                                                            />
                                                        </div>-->

                                                </c:if>

                                                <c:if test="${field.idType == 4}">
                                                    <label data-toggle="tooltip" title="${field.tooltip}" for="inputNumber_${field.name}" class="${field.dependsOnActivity} col-sm-3 control-label al-center">${field.name} <c:if test="${field.required == true}"> * </c:if> </label>

                                                    <div class="col-sm-9">
                                                        <form:input type="number" class="${field.dependsOnActivity} fields_of_project_id_${selectedProject.name} isRequired_${field.required}" id="inputNumber_${field.name}" path="${field.modelFieldName}"/>
                                                    </div>

                                                </c:if>

                                                <c:if test="${field.idType == 5}">
                                                    <p>
                                                    <label data-toggle="tooltip" title="${field.tooltip}" for="textarea_${field.name}" class="${field.dependsOnActivity} col-sm-3 control-label al-center">${field.name} <c:if test="${field.required == true}"> * </c:if> </label>

                                                    <div class="col-sm-9">
                                                        <form:textarea class="${field.dependsOnActivity} for-textarea form-control fields_of_project_id_${selectedProject.name} isRequired_${field.required}" id="textarea_${field.name}" path="${field.modelFieldName}"/> <%--required="true"--%>
                                                    </div>
                                                    </p>
                                                </c:if>

                                                <%-- <c:if test="${field.idType == 6}">
                                                     <!-- dd/MM/yyyy -->

                                                         <label data-toggle="tooltip" title="${field.tooltip}" for="date_${field.name}" class="${field.dependsOnActivity} col-sm-3 control-label al-center">${field.name}</label>

                                                        <div  class="input-group date col-sm-9">
                                                             <form:input data-toggle = "datepicker" class="${field.dependsOnActivity}fields_of_project_id_${selectedProject.name}" id="date_${field.name}" path="${field.modelFieldName}"/>

                                                                &lt;%&ndash;required="true"&ndash;%&gt;
                                                        </div>
                                                     <div class="input-append date form_datetime">
                                                         <input size="16" type="text" value="" readonly>
                                                         <span class="add-on"><i class="icon-th"></i></span>
                                                     </div>


                                                 </c:if>
--%>
                                             </div>
                                        </c:forEach>
                                        </div>
                                    </c:forEach>

                                 <div class="modal-footer">
                                    <button id="modalClose" type="button" class="btn btn-default">Close</button>
                                        <c:if test="${empty report.activity}">
                                            <button type="button" onclick="submitForm();" class="btn btn-primary" name="addButton" id="submitModal_Add" value="Add">Add</button>
                                        </c:if>
                                        <c:if test="${!empty report.activity}">
                                            <button type="button" onclick="submitForm();" class="btn btn-primary" name="editButton" id="submitModal_Edit" value="Edit">Edit</button>
                                         </c:if>
                            </form:form>
                        </div>

                    </div>
                </div>
            </div>


<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>

    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<%--<!-- Morris Charts JavaScript -->
<script src="${pageContext.request.contextPath}/js/plugins/morris/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/morris/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/morris/morris-data.js"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popupForCreatingActivities.js"></script>

            <!--done-->
<!--<script>
    $( "#select_Devices_M0bile" ).change(function() {
        var pickerValue = $( "#select_Devices_M0bile" ).val();
        var inputValue = document.getElementById('users_Devices_M0bile');
        inputValue.value = pickerValue.toString();
       console.log( 'Picker = ' + pickerValue);
       console.log('Input = ' + inputValue);
    });
</script>-->


            <script>
                $(document).ready(function(){
                    $('[data-toogle="tooltip"]').tooltip();
                })
            </script>


</body>
</html>