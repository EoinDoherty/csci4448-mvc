package spring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import models.Note;
import models.User;
import mongo.DatabaseManager;

@Controller
@RequestMapping("/user")
@SessionAttributes("userClass")
public class UserPageController {
	
	private String noteCollectionName = "Notes";
	private User u;
	
	@Autowired
	private ServletContext sc;

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView loadUserPage(Model model) {
		return new ModelAndView("userPage", "userClass", new User("default"));
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView loadUserPage(@ModelAttribute("userClass") User u, BindingResult result, ModelMap model) {
		ModelAndView m = new ModelAndView("userPage");
		m.addObject("contextPath", sc.getContextPath());
		m.addObject("userNotes", u.getUserNotes());
		return m;
	}
	
	@RequestMapping(value="/redirectNote", method=RequestMethod.GET)
	public ModelAndView redirectNote(@ModelAttribute("userClass") User u, 
			@RequestParam("id") String selectedId, BindingResult result, ModelMap model) {
		
		ModelAndView mv = new ModelAndView("redirect:/note");
		mv.addObject("noteId", selectedId);
		
		return mv;
	}
	
	@RequestMapping(value="/newNote", method=RequestMethod.GET)
	public ModelAndView newNote(@ModelAttribute("userClass") User u) {
		return new ModelAndView("redirect:/note");
	}
	
}
