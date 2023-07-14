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
<style type="text/css">
    th.th-hover:hover {
        color: deepskyblue;
        cursor: pointer;
    }
</style>
<body>
<c:set var="menu" value="department"/>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
    <div class="row my-4">
        <div class="col-3">
            <div class="card shadow">
                <div class="card-header">Departments</div>
                <div class="card-body">
                    <div id="dept-list" class="list-group overflow-auto" style="max-height: 500px;">
                        <c:forEach var="department" items="${departments}">
                            <a id="dept-${department.id}" href="detail/id=${department.id}"
                               class="list-group-item list-group-item-action"
                               onclick="fetchDepartmentDetails(event, ${department.id})">${department.name}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-9">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">Department</div>
                        <div class="card-body">
                            <table class="table table-bordered my-1">
                                <tbody>
                                <tr>
                                    <th style="width: 15%">Identifier</th>
                                    <td style="width: 35%"><span id="department-id"></span></td>
                                    <th style="width: 15%">Title</th>
                                    <td style="width: 35%"><span id="department-title"></span></td>
                                </tr>
                                <tr>
                                    <th style="width: 15%">Manager</th>
                                    <td style="width: 35%"><span id="department-mng"></span></td>
                                    <th style="width: 15%">Employees</th>
                                    <td style="width: 35%"><span id="department-emps"></span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row my-2">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">Employees</div>
                        <div class="card-body">
                            <table class="table" id="employees-table">
                                <thead>
                                <tr>
                                    <th onclick="sortTable('numeric', 1)" class="th-hover">Identifier</th>
                                    <th onclick="sortTable('alphabetic', 2)" class="th-hover">Name</th>
                                    <th onclick="sortTable('alphabetic', 3)" class="th-hover">Job</th>
                                    <th onclick="sortTable('numeric', 4)" class="th-hover">Salary</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal modal-lg" tabindex="-1" id="modal-emp-info">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modal title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Details</p>
                <table class="table table-bordered" id="table-emp-detail">
                    <tr>
                        <th style="width: 15%;">Identifier</th>
                        <td style="width: 35%;" id="cell-emp-id"></td>
                        <th style="width: 15%;">Name</th>
                        <td style="width: 35%;" id="cell-emp-name"></td>
                    </tr>
                    <tr>
                        <th style="width: 15%;">Email</th>
                        <td style="width: 35%;" id="cell-emp-email"></td>
                        <th style="width: 15%;">Contact</th>
                        <td style="width: 35%;" id="cell-emp-phone"></td>
                    </tr>
                    <tr>
                        <th style="width: 15%;">Joined Date</th>
                        <td style="width: 35%;" id="cell-emp-hiredate"></td>
                        <th style="width: 15%;">Job</th>
                        <td style="width: 35%;" id="cell-emp-job"></td>
                    </tr>
                    <tr>
                        <th style="width: 15%;">Salary</th>
                        <td style="width: 35%;" id="cell-emp-salary"></td>
                        <th style="width: 15%;">Commission</th>
                        <td style="width: 35%;" id="cell-emp-comm"></td>
                    </tr>
                    <tr>
                        <th style="width: 15%;">Manager</th>
                        <td style="width: 35%;" id="cell-manager-name"></td>
                        <th style="width: 15%;">Department</th>
                        <td style="width: 35%;" id="cell-dept-name"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    const empModal = new bootstrap.Modal('#modal-emp-info', {
        keyboard: false
    })

    let modalElem = document.querySelector("#modal-emp-info");
    modalElem.addEventListener("hidden.bs.modal", function() {

        let table = document.querySelector("#table-emp-detail")
        Array.from(table.querySelectorAll('tr td')).forEach(td => {
            td.textContent = "";
        })
    })

    function detailsModal(employee_id) {
        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let employee = JSON.parse(xhr.responseText);

                document.getElementById("cell-emp-id").textContent = employee.id;
                document.getElementById("cell-emp-name").textContent = employee.firstName;
                document.getElementById("cell-emp-email").textContent = employee.email;
                document.getElementById("cell-emp-phone").textContent = employee.phoneNumber;
                document.getElementById("cell-emp-hiredate").textContent = employee.hireDate;
                document.getElementById("cell-emp-job").textContent = employee.job.title;
                document.getElementById("cell-emp-salary").textContent = employee.salary;
                document.getElementById("cell-emp-comm").textContent = employee.commissionPct || '';
                document.getElementById("cell-manager-name").textContent = employee.manager.firstName || '';
                document.getElementById("cell-dept-name").textContent = employee.department.name || '';
            }
        }

        xhr.open("get", "../emp/detail?id=" + employee_id);
        xhr.send()

        empModal.show();
    }

    function fetchDepartmentDetails(event, department_id) {

        event.preventDefault();

        let elements = document.querySelectorAll("#dept-list a")
        elements.forEach(function (el, index) {
            el.classList.remove('active');
        })

        let clickedElement = document.querySelector("#dept-" + department_id);
        clickedElement.classList.add("active");

        document.querySelector("#employees-table tbody").innerHTML = "";

        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let text = xhr.responseText;
                let result = JSON.parse(text);

                document.querySelector("#department-id").textContent = result.department.id;
                document.querySelector("#department-title").textContent = result.department.name
                document.querySelector("#department-emps").textContent = result.employees.length
                if (result.department.manager) {
                    document.querySelector("#department-mng").textContent = result.department.manager.firstName
                }

                let employees = result.employees;
                let htmlContent = "";

                employees.forEach(function (emp, index) {
                    htmlContent += `
                        <tr>
                            <td>\${emp.id}</td>
                            <td>\${emp.firstName} \${emp.lastName}</td>
                            <td>\${emp.job.id}</td>
                            <td>\${emp.salary}</td>
                            <td><button class="btn btn-outline-primary btn-sm" onclick=detailsModal(\${emp.id})>details</td>
                        </tr>
                    `
                })

                document.querySelector("#employees-table tbody").innerHTML += htmlContent;
            }
        }

        xhr.open("get", "detail?id=" + department_id);
        xhr.send();
    }

    let ascending = true;

    function sortTable(sortKey, col) {
        let table = document.querySelector("#employees-table");
        let rows = Array.from(table.querySelectorAll('tbody tr'));

        rows.sort(function (a, b) {
            let valueA = a.querySelector(`td:nth-child(\${col})`).textContent;
            let valueB = b.querySelector(`td:nth-child(\${col})`).textContent;

            if (sortKey == 'numeric') {
                valueA = parseInt(valueA);
                valueB = parseInt(valueB);

                if (ascending) {
                    return valueA - valueB;
                } else {
                    return valueB - valueA;
                }
            }

            if (ascending) {
                return valueA.localeCompare(valueB);
            } else {
                return valueB.localeCompare(valueA);
            }
        })

        table.querySelector('tbody').innerHTML = '';

        rows.forEach(function (row) {
            table.querySelector('tbody').appendChild(row);
        });

        ascending = !ascending;
    }
</script>
</html>