package com.websystique.springmvc.dto;

import com.websystique.springmvc.model.ProfileEnum;

/**
 * Created by nicu on 6/5/2017.
 */
public class NewUserDto {
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String photoLink;
    private String ssoId;
    private String email;
    private String shortName;
    private ProfileEnum profileEnum;
    private Long groupNumber;

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public ProfileEnum getProfileEnum() {
        return profileEnum;
    }

    public void setProfileEnum(ProfileEnum profileEnum) {
        this.profileEnum = profileEnum;
    }

    public Long getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Long groupNumber) {
        this.groupNumber = groupNumber;
    }
}
