<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <!-- Include DataTables library -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f8f9fa;
    }
    .container {
        margin: 50px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }
    h2 {
        color: #333;
        text-align: center;
    }
    table {
        border-collapse: collapse;
        margin-top: 20px;
        width: calc(100% - 40px); /* Adjusted table width */
        margin-left: 20px; /* Center-align the table */
        margin-right: 20px; /* Center-align the table */
    }
    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd; /* Add bottom border for horizontal lines */
        border-right: 1px solid #ddd; /* Add right border for vertical lines */
    }
    th {
        background-color: #007bff;
        color: #fff;
    }
    td:last-child {
        border-right: none; /* Remove right border for the last column */
    }
    td {
        background-color: #f9f9f9;
    }
    tr:hover {
        background-color: #f2f2f2;
    }
    button {
        padding: 8px 12px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        background-color: #007bff;
        color: #fff;
        transition: background-color 0.3s ease;
    }
    button:hover {
        background-color: #0056b3;
    }
    .add-button {
        margin-top: 20px;
        text-align: center;
    }
    .logout-link {
        margin-left: 10px; /* Add margin to separate the buttons */
        color: red; /* Set text color to red */
        text-decoration: none; /* Remove underline */
    }
    .logout-link:hover {
        text-decoration: underline; /* Underline on hover */
    }
</style>

</head>
<body>

<div class="container">
    <h2>User List</h2>

    <table id="userTable">
        <thead>
        <tr>
            <th>User ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Languages</th>
            <th>Hobby</th>
            <th>Country</th>
            <th>Image</th> 
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.gender}</td>
                <td>${user.email}</td>
                <td>${user.mobile}</td>
                 <td>
                    <c:if test="${user.languages != null}">
                        <c:forEach var="language" items="${user.languages}" varStatus="loop">
                            ${language}<c:if test="${!loop.last}">, </c:if>
                        </c:forEach>
                    </c:if>
                </td>
                <td>
                    <c:if test="${user.hobbies != null}">
                        <c:forEach var="hobby" items="${user.hobbies}" varStatus="loop">
                            ${hobby}<c:if test="${!loop.last}">, </c:if>
                        </c:forEach>
                    </c:if>
                </td> 
              <!-- <td>${user.hobbiesAsString}</td>
            <td>${user.languagesAsString}</td> -->  
                <td>${user.country}</td>
                <td>
                    <img src="data:image/jpg;base64,${user.imageFile}" width="50" height="50" alt="User Image">
                </td>
                <td>
    <button onclick="editUser(${user.userId}, '${user.firstName}', '${user.lastName}', '${user.hobbiesAsString}', '${user.languagesAsString}')">Edit</button>
    <button onclick="deleteUser(${user.userId})">Delete</button>
</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="add-button">
        <button onclick="addUser()">Add User</button>
        <a href="LogoutServlet" class="logout-link">Logout</a>

    </div>
</div>

<script>
    $(document).ready(function () {
        $('#userTable').DataTable();
    });

    function addUser() {
        window.location.href = 'addUser.jsp';
    }

    function editUser(userId, firstName, lastName, hobbiesAsString, languagesAsString) {
        console.log('Editing user:', { userId, firstName, lastName, hobbiesAsString, languagesAsString });

        window.location.href = 'editUser.jsp?userId=' + encodeURIComponent(userId);
    }


    function deleteUser(userId) {
        var confirmDelete = confirm("Are you sure you want to delete this user?");
        if (confirmDelete) {
            $.ajax({
                url: 'UserListServlet',
                method: 'POST',
                data: {
                    action: 'delete',
                    userId: userId
                },
                success: function (data) {
                    location.reload();
                },
                error: function (error) {
                    console.error('Error deleting user:', error);
                    alert('Error deleting user. Please try again.');
                }
            });
        }
    }
</script>

</body>
</html>
