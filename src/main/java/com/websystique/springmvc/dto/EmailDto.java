package com.websystique.springmvc.dto;

/**
 * Created by nicu on 6/4/2017.
 */
public class EmailDto {
    private String adminEmails;

    private String studentEmails;

    private String teacherEmails;

    public String getAdminEmails() {
        return adminEmails;
    }

    public void setAdminEmails(String adminEmails) {
        this.adminEmails = adminEmails;
    }

    public String getStudentEmails() {
        return studentEmails;
    }

    public void setStudentEmails(String studentEmails) {
        this.studentEmails = studentEmails;
    }

    public String getTeacherEmails() {
        return teacherEmails;
    }

    public void setTeacherEmails(String teacherEmails) {
        this.teacherEmails = teacherEmails;
    }
}
