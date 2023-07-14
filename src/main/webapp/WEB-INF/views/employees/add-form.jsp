<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
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
<%@ include file="../common/navbar.jsp" %>
<div class="container">
    <div class="row my-3">
        <div class="co1-12">
            <div class="card shadow">
                <div class="card-header">Registration</div>
                <div class="card-body">
                    <form method="post" action="add">
                        <div class="row mb-3">
                            <label class="col-sm-1 col-form-label text-end">Name</label>
                            <div class="col-5 mb-3">
                                <input type="text" class="form-control" name="firstName" />
                            </div>
                            <div class="col-5 offset-1 mb-3">
                                <input type="text" class="form-control" name="lastName" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label class="col-sm-1 col-form-label text-end">Email</label>
                            <div class="col-5 mb-3">
                                <input type="text" class="form-control" name="email" />
                            </div>
                            <label class="col-sm-1 col-form-label text-end">Phone Number</label>
                            <div class="col-5 mb-3">
                                <input type="text" class="form-control" name="phoneNumber" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label class="col-sm-1 col-form-label text-end">Password</label>
                            <div class="col-11 mb-3">
                                <input type="password" class="form-control" name="password" />
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label class="col-sm-1 col-form-label text-end">Joined Date</label>
                            <div class="col-5 mb-3">
                                <input type="date" class="form-control" name="hireDate" />
                            </div>
                            <label class="col-sm-1 col-form-label text-end">Job</label>
                            <div class="col-5 mb-3">
                                <select class="form-select" name="jobId">
                                    <c:forEach var="job" items="${jobs}">
                                        <option value=${job.id}>${job.title} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label class="col-sm-1 col-form-label text-end">Department</label>
                            <div class="col-5 mb-3">
                                <select class="form-select" name="departmentId" onchange="onChangesOnDepartment()">
                                    <c:forEach var="department" items="${departments}">
                                        <option value=${department.id}>${department.name} </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="col-sm-1 col-form-label text-end">Manager</label>
                            <div class="col-5 mb-3">
                                <select class="form-select" name="managerId" disabled>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label class="col-sm-1 col-form-label text-end">Salary</label>
                            <div class="col-5 mb-3">
                                <input type="text" class="form-control" name="salary" />
                            </div>
                            <label class="col-sm-1 col-form-label text-end">Commission Percent</label>
                            <div class="col-5 mb-3">
                                <input type="text" class="form-control" name="commissionPct" disabled />
                            </div>
                        </div>
                        <div class="text-end">
                            <a href="list" class="btn btn-secondary">cancel</a>
                            <button type="submit" class="btn btn-primary">submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>

    function onChangesOnDepartment() {

        toggleCommissionField();
        fetchMangers();
    }

    function fetchMangers() {
        let managerField = document.querySelector("select[name=managerId]");
        managerField.innerHTML = "";
        managerField.disabled = false;

        let departmentId = document.querySelector("select[name=departmentId]").value;

        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let text = xhr.responseText;

                if (text === '') {
                    managerField.disabled = true;
                    return;
                }
                let employeesParsed = JSON.parse(text);

                if (employeesParsed.length === 0) {
                    managerField.disabled = true;
                } else {
                    let options = "";
                    employeesParsed.forEach(function(employee) {
                        options += `<option value="\${employee.id}"> \${employee.firstName} \${employee.lastName}</option>`
                    })
                    managerField.innerHTML = options;
                };
            }
        }
        xhr.open("get", "/emp/by-department?department=" + departmentId);
        xhr.send();
    }

    function toggleCommissionField() {
        let departmentId = document.querySelector("select[name=departmentId]").value;
        let commissionField = document.querySelector("input[name=commissionPct]");
        if (departmentId === '80') {
            commissionField.disabled = false;
        } else {
            commissionField.disabled = true;
        }
    }

</script>
</html>