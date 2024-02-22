<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.User" %>
 
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Your Profile</title>
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

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 400px;
            max-height: 70%;
            overflow-y: auto; /* Add scrollbar if content exceeds the height */
        }

        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
            margin-top: 0; /* Remove default top margin */
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        input[type="text"],
        input[type="password"],
        select {
            width: calc(100% - 10px);
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="file"] {
            width: calc(100% - 10px);
            padding: 8px;
            margin-bottom: 15px;
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
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        select {
            width: 100%;
        }

        input[type="checkbox"] {
            margin-right: 5px;
        }

        button {
            background-color: #007bff;
            color: #fff;
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* Validation error style */
        .error {
            border: 1px solid red;
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
<div class="contact-form bg-light p-30">
 
    
<form id="editProfileForm" action="EditUserProfileServlet?action=edit" method="post" enctype="multipart/form-data">
    <h2>Edit Your Profile</h2>
    <!-- Hidden field to store the user ID -->
    <input type="hidden" name="userId" value="${user.userId}">

    <!-- Add input fields for user details -->
    <label for="firstName">First Name:</label>
    <input type="text" name="firstName" id="firstName" value="${user.firstName}"><span class="error-message" id="firstNameError"></span>

    <label for="lastName">Last Name:</label>
    <input type="text" name="lastName" id="lastName" value="${user.lastName}"><span class="error-message" id="lastNameError"></span>

    <label for="gender">Gender:</label>
    <select name="gender" id="gender">
        <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
        <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
    </select>

    <label for="email">Email:</label>
    <input type="text" name="email" id="email" value="${user.email}" ><span class="error-message" id="emailError"></span>

    <label for="mobile">Mobile:</label>
    <input type="text" name="mobile" id="mobile" value="${user.mobile}"><span class="error-message" id="mobileError"></span>

    <label for="password">Password:</label>
    <input type="password" name="password" id="password" value="${user.password}"><span class="error-message" id="passwordError"></span>

 <label for="image">Profile Image:</label>
    <img src="UserImageServlet" width="50" height="50" alt="User Image">
    <input type="file" name="image" id="image"> 

   <!-- Languages -->
   <!-- label for="languages">Languages:</label>
<select name="languages" id="languages" multiple>
    <option value="english" ${Arrays.asList(user.languages).contains("english") ? 'selected' : ''}>English</option>
    <option value="spanish" ${Arrays.asList(user.languages).contains("spanish") ? 'selected' : ''}>Spanish</option>
</select>  
  -->
    
 <!-- <select name="languages" id="languages" multiple>
        <c:forEach var="language" items="${user.languages}">
            <option value="${language}" ${param.languages != null && param.languages.contains(language) ? 'selected' : ''}>${language}</option>
        </c:forEach>
</select> --> 
 
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
            <option value="hindi" 
                <c:forEach var="language" items="${user.languages}">
                    <c:if test="${language == 'hindi'}">
                        selected
                    </c:if>
                </c:forEach>>
                Hindi
            </option>
        </select>
    </div>
</div>


<!-- Hobby 
<label>Hobbies:</label>
<input type="checkbox" name="hobbies" id="reading" value="reading" ${Arrays.asList(user.hobbies).contains("reading") ? 'checked' : ''}> Reading
<input type="checkbox" name="hobbies" id="traveling" value="traveling" ${Arrays.asList(user.hobbies).contains("traveling") ? 'checked' : ''}> Traveling

-->
<!-- label>Hobbies:</label>
<c:forEach var="hobby" items="${user.hobbies}">
    <label>
        <input type="checkbox" name="hobbies" value="${hobby}" ${param.hobbies != null && param.hobbies.contains(hobby) ? 'checked' : ''}>
        
        ${hobby}
    </label>
    <br>
</c:forEach> -->

 
<!-- <label>Hobbies:</label>
<c:forEach var="allHobby" items="${user.hobbies}">
    <c:set var="isChecked" value="false" />
    <c:forEach var="selectedHobby" items="${user.hobbies}">
        <c:if test="${selectedHobby eq allHobby}">
            <c:set var="isChecked" value="true" />
        </c:if>
    </c:forEach>
    <label>
        <input type="checkbox" name="hobbies" value="${allHobby}" ${isChecked ? 'checked' : ''}>
        ${allHobby}
    </label>
    <br>
</c:forEach>  -->


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
$(document).ready(function () {
   
    $("#editProfileForm").submit(function (e) {
        e.preventDefault(); 

        var isValid = true;

        if ($("#firstName").val().trim() === "") {
            $("#firstName").addClass("error");
            $("#firstNameError").text("First Name is required.");
            isValid = false;
        } else {
            $("#firstName").removeClass("error");
            $("#firstNameError").text("");
        }

        if ($("#lastName").val().trim() === "") {
            $("#lastName").addClass("error");
            $("#lastNameError").text("Last Name is required.");
            isValid = false;
        } else {
            $("#lastName").removeClass("error");
            $("#lastNameError").text("");
        }

        if ($("#gender").val().trim() === "") {
            $("#gender").addClass("error");
            $("#genderError").text("Gender is required.");
            isValid = false;
        } else {
            $("#gender").removeClass("error");
            $("#genderError").text("");
        }

        var email = $("#email").val().trim();
        if (email === "") {
            $("#email").addClass("error");
            $("#emailError").text("Email is required.");
            isValid = false;
        } else if (!isValidEmail(email)) {
            $("#email").addClass("error");
            $("#emailError").text("Enter a valid email address.");
            isValid = false;
        } else {
            $("#email").removeClass("error");
            $("#emailError").text("");
        }

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

        if ($("#password").val().trim() === "") {
            $("#password").addClass("error");
            $("#passwordError").text("Password is required.");
            isValid = false;
        } else {
            $("#password").removeClass("error");
            $("#passwordError").text("");
        }

     //  var image = $("#image").val();
       // if (image === "") {
         //  $("#image").addClass("error");
           // $("#imageError").text("Profile Image is required.");
        //    isValid = false;
        //} else {
          //  $("#image").removeClass("error");
            //$("#imageError").text("");
        //}
        
        var languages = $("#languages").val();
        if (!languages || languages.length === 0) {
            isValid = false;
            $("#languagesError").text("Please select at least one language.");
        } else {
            $("#languagesError").text("");
        }

        var hobbies = $("input[name='hobbies']:checked").length;
        if (hobbies === 0) {
            $("#hobbiesError").text("Please select at least one hobby.");
            isValid = false;
        } else {
            $("#hobbiesError").text("");
        }

        var country = $("#country").val().trim();
        if (country === "") {
            $("#country").addClass("error");
            $("#countryError").text("Country is required.");
            isValid = false;
        } else {
            $("#country").removeClass("error");
            $("#countryError").text("");
        }


        if (isValid) {
            $.ajax({
                type: "POST",
                url: "EditUserProfileServlet?action=edit",
                data: new FormData(this),
                processData: false,
                contentType: false,
                success: function () {
                    window.location.href = 'UserProfileServlet';
                },
                error: function () {
                    alert("An error occurred while saving changes.");
                }
            });
        }
    });

    function isValidEmail(email) {
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    // Function to validate mobile number format
    function isValidMobile(mobile) {
        var mobileRegex = /^\d{10}$/;
        return mobileRegex.test(mobile);
    }
});
</script>


</body>
</html>
