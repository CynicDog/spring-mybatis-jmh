<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Application</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<c:set var="menu" value="employee" />
<%@ include file="../common/navbar.jsp"%>
<div class="container">
    <div class="row my-4">
        <div class="col-12">
            <div class="card shadow">
                <div class="card-header">
                    Employees
                    <a href="add" class="btn btn-primary btn-sm float-end">add new employee</a>
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Identifier</th>
                                <th>Name</th>
                                <th>Joined Date</th>
                                <th>Job</th>
                                <th>Department</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="employee" items="${employees}">
                            <tr>
                                <td>${employee.id}</td>
                                <td>${employee.firstName}</td>
                                <td>${employee.hireDate}</td>
                                <td>${employee.job.title}</td>
                                <td>${employee.department.name}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
