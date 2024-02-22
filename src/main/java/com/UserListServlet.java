//package com;
//
//import java.io.IOException;
//import java.util.*;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.Part;
//
//@MultipartConfig
//@WebServlet("/UserListServlet")
//public class UserListServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//       
//        HttpSession session = request.getSession(); 
//        User user = (User) session.getAttribute("user"); 
//
//        if (user != null && "admin".equals(user.getRole())) {
//            List<User> userList = getUserListFromDatabase(); 
//            
//            
//            request.setAttribute("userList", userList);
//            request.getRequestDispatcher("userList.jsp").forward(request, response);
//        } else {
//          
//            response.sendRedirect("login.jsp");
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//      
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//
//        
//        if (user != null && "admin".equals(user.getRole())) {
//
//            String action = request.getParameter("action");
//
//            if ("add".equals(action)) {
//               
//                addUser(request);
//            } else if ("edit".equals(action)) {
//          
//                editUser(request);
//                
//            } else if ("delete".equals(action)) {
//         
//                deleteUser(request);
//            }
//
//            List<User> userList = getUserListFromDatabase();
//            request.setAttribute("userList", userList);
//            request.getRequestDispatcher("userList.jsp").forward(request, response);
////        } else if(user != null && "user".equals(user.getRole())){
////        	String action = request.getParameter("action");
////        	  if("edit".equals(action)) {
////                 
////                 editUser(request);
////             }
//        } else {
//        	response.sendRedirect("login.jsp");
//        }
//    }
//
//    private User getUserById(int userId) {
//        User user = null;
//        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
//        String dbUser = "postgres";
//        String dbPassword = "Iamthebest9879!";
//
//        try {
//            Class.forName("org.postgresql.Driver");
//            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
//                String sql = "SELECT * FROM jaydave.user_data WHERE user_id=?";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                    preparedStatement.setInt(1, userId);
//                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                        if (resultSet.next()) {
//                            user = new User();
//                            user.setUserId(resultSet.getInt("user_id"));
//                            user.setFirstName(resultSet.getString("first_name"));
//                            user.setLastName(resultSet.getString("last_name"));
//                            user.setGender(resultSet.getString("gender"));
//                            user.setEmail(resultSet.getString("email"));
//                            user.setMobile(resultSet.getString("mobile_no"));
//                            // Retrieve other user details and set them accordingly
//                        }
//                    }
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//
//    
//    private List<User> getUserListFromDatabase() {
//        List<User> userList = new ArrayList<>();
//        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
//        String dbUser = "postgres";
//        String dbPassword = "Iamthebest9879!";
//
//        try {
//            Class.forName("org.postgresql.Driver");
//            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
//                String sql = "SELECT * FROM jaydave.user_data WHERE role != 'admin'";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                        while (resultSet.next()) {
//                            User user = new User();
//                            user.setUserId(resultSet.getInt("user_id"));
//                            user.setFirstName(resultSet.getString("first_name"));
//                            user.setLastName(resultSet.getString("last_name"));
//                            user.setGender(resultSet.getString("gender"));
//                            user.setEmail(resultSet.getString("email"));
//                            user.setMobile(resultSet.getString("mobile_no"));
//                            user.setPassword(resultSet.getString("password"));
//                            user.setRole("role");
//
//                            String languagesString = resultSet.getString("languages");
//                            user.setLanguages(languagesString != null ? languagesString.split(",") : new String[0]);
//
//                            String hobbiesString = resultSet.getString("hobby");
//                            user.setHobbies(hobbiesString != null ? hobbiesString.split(",") : new String[0]);
//
//                            user.setCountry(resultSet.getString("country"));
//
//                            userList.add(user);
//                        }
//                    }
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return userList;
//    }
//
//    private void addUser(HttpServletRequest request) {
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String gender = request.getParameter("gender");
//        String email = request.getParameter("email");
//        String mobile = request.getParameter("mobile");
//        String password = request.getParameter("password");
//        String[] languages = request.getParameterValues("languages");
//        String[] hobbies = request.getParameterValues("hobbies");
//        String country = request.getParameter("country");
////        String role = request.getParameter("role");
//
//        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
//        String dbUser = "postgres";
//        String dbPassword = "Iamthebest9879!";
//
//        try {
//            Class.forName("org.postgresql.Driver");
//            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
//                String sql = "INSERT INTO jaydave.user_data (first_name, last_name, gender, email, mobile_no, password, languages, hobby, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                    preparedStatement.setString(1, firstName);
//                    preparedStatement.setString(2, lastName);
//                    preparedStatement.setString(3, gender);
//                    preparedStatement.setString(4, email);
//                    preparedStatement.setString(5, mobile);
//                    preparedStatement.setString(6, password);
//                    preparedStatement.setArray(7, connection.createArrayOf("text", languages));
//                    preparedStatement.setArray(8, connection.createArrayOf("text", hobbies));
//                    preparedStatement.setString(9, country);
//
//                    preparedStatement.executeUpdate();
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        
//        }
//    }
//
//    private void editUser(HttpServletRequest request) throws IOException, ServletException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String gender = request.getParameter("gender");
//        String email = request.getParameter("email");
//        String mobile = request.getParameter("mobile");
//    
//        // String password = request.getParameter("password"); 
//
//        String[] languages = request.getParameterValues("languages");
//        String[] hobbies = request.getParameterValues("hobbies");
//        String country = request.getParameter("country");
//
//        // Print user details for testing
//        System.out.println("UserId: " + userId);
//        System.out.println("FirstName: " + firstName);
//        System.out.println("LastName: " + lastName);
//        System.out.println("gender: " + gender);
//        System.out.println("email: " + email);
//        // System.out.println("password: " + password); // Remove printing password
//        System.out.println("languages: " + Arrays.toString(languages));
//        System.out.println("hobbies: " + Arrays.toString(hobbies));
//        System.out.println("country: " + country);
//
//        InputStream inputStream = null;
//        Part filePart = request.getPart("image");
//        try {
//            if (filePart != null && filePart.getSize() > 0) {
//                inputStream = filePart.getInputStream();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
//        String dbUser = "postgres";
//        String dbPassword = "Iamthebest9879!";
//
//        try {
//            Class.forName("org.postgresql.Driver");
//            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
//                String sql = "UPDATE jaydave.user_data SET first_name=?, last_name=?, gender=?, email=?, mobile_no=?, image_file=?, languages=?, hobby=?, country=?, role='user' WHERE user_id=?";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                    preparedStatement.setString(1, firstName);
//                    preparedStatement.setString(2, lastName);
//                    preparedStatement.setString(3, gender);
//                    preparedStatement.setString(4, email);
//                    preparedStatement.setString(5, mobile);
//                    
//                    // preparedStatement.setString(6, password);
//                    
//                    if (inputStream != null) {
//                        byte[] imageBytes = inputStream.readAllBytes();
//                        preparedStatement.setBytes(6, imageBytes);
//                    } else {
//                        preparedStatement.setNull(6, java.sql.Types.BLOB);
//                    }
//
//                    preparedStatement.setArray(7, connection.createArrayOf("text", languages));
//                    preparedStatement.setArray(8, connection.createArrayOf("text", hobbies));
//                    preparedStatement.setString(9, country);
//                    preparedStatement.setInt(10, userId);
//
//                    System.out.println("Edited...");
//                    preparedStatement.executeUpdate();
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } 
//    }
//
//    
//    private void deleteUser(HttpServletRequest request) {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//
//        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
//        String dbUser = "postgres";
//        String dbPassword = "Iamthebest9879!";
//        System.out.println("Yoo I delete....");
//        try {
//            Class.forName("org.postgresql.Driver");
//            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
//                String sql = "DELETE FROM jaydave.user_data WHERE user_id=?";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                    preparedStatement.setInt(1, userId);
//
//                    preparedStatement.executeUpdate();
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            
//        }
//    }
//
//}
package com;

