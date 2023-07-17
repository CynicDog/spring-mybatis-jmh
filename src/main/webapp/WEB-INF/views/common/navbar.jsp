<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark border-bottom">
    <div class="container">
        <a class="navbar-brand" href="/">Human Resource</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link ${menu eq 'home' ? 'active' : ''}" href="/">Home</a>
                </li>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link ${menu eq 'department' ? 'active' : ''}" href="/dept/list">Departments</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${menu eq 'job' ? 'active' : ''}" href="/job/list">Jobs</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${menu eq 'employee' ? 'active' : ''}" href="/emp/list">Employees</a>
                    </li>
                </sec:authorize>
            </ul>
            <span class="navbar-text">
                <sec:authorize access="isAuthenticated()">
                <strong class="text-white">
                        <p class="my-1 mx-2">
                            <sec:authentication property="principal" var="employee"/>
                            Welcome, ${employee.firstName} ${employee.lastName}
                        </p>
                </strong>
                </sec:authorize>
            </span>
            <ul class="navbar-nav">
                <sec:authorize access="isAnonymous()">
                    <li class="nav-item">
                        <a class="nav-link" href="/emp/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/emp/add">Sign up</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/emp/info" >My Page</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/emp/logout">Logout</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
