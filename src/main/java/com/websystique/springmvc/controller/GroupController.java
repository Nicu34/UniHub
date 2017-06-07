package com.websystique.springmvc.controller;

import com.websystique.springmvc.dto.SchoolGroupDto;
import com.websystique.springmvc.model.SchoolGroup;
import com.websystique.springmvc.model.University;
import com.websystique.springmvc.service.GroupService;
import com.websystique.springmvc.service.StudyYearService;
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
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudyYearService studyYearService;

    /**
     * Adds a group
     */
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String addGroup(@ModelAttribute SchoolGroupDto schoolGroupDto) {
        SchoolGroup schoolGroup = new SchoolGroup();
        University university = userService.findBySSO(getPrincipal()).getUniversity();
        schoolGroup.setGroupNumber(schoolGroupDto.getGroupName());
        schoolGroup.setStudyYear(studyYearService.findByYearAndUniversity(schoolGroupDto.getStudyYear(), university));
        schoolGroup.setUniversity(university);
        groupService.save(schoolGroup);

        return "redirect:/";
    }


    /**
     * Gets a group by given id as path variable.
     */
    @RequestMapping(value = "/view-group-{id}", method = RequestMethod.GET)
    public String viewGroupDetails(@PathVariable Integer id, ModelMap model) {
        SchoolGroup schoolGroup = groupService.findById(id);

        model.addAttribute("group", schoolGroup);

        return "groupDetails";
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
