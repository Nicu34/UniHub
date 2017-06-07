package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.Course;
import com.websystique.springmvc.model.ProfileEnum;
import com.websystique.springmvc.model.Teacher;
import com.websystique.springmvc.model.University;
import com.websystique.springmvc.service.TeacherService;
import com.websystique.springmvc.service.UniversityService;
import com.websystique.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by nicu on 6/7/2017.
 */
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    /**
     * Opens university details by given id as path variable.
     */
    @RequestMapping(value = "/view-university-{id}", method = RequestMethod.GET)
    public String viewUniversityDetails(@PathVariable Integer id, ModelMap modelMap) {
        University university = universityService.findById(id);
        List<Teacher> teachers = userService.findAllUsers(university).stream().filter(user -> user.getProfileEnum() == ProfileEnum.TEACHER).map(user -> teacherService.findByUser(user)).filter(Objects::nonNull).collect(Collectors.toList());
        List<Course> courses = new ArrayList<>();

        for (Teacher teacher : teachers) {
            if (teacher != null) {
                courses.addAll(teacher.getCourses());
            }
        }

        modelMap.addAttribute("university", university);
        modelMap.addAttribute("courses", new HashSet<>(courses));
        modelMap.addAttribute("teachers", teachers);

        return "universityDetails";
    }
}
