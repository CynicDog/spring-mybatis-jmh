<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<c:set var="menu" value="employee"/>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
    <div class="row my-4 justify-content-center align-items-center">
        <div class="col-10">
            <div class="card shadow">
                <div class="card-header">
                    <p class="cart-text my-2">
                        Hola, <strong>${employee.firstName}</strong>.
                    </p>
                </div>
                <div class="card-body">
                    <table class="table">
                        <tbody>
                        <tr>
                            <th style="width: 15%">Identifier</th>
                            <td style="width: 35%">${employee.id}</td>
                            <th style="width: 15%">Name</th>
                            <td style="width: 35%">${employee.firstName} ${employee.lastName}</td>
                        </tr>
                        <tr>
                            <th style="width: 15%">Email</th>
                            <td style="width: 35%">${employee.email}</td>
                            <th style="width: 15%">Phone</th>
                            <td style="width: 35%">${employee.phoneNumber}</td>
                        </tr>
                        <tr>
                            <th style="width: 15%">Job</th>
                            <td style="width: 35%">${employee.job.title}</td>
                            <th style="width: 15%">Joined Date</th>
                            <td style="width: 35%">${employee.hireDate}</td>
                        </tr>
                        <tr>
                            <th style="width: 15%">Salary</th>
                            <td style="width: 35%">$ <fmt:formatNumber value="${employee.salary}" /></td>
                            <th style="width: 15%">Commission</th>
                            <td style="width: 35%">${employee.commissionPct}</td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
