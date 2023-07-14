<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
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
<c:set var="menu" value="home"/>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="bg-light border shadow p-3 my-4">
                <p style="font-size: large; font-weight: bold">
                    Something happened while processing your request 😥
                </p>
                <p>We are very sorry for the inconvenience caused to you.</p>
                <p>Please come back later and try again. Below is the detailed information of the occurred error.</p>
                <p>${pageContext.exception.message}</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>