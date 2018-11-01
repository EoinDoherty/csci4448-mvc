package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import models.User;

@Controller
public class TestController {
	@RequestMapping("/home")
	public ModelAndView loadHome(Model model) {
		return new ModelAndView("home", "user", new User("default"));
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView loadHome(@ModelAttribute("user") User u, BindingResult result, ModelMap model) {
		System.out.println("Home loader called");
		model.addAttribute("name", u.getUsername());
		return new ModelAndView("home", "user", u);
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public String handleInput(@ModelAttribute("user") User u, BindingResult result, ModelMap model) {
		model.addAttribute("name", u.getUsername());
		return "home";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	//@RequestMapping("/test")
	public String handleInput(@RequestParam String action, Model m) {
		
		if(action.equals("beep")) {
			System.out.println("***beep***");
		} else if (action.equals("changeName")) {
			m.addAttribute("name", "changed");
		}
		
		return "home";
	}
}
