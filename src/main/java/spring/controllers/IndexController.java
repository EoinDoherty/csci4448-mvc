package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for welcome/index page
 * @author eoin
 *
 */
@Controller
public class IndexController {
	/**
	 * Handle redirect to login and signup page
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView redirectToLogin() {
		return new ModelAndView("redirect:/login");
	}
}
