package com.websystique.springmvc.converter;

import com.websystique.springmvc.dto.CourseDto;
import com.websystique.springmvc.model.Course;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by nicu on 6/5/2017.
 */
public class CourseDtoToCourse implements Converter<CourseDto, Course> {

    /**
     * Converts courseDto object into Course
     * @param source
     * @return Course object
     */
    @Override
    public Course convert(CourseDto source) {
        Course course = new Course();

        course.setSyllabus(source.getSyllabusLink());
        course.setCourseMaterialsLink(source.getCourseMaterialsLink());
        course.setName(source.getCourseName());

        return course;
    }
}
