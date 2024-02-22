<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: cyan;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
            text-align: center;
        }
        h2 {
            margin-top: 0;
            font-weight: 500;
            color: #333;
            margin-bottom: 20px;
        }
        p {
            margin: 8px 0;
            color: #666;
            line-height: 1.6;
        }
        img {
            border-radius: 50%;
            margin-top: 20px;
            max-width: 200px;
        }
        .edit-link {
            display: inline-block;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 25px;
            transition: background-color 0.3s ease;
            font-size: 16px;
            margin-top: 20px;
            margin-right: 10px;
        }
        .edit-link:hover {
            background-color: #0056b3;
        }
        .logout-link {
            display: inline-block;
            background-color: #dc3545;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 25px;
            transition: background-color 0.3s ease;
            font-size: 16px;
            margin-top: 20px;
        }
        .logout-link:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

<div class="container">

    <h2>User Profile</h2>

<%
                            if(request.getAttribute("msg")!=null)
                            {
                            	out.print("<b>"+request.getAttribute("msg")+"</b>");        	
                            }
                        %>    
    <c:if test="${not empty user}">
        <p><strong>First Name:</strong> ${user.firstName}</p>
        <p><strong>Last Name:</strong> ${user.lastName}</p>
        <p><strong>Gender:</strong> ${user.gender}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Mobile:</strong> ${user.mobile}</p>
        <p><strong>Password:</strong> *********</p>
        
        <p><strong>Languages:</strong> 
            <c:forEach var="language" items="${user.languages}">
                ${language}<c:if test="${!loop.last}">, </c:if>
            </c:forEach>
        </p>
        
        <p><strong>Hobbies:</strong> 
            <c:forEach var="hobby" items="${user.hobbies}">
                ${hobby}<c:if test="${!loop.last}">, </c:if>
            </c:forEach>
        </p>
        
        <img src="UserImageServlet" alt="User Image">
        
        <p><strong>Country:</strong> ${user.country}</p>
        
        <a href="editUserProfile.jsp" class="edit-link">Edit Profile</a>
        <a href="LogoutServlet" class="logout-link">Logout</a>
    </c:if>

    <c:if test="${empty user}">
        <p>No user found.</p>
    </c:if>
</div>

</body>
</html>
