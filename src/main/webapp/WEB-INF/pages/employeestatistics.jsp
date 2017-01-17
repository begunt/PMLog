<%@ page import="by.bsuir.bugTrackingSystem.model.Employee" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%Employee employeeSession = (Employee) session.getAttribute("employeeSession");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>Статистика по сотрудникам</title>

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
    <script src="${pageContext.request.contextPath}/js/modernizr.custom.js"></script>

    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <meta charset="UTF-8">

    <!-- Загрузка AJAX API -->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

        google.load('visualization', '1.0', {'packages': ['corechart']});
        google.setOnLoadCallback(drawChartByCount);
        google.setOnLoadCallback(drawChartByWeight);
        google.setOnLoadCallback(drawChartBySeverityFirstEmployee);
        google.setOnLoadCallback(drawChartBySeveritySecondEmployee);
        google.setOnLoadCallback(drawLineChartByActivity);

        function drawChartByWeight() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Сотрудник');
            data.addColumn('number', 'Общий вес дефектов');

            data.addRows([
                ['Бегун Татьяна', 35],
                ['Жебит Алексей', 5],
                ['Новик Павел', 13],
                ['Козляк Роман', 7],
                ['Беломызова Дарья', 15],
                ['Кибрик Алексей', 25]
            ]);

            var options = {
                'title': 'Статистика по весу дефектов',
                'width': 1000,
                'height': 550,
                'is3D': true
            };

            var chart = new google.visualization.PieChart(document.getElementById('pie_chart_byWeight'));
            chart.draw(data, options);
        }
        function drawChartByCount() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Сотрудник');
            data.addColumn('number', 'Количество');

            data.addRows([
                ['Бегун Татьяна', 25],
                ['Жебит Алексей', 20],
                ['Новик Павел', 5],
                ['Козляк Роман', 10],
                ['Беломызова Дарья', 25],
                ['Кибрик Алексей', 15]
            ]);

            var options = {
                'title': 'Статистика по количеству заведенных дефектов',
                'width': 1000,
                'height': 550,
                'is3D': true
            };

            var chart = new google.visualization.PieChart(document.getElementById('pie_chart_byCount'));
            chart.draw(data, options);
        }
        function drawChartBySeverityFirstEmployee() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Важность');
            data.addColumn('number', 'Количество');

            data.addRows([
                ['Critical', 35],
                ['Major', 20],
                ['Average', 15],
                ['Minor', 16],
                ['Enchancement', 14]
            ]);

            var options = {
                'title': 'Статистика по важности дефектов',
                'width': 1000,
                'height': 550,
                'is3D': true
            };

            var chart = new google.visualization.PieChart(document.getElementById('pie_chart_bySeverityFirst'));
            chart.draw(data, options);
        }
        function drawChartBySeveritySecondEmployee() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Важность');
            data.addColumn('number', 'Количество');

            data.addRows([
                ['Critical', 5],
                ['Major', 10],
                ['Average', 35],
                ['Minor', 18],
                ['Enchancement', 32]
            ]);

            var options = {
                'title': 'Статистика по важности дефектов',
                'width': 1000,
                'height': 550,
                'is3D': true
            };

            var chart = new google.visualization.PieChart(document.getElementById('pie_chart_bySeveritySecond'));
            chart.draw(data, options);
        }
        function drawLineChartByActivity() {
            var data = google.visualization.arrayToDataTable([
                ['Месяц', 'Бегун Татьяна', 'Жебит Алексей', 'Новик Павел', 'Козляк Роман', 'Беломызова Дарья', 'Кибрик Алексей'],
                ['Январь',  7,8,6,7,8,9],
                ['Февраль', 6,5,7,6,5,6],
                ['Март',  6,5,6,5,5,5],
                ['Апрель',  4,4,4,3,4,3],
                ['Май',  4,3,3,3,2,3],
                ['Июнь',  2,2,3,2,3,2],
                ['Июль',  3,2,2,2,1,1],
                ['Август', 2,3,2,2,1,3],
                ['Сентябрь',  2,2,2,1,1,2],
                ['Октябрь',  1,0,2,0,1,1],
                ['Ноябрь',  0,2,1,1,1,1],
                ['Декабрь',  3,2,3,1,0,1]
            ]);

            var options = {
                title: 'Статистика активностей сотрудников по месяцам',
                hAxis: {
                    title: 'Месяц'
                },
                vAxis: {
                    title: 'Количество'
                },
                curveType: 'function',
                legend: { position: 'bottom' },
                'width': 1100,
                'height': 450
            };

            var chart = new google.visualization.LineChart(document.getElementById('line_chart_byActivity'));

            chart.draw(data, options);
        }
    </script>

<head>
<body>
<div id="wrapper">
    <c:import url="template.jsp"></c:import>

    <div id="page-wrapper">
        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Статистика по сотрудникам</h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="glyphicon glyphicon-home"></i><a href="/mainmenu" target="_blank"> Главное
                            меню</a>
                        </li>
                        <li>
                            <i class="fa fa-user"></i> Сотрудники
                        </li>
                        <li class="active">
                            <i class="fa fa-fw fa-bar-chart-o"></i> Статистика по сотрудникам
                        </li>
                    </ol>
                </div>
            </div>
            <section class="color-5">
                <div class="cl-effect-2">
                    <a id="linkByCount"><span data-hover="По количеству дефектов">По количеству дефектов</span></a>
                    <a id="linkByWeight"><span data-hover="По весу дефектов">По весу дефектов</span></a>
                    <a id="linkByActivity"><span data-hover="По активностям">По активностям</span></a>
                    <a id="linkBySeverity"><span data-hover="По важности">По важности</span></a>
                </div>
            </section>
            <div class="charts">
                <div id="main"><h1>Выберите статистику для просмотра</h1></div>
                <%--<c:forEach items="${listEmployees}" var="employee">
                    <div id='${employee.idEmployee}name'>${statisticByCount.get(employee.idEmployee)} for
                        id ${employee.idEmployee}</div>
                    <div id=${employee.idEmployee}>${employee.firstName} ${employee.lastName}</div>

                </c:forEach>--%>

                <div id="pie_chart_byCount" style="display:none"></div>
                <div id="pie_chart_byWeight" style="display:none"></div>
                <div id="line_chart_byActivity" style="display:none"></div>
                <div id="pie_chart_bySeverityFirst" style="display:none"></div>
                <div id="pie_chart_bySeveritySecond" style="display:none"></div>
                <div class="form-group" style="display:none" id="divEmployee">
                    <label for="selectEmployeeStatistic"
                           class="col-sm-2 control-label">Сотрудник</label>
                    <div class="col-sm-10">
                        <select id="selectEmployeeStatistic" class="form-control" style="margin-left: 30px;">
                            <c:forEach items="${listEmployees}" var="employee">
                                <option value="${employee.idEmployee}">${employee.firstName} ${employee.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

        </div>
    </div>


    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/plugins/morris/raphael.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/morris/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/morris/morris-data.js"></script>
    <script src="${pageContext.request.contextPath}/js/statisticEmployeePage.js"></script>

</body>
</html>