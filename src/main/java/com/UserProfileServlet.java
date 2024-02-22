//package com;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/UserProfileServlet")
//public class UserProfileServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // Get the user object from the session
//        User user = (User) request.getSession().getAttribute("user");
//
//        if (user != null) {
//            // Retrieve user data from the database using the user's email
//            user = getUserData(user.getEmail());
//
//            // Update the user object in the session with the complete data
//            request.getSession().setAttribute("user", user);
//
//            // Forward to userProfile.jsp
//            request.getRequestDispatcher("userProfile.jsp").forward(request, response);
//        } else {
//            // Redirect to login page if user is not found in the session
//            response.sendRedirect("login.jsp");
//        }
//    }
//
//    private User getUserData(String userEmail) {
//        User user = new User();
//
//        try {
//            String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
//            String dbUser = "postgres";
//            String dbPassword = "Iamthebest9879!";
//
//            Class.forName("org.postgresql.Driver");
//            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
//                String sql = "SELECT * FROM jaydave.user_data WHERE email=?";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                    preparedStatement.setString(1, userEmail);
//
//                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                        if (resultSet.next()) {
//                            user.setUserId(resultSet.getInt("user_id"));
//                            user.setFirstName(resultSet.getString("first_name"));
//                            user.setLastName(resultSet.getString("last_name"));
//                            user.setGender(resultSet.getString("gender"));
//                            user.setEmail(resultSet.getString("email"));
//                            user.setMobile(resultSet.getString("mobile_no"));
//                            user.setPassword(resultSet.getString("password"));
//                            
//
//                        }
//                    }
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//}
package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            
            user = getUserData(user.getEmail());

            request.getSession().setAttribute("user", user); 

            request.getRequestDispatcher("userProfile.jsp").forward(request, response);
        } else {
          
            response.sendRedirect("login.jsp");
        }
    }

    private User getUserData(String userEmail) {
        User user = new User();

        try {
            String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
            String dbUser = "postgres";
            String dbPassword = "Iamthebest9879!";

            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT * FROM jaydave.user_data WHERE email=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, userEmail);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            user.setUserId(resultSet.getInt("user_id"));
                            user.setFirstName(resultSet.getString("first_name"));
                            user.setLastName(resultSet.getString("last_name"));
                            user.setGender(resultSet.getString("gender"));
                            user.setEmail(resultSet.getString("email"));
                            user.setMobile(resultSet.getString("mobile_no"));
                            user.setPassword(resultSet.getString("password"));
                            user.setCountry(resultSet.getString("country"));
                            
                            user.setHobbies(resultSet.getString("hobby"));
                            //checking values of hobbies as per user
//                            String languagesww = user.getLanguagesAsString();
//                                    System.out.println(languagesww);
                                
                            
                            
                            String languagesString = resultSet.getString("languages");
                            List<String> languages = new ArrayList<>();
                            if (languagesString != null && !languagesString.isEmpty()) {
                                String[] languageArray = languagesString.split(",");
                                for (String language : languageArray) {
                                    languages.add(language.trim());
                                }
                            }
                            user.setLanguages(languages);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
