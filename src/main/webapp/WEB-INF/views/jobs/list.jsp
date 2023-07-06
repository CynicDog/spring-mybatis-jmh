<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Application</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

    <style type="text/css">
        th.th-hover:hover {
            color: deepskyblue;
            cursor: pointer;
        }
    </style>
</head>
<body>
<c:set var="menu" value="jobs"/>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
    <div class="row my-4">
        <div class="col-12">
            <div class="card shadow">
                <div class="card-header">
                    Jobs
                    <a href="add" class="btn btn-primary btn-sm float-end">add new job</a>
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th style="width: 23%" onclick="sortTable('alphabetic', 1)" class="th-hover">Identifier</th>
                                <th style="width: 23%" onclick="sortTable('alphabetic', 2)" class="th-hover">Title</th>
                                <th style="width: 23%" onclick="sortTable('numeric', 3)" class="th-hover">
                                    Min Salary
                                </th>
                                <th style="width: 23%" onclick="sortTable('numeric', 4)" class="th-hover">
                                    Max Salary
                                </th>
                                <th style="width: 8%"></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="job" items="${jobs}">
                            <tr>
                                <td>${job.id}</td>
                                <td>${job.title}</td>
                                <td>${job.minSalary}</td>
                                <td>${job.maxSalary}</td>
                                <td>
                                    <a href="details?id=${job.id}" class="btn btn-outline-primary btn-sm">Details</a>
                                </td>
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
<script>
    // Initially set the ascending order
    let ascending = true;

    function sortTable(sortKey, col) {
        let table = document.querySelector('.table');
        let rows = Array.from(table.querySelectorAll('tbody tr'));

        rows.sort(function(a, b) {
            let valueA = a.querySelector(`td:nth-child(\${col})`).textContent;
            let valueB = b.querySelector(`td:nth-child(\${col})`).textContent;

            if (sortKey === 'numeric') {
                valueA = parseInt(valueA);
                valueB = parseInt(valueB);

                if (ascending) { return valueA - valueB; }
                else { return valueB - valueA; }
            }

            if (ascending) { return valueA.localeCompare(valueB); }
            else { return valueB.localeCompare(valueA); }
        });

        table.querySelector('tbody').innerHTML = '';

        rows.forEach(function(row) {
            table.querySelector('tbody').appendChild(row);
        });

        ascending = !ascending;
    }

</script>
</html>
