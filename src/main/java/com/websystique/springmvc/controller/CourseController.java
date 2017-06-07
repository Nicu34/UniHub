package com.websystique.springmvc.controller;

import com.websystique.springmvc.converter.CourseDtoToCourse;
import com.websystique.springmvc.dto.CourseDto;
import com.websystique.springmvc.model.*;
import com.websystique.springmvc.service.CourseService;
import com.websystique.springmvc.service.StudyYearService;
import com.websystique.springmvc.service.TeacherService;
import com.websystique.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by nicu on 6/7/2017.
 */
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudyYearService studyYearService;

    @Autowired
    private TeacherService teacherService;

    /**
     * Adds a new course.
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute CourseDto courseDto) {
        Course course = new CourseDtoToCourse().convert(courseDto);
        User user = userService.findBySSO(getPrincipal());
        University university = user.getUniversity();
        StudyYear studyYear = studyYearService.findByYearAndUniversity(courseDto.getStudyYear(), university);
        Teacher teacher = teacherService.findByUser(user);

        course.setTeacher(teacher);
        course.setStudyYear(studyYear);
        courseService.save(course);

        return "redirect:/";
    }


    /**
     * Opens a course details by given id as path variable.
     */
    @RequestMapping(value = "/view-course-{id}", method = RequestMethod.GET)
    public String viewCourseDetails(@PathVariable Integer id, ModelMap modelMap) {
        modelMap.addAttribute("course", courseService.findById(id));

        return "courseDetails";
    }


    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
