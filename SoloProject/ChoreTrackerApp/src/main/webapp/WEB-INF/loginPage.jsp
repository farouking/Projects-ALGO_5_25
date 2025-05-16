<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login and Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <!-- Registration Form -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h3>Register</h3>
                    </div>
                    <div class="card-body">
                        <form:form action="/register" method="post" modelAttribute="newUser">
                            <div class="form-group">
                                <form:label path="firstName">First Name:</form:label>
                                <form:input path="firstName" class="form-control" />
                                <form:errors path="firstName" class="text-danger" />
                            </div>
                            <div class="form-group">
                                <form:label path="lastName">Last Name:</form:label>
                                <form:input path="lastName" class="form-control" />
                                <form:errors path="lastName" class="text-danger" />
                            </div>
                            <div class="form-group">
                                <form:label path="email">Email:</form:label>
                                <form:input path="email" class="form-control" />
                                <form:errors path="email" class="text-danger" />
                            </div>
                            <div class="form-group">
                                <form:label path="password">Password:</form:label>
                                <form:password path="password" class="form-control" />
                                <form:errors path="password" class="text-danger" />
                            </div>
                            <div class="form-group">
                                <form:label path="confirmPassword">Confirm Password:</form:label>
                                <form:password path="confirmPassword" class="form-control" />
                                <form:errors path="confirmPassword" class="text-danger" />
                            </div>
                            <button type="submit" class="btn btn-primary">Register</button>
                        </form:form>
                    </div>
                </div>
            </div>
            
            <!-- Login Form -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h3>Login</h3>
                    </div>
                    <div class="card-body">
                        <form:form action="/login" method="post" modelAttribute="newLogin">
                            <div class="form-group">
                                <form:label path="email">Email:</form:label>
                                <form:input path="email" class="form-control" />
                                <form:errors path="email" class="text-danger" />
                            </div>
                            <div class="form-group">
                                <form:label path="password">Password:</form:label>
                                <form:password path="password" class="form-control" />
                                <form:errors path="password" class="text-danger" />
                            </div>
                            <button type="submit" class="btn btn-success">Login</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>