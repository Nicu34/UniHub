package com.websystique.springmvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by nicu on 6/5/2017.
 */
@Entity
public class InvitedUser implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(unique=true, nullable=false)
    private String ssoId;

    @NotEmpty
    @Column(nullable=false)
    private String email;

    @NotNull
    @Column
    private ProfileEnum profileEnum;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private University university;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private SchoolGroup schoolGroup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public ProfileEnum getProfileEnum() {
        return profileEnum;
    }

    public void setProfileEnum(ProfileEnum profileEnum) {
        this.profileEnum = profileEnum;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public SchoolGroup getSchoolGroup() {
        return schoolGroup;
    }

    public void setSchoolGroup(SchoolGroup schoolGroup) {
        this.schoolGroup = schoolGroup;
    }
}
