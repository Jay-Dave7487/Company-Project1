package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserDataServlet")
public class UserDataServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        // Retrieve user data from the database
        User user = getUserData(userId);

        // Convert user object to JSON format
        String jsonData = userToJson(user);

        // Set response content type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send JSON response back to the client
        PrintWriter out = response.getWriter();
        out.print(jsonData);
        out.flush();
    }

    private User getUserData(int userId) {
        User user = null;

        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";

        try {
            Class.forName("org.postgresql.Driver");

            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT * FROM jaydave.user_data WHERE user_id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, userId);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            user = new User();
                            user.setUserId(resultSet.getInt("user_id"));
                            user.setFirstName(resultSet.getString("first_name"));
                            user.setLastName(resultSet.getString("last_name"));
                            user.setGender(resultSet.getString("gender"));
                            user.setEmail(resultSet.getString("email"));
                            user.setMobile(resultSet.getString("mobile_no"));

                            // Set languages
                            String languagesString = resultSet.getString("languages");
                            user.setLanguages(languagesString != null ? languagesString.split(",") : new String[0]);

                            // Set hobbies
                            String hobbiesString = resultSet.getString("hobby");
                            user.setHobbies(hobbiesString != null ? hobbiesString.split(",") : new String[0]);

                            // Set country
                            user.setCountry(resultSet.getString("country"));
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    private String userToJson(User user) {
        if (user == null) {
            return "{}"; // Return empty JSON object if user is null
        }

        // Convert User object to JSON format
        StringBuilder jsonData = new StringBuilder("{");
        jsonData.append("\"userId\": ").append(user.getUserId()).append(",");
        jsonData.append("\"firstName\": \"").append(user.getFirstName()).append("\",");
        jsonData.append("\"lastName\": \"").append(user.getLastName()).append("\",");
        jsonData.append("\"gender\": \"").append(user.getGender()).append("\",");
        jsonData.append("\"email\": \"").append(user.getEmail()).append("\",");
        jsonData.append("\"mobile\": \"").append(user.getMobile()).append("\",");

        // Append languages
        jsonData.append("\"languages\": ").append(arrayToJson(user.getLanguages())).append(",");

        // Append hobbies
        jsonData.append("\"hobbies\": ").append(arrayToJson(user.getHobbies())).append(",");

        // Append country
        jsonData.append("\"country\": \"").append(user.getCountry()).append("\"");

        jsonData.append("}");

        return jsonData.toString();
    }

    private String arrayToJson(String[] array) {
        if (array == null || array.length == 0) {
            return "[]"; // Return empty JSON array if array is null or empty
        }

        // Convert array to JSON format
        StringBuilder jsonData = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            jsonData.append("\"").append(array[i]).append("\"");
            if (i < array.length - 1) {
                jsonData.append(",");
            }
        }
        jsonData.append("]");

        return jsonData.toString();
    }
}
