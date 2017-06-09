package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by nicu on 6/7/2017.
 */
@Controller("/")
public class FooController{

    //returns the ModelAttribute fooListWrapper with the view fooForm
    @RequestMapping(value = "/FOO", method = RequestMethod.GET)
    public String getFooForm(Model model) {
        FooListWrapper fooListWrapper = new FooListWrapper();
        fooListWrapper.add(new Foo("hello"));
        fooListWrapper.add(new Foo("there"));

        //add as many FOO you need

        model.addAttribute("fooListWrapper", fooListWrapper);

        return "fooForm";
    }

    @RequestMapping(value = "/FOO", method = RequestMethod.POST)
    public String postFooList(@ModelAttribute("fooListWrapper")FooListWrapper fooListWrapper, Model model) {

        System.out.println("hello");

        return "fooForm";
    }

}
