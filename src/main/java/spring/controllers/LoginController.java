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
import org.springframework.web.servlet.ModelAndView;

import models.User;

@Controller
@RequestMapping("/login")
@SessionAttributes("userClass")
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(Model model) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("userClass", new User("default"));
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView handleLogin(@ModelAttribute("userClass") User u, Model model) {
		System.out.println(u.getUsername());
		ModelAndView mv = new ModelAndView("redirect:/home");
		mv.addObject("userClass", u);
		
		return new ModelAndView("redirect:/user");
	}
}
