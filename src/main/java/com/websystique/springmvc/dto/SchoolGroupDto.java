package com.websystique.springmvc.dto;

/**
 * Created by nicu on 6/4/2017.
 */
public class SchoolGroupDto {
    private Long groupName;
    private Integer studyYear;

    public Long getGroupName() {
        return groupName;
    }

    public void setGroupName(Long groupName) {
        this.groupName = groupName;
    }

    public Integer getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }
}
