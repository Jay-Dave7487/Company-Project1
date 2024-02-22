<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
           body {
            background-image: url(background.jpeg); /* Path to your background image */
            background-size: cover;
            background-position: center;
            height: 100vh; /* Set the height to fill the viewport */
            margin: 0; /* Remove default margin */
            padding: 0; /* Remove default padding */
            display: flex;
            justify-content: center; /* Center content horizontally */
            align-items: center; /* Center content vertically */
        }

        .login-form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center; /* Center-align the form */
        }

        h2 {
            color: #333333;
            margin-bottom: 20px; /* Add some spacing below the heading */
        }

        label {
            display: block;
            margin: 10px 0 5px;
            color: #333333;
        }

        input {
            width: calc(100% - 22px); /* Adjusted width to account for padding */
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .link {
            margin-top: 10px;
            color: #3498db;
            text-decoration: none;
        }

        .error-message {
            color: red;
            font-size: 12px;
            margin-top: -10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="login-form">
    <h2>User Login</h2>
    <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="error-message"><%= request.getAttribute("errorMessage") %></div>
    <% } %>
    <div id="successMessage" class="alert alert-success" role="alert" style="display: none;">
        Successfully logged in!
    </div>
    <form id="loginForm" action="/LoginApplication/LoginServlet" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" id="email">

        <div id="emailError" class="error-message"></div>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password">

        <div id="passwordError" class="error-message"></div>

        <input type="submit" value="Login">
    </form>

    <a href="#" onclick="redirectToSetPassword(); return false;" class="link">Change Password</a>

    <br>
    <a href="/LoginApplication/registration.jsp" class="link">Register</a><br>
    <a href="/LoginApplication/forgotpassword.jsp" class="link">Forgot Password</a>
</div>


    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#loginForm').submit(function (e) {
                e.preventDefault();
                var email = $('#email').val();
                var password = $('#password').val();

                // Reset error messages
                $('#emailError').text('');
                $('#passwordError').text('');

                // Validate email
                if (!email) {
                    $('#emailError').text('Email is required');
                    return;
                }

                // Validate password
                if (!password) {
                    $('#passwordError').text('Password is required');
                    return;
                }

                // Submit form if validation passes
                $(this).unbind('submit').submit();
                
                $('#successMessage').fadeIn();
            });
        });

        function redirectToSetPassword() {
            var email = $('#email').val();
            var setPasswordLink = "setPassword.jsp?email=" + encodeURIComponent(email);
            window.location.href = setPasswordLink;
        }
    </script>
</body>
</html>
