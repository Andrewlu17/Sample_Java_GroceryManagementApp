package com.grocerymanagement.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.grocerymanagement.dto.UserDTO;
import com.grocerymanagement.exceptions.RequestManagerException;
import com.grocerymanagement.service.UserService;

@Controller
@SessionAttributes("loggedUser")
public class UserController {
	public static final Logger log = Logger.getLogger(UserController.class);
	@Autowired
	ModelAndView modelAndView;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView log(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserDTO user) {
		UserDTO loggedUser = (UserDTO) httpSession.getAttribute("loggedUser");
		if (loggedUser != null) {

			modelAndView.setViewName("Home");
			modelAndView.addObject("ErrorMessage", "");
			modelAndView.addObject("Message", "");

		} else {
		
			modelAndView.addObject("ErrorMessage", "Enter your credentials");
		
			modelAndView.setViewName("Login");
		}
		
		return modelAndView;

	}

	@RequestMapping("/loginUser")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("customerForm") UserDTO user) {
		log.info("controller for login" + user);
		ModelAndView modelAndView = new ModelAndView();

		try {
			String password = user.getPassword();
			user = userService.getUserDetail(user);
			log.info("controller for login" + user);
			if (user != null && password.equals(user.getPassword())) {
				httpSession.setAttribute("loggedUser", user);
				log.info("((()))){{{}}}---" + user.getProductListDTO().size());
				modelAndView.setViewName("index");
			} else {
				modelAndView.setViewName("Login");
				modelAndView.addObject("ErrorMessage", "Wrong credentials");

			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return modelAndView;

	}

	/**
	 * 
	 * @param request
	 * @param status
	 * @return
	 */

	@RequestMapping("logout")
	public ModelAndView logout(WebRequest request, SessionStatus status) {
		status.setComplete();
		request.removeAttribute("loggedUser", WebRequest.SCOPE_SESSION);
		ModelAndView mv = new ModelAndView("Login", "user", new UserDTO());
		mv.addObject("Message", "Successfully Logout");
		return mv;
	}

	@RequestMapping("/user")
	public ModelAndView createuser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserDTO user) throws RequestManagerException {

		ModelAndView mv = new ModelAndView("Login");
		String serviseResponse = "";
		try {

			user = userService.createOrUpdateUser(user);
			log.info(serviseResponse);

			if ("success".equals(user.getServiseResponse()))
				;
			{

				mv.addObject("Message", "Saved successfully");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			mv.addObject("ErrorMessage", e.getMessage());
		}
		return mv;
	}

	@RequestMapping("/forgotPassword")
	public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse responses)
			throws RequestManagerException {

		modelAndView.setViewName("ForgotPassword");
		return modelAndView;
	}

	@RequestMapping("/profile")
	public ModelAndView profileUpdate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserDTO user) throws RequestManagerException {
		user = (UserDTO) httpSession.getAttribute("loggedUser");
		if (user == null)
			user = new UserDTO();
		ModelAndView mv = new ModelAndView("CreateAccount", "user", user);

		return mv;
	}

}
