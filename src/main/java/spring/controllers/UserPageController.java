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
import org.springframework.web.servlet.ModelAndView;

import models.Note;
import models.User;
import mongo.DatabaseManager;

@Controller
@RequestMapping("/user")
public class UserPageController {
	
	private DatabaseManager db;
	private String noteCollectionName = "Notes";
	
	@Autowired
	private ServletContext sc;

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView loadUserPage(Model model) {
		return new ModelAndView("userPage", "user", new User("default"));
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView loadUserPage(@ModelAttribute("user") User u, BindingResult result, ModelMap model) {
//		System.out.println("User page loader called with user: " + u.getUsername());
//		model.addAttribute("name", u.getUsername());
		//model.addAttribute("user", u);
		//return new ModelAndView("userPage");
		ModelAndView m = new ModelAndView("userPage");
		m.addObject("userClass", u);
		m.addObject("contextPath", sc.getContextPath());
		m.addObject("userNotes", u.getUserNotes());
		return m;
	}
	
	@RequestMapping(value="/redirectNote", method=RequestMethod.GET)
	public ModelAndView redirectNote(@ModelAttribute("user") User u, 
			@RequestParam("id") String selectedId, BindingResult result, ModelMap model) {
		
		ModelAndView mv = new ModelAndView("redirect:/note");
		mv.addObject("userClass", u);
		mv.addObject("noteId", selectedId);
		
		return mv;
	}
	
	@RequestMapping(value="/newNote", method=RequestMethod.GET)
	public ModelAndView newNote(@ModelAttribute("user") User u) {
		ModelAndView mv = new ModelAndView("redirect:/note");
		mv.addObject("userClass", u);
		
		return mv;
	}
	
}
