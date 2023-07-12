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
<c:set var="menu" value="employee"/>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
    <div class="row my-4">
        <div class="col-12">
            <div class="card shadow">
                <div class="card-header">
                    Employees
                    <span class="float-end">
                        <a href="fetch-in-xls" class="btn btn-outline-primary btn-sm">get in batch</a>
                        <a href="files" class="btn btn-outline-primary btn-sm mx-1">add in batch</a>
                        <a href="add" class="btn btn-primary btn-sm mx-1">add new employee</a>
                    </span>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-start my-2">
                        <select class="form-select mx-1" name="sort" style="width: 150px" onchange="changeSort()">
                            <option value="id" ${param.sort eq 'id' ? 'selected' : ''}>Identifier</option>
                            <option value="name" ${param.sort eq 'name' ? 'selected' : ''}>Name</option>
                            <option value="Date" ${param.sort eq 'date' ? 'selected' : ''}>Date</option>
                            <option value="job" ${param.sort eq 'job' ? 'selected': ''}>Job</option>
                            <option value="dept" ${param.sort eq 'dept' ? 'selected': ''}>Department</option>
                        </select>
                        <select class="form-select mx-1" name="rows" style="width: 150px" onchange="changeRows()">
                            <option value="10" ${param.rows eq 10 ? 'selected' : ''}>10</option>
                            <option value="30" ${param.rows eq 30 ? 'selected' : ''}>30</option>
                            <option value="50" ${param.rows eq 50 ? 'selected' : ''}>50</option>
                        </select>
                    </div>
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
                        <c:choose>
                            <c:when test="${not empty response.employees}">
                                <c:forEach var="employee" items="${response.employees}">
                                    <tr>
                                        <td>${employee.id}</td>
                                        <td>${employee.firstName}</td>
                                        <td>${employee.hireDate}</td>
                                        <td>${employee.job.title}</td>
                                        <td>${employee.department.name}</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="5" class="text-center">Found nothing.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                    <c:if test="${response.pagination.totalRows gt 0}">
                        <nav>
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${response.pagination.first ? 'disabled': ''}">
                                    <a href="" class="page-link" onclick="changePage(event, 1)">First</a>
                                </li>
                                <li class="page-item ${response.pagination.first ? 'disabled': ''}">
                                    <a class="page-link" href="" aria-label="Prev"
                                       onclick="changePage(event, ${response.pagination.prePage})">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only"></span>
                                    </a>
                                </li>
                                <c:forEach var="num" begin="${response.pagination.beginPage}"
                                           end="${response.pagination.endPage}">
                                    <li class="page-item ${response.pagination.page eq num ? "active" : ""}">
                                        <a href="" class="page-link" onclick="changePage(event, ${num})">${num}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item ${response.pagination.last ? 'disabled': ''}">
                                    <a class="page-link" href="" aria-label="Next"
                                       onclick="changePage(event, ${response.pagination.nextPage})">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only"></span>
                                    </a>
                                </li>
                                <li class="page-item ${response.pagination.last ? 'disabled': ''}">
                                    <a href="" class="page-link"
                                       onclick="changePage(event, ${response.pagination.totalPages})">Last</a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>
                    <div class="d-flex justify-content-center">
                        <form id="employee-search-command" class="row row-cols-md-auto g-3 align-items-center"
                              method="get" action="list">
                            <input type="hidden" name="sort" value="${param.sort}">
                            <input type="hidden" name="rows" value="${param.rows}">
                            <input type="hidden" name="page" value="${param.page}">
                            <div class="col-12">
                                <select class="form-select" name="opt">
                                    <option value="name" ${param.opt eq 'name' ? 'selected' : ''}>Name</option>
                                    <option value="job" ${param.opt eq 'job' ? 'selected': ''}>Job</option>
                                    <option value="phone" ${param.opt eq 'phone' ? 'selected' : ''}>Phone</option>
                                    <option value="dept" ${param.opt eq 'dept' ? 'selected' : ''}>Department</option>
                                </select>
                            </div>
                            <div class="col-12">
                                <input type="text" class="form-control" name="keyword"/>
                            </div>
                            <div class="col-12">
                                <button type="button" class="btn btn-outline-primary btn-sm"
                                        onclick="searchEmployees()">search
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function changeSort() {
        let sort = document.querySelector("select[name=sort]").value;
        document.querySelector("input[name=sort]").value = sort;
        document.querySelector("input[name=page]").value = 1;

        document.querySelector("#employee-search-command").submit();
    }

    function changeRows() {
        let rows = document.querySelector("select[name=rows]").value;
        document.querySelector("input[name=rows]").value = rows;
        document.querySelector("input[name=page]").value = 1;

        document.querySelector("#employee-search-command").submit();
    }

    function changePage(event, page) {
        event.preventDefault();
        document.querySelector("input[name=page]").value = page;

        document.querySelector("#employee-search-command").submit();
    }

    function searchEmployees() {
        let keyword = document.querySelector("input[name=keyword]").value;

        if (keyword.trim() === "") {
            alert("Keyword must be specified.")

            document.querySelector("input[name=keyword]").focus();

            return;
        }

        document.querySelector("input[name=page]").value = 1;
        document.querySelector("#employee-search-command").submit();
    }
</script>
</html>
