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
</head>
<body>
<c:set var="menu" value="jobs"/>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
    <div class="row my-4">
        <div class="col-12">
            <div class="card shadow">
                <div class="card-header">Registration</div>
                <div class="card-body">
                    <form method="post" action="add">
                        <div class="form-group my-1">
                            <label class="form-label">Identifier</label>
                            <input type="text" class="form-control" name="id">
                        </div>
                        <div class="form-group my-1">
                            <label class="form-label">Title</label>
                            <input type="text" class="form-control" name="title">
                        </div>
                        <div class="form-group my-1">
                            <label class="form-label">Minimum Salary</label>
                            <input type="text" class="form-control" name="minSalary">
                        </div>
                        <div class="form-group my-1">
                            <label class="form-label">Maximum Salary</label>
                            <input type="text" class="form-control" name="maxSalary">
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
</html>
