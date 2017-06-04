package com.websystique.springmvc.controller;

import com.websystique.springmvc.converter.AdminDtoToUniversity;
import com.websystique.springmvc.converter.AdminDtoToUser;
import com.websystique.springmvc.dto.AdminDto;
import com.websystique.springmvc.dto.EmailDto;
import com.websystique.springmvc.dto.SchoolGroupDto;
import com.websystique.springmvc.model.*;
import com.websystique.springmvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	UniversityService universityService;

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	EmailService emailService;

	@Autowired
	GroupService groupService;

	@Autowired
	StudyYearService studyYearService;

	@Autowired
	InvitedUserService invitedUserService;

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) throws MessagingException {
		String userName = getPrincipal();
		University university = userService.findBySSO(userName).getUniversity();
		List<User> users = userService.findAllUsers(university);
		model.addAttribute("adminUsers", users.stream().filter(user -> user.getProfiles().contains(new Profile(ProfileEnum.ADMIN.getUserProfileType()))).collect(Collectors.toSet()));
		model.addAttribute("studentUsers", users.stream().filter(user -> user.getProfiles().contains(new Profile(ProfileEnum.STUDENT.getUserProfileType()))).collect(Collectors.toSet()));
		model.addAttribute("teacherUsers", users.stream().filter(user -> user.getProfiles().contains(new Profile(ProfileEnum.TEACHER.getUserProfileType()))).collect(Collectors.toSet()));
		model.addAttribute("loggedinuser", userName);
		model.addAttribute("userDetails", userService.findBySSO(userName));
		model.addAttribute("emailDto", new EmailDto());
		model.addAttribute("groupsList", groupService.findAll(university));
		model.addAttribute("studyYears", studyYearService.findAll(university));
		model.addAttribute("schoolGroupDto", new SchoolGroupDto());

		return "admin";
	}

	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public String addGroup(@ModelAttribute SchoolGroupDto schoolGroupDto) {
		SchoolGroup schoolGroup = new SchoolGroup();

		schoolGroup.setGroupNumber(schoolGroupDto.getGroupName());
		schoolGroup.setStudyYear(studyYearService.findByYear(schoolGroupDto.getStudyYear()));
		schoolGroup.setUniversity(userService.findBySSO(getPrincipal()).getUniversity());
		groupService.save(schoolGroup);

		return "redirect:/";
	}

	@RequestMapping(value = "/inviteAdmins", method = RequestMethod.POST)
	public String inviteAdmins(@ModelAttribute EmailDto emailDto) throws MessagingException {
		registerTemporaryAccountAndSendEmail(emailDto.getAdminEmails(), getPrincipal(), ProfileEnum.ADMIN);

		return "redirect:/";
	}

	@RequestMapping(value = "/inviteTeachers", method = RequestMethod.POST)
	public String inviteTeachers(@ModelAttribute EmailDto emailDto) throws MessagingException {
		registerTemporaryAccountAndSendEmail(emailDto.getTeacherEmails(), getPrincipal(), ProfileEnum.TEACHER);

		return "redirect:/";
	}

	@RequestMapping(value = "/inviteStudents", method = RequestMethod.POST)
	public String inviteStudents(@ModelAttribute EmailDto emailDto) throws MessagingException {
		registerTemporaryAccountAndSendEmail(emailDto.getStudentEmails(), getPrincipal(), ProfileEnum.STUDENT);

		return "redirect:/";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		model.addAttribute("admin", new AdminDto());
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());

		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(AdminDto admin, BindingResult result,
			ModelMap model) {

		if(!userService.isUserSSOUnique(100, admin.getUsername())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{admin.getUsername()}, Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}

		User user = new AdminDtoToUser().convert(admin);
		University university = new AdminDtoToUniversity().convert(admin);

		registerNewAdmin(user, university, admin.getUniversityStudyYears());

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());

		return "registrationsuccess";
	}

	@Transactional
	private void registerTemporaryAccountAndSendEmail(String invitedEmails, String loggedInUsername, ProfileEnum role) throws MessagingException {
	    invitedUserService.saveMultipleAccounts(invitedEmails, role, userService.findBySSO(loggedInUsername).getUniversity());
	    emailService.sendEmailInvitation(invitedEmails, loggedInUsername, role);
	}

	@Transactional
	private void registerNewAdmin(User user, University university, Integer studyYears) {
		Set<Profile> profiles = new HashSet<>();
		Profile profile = new Profile();

		profile.setType(ProfileEnum.ADMIN.getUserProfileType());
		profiles.add(profile);
		user.setProfiles(profiles);
		user.setUniversity(university);

		universityService.save(university, studyYears);
		userService.saveUser(user);
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}

	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}
	

	/**
	 * This method will provide Profile list to views
	 */
	@ModelAttribute("roles")
	public List<Profile> initializeProfiles() {
		return userProfileService.findAll();
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		model.addAttribute("admin", new AdminDto());
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
	    } else {
	    	return "redirect:/list";  
	    }
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
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
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}


}