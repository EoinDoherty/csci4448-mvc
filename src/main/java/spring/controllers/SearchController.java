package spring.controllers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import models.Search;
import models.User;

/**
 * Controller for search results page
 * @author eoin
 *
 */
@Controller
@SessionAttributes("userClass")
public class SearchController {
	
	Search s;
	
	@Autowired
	private ServletContext sc;

	/**
	 * Run the search
	 * @param u User object for user who requested the search
	 * @param searchQuery String search query
	 * @return ModelAndView for search page with requested data added
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ModelAndView handleSearch(@ModelAttribute("userClass") User u, @ModelAttribute("query") String searchQuery) {
		s = new Search(searchQuery, u);
		
		ModelAndView mv = new ModelAndView("/search");
		mv.addObject("contextPath", sc.getContextPath());
		mv.addObject("searchClass", s);
		return mv;
	}
}
