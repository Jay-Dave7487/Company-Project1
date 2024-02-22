package com;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/EditUserProfileServlet")
public class EditUserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    HttpSession session = request.getSession(); 
    User user = (User) session.getAttribute("user"); 
    
    if (user != null) {

        request.getSession().setAttribute("user", user); 
        request.getRequestDispatcher("editUserProfile.jsp").forward(request, response);
    } else {
        response.sendRedirect("login.jsp");
    }
}
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");

        
        if ("edit".equals(action)) {
            try {
                editUser(request);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        List<User> userList = getUserListFromDatabase();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("userProfile.jsp").forward(request, response);
    }

    private void editUser(HttpServletRequest request) throws IOException, ServletException, ClassNotFoundException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String[] languages = request.getParameterValues("languages");
        String[] hobbies = request.getParameterValues("hobbies");
        String country = request.getParameter("country");

        // Convert arrays to comma-separated strings
        String languagesAsString = String.join(",", languages);
        String hobbiesAsString = String.join(",", hobbies);

        InputStream inputStream = null;
        Part filePart = request.getPart("image");
        try {
            if (filePart != null && filePart.getSize() > 0) {
                inputStream = filePart.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                connection.setAutoCommit(false);
                
                String sql;
                if (inputStream != null) {
                    sql = "UPDATE jaydave.user_data SET first_name=?, last_name=?, gender=?, email=?, mobile_no=?, password=?, languages=?, hobby=?, country=?, image_file=? WHERE user_id=?";
                } else {
                    sql = "UPDATE jaydave.user_data SET first_name=?, last_name=?, gender=?, email=?, mobile_no=?, password=?, languages=?, hobby=?, country=? WHERE user_id=?";
                }
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
                    if (inputStream != null) {
                        preparedStatement.setBinaryStream(10, inputStream);
                        preparedStatement.setInt(11, userId);
                    } else {
                        preparedStatement.setInt(10, userId);
                    }
                    preparedStatement.executeUpdate();
                    connection.commit();
                }
            }
        } catch (SQLException e) {
        	request.setAttribute("msg","email is alredy there");
        	System.out.println("-----------exception called------------");
            e.printStackTrace();
        }
        
    }



    private List<User> getUserListFromDatabase() {
        List<User> userList = new ArrayList<>();
        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT * FROM jaydave.user_data";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            User user = new User();
                            user.setUserId(resultSet.getInt("user_id"));
                            user.setFirstName(resultSet.getString("first_name"));
                            user.setLastName(resultSet.getString("last_name"));
                            user.setGender(resultSet.getString("gender"));
                            user.setEmail(resultSet.getString("email"));
                            user.setMobile(resultSet.getString("mobile_no"));
                            user.setPassword(resultSet.getString("password"));
                            user.setRole("role");
                            
                            String languagesString = resultSet.getString("languages");
                            user.setLanguages(languagesString != null ? languagesString.split(",") : new String[0]);

                            String hobbiesString = resultSet.getString("hobby");
                            user.setHobbies(hobbiesString != null ? hobbiesString.split(",") : new String[0]);

                            user.setCountry(resultSet.getString("country"));
                            userList.add(user);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


}