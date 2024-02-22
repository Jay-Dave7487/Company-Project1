package com;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String mobile;
    private String password;
    private String[] languages;
    private String imageFileName;
    private String[] hobbies;
    private String country;
//    private byte[] imageFile; 
 
    private String role;
    
    private String imageFile;

    public String getImageFile() {
        return imageFile;
    }

//    public void setImageFile(byte[] imageFile) {
//        this.imageFile = imageFile;
//    }
    public void setImageFile(byte[] imageData) {
        // Convert byte array to Base64 encoded string
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        this.imageFile = base64Image;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
   
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

 
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

  
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and setter methods for imageFileName
    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] string) {
        this.languages = string;
    }

    // Getter and setter methods for hobbies
    public String[] getHobbies() {
        return hobbies;
    }

    public String getHobbiesAsString() {
        if (hobbies != null) {
            return String.join(", ", hobbies);
        } else {
            return "";
        }
    }
//    public String getLanguagesAsString() {
//        return String.join(",", languages);
//    }
    public String getLanguagesAsString() {
        if (languages != null) {
            return String.join(", ", languages);
        } else {
            return "";
        }
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }
//
    public String getCountry() {
        return country;
    }
    public void setLanguages(List<String> languages) {
        this.languages = languages.toArray(new String[0]);
    }
//    public void setLanguages(String languages) {
//        // Assuming languages is a comma-separated string
//        this.languages = languages.split(",");
//    }
    public void setHobbies(String hobbies) {
        // Assuming hobbies is a comma-separated string
        this.hobbies = hobbies.split(",");
    }
    public void setCountry(String country) {
        this.country = country;
    }
}

