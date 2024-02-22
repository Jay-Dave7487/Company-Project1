<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.User" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    
    
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

        .edit-form {
            background-color: #fff;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            max-width: 350px;
            width: 100%;
            max-height: 80vh;
            overflow-y: auto;
        }

        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        input[type="password"],
        select {
            width: calc(100% - 22px);
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        select[multiple] {
            height: 100px;
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

        .checkbox-container {
            margin-bottom: 15px;
        }

        .checkbox-container label {
            margin-right: 15px;
        }

        .error-message {
            color: red;
            margin-top: -5px;
            margin-bottom: 5px;
            font-size: 14px;
        }
    </style>
</head>
<body>

<div class="edit-form">
    <h2>Edit User</h2>
    <form id="editForm" action="UserListServlet" method="post" enctype="multipart/form-data">
    <!-- Hidden field to store the action -->
    <input type="hidden" name="action" value="edit">
    <!-- Hidden field to store the user ID -->
    <input type="hidden" name="userId" value="${param.userId}">

        <!-- Add input fields for user details -->
        <label for="firstName">First Name:</label>
        
       <input type="text" id="firstName" name="firstName" value="${user.firstName}">
        <span class="error-message" id="firstNameError"></span>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${user.lastName}">
        <span class="error-message" id="lastNameError"></span>

        <label for="gender">Gender:</label>
    <select name="gender" id="gender">
        <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
        <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
    </select>

        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="${user.email}" readonly>
        <span class="error-message" id="emailError"></span>

        <label for="mobile">Mobile:</label>
        <input type="text" id="mobile" name="mobile" value="${user.mobile}">
        <span class="error-message" id="mobileError"></span>

     <!--     <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${user.password}">
        <span class="error-message" id="passwordError"></span> -->
        
          <label for="image">Profile Image:</label>
          <input type="file" name="image" id="image">

        <div class="col-md-12">
    <label class="labels">Languages:</label>
    <div class="languages-options">
        <select id="languages" name="languages" multiple>
            <option value="english" 
                <c:forEach var="language" items="${user.languages}">
                    <c:if test="${language == 'english'}">
                        selected
                    </c:if>
                </c:forEach>>
                English
            </option>
            <option value="spanish" 
                <c:forEach var="language" items="${user.languages}">
                    <c:if test="${language == 'spanish'}">
                        selected
                    </c:if>
                </c:forEach>>
                Spanish
               
                
            </option>
        </select>
    </div>
</div>

<!-- Hobbies -->

   <div class="col-md-12">
    <label class="labels">Hobbies:</label>
    <div class="hobbies-options">
        Reading <input type="checkbox" id="reading" name="hobbies" value="reading" 
            <c:forEach var="hobby" items="${user.hobbies}">
                <c:if test="${hobby == 'reading'}">
                    checked
                </c:if>
            </c:forEach>>
        Traveling <input type="checkbox" id="traveling" name="hobbies" value="traveling" 
            <c:forEach var="hobby" items="${user.hobbies}">
                <c:if test="${hobby == 'traveling'}">
                    checked
                </c:if>
            </c:forEach>> 
    </div>
</div>


        <!-- Country -->
<label for="country">Country:</label>
<select name="country" id="country">
    <option value="us" ${user.country.equals("us") ? 'selected' : ''}>United States</option>
    <option value="uk" ${user.country.equals("uk") ? 'selected' : ''}>United Kingdom</option>
    <option value="in" ${user.country.equals("in") ? 'selected' : ''}>India</option>
</select>

        <input type="submit" value="Save Changes">
    </form>
</div>

<script>
$(document).ready(function() {
	$.ajax({
	    type: "GET",
	    url: "UserDataServlet", 
	    data: { userId: "${param.userId}" }, 
	    dataType: "json",
	    success: function(response) {
	        if (response && Object.keys(response).length > 0) {
	            $("#firstName").val(response.firstName);
	            $("#lastName").val(response.lastName);
	            $("#gender").val(response.gender);
	            $("#email").val(response.email);
	            $("#mobile").val(response.mobile);
	            
	            // Set languages
	            var languages = response.languages;
	            for (var i = 0; i < languages.length; i++) {
	                $("#languages option[value='" + languages[i] + "']").prop('selected', true);
	            }
	            
	            // Set hobbies
	            var hobbies = response.hobbies;
	            for (var j = 0; j < hobbies.length; j++) {
	                $("input[name='hobbies'][value='" + hobbies[j] + "']").prop('checked', true);
	            }
	            
	            // Set country
	            $("#country").val(response.country);
	        } else {
	            console.error("Empty or invalid response received.");
	        }
	    },
	    error: function(xhr, status, error) {
	        console.error("Error fetching user data: " + error);
	    }
	});

    $('#editForm').submit(function(e) {
        e.preventDefault(); // Prevent the form from submitting

        var isValid = validateForm();

        if (isValid) {
            this.submit();
        }
    });

    function validateForm() {
        var isValid = true;

        // Validate First Name
        var firstName = $("#firstName").val();
        if (firstName.trim() === "") {
            isValid = false;
            $("#firstNameError").text("First Name is required.");
        } else {
            $("#firstNameError").text("");
        }

        // Validate Last Name
        var lastName = $("#lastName").val();
        if (lastName.trim() === "") {
            isValid = false;
            $("#lastNameError").text("Last Name is required.");
        } else {
            $("#lastNameError").text("");
        }

        // Validate Email
        var email = $("#email").val();
        if (email.trim() === "") {
            isValid = false;
            $("#emailError").text("Email is required.");
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
      // var password = $("#password").val();
       // if (password.trim() === "") {
        //    isValid = false;
        //    $("#passwordError").text("Password is required.");
       // } else {
       //     $("#passwordError").text("");
      //  }

// Validate Languages
    var languages = $("#languages").val();
    if (!languages || languages.length === 0) {
        isValid = false;
        $("#languagesError").text("Please select at least one language.");
    } else {
        $("#languagesError").text("");
    }

    // Validate Hobbies
    var hobbies = $("input[name='hobbies']:checked").length;
    if (hobbies === 0) {
        isValid = false;
        $("#hobbiesError").text("Please select at least one hobby.");
    } else {
        $("#hobbiesError").text("");
    }

    // Validate Image
 //   var image = $("#image").val();
   // if (!image) {
     //   isValid = false;
       // $("#imageError").text("Please select an image file.");
    //} else {
      //  $("#imageError").text("");
    //}

        return isValid;
    }
});

</script>


</body>
</html>
