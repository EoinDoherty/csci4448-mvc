package spring.controllers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import models.Login;
import models.Signup;
import models.User;

/**
 * Controller for login page
 * @author eoin
 *
 */
@Controller
//@RequestMapping("/login")
@SessionAttributes("userClass")
public class LoginController {
	
	/**
	 * Generate a temporary user class when login page is accessed
	 * @param model Model for the page
	 * @return ModelAndView directing to login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model) {
		System.out.println("GET login");
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("userClass", null);
		return mv;
	}
	
	/**
	 * Log a user in
	 * If the user's credentials are correct, redirect to processUser
	 * @param username String username from login form submission
	 * @param password String password from login form submission
	 * @return ModelAndView directing to the right place
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView handleLogin(@ModelAttribute("u") String username, @ModelAttribute("p") String password) {
		
		Login l = new Login(username, password);
		
		if (l.isValid()) {
			System.out.println("redirecting");
			ModelAndView mv = new ModelAndView("redirect:/processUser");
			mv.addObject("usr", username);
			
			return mv;
		}
		
		return new ModelAndView("login");
	}
	
	/**
	 * Common endpoint between login and signup
	 * Takes username and generates a User object
	 * @param username String username
	 * @return ModelAndView to user page
	 */
	@RequestMapping(value="/processUser")
	@ModelAttribute("userClass")
	public ModelAndView processUser(@ModelAttribute("usr") String username) {
		System.out.println("user: " + username);
		User u = new User(username);
		ModelAndView mv = new ModelAndView("redirect:/user");
		mv.addObject("userClass", u);
		
		return mv;
	}
	
	/**
	 * Sign a user up
	 * If the username has not been used before, add it and the password to the database
	 * Adding plaintext passwords is bad but this is just a prototype
	 * @param username String username from the signup form submission
	 * @param password String password from the signup form submission
	 * @return ModelAndView redirecting to processUser or login
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView handleSignup(@ModelAttribute("u") String username, @ModelAttribute("p") String password) {
		
		Signup s = new Signup(username, password);
		
		if (s.isValid()) {
			s.createAccount();
			
			ModelAndView mv = new ModelAndView("redirect:/processUser");
			mv.addObject("usr", username);
			
			return mv;
		}
		
		return new ModelAndView("login");
	}
}
