package com;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        boolean exists = checkEmailExists(email);

        HttpSession session = request.getSession();
        if (exists) {
            session.setAttribute("emailStatus", "exists");
        } else {
            session.setAttribute("emailStatus", "notexists");
        }
     // Forward the request to the JSP page
        request.getRequestDispatcher("editUserProfile.jsp").forward(request, response);
    }


    private boolean checkEmailExists(String email) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";
        boolean exists = false;

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT COUNT(*) AS count FROM jaydave.user_data WHERE email = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, email);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            int count = resultSet.getInt("count");
                            exists = count > 0;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
}