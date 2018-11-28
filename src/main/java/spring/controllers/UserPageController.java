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
import models.UserPage;

/**
 * Controller for user page
 * @author eoin
 *
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("userClass")
public class UserPageController {
	
	@Autowired
	private ServletContext sc;

	/**
	 * Load the user page
	 * @param u User object for user viewing the page
	 * @param usr optional String of what user's page to view
	 * @return ModelAndView of userPage with model added
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView loadUserPage(@ModelAttribute("userClass") User u, @ModelAttribute("usr") String usr) {
		
		UserPage up = new UserPage(u, usr);
		
		ModelAndView m = new ModelAndView("userPage");
		m.addObject("contextPath", sc.getContextPath());
		m.addObject("userPageClass", up);
		m.addObject("userNotes", up.getNotes());
		return m;
	}
	
	/**
	 * Redirect from the user page to a note
	 * @param u User object for user who requested this page
	 * @param selectedId String database id of the note requested
	 * @return ModelAndView for note page
	 */
	@RequestMapping(value="/redirectNote", method=RequestMethod.GET)
	public ModelAndView redirectNote(@ModelAttribute("userClass") User u, @RequestParam("id") String selectedId) {
		
		ModelAndView mv = new ModelAndView("redirect:/note");
		mv.addObject("noteId", selectedId);
		
		return mv;
	}
	
	/**
	 * Redirect to a new note page
	 * @param u User object for user who created the new note
	 * @return ModelAndView for note page
	 */
	@RequestMapping(value="/newNote", method=RequestMethod.GET)
	public ModelAndView newNote(@ModelAttribute("userClass") User u) {
		return new ModelAndView("redirect:/note");
	}
	
}
