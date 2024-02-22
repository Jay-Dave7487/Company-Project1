package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        boolean isAuthenticated = authenticateUser(email, password);

        if (isAuthenticated) {
            User user = getUserData(email);

            if (user != null) {
                user.setEmail(email);
                user.setPassword(password);

                String userRole = determineUserRole(email);
                user.setRole(userRole);
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);

//                session.setMaxInactiveInterval(10);  

//                request.getSession().setAttribute("user", user);

                if ("admin".equals(userRole)) {
                    response.sendRedirect("UserListServlet");
                } else {
                    response.sendRedirect("UserProfileServlet");
                } 
                
            }
        } else {
            request.setAttribute("errorMessage", "Incorrect email or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
//            } else {
//                response.sendRedirect("login.jsp?error=user_not_found");
//            }
//        } else {
//            response.sendRedirect("login.jsp?error=invalid_credentials");
//        }
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

                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private boolean authenticateUser(String email, String password) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT COUNT(*) FROM jaydave.user_data WHERE email=? AND password=?"; 
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) { 
                    preparedStatement.setString(1, email); 
                    preparedStatement.setString(2, password); 

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next() && resultSet.getInt(1) > 0) {
                            return true; 
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false; 
    }


    private String determineUserRole(String email) {
        return "admin@example.com".equals(email) ? "admin" : "user";
    }
}