import java.io.IOException;
import java.util.*;
import java.io.InputStream;
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
    import java.util.Base64;
@MultipartConfig
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    HttpSession session = request.getSession(); 
    User user = (User) session.getAttribute("user"); 

    if (user != null && "admin".equals(user.getRole())) {
        List<User> userList = getUserListFromDatabase(); 
     
        for (User user1 : userList) {
            byte[] imageData = getImageDataFromDatabase(user1.getUserId());
            if (imageData != null) {
                user1.setImageFile(imageData);
            }
        }
        
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("userList.jsp").forward(request, response);

    } else {
        response.sendRedirect("login.jsp");
    }
}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        
        if (user != null && "admin".equals(user.getRole())) {

            String action = request.getParameter("action");
            

            if ("add".equals(action)) {
               
                addUser(request);
            } else if ("edit".equals(action)) {
          
                try {
					editUser(request);
				} catch (IOException | ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            } else if ("delete".equals(action)) {
         
                deleteUser(request);
            }

            List<User> userList = getUserListFromDatabase();
            for (User user1 : userList) {
                user1.setImageFile(getImageDataFromDatabase(user1.getUserId()));
            }
         
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("userList.jsp").forward(request, response);
        } else {
        	response.sendRedirect("login.jsp");
        }
    }

    private User getUserById(int userId) {
        User user = null;
        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT * FROM jaydave.user_data WHERE user_id=?";
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
                            String languagesString = resultSet.getString("languages");
                            user.setLanguages(languagesString != null ? languagesString.split(",") : new String[0]);

                            String hobbiesString = resultSet.getString("hobby");
                            user.setHobbies(hobbiesString != null ? hobbiesString.split(",") : new String[0]);

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


    
    private List<User> getUserListFromDatabase() {
        List<User> userList = new ArrayList<>();
        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT * FROM jaydave.user_data WHERE role != 'admin'";
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

    private void addUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String[] languages = request.getParameterValues("languages");
        String[] hobbies = request.getParameterValues("hobbies");
        String country = request.getParameter("country");
//        String role = request.getParameter("role");

        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "INSERT INTO jaydave.user_data (first_name, last_name, gender, email, mobile_no, password, languages, hobby, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, gender);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, mobile);
                    preparedStatement.setString(6, password);
                    preparedStatement.setArray(7, connection.createArrayOf("text", languages));
                    preparedStatement.setArray(8, connection.createArrayOf("text", hobbies));
                    preparedStatement.setString(9, country);

                    preparedStatement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        
        }
    }

