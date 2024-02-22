<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <!-- Include Bootstrap CSS and JS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Include jQuery and DataTables CSS and JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>User List</h2>
    <table id="userTable" class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <!-- Populate this section dynamically with user data -->
        </tbody>
    </table>
</div>

<!-- Include Bootstrap and DataTables JS scripts -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    // Initialize DataTable
    $(document).ready(function() {
        $('#userTable').DataTable();
    });
</script>
<!-- Inside the user table -->
<tr>
    <td>${user.id}</td>
    <td>${user.firstName}</td>
    <td>${user.lastName}</td>
    <td>${user.email}</td>
    <td>
        <a href="update.jsp?id=${user.id}" class="btn btn-primary">Edit</a>
        <button class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button>
    </td>
</tr>
<script>
    function deleteUser(userId) {
        // Show Bootstrap confirmation alert
        if (confirm("Are you sure you want to delete this user?")) {
            // If user confirms, send AJAX request to delete
            $.ajax({
                url: 'DeleteUserServlet', // Replace with your servlet URL
                type: 'POST',
                data: { id: userId },
                success: function(response) {
                    // Reload the table or update it as needed
                    $('#userTable').DataTable().ajax.reload();
                },
                error: function(xhr, status, error) {
                    console.error(xhr.responseText);
                }
            });
        }
    }
</script>

</body>
</html>
