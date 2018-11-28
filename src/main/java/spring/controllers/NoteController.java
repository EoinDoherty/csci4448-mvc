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

/**
 * Controller for note page
 * @author eoin
 *
 */
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
	
	/**
	 * Set up note page
	 * @param id String database id of the note
	 * @param u User object of the user accessing this note
	 * @return ModelAndView of this page with the proper data added
	 */
	@RequestMapping(value="/note", method=RequestMethod.GET)
	public ModelAndView setUpNote(@ModelAttribute("noteId") String id, 
			@ModelAttribute("userClass") User u) {
		ModelAndView mv = new ModelAndView();
		
		setNoteById(id, u);
		
		mv.addObject("noteModel", n.getNoteChange());
		mv.addObject("contextPath", sc.getContextPath());
		
		return mv;
	}
	
	/**
	 * Write changes to the note to the database
	 * @param formNote NoteChange object to store the edits made to the note
	 * @param u User class of user viewing the note
	 * @return ModelAndView back to the note page
	 */
	@RequestMapping("/update")
	public ModelAndView updateNote(@ModelAttribute("noteModel") NoteChange formNote, @ModelAttribute("userClass") User u) {
		n.overwrite(formNote);
		
		ModelAndView mv = new ModelAndView("redirect:/note");
		mv.addObject("noteModel", n.getNoteChange());
		mv.addObject("noteId", n.getId());
		
		return mv;
	}
	
	/**
	 * Delete this note from the database
	 * @param noteId String database id of the note
	 * @return ModelAndView redirecting to the user page
	 */
	@RequestMapping("/deleteNote")
	public ModelAndView deleteNote(@ModelAttribute("noteId") String noteId) {
		ModelAndView mv = new ModelAndView("redirect:/user");
		
		if (!noteId.equals("")) {
			n.deleteNote();
		}
		
		return mv;
	}
}
