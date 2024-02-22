<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Set New Password</title>
    <!-- Include jQuery library -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .password-form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
        }

        input[type="password"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            display: inline-block;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            margin-top: -5px;
            margin-bottom: 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="password-form">
        <h2>Set New Password</h2>
        <form id="passwordForm" action="SetPasswordServlet" method="post">
            <!-- Hidden field to pass user email -->
            <input type="hidden" name="email" id="email" value="<%= request.getParameter("email") %>">
            
            <!-- Email -->
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" ><br>
            
            <!-- Current Password -->
            <label for="currentPassword">Current Password:</label>
            <input type="password" name="currentPassword" id="currentPassword">
            <span class="error-message" id="currentPasswordError"></span><br>

            <!-- New Password -->
            <label for="newPassword">New Password:</label>
            <input type="password" name="newPassword" id="newPassword">
            <span class="error-message" id="newPasswordError"></span><br>

            <!-- Action to specify the password update operation -->
            <input type="hidden" name="action" value="updatePassword">
          
            <input type="submit" value="Submit">
        </form>
    </div>

    <script>
        $(document).ready(function () {
        	 e.preventDefault();
            // Prefill email field from URL parameter
            $("#email").val(decodeURIComponent("<%= request.getParameter("email") %>"));
            
            $("#passwordForm").submit(function (e) {
                e.preventDefault(); // Prevent form submission
                
                // Validate form fields
                var isValid = true;
                var currentPassword = $("#currentPassword").val().trim();
                var newPassword = $("#newPassword").val().trim();
                if (currentPassword === "") {
                    $("#currentPasswordError").text("Current Password is required.");
                    isValid = false;
                } else {
                    $("#currentPasswordError").text("");
                }
                if (newPassword === "") {
                    $("#newPasswordError").text("New Password is required.");
                    isValid = false;
                } else {
                    $("#newPasswordError").text("");
                }

                // If form is valid, submit the form
                if (isValid) {
                    this.submit();
                }
            });
        });
    </script>
</body>
</html>
