package com.websystique.springmvc.dto;

/**
 * Created by nicu on 5/26/2017.
 */
public class AdminDto {
    private String username;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String universityShortName;
    private String universityLongName;
    private String universityCity;
    private String universityAddress;
    private String universityPhone;
    private Integer universityStudyYears;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUniversityShortName() {
        return universityShortName;
    }

    public void setUniversityShortName(String universityShortName) {
        this.universityShortName = universityShortName;
    }

    public String getUniversityLongName() {
        return universityLongName;
    }

    public void setUniversityLongName(String universityLongName) {
        this.universityLongName = universityLongName;
    }

    public String getUniversityCity() {
        return universityCity;
    }

    public void setUniversityCity(String universityCity) {
        this.universityCity = universityCity;
    }

    public String getUniversityAddress() {
        return universityAddress;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }

    public String getUniversityPhone() {
        return universityPhone;
    }

    public void setUniversityPhone(String universityPhone) {
        this.universityPhone = universityPhone;
    }

    public Integer getUniversityStudyYears() {
        return universityStudyYears;
    }

    public void setUniversityStudyYears(Integer universityStudyYears) {
        this.universityStudyYears = universityStudyYears;
    }
}
