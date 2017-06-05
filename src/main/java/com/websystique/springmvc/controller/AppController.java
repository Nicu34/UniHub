package com.websystique.springmvc.controller;

import com.websystique.springmvc.converter.AdminDtoToUniversity;
import com.websystique.springmvc.converter.AdminDtoToUser;
import com.websystique.springmvc.converter.CourseDtoToCourse;
import com.websystique.springmvc.converter.NewUserDtoToUser;
import com.websystique.springmvc.dto.*;
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
import java.util.List;
import java.util.Locale;
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

	@Autowired
	TeacherService teacherService;

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listUsers(ModelMap model) throws MessagingException {
		String userName = getPrincipal();
		University university = userService.findBySSO(userName).getUniversity();
		List<User> users = userService.findAllUsers(university);
		User userDetails = userService.findBySSO(userName);

		model.addAttribute("userDetails", userDetails);
		model.addAttribute("loggedinuser", userName);
		model.addAttribute("studyYears", studyYearService.findAll(university));

		if (userDetails.getProfileEnum() == ProfileEnum.ADMIN) {
			model.addAttribute("adminUsers", users.stream().filter(user -> user.getProfileEnum() == ProfileEnum.ADMIN).collect(Collectors.toSet()));
			model.addAttribute("studentUsers", users.stream().filter(user -> user.getProfileEnum() == ProfileEnum.STUDENT).collect(Collectors.toSet()));
			model.addAttribute("teacherUsers", users.stream().filter(user -> user.getProfileEnum() == ProfileEnum.TEACHER).collect(Collectors.toSet()));
			model.addAttribute("emailDto", new EmailDto());
			model.addAttribute("groupsList", groupService.findAll(university));
			model.addAttribute("schoolGroupDto", new SchoolGroupDto());

			return "admin";
		}
		else {
			Teacher teacher = teacherService.findByUser(userDetails);

		    model.addAttribute("courseDto", new CourseDto());
		    model.addAttribute("courses", teacher.getCourses());
			model.addAttribute("schedule", teacher.getScheduleLink());
			model.addAttribute("materials", teacher.getFilesLink());

			return "teacher";
		}
	}

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

	@RequestMapping(value = "/inviteAdmins", method = RequestMethod.POST)
	public String inviteAdmins(@ModelAttribute EmailDto emailDto) throws MessagingException {
		registerTemporaryAccountAndSendEmail(emailDto.getAdminEmails(), getPrincipal(), ProfileEnum.ADMIN, emailDto.getGroupNumber());

		return "redirect:/";
	}

	@RequestMapping(value = "/inviteTeachers", method = RequestMethod.POST)
	public String inviteTeachers(@ModelAttribute EmailDto emailDto) throws MessagingException {
		registerTemporaryAccountAndSendEmail(emailDto.getTeacherEmails(), getPrincipal(), ProfileEnum.TEACHER, emailDto.getGroupNumber());

		return "redirect:/";
	}

	@RequestMapping(value = "/inviteStudents", method = RequestMethod.POST)
	public String inviteStudents(@ModelAttribute EmailDto emailDto) throws MessagingException {
		registerTemporaryAccountAndSendEmail(emailDto.getStudentEmails(), getPrincipal(), ProfileEnum.STUDENT, emailDto.getGroupNumber());

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
			return "redirect:/";
		}

		User user = new AdminDtoToUser().convert(admin);
		University university = new AdminDtoToUniversity().convert(admin);

		registerNewAdminWithNewUniversity(user, university, admin.getUniversityStudyYears());

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());

		return "registrationsuccess";
	}

	@Transactional
	private void registerTemporaryAccountAndSendEmail(String invitedEmails, String loggedInUsername, ProfileEnum role, Long groupNumber) throws MessagingException {
	    invitedUserService.saveMultipleAccounts(invitedEmails, role, userService.findBySSO(loggedInUsername).getUniversity(), groupService.findByGroupNumber(groupNumber));
	    emailService.sendEmailInvitation(invitedEmails, loggedInUsername, role);
	}

	@Transactional
	private void registerNewAdminWithNewUniversity(User user, University university, Integer studyYears) {
		user.setProfileEnum(ProfileEnum.ADMIN);
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

	@RequestMapping(value = "/create-account/{account}", method = RequestMethod.GET)
	public String initializeCreateAdminPage(@PathVariable String account, ModelMap modelMap) {
		InvitedUser invitedUser = invitedUserService.findBySsoId(account);

		modelMap.put("userDetails", invitedUser);
		modelMap.put("newUserDto", new NewUserDto());

		return "createAccount";
	}

	@RequestMapping(value = "/create-account", method = RequestMethod.POST)
	public String createNewUserFromInvitation(@ModelAttribute NewUserDto newUserDto) {
		User user = new NewUserDtoToUser().convert(newUserDto);
		user.setUniversity(universityService.findByShortName(newUserDto.getShortName()));
		registerInvitedUser(user, newUserDto.getGroupNumber());

		return "redirect:/";
	}

	@Transactional
	private void registerInvitedUser(User user, Long groupNumber) {
		invitedUserService.deleteByEmail(user.getEmail());
		userService.saveUser(user);
		if (user.getProfileEnum() == ProfileEnum.STUDENT) {
			registerInvitedStudent(user, groupService.findByGroupNumber(groupNumber));
		}
		else if (user.getProfileEnum() == ProfileEnum.TEACHER) {
			registerInvitedTeacher(user);
		}
	}

	private void registerInvitedTeacher(User user) {
		Teacher teacher = new Teacher();

		teacher.setUser(user);
		teacherService.save(teacher);
	}

	private void registerInvitedStudent(User user, SchoolGroup group) {
		Student student = new Student();

		student.setUser(user);
		student.setSchoolGroup(group);
		studentService.save(student);
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