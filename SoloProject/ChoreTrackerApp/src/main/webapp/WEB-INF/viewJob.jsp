<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Job</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <div class="row mb-4">
            <div class="col-md-8">
                <h1>Job Details</h1>
            </div>
            <div class="col-md-4 text-right">
                <a href="/dashboard" class="btn btn-secondary">Back to Dashboard</a>
                <a href="/logout" class="btn btn-danger">Logout</a>
            </div>
        </div>
        
        <div class="card">
            <div class="card-header">
                <h3>${job.name}</h3>
            </div>
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-3 font-weight-bold">Location:</div>
                    <div class="col-md-9">${job.location}</div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-3 font-weight-bold">Description:</div>
                    <div class="col-md-9">${job.description}</div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-3 font-weight-bold">Posted By:</div>
                    <div class="col-md-9">${job.creator.firstName} ${job.creator.lastName}</div>
                </div>
                
                <div class="row mt-4">
                    <div class="col-md-12">
                        <c:choose>
                            <c:when test="${job.creator.id == user.id}">
                                <a href="/jobs/${job.id}/edit" class="btn btn-warning">Edit</a>
                                <a href="/jobs/${job.id}/cancel" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this job?')">Cancel</a>
                            </c:when>
                            <c:when test="${job.worker == null}">
                                <a href="/jobs/${job.id}/add" class="btn btn-success">Add to My Jobs</a>
                            </c:when>
                            <c:when test="${job.worker.id == user.id}">
                                <a href="/jobs/${job.id}/done" class="btn btn-success" onclick="return confirm('Mark this job as done?')">Mark as Done</a>
                            </c:when>
                            <c:otherwise>
                                <p class="text-danger">This job has already been taken by another user.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>