//    private void editUser(HttpServletRequest request) throws IOException, ServletException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String gender = request.getParameter("gender");
//        String email = request.getParameter("email");
//        String mobile = request.getParameter("mobile");
//    
//        // String password = request.getParameter("password"); 
//
//        String[] languages = request.getParameterValues("languages");
//        String[] hobbies = request.getParameterValues("hobbies");
//        String country = request.getParameter("country");
//        // Convert arrays to comma-separated strings
//        String languagesAsString = String.join(",", languages);
//        String hobbiesAsString = String.join(",", hobbies);
//        
//        System.out.println("UserId: " + userId);
//        System.out.println("FirstName: " + firstName);
//        System.out.println("LastName: " + lastName);
//        System.out.println("gender: " + gender);
//        System.out.println("email: " + email);
//        // System.out.println("password: " + password);
//        System.out.println("languages: " + Arrays.toString(languages));
//        System.out.println("hobbies: " + Arrays.toString(hobbies));
//        System.out.println("country: " + country);
//
//        InputStream inputStream = null;
//        Part filePart = request.getPart("image");
//        try {
//            if (filePart != null && filePart.getSize() > 0) {
//                inputStream = filePart.getInputStream();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
//        String dbUser = "postgres";
//        String dbPassword = "Iamthebest9879!";
//
//        try {
//            Class.forName("org.postgresql.Driver");
//            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
//                String sql = "UPDATE jaydave.user_data SET first_name=?, last_name=?, gender=?, email=?, mobile_no=?, image_file=?, languages=?, hobby=?, country=?, role='user' WHERE user_id=?";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                    preparedStatement.setString(1, firstName);
//                    preparedStatement.setString(2, lastName);
//                    preparedStatement.setString(3, gender);
//                    preparedStatement.setString(4, email);
//                    preparedStatement.setString(5, mobile);
//                    
//                    // preparedStatement.setString(6, password);
//                    
//                    if (inputStream != null) {
//                        byte[] imageBytes = inputStream.readAllBytes();
//                        preparedStatement.setBytes(6, imageBytes);
//                    } else {
//                        preparedStatement.setNull(6, java.sql.Types.BLOB);
//                    }
//
//                    preparedStatement.setString(7, languagesAsString);
//                    preparedStatement.setString(8, hobbiesAsString);
//                    preparedStatement.setString(9, country);
//                    preparedStatement.setInt(10, userId);
//
//                    System.out.println("Edited...");
//                    preparedStatement.executeUpdate();
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } 
//    }
   
//    private void editUser(HttpServletRequest request) throws IOException, ServletException, ClassNotFoundException {
//    	 int userId = Integer.parseInt(request.getParameter("userId"));
//         String firstName = request.getParameter("firstName");
//         String lastName = request.getParameter("lastName");
//         String gender = request.getParameter("gender");
//         String email = request.getParameter("email"); 
//         String mobile = request.getParameter("mobile");
////         String password = request.getParameter("password");
//         String[] languages = request.getParameterValues("languages");
//         String[] hobbies = request.getParameterValues("hobbies");
//         String country = request.getParameter("country");
//         
//      // Convert arrays to comma-separated strings
//         String languagesAsString = String.join(",", languages);
//         String hobbiesAsString = String.join(",", hobbies);
//         
//         System.out.println("UserId: " + userId);
//             System.out.println("FirstName: " + firstName);
//             System.out.println("LastName: " + lastName);
//             System.out.println("gender: " + gender);
//             System.out.println("email: " + email);
//             // System.out.println("password: " + password);
//             System.out.println("languages: " + Arrays.toString(languages));
//             System.out.println("hobbies: " + Arrays.toString(hobbies));
//             System.out.println("country: " + country);
//         
//         InputStream inputStream = null;
//         Part filePart = request.getPart("image");
//         if (filePart != null && filePart.getSize() > 0) { 
//             inputStream = filePart.getInputStream();
//         }
//         
//         String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
//         String dbUser = "postgres";
//         String dbPassword = "Iamthebest9879!";
//         
//         try {
//             Class.forName("org.postgresql.Driver");
//             try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
//                 connection.setAutoCommit(false); 
//                 String sql = "UPDATE jaydave.user_data SET first_name=?, last_name=?, gender=?, email=?, mobile_no=?, languages=?, hobby=?, country=?, image_file=? WHERE user_id=?";
//                 try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                     preparedStatement.setString(1, firstName);
//                     preparedStatement.setString(2, lastName);
//                     preparedStatement.setString(3, gender);
//                     preparedStatement.setString(4, email);
//                     preparedStatement.setString(5, mobile);
////                     preparedStatement.setString(6, password);
////                     preparedStatement.setArray(7, connection.createArrayOf("text", languages));
////                     preparedStatement.setArray(8, connection.createArrayOf("text", hobbies));
//                     preparedStatement.setString(6, languagesAsString);
//                     preparedStatement.setString(7, hobbiesAsString);
//                     preparedStatement.setString(8, country);
//                     if (inputStream != null) {
//                         preparedStatement.setBinaryStream(9, inputStream); 
//                     } else {
//                         preparedStatement.setNull(9, java.sql.Types.BLOB); 
//                     }
//                     preparedStatement.setInt(10, userId);
//                     preparedStatement.executeUpdate();
//                     connection.commit();
//                 } 
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//    }

    private void editUser(HttpServletRequest request) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String mobile = request.getParameter("mobile");
        String[] languages = request.getParameterValues("languages");
        String[] hobbies = request.getParameterValues("hobbies");
        String country = request.getParameter("country");
        // Convert arrays to comma-separated strings
        String languagesAsString = String.join(",", languages);
        String hobbiesAsString = String.join(",", hobbies);
        // Retrieve existing mobile number from the database
        String existingMobile = getMobileByUserId(userId);
        System.out.println("UserId: " + userId);
        System.out.println("FirstName: " + firstName);
        System.out.println("LastName: " + lastName);
        System.out.println("gender: " + gender);
        System.out.println("mobile: " + mobile);
        System.out.println("languages: " + Arrays.toString(languages));
        System.out.println("hobbies: " + Arrays.toString(hobbies));
        System.out.println("country: " + country);

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
                // Check if the mobile number is being updated
                String sql;
                if (inputStream != null) {
                    // Update user details along with the image
                    sql = "UPDATE jaydave.user_data SET first_name=?, last_name=?, gender=?, mobile_no=?, image_file=?, languages=?, hobby=?, country=?, role='user' WHERE user_id=?";
                } else {
                    // Update user details without changing the image
                    sql = "UPDATE jaydave.user_data SET first_name=?, last_name=?, gender=?, mobile_no=?, languages=?, hobby=?, country=?, role='user' WHERE user_id=?";
                }
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, gender);
                    preparedStatement.setString(4, mobile);
                    if (inputStream != null) {
                        // Set image if provided
                        byte[] imageBytes = inputStream.readAllBytes();
                        preparedStatement.setBytes(5, imageBytes);
                    }
                    preparedStatement.setString(inputStream != null ? 6 : 5, languagesAsString);
                    preparedStatement.setString(inputStream != null ? 7 : 6, hobbiesAsString);
                    preparedStatement.setString(inputStream != null ? 8 : 7, country);
                    preparedStatement.setInt(inputStream != null ? 9 : 8, userId);

                    System.out.println("Edited...");
                    preparedStatement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    
    private String getMobileByUserId(int userId) {
        String mobile = null;
        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT mobile_no FROM jaydave.user_data WHERE user_id=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, userId);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            mobile = resultSet.getString("mobile_no");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return mobile;
    }

    private boolean isMobileUnique(String mobile) {
        boolean isUnique = true;
        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT COUNT(*) AS count FROM jaydave.user_data WHERE mobile_no=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, mobile);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            int count = resultSet.getInt("count");
                            isUnique = count == 0;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isUnique;
    }

    
    private void deleteUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));

        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";
        System.out.println("Yoo I delete....");
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "DELETE FROM jaydave.user_data WHERE user_id=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, userId);

                    preparedStatement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        }
    }

    private byte[] getImageDataFromDatabase(int userId) {
        byte[] imageData = null;
        String jdbcUrl = "jdbc:postgresql://localhost:5432/jaydave";
        String dbUser = "postgres";
        String dbPassword = "Iamthebest9879!";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String sql = "SELECT image_file FROM jaydave.user_data WHERE user_id=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, userId);
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
