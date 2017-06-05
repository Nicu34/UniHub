package com.websystique.springmvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by nicu on 5/20/2017.
 */
@Entity
public class StudyYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer year;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private University university;

    public StudyYear() {
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudyYear studyYear = (StudyYear) o;

        if (id != null ? !id.equals(studyYear.id) : studyYear.id != null) return false;
        if (year != null ? !year.equals(studyYear.year) : studyYear.year != null) return false;
        return university != null ? university.equals(studyYear.university) : studyYear.university == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (university != null ? university.hashCode() : 0);
        return result;
    }
}
