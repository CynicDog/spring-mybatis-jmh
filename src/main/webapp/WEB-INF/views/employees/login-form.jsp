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
    <div class="row my-4 justify-content-center align-items-center">
        <div class="col-6">
            <div class="card shadow">
                <div class="card-header">Login</div>
                <c:choose>
                    <c:when test="${param.error eq 'fail'}">
                        <div class="alert alert-danger m-3">
                            <strong>Bad Credentials.</strong>
                        </div>
                    </c:when>
                    <c:when test="${param.error eq 'denied'}">
                        <div class="alert alert-danger m-3">
                            <strong>Access Denied. You may need to be authenticated first.</strong>
                        </div>
                    </c:when>
                    <c:when test="${param.error eq 'forbidden'}">
                        <div class="alert alert-danger m-3">
                            <strong>Not allowed with the granted authority of yours</strong>
                        </div>
                    </c:when>
                </c:choose>
                <div class="card-body">
                    <form action="/emp/login" method="post">
                        <div class="form-group my-2">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" >
                        </div>
                        <div class="form-group my-2">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" name="password" >
                        </div>
                        <div class="text-end">
                            <button type="submit" class="btn btn-outline-primary my-2">submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
