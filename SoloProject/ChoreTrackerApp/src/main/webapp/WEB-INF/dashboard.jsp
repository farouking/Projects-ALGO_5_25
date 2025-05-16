<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <div class="row mb-4">
            <div class="col-md-8">
                <h1>Welcome, ${user.firstName}!</h1>
            </div>
            <div class="col-md-4 text-right">
                <a href="/logout" class="btn btn-danger">Logout</a>
            </div>
        </div>
        
        <!-- Available Jobs Section -->
        <div class="card mb-4">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h3>Available Jobs</h3>
                <a href="/jobs/new" class="btn btn-primary">Add a Job</a>
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Job</th>
                            <th>Location</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="job" items="${availableJobs}">
                            <tr>
                                <td>${job.name}</td>
                                <td>${job.location}</td>
                                <td>
                                    <a href="/jobs/${job.id}" class="btn btn-info btn-sm">View</a>
                                    <c:if test="${job.creator.id == userId}">
                                        <a href="/jobs/${job.id}/edit" class="btn btn-warning btn-sm">Edit</a>
                                        <a href="/jobs/${job.id}/cancel" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this job?')">Cancel</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <!-- My Jobs Section -->
        <div class="card">
            <div class="card-header">
                <h3>My Jobs</h3>
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Job</th>
                            <th>Location</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="job" items="${myJobs}">
                            <tr>
                                <td>${job.name}</td>
                                <td>${job.location}</td>
                                <td>
                                    <a href="/jobs/${job.id}" class="btn btn-info btn-sm">View</a>
                                    <a href="/jobs/${job.id}/done" class="btn btn-success btn-sm" onclick="return confirm('Mark this job as done?')">Done</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>