package spring.controllers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import models.Note;
import models.NoteChange;
import models.User;

@Controller
@SessionAttributes("userClass")
public class NoteController {
	
	@Autowired
	private ServletContext sc;
	
	private Note n;
	
	private void setNoteById(String id, User u) {
		if (n == null || !n.getId().equals(id)) {
			if (id.equals("")) {
				n = new Note(u);
				return;
			}
			n = new Note(u, id);
		}
	}
	
	@RequestMapping(value="/note", method=RequestMethod.GET)
	public ModelAndView setUpNote(@ModelAttribute("noteId") String id, 
			@ModelAttribute("userClass") User u) {
		ModelAndView mv = new ModelAndView();
		
		setNoteById(id, u);
		
		mv.addObject("noteModel", n.getNoteChange());
		mv.addObject("contextPath", sc.getContextPath());
		
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView updateNote(@ModelAttribute("noteModel") NoteChange formNote, @ModelAttribute("userClass") User u) {
		n.overwrite(formNote);
		
		ModelAndView mv = new ModelAndView("redirect:/note");
		mv.addObject("noteModel", n.getNoteChange());
		mv.addObject("noteId", n.getId());
		
		return mv;
	}
	
	@RequestMapping("/deleteNote")
	public ModelAndView deleteNote(@ModelAttribute("noteId") String noteId) {
		ModelAndView mv = new ModelAndView("redirect:/user");
		System.out.println("request to delete: " + noteId);
		
		if (!noteId.equals("")) {
			n.deleteNote();
		}
		
		return mv;
	}
	
	//TODO: Add redirect to user page
}
