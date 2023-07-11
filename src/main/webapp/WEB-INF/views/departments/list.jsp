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
<c:set var="menu" value="department" />
<%@ include file="../common/navbar.jsp" %>
<div class="container">
    <div class="row my-4">
        <div class="col-3">
            <div class="card shadow">
                <div class="card-header">Departments</div>
                <div class="card-body">
                    <div id="dept-list" class="list-group overflow-auto" style="max-height: 500px;">
                        <c:forEach var="department" items="${departments}">
                            <a id="dept-${department.id}" href="detail/id=${department.id}" class="list-group-item list-group-item-action" onclick="fetchDepartmentDetails(event, ${department.id})">${department.name}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function fetchDepartmentDetails(event, department_id) {

        event.preventDefault();

        let elements = document.querySelectorAll("#dept-list a")
        elements.forEach(function(el, index) {
            el.classList.remove('active');
        })

        let clickedElement = document.querySelector("#dept-" + department_id);
        clickedElement.classList.add("active");

        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let text = xhr.responseText;

                // TODO: represent data
            }
        }

        xhr.open("get", "detail?id=" + department_id);
        xhr.send();


    }
</script>
</html>