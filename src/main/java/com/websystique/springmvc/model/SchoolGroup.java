package com.websystique.springmvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicu on 5/24/2017.
 */
@Entity
public class SchoolGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Long groupNumber;

    @ManyToOne(cascade = CascadeType.MERGE)
    private StudyYear studyYear;

    @OneToMany(mappedBy = "schoolGroup")
    private List<Student> students = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private University university;

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Long groupNumber) {
        this.groupNumber = groupNumber;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(StudyYear studyYear) {
        this.studyYear = studyYear;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
