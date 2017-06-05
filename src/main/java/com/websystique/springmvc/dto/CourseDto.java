package com.websystique.springmvc.dto;

/**
 * Created by nicu on 6/5/2017.
 */
public class CourseDto {
    private String courseName;
    private String syllabusLink;
    private String courseMaterialsLink;
    private Integer studyYear;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSyllabusLink() {
        return syllabusLink;
    }

    public void setSyllabusLink(String syllabusLink) {
        this.syllabusLink = syllabusLink;
    }

    public String getCourseMaterialsLink() {
        return courseMaterialsLink;
    }

    public void setCourseMaterialsLink(String courseMaterialsLink) {
        this.courseMaterialsLink = courseMaterialsLink;
    }

    public Integer getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }
}
