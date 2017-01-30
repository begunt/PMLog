<%@ page import="by.a1qa.model.Employee" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Employee employeeSession =(Employee) session.getAttribute("employeeSession");%>


<div id="wrapper">
  <!-- Navigation -->
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <a class="navbar-brand" target="_blank">Product Madness - A1QA</a>
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${report.person} <b></b></a>
      </li>
    </ul>

  </nav>

</div>
<!-- /#wrapper -->