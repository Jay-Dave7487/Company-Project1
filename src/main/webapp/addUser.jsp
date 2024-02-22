<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
           background-color: blue;
            margin: 0;
            padding: 0;
        }
        h2 {
            text-align: center;
            margin-top: 20px;
        }
        form {
            max-width: 500px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="email"],
        input[type="tel"],
        input[type="password"],
        select,
        input[type="file"] {
            width: calc(100% - 10px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
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
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    
    <script>
        $(document).ready(function() {
            // Function to validate the form
            function validateForm() {
                var isValid = true;

                // Validate First Name
                var firstName = $("#firstName").val();
                if (firstName.trim() === "") {
                    isValid = false;
                    $("#firstNameError").text("First Name is required.").css("color", "red");
                } else {
                    $("#firstNameError").text("");
                }

                // Validate Last Name
                var lastName = $("#lastName").val();
                if (lastName.trim() === "") {
                    isValid = false;
                    $("#lastNameError").text("Last Name is required.").css("color", "red");
                } else {
                    $("#lastNameError").text("");
                }

                // Validate Email
                var email = $("#email").val();
                if (email.trim() === "") {
                    isValid = false;
                    $("#emailError").text("Email is required.").css("color", "red");
                } else {
                    $("#emailError").text("");
                }

                // Validate Mobile Number
                var mobile = $("#mobile").val();
                if (mobile.trim() === "") {
                    isValid = false;
                    $("#mobileError").text("Mobile Number is required.").css("color", "red");
                } else if (!/^\d{10}$/.test(mobile.trim())) {
                    // Check if mobile number contains exactly 10 digits
                    isValid = false;
                    $("#mobileError").text("Mobile Number must be 10 digits.").css("color", "red");
                } else {
                    $("#mobileError").text("");
                }

                // Validate Password
                var password = $("#password").val();
                if (password.trim() === "") {
                    isValid = false;
                    $("#passwordError").text("Password is required.").css("color", "red");
                } else {
                    $("#passwordError").text("");
                }

                // Add more validations as needed


                return isValid;
            }

            // Attach form submission event
            $("form").submit(function() {
                return validateForm();
            });
        });
    </script>
</head>
<body>
    <h2>Add User</h2>
    <form action="RegistrationServlet" method="post" enctype="multipart/form-data"> 
        
        <!-- First Name -->
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName">
        <span id="firstNameError"></span><br>

        <!-- Last Name -->
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName">
        <span id="lastNameError"></span><br>

        <!-- Date of Birth (dob) -->
        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" max="<%= java.time.LocalDate.now() %>"><br>

        <!-- Gender -->
        <label>Gender:</label>
        <input type="radio" id="male" name="gender" value="male"> Male
        <input type="radio" id="female" name="gender" value="female"> Female<br>

        <!-- Email -->
        <label for="email">Email:</label>
        <input type="email" id="email" name="email">
        <span id="emailError"></span><br>

        <!-- Mobile Number -->
        <label for="mobile">Mobile Number:</label>
        <input type="tel" id="mobile" name="mobile">
        <span id="mobileError"></span><br>

        <!-- Password -->
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
        <span id="passwordError"></span><br>

        <!-- Languages -->
        <label for="languages">Languages:</label>
        <select id="languages" name="languages" multiple>
            <option value="english">English</option>
            <option value="spanish">Spanish</option>
        </select><br>

        <!-- Image Upload -->
        <label for="image">Image:</label>
        <input type="file" id="image" name="image" accept="image/*"><br>

        <!-- Hobby -->
        <label>Hobbies:</label>
        <input type="checkbox" id="reading" name="hobby" value="reading"> Reading 
        <input type="checkbox" id="traveling" name="hobby" value="traveling"> Traveling<br>

        <!-- Country -->
        <label for="country">Country:</label>
        <select id="country" name="country">
            <option value="us">United States</option>
            <option value="uk">United Kingdom</option>
            <option value="india">India</option>
        </select><br>
        <br>
        <input type="submit" value="Register"><br>
       
    </form>
</body>
</html>
