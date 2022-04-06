package vehicalDemo.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vehicalDemo.data.User;
import vehicalDemo.services.LoginAuthenticator;

/*
 * handles the routing to login page as
 * well as authentication.
 */
@Controller
public class Login {

	@Autowired
	HttpSession session;

	@Autowired
	LoginAuthenticator la;

	private final Logger logger = LoggerFactory.getLogger(getClass());


	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam String username, @RequestParam String password, ModelMap model)
			throws ServletException, IOException {

		// login validation
		Optional<User> user = la.authenticate(username, password);

		if (user.isPresent()) {
			logger.info("authentical successful");
			session.setAttribute("user", user.get());
			model.addAttribute("message", "Authentication Successful");
			return new ModelAndView("redirect:/dashboard?section=serviceRecords", model);

		} else {
			logger.info("authentication failed");
			model.addAttribute("message", "Authentication Failed");
			return new ModelAndView("login", model);
		}

	}
}
