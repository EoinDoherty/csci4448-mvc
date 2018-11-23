package spring.controllers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import models.Search;
import models.User;

@Controller
@SessionAttributes("userClass")
public class SearchController {
	
	Search s;
	
	@Autowired
	private ServletContext sc;

	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ModelAndView handleSearch(@ModelAttribute("userClass") User u, @ModelAttribute("query") String searchQuery) {
		s = new Search(searchQuery, u);
		
		ModelAndView mv = new ModelAndView("/search");
		mv.addObject("contextPath", sc.getContextPath());
		mv.addObject("searchClass", s);
		return mv;
	}
}
