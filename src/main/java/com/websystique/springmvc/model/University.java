package com.websystique.springmvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nicu on 5/20/2017.
 */
@Entity
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String longName;

    @NotNull
    private String shortName;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StudyYear> studyYears;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<StudyYear> getStudyYears() {
        return studyYears;
    }

    public void setStudyYears(Set<StudyYear> studyYears) {
        this.studyYears = studyYears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        University that = (University) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (longName != null ? !longName.equals(that.longName) : that.longName != null) return false;
        return shortName != null ? shortName.equals(that.shortName) : that.shortName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (longName != null ? longName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        return result;
    }
}
