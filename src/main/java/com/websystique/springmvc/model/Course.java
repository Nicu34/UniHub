package com.websystique.springmvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by nicu on 5/24/2017.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String syllabus;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Teacher teacher;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private StudyYear studyYear;

    @NotNull
    private String courseMaterialsLink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getCourseMaterialsLink() {
        return courseMaterialsLink;
    }

    public void setCourseMaterialsLink(String courseMaterialsLink) {
        this.courseMaterialsLink = courseMaterialsLink;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(StudyYear studyYear) {
        this.studyYear = studyYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return id != null ? id.equals(course.id) : course.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
