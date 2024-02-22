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

@WebServlet("/UserImageServlet")
public class UserImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            byte[] imageData = getUserImage(user.getEmail());

            if (imageData != null) {
               
                response.setContentType("image/jpeg");
                response.setContentLength(imageData.length);
                response.getOutputStream().write(imageData);
            } else {
                
                response.setContentType("text/plain");
                response.getWriter().write("Image not found");
            }
        } else {
           
            response.sendRedirect("login.jsp");
        }
    }

    private byte[] getUserImage(String userEmail) {
        byte[] imageData = null;

        try {
            String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
            String dbUser = "postgres";
            String dbPassword = "Iamthebest9879!";

            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT image_file FROM jaydave.user_data WHERE email=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, userEmail);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            
                            imageData = resultSet.getBytes("image_file");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return imageData;
    }
}
