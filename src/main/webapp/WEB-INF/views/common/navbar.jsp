<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <li class="nav-item">
                    <a class="nav-link ${menu eq 'department' ? 'active' : ''}" href="/dept/list">Departments</a>
                </li>
                <li class="nav-ite ">
                    <a class="nav-link ${menu eq 'job' ? 'active' : ''}" href="/job/list">Jobs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${menu eq 'employee' ? 'active' : ''}" href="/emp/list">Employees</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/signup">Sign up</a>
                </li>
            </ul>
        </div>
    </div>
</nav>