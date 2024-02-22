package com;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//    
//        response.getWriter().append("Served at: ").append(request.getContextPath()); 
//    }

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    	String firstName = request.getParameter("firstName"); 
        String lastName = request.getParameter("lastName"); 
        String gender = request.getParameter("gender"); 
        String email = request.getParameter("email"); 
        String mobile = request.getParameter("mobile"); 
        
        String password = request.getParameter("password");
        String[] languages = request.getParameterValues("languages");
//        String imageFileName = request.getPart("image").getSubmittedFileName();
        String[] hobbies = request.getParameterValues("hobby");
        String country = request.getParameter("country");

        String languagesAsString = String.join(",", languages);
        String hobbiesAsString = String.join(",", hobbies);

         String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
         String dbUser = "postgres";
         String dbPassword = "Iamthebest9879!";

         try {
             Class.forName("org.postgresql.Driver");

             try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
              
            	 connection.setSchema("jaydave");
            	 
            	 if (isEmailAlreadyRegistered(connection, email)) {
            		 String errorMessage = "Email already registered";
            	        request.setAttribute("errorMessage", errorMessage);
            	        request.getRequestDispatcher("registration.jsp").forward(request, response);
            	        return;
                 }

            	 String imageFileName = request.getPart("image").getSubmittedFileName();
            	 InputStream inputStream = request.getPart("image").getInputStream();

            	 // Convert InputStream to byte array
            	 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            	 byte[] buffer = new byte[4096];
            	 int bytesRead = -1;
            	 while ((bytesRead = inputStream.read(buffer)) != -1) {
            	     outputStream.write(buffer, 0, bytesRead);
            	 }
            	 byte[] imageBytes = outputStream.toByteArray();

            	 
            	 // Now you can use imageBytes in your PreparedStatement
            	 String sql = "INSERT INTO jaydave.user_data (first_name, last_name, gender, email, mobile_no, password, languages, hobby, country, image_file) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            	 try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            	     preparedStatement.setString(1, firstName);
            	     preparedStatement.setString(2, lastName);
            	     preparedStatement.setString(3, gender);
            	     preparedStatement.setString(4, email);
            	     preparedStatement.setString(5, mobile);
            	     preparedStatement.setString(6, password);
            	     preparedStatement.setString(7, languagesAsString);
                     preparedStatement.setString(8, hobbiesAsString);
            	     preparedStatement.setString(9, country);
            	     preparedStatement.setBytes(10, imageBytes); // Set image bytes
            	     preparedStatement.executeUpdate();
            	 }
             }
         } catch (ClassNotFoundException | SQLException e) { 
             e.printStackTrace(); 
             response.sendRedirect("registration.jsp?error=database_error"); 
             return; 
         } 

         // Registration successful, redirect to login page
         response.sendRedirect("login.jsp");  
        
    }
    private boolean isEmailAlreadyRegistered(Connection connection, String email) {
    	boolean emailExists = false;
        String sql = "SELECT COUNT(*) FROM jaydave.user_data WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    emailExists = count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emailExists;
    }
}
