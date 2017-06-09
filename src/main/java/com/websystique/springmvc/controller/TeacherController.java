package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.Teacher;
import com.websystique.springmvc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by nicu on 6/7/2017.
 */
@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * Opens a teacher details by given id as path variable.
     */
    @RequestMapping(value = "/view-teacher-{id}", method = RequestMethod.GET)
    public String viewTeacherDetails(@PathVariable Integer id, ModelMap modelMap) {
        Teacher teacher = teacherService.findById(id);

        modelMap.addAttribute("teacher", teacher);

        return "teacherDetails";
    }

}
