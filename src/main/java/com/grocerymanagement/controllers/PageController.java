package com.grocerymanagement.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.grocerymanagement.dto.UserDTO;
import com.grocerymanagement.service.PageService;

@Controller
@SessionAttributes("loggedUser")
public class PageController implements ServletContextAware {
	public static final Logger log = Logger.getLogger(PageController.class);
	private ServletContext context;
	@Value("${domain.name}")
	public String domainName;
	@Value("${application.owner.contactno}")
	public String contactInfo;

	@Autowired
	ModelAndView modelAndView;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private PageService pageService;

	@RequestMapping("/home")
	public ModelAndView loadHomePage(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserDTO user) {
		context.setAttribute("domainName", domainName);
		context.setAttribute("contactInfo", contactInfo);
		user = (UserDTO) httpSession.getAttribute("loggedUser");
		log.info("Logged User Info" + user);
		UserDTO loggedUser = (UserDTO) httpSession.getAttribute("loggedUser");
		if (loggedUser != null) {
			modelAndView.addObject("ErrorMessage", "");
			modelAndView.addObject("Message", "");

			modelAndView.setViewName("index");
		} else {
			modelAndView.addObject("ErrorMessage", "Enter your credentials");
			modelAndView.addObject("Message", "");

			modelAndView.setViewName("Login");

		}

		return modelAndView;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;

	}

}
