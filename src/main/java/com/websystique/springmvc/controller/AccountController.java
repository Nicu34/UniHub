//package com.websystique.springmvc.controller;
//
//import com.websystique.springmvc.model.User;
//import com.websystique.springmvc.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.security.authentication.AuthenticationTrustResolver;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.mail.MessagingException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Locale;
//
//
//@Controller
//@RequestMapping("/")
//public class AccountController {
//
//	@Autowired
//	UserService userService;
//
//	@Autowired
//	MessageSource messageSource;
//
//	@Autowired
//	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
//
//	@Autowired
//	AuthenticationTrustResolver authenticationTrustResolver;
//
//	/**
//	 * This method will set the model values and will return the page depending on user profile type
//	 * @param model
//	 * @return the page depending on user profile type
//	 * @throws MessagingException in case that the email couldn't be sent
//	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String listUsers(ModelMap model) throws MessagingException {
//		String userName = getPrincipal();
//		User userDetails = userService.findBySSO(userName);
//
//		model.addAttribute("userDetails", userDetails);
//		model.addAttribute("loggedinuser", userName);
//
//		return "redirect:/login";
//	}
//
//
//	/**
//	 * This method will provide the medium to add a new user.
//	 */
//	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
//	public String newUser(ModelMap model) {
//		model.addAttribute("user", new User());
//		model.addAttribute("loggedinuser", getPrincipal());
//
//		return "registration";
//	}
//
//	/**
//	 * Creates a new username, after the user complette the registration invitation sent in a mail.
//	 */
//	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
//	public String saveUser(User user, BindingResult result,
//			ModelMap model) {
//
//		if(!userService.isUserSSOUnique(100, user.getSsoId())){
//			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
//			result.addError(ssoError);
//			return "redirect:/";
//		}
//
//		registerUser(user);
//
//		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
//		model.addAttribute("loggedinuser", getPrincipal());
//
//		return "registrationsuccess";
//	}
//
//	/**
//	 * This method handles Access-Denied redirect.
//	 */
//	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
//	public String accessDeniedPage(ModelMap model) {
//		model.addAttribute("loggedinuser", getPrincipal());
//		return "accessDenied";
//	}
//
//	/**
//	 * This method handles login GET requests.
//	 * If users is already logged-in and tries to goto login page again, will be redirected to home page.
//	 */
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String loginPage(ModelMap model) {
//		model.addAttribute("user", new User());
//		if (isCurrentAuthenticationAnonymous()) {
//			return "login";
//	    } else {
//	    	return "redirect:/quiz-setup";
//	    }
//	}
//
//	/**
//	 * This method handles logout requests.
//	 */
//	@RequestMapping(value="/logout", method = RequestMethod.GET)
//	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null){
//			//new SecurityContextLogoutHandler().logout(request, response, auth);
//			persistentTokenBasedRememberMeServices.logout(request, response, auth);
//			SecurityContextHolder.getContext().setAuthentication(null);
//		}
//		return "redirect:/login?logout";
//	}
//
//	/**
//	 * This method returns the principal[user-name] of logged-in user.
//	 */
//	private String getPrincipal(){
//		String userName = null;
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		if (principal instanceof UserDetails) {
//			userName = ((UserDetails)principal).getUsername();
//		} else {
//			userName = principal.toString();
//		}
//		return userName;
//	}
//
//	/**
//	 * This method returns true if users is already authenticated [logged-in], else false.
//	 */
//	private boolean isCurrentAuthenticationAnonymous() {
//	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    return authenticationTrustResolver.isAnonymous(authentication);
//	}
//
//
//	/**
//	 * Registers a temporary admin account and sends the email.
//	 */
//	@Transactional
//	private void registerUser(User user) {
//
//		userService.saveUser(user);
//	}
//}