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

@Controller
//@RequestMapping("/login")
@SessionAttributes("userClass")
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model) {
		System.out.println("GET");
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("userClass", null);
		return mv;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ModelAttribute("userClass")
	public ModelAndView handleLogin(@ModelAttribute("u") String username, @ModelAttribute("p") String password) {
		
		Login l = new Login(username, password);
		
		if (l.isValid()) {
			User u = new User(username);
			ModelAndView mv = new ModelAndView("redirect:/processUser");
			mv.addObject("userClass", u);
			
			return mv;
		}
		
		return new ModelAndView("login");
	}
	
	// Redirecting from /login and /signup led to username and password being in the url
	// Plus this is a common point between login and signup which could be useful in the future
	@RequestMapping(value="/processUser")
	public ModelAndView processUser(@ModelAttribute("userClass") User u) {
		return new ModelAndView("redirect:/user");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ModelAttribute("userClass")
	public ModelAndView handleSignup(@ModelAttribute("u") String username, @ModelAttribute("p") String password) {
		
		Signup s = new Signup(username, password);
		
		if (s.isValid()) {
			s.createAccount();
			User u = new User(username);
			ModelAndView mv = new ModelAndView("redirect:/processUser");
			mv.addObject("userClass", u);
			
			return mv;
		}
		
		return new ModelAndView("login");
	}
}
