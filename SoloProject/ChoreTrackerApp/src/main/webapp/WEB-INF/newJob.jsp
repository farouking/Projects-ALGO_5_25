<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add a Job</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <div class="row mb-4">
            <div class="col-md-8">
                <h1>Add a New Job</h1>
            </div>
            <div class="col-md-4 text-right">
                <a href="/dashboard" class="btn btn-secondary">Back to Dashboard</a>
                <a href="/logout" class="btn btn-danger">Logout</a>
            </div>
        </div>
        
        <div class="card">
            <div class="card-body">
                <form:form action="/jobs" method="post" modelAttribute="job">
                    <div class="form-group">
                        <form:label path="name">Job Name:</form:label>
                        <form:input path="name" class="form-control" />
                        <form:errors path="name" class="text-danger" />
                    </div>
                    
                    <div class="form-group">
                        <form:label path="location">Location:</form:label>
                        <form:input path="location" class="form-control" />
                        <form:errors path="location" class="text-danger" />
                    </div>
                    
                    <div class="form-group">
                        <form:label path="description">Description:</form:label>
                        <form:textarea path="description" class="form-control" rows="4" />
                        <form:errors path="description" class="text-danger" />
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Create Job</button>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>