package spring.controllers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import models.Note;
import models.User;

@Controller
public class NoteController {
	
	@Autowired
	private ServletContext sc;
	private Note n;
	private User u;
	
	private void setNoteById(String id) {
		if (n == null || !n.getId().equals(id)) {
			if (id.equals("")) {
				n = new Note();
				return;
			}
			n = new Note(id);
		}
	}
	
	@RequestMapping(value="/note", method=RequestMethod.GET)
	public ModelAndView setUpNote(@ModelAttribute("noteId") String id, 
			@ModelAttribute("userClass") User u) {
		ModelAndView mv = new ModelAndView();
		
		setNoteById(id);
		this.u = u;
		
		mv.addObject("noteModel", n);
		mv.addObject("user", u);
		mv.addObject("contextPath", sc.getContextPath());
		
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView updateNote(@ModelAttribute("noteModel") Note formNote) {
		//System.out.println(formNote.getTitle() + formNote.getText());
		n.setText(formNote.getText());
		n.setTitle(formNote.getTitle());
		n.saveChanges();
		
		ModelAndView mv = new ModelAndView("redirect:/note");
		mv.addObject("noteModel", n);
		mv.addObject("noteId", n.getId());
		
		return mv;
	}
	
	//TODO: Add redirect to user page
}
