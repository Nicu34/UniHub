package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.Student;
import com.websystique.springmvc.service.StudentService;
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
public class StudentController {

    @Autowired
    private StudentService studentService;


    /**
     * Opens a student details by given id as path variable.
     */
    @RequestMapping(value = "/view-student-{id}", method = RequestMethod.GET)
    public String viewStudentDetails(@PathVariable Integer id, ModelMap model) {
        Student student = studentService.findById(id);

        model.addAttribute("student", student);

        return "studentDetails";
    }
}
