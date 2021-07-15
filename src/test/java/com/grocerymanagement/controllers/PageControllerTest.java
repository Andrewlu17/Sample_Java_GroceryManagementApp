package com.grocerymanagement.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.grocerymanagement.dto.UserDTO;
import com.grocerymanagement.service.ProductService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-servlet.xml" })
public class PageControllerTest {
	@Resource
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	ProductService serviceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void loadHomePageSessionUserNullThenViewLoginPage() throws Exception {
		mockMvc.perform(get("/home")).andExpect(status().isOk()).andExpect(view().name("Login"));
	}

	@Test
	public void loadHomePageSessionUserFoundThenViewProductListsPage() throws Exception {
		mockMvc.perform(get("/home").sessionAttr("loggedUser", new UserDTO())).andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

	@Test
	public void loadHomePageSessionUserNullThenViewLoginPageModelContainsError() throws Exception {
		mockMvc.perform(get("/home")).andExpect(status().isOk()).andExpect(view().name("Login"))
				.andExpect(model().attribute("ErrorMessage", is("Enter your credentials")));
	}

	@Test
	public void loadHomePageSessionUserFoundThenViewProductListsPageErrorMessageEmpty() throws Exception {
		mockMvc.perform(get("/home").sessionAttr("loggedUser", new UserDTO())).andExpect(status().isOk())
				.andExpect(view().name("index")).andExpect(model().attribute("ErrorMessage", is("")));
	}

}
