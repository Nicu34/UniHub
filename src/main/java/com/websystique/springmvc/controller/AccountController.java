package com.websystique.springmvc.controller;

import com.websystique.springmvc.converter.AdminDtoToUniversity;
import com.websystique.springmvc.converter.AdminDtoToUser;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class AccountController {

	@Autowired
	UserService userService;

	@Autowired
	UniversityService universityService;

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

	/**
	 * This method will set the model values and will return the page depending on user profile type
	 * @param model
	 * @return the page depending on user profile type
	 * @throws MessagingException in case that the email couldn't be sent
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
		else if (userDetails.getProfileEnum() == ProfileEnum.STUDENT) {
		    Student student = studentService.findByUser(userDetails);

			model.addAttribute("student", student);
			model.addAttribute("courses", student.getSchoolGroup().getStudyYear().getCourseList());

			return "student";
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

	/**
	 * Creates a temporary account and send an email to invited users for admin role.
	 * @throws MessagingException in case that the email couldn't be sent
	 */
	@RequestMapping(value = "/inviteAdmins", method = RequestMethod.POST)
	public String inviteAdmins(@ModelAttribute EmailDto emailDto) throws MessagingException {
		registerTemporaryAccountAndSendEmail(emailDto.getAdminEmails(), getPrincipal(), ProfileEnum.ADMIN, emailDto.getGroupNumber());

		return "redirect:/";
	}

	/**
	 * Creates a temporary teacher account and sends and email to invited users for teacher role.
	 * @param emailDto
	 * @throws MessagingException in case that the email couldn't be send
	 */
	@RequestMapping(value = "/inviteTeachers", method = RequestMethod.POST)
	public String inviteTeachers(@ModelAttribute EmailDto emailDto) throws MessagingException {
		registerTemporaryAccountAndSendEmail(emailDto.getTeacherEmails(), getPrincipal(), ProfileEnum.TEACHER, emailDto.getGroupNumber());

		return "redirect:/";
	}

	/**
	 * Creates a temporary student account and sends an email to invited users for student role.
	 * @param emailDto
	 * @return
	 * @throws MessagingException in case that the email couldn't be sent
	 */
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
	 * Creates a new username, after the user complette the registration invitation sent in a mail.
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

	/**
	 * Gets details about an administrator by id.
	 */
	@RequestMapping(value = "/view-admin-{id}", method = RequestMethod.GET)
	public String viewAdminDetails(@PathVariable Integer id, ModelMap modelMap) {
		User user = userService.findById(id);

		modelMap.addAttribute("userDetails", user);

		return "adminDetails";
	}

	/**
	 * Provides data for a user which enters the invitation link;
	 */
	@RequestMapping(value = "/create-account/{account}", method = RequestMethod.GET)
	public String initializeCreateAdminPage(@PathVariable String account, ModelMap modelMap) {
		InvitedUser invitedUser = invitedUserService.findBySsoId(account);

		modelMap.put("userDetails", invitedUser);
		modelMap.put("newUserDto", new NewUserDto());

		return "createAccount";
	}


	/**
	 * Deletes the temporary account and creates a new account.
	 */
	@RequestMapping(value = "/create-account", method = RequestMethod.POST)
	public String createNewUserFromInvitation(@ModelAttribute NewUserDto newUserDto) {
		User user = new NewUserDtoToUser().convert(newUserDto);
		user.setUniversity(universityService.findByShortName(newUserDto.getShortName()));
		registerInvitedUser(user, newUserDto.getGroupNumber(), newUserDto.getScheduleLink(), newUserDto.getFilesLink());

		return "redirect:/";
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
	 * If users is already logged-in and tries to goto login page again, will be redirected to home page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		model.addAttribute("admin", new AdminDto());
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
	    } else {
	    	return "redirect:/";
	    }
	}

	/**
	 * This method handles logout requests.
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


	/**
	 * Registers a new user into application.
	 */
	@Transactional
	private void registerInvitedUser(User user, Long groupNumber, String scheduleLink, String filesLink) {
		invitedUserService.deleteByEmail(user.getEmail());
		userService.saveUser(user);
		if (user.getProfileEnum() == ProfileEnum.STUDENT) {
			registerInvitedStudent(user, groupService.findByGroupNumber(groupNumber));
		}
		else if (user.getProfileEnum() == ProfileEnum.TEACHER) {
			registerInvitedTeacher(user, scheduleLink, filesLink);
		}
	}

	/**
	 * Registers a teacher.
	 */
	private void registerInvitedTeacher(User user, String scheduleLink, String filesLink) {
		Teacher teacher = new Teacher();

		teacher.setUser(user);
		teacher.setFilesLink(filesLink);
		teacher.setScheduleLink(scheduleLink);
		teacherService.save(teacher);
	}

	/**
	 * Registers a student among with his group.
	 */
	private void registerInvitedStudent(User user, SchoolGroup group) {
		Student student = new Student();

		student.setUser(user);
		student.setSchoolGroup(group);
		studentService.save(student);
	}

	/**
	 * Registers a temporary account and sends the email.
	 */
	@Transactional
	private void registerTemporaryAccountAndSendEmail(String invitedEmails, String loggedInUsername, ProfileEnum role, Long groupNumber) throws MessagingException {
		invitedUserService.saveMultipleAccounts(invitedEmails, role, userService.findBySSO(loggedInUsername).getUniversity(), groupService.findByGroupNumber(groupNumber));
		emailService.sendEmailInvitation(invitedEmails, loggedInUsername, role);
	}

	/**
	 * Registers a temporary admin account and sends the email.
	 */
	@Transactional
	private void registerNewAdminWithNewUniversity(User user, University university, Integer studyYears) {
		user.setProfileEnum(ProfileEnum.ADMIN);
		user.setUniversity(university);

		universityService.save(university, studyYears);
		userService.saveUser(user);
	}
}