package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import models.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(Model model) {
		
		return new ModelAndView("login", "user", new User("default"));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView handleLogin(@ModelAttribute("user") User u, Model model) {
		System.out.println(u.getUsername());
		model.addAttribute("username", u.getUsername());
		ModelAndView mv = new ModelAndView("redirect:/home");
		mv.addObject("user", u);
		
		return new ModelAndView("redirect:/user");
	}
}
