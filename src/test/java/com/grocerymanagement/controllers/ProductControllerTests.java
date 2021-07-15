package com.grocerymanagement.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
public class ProductControllerTests {
	@Resource
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	ProductService serviceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getProductListSessionUserNullThenViewLoginPage() throws Exception {
		mockMvc.perform(get("/productLists")).andExpect(status().isOk()).andExpect(view().name("Login"));
	}

	@Test
	public void getProductListSessionUserFoundThenViewProductListsPage() throws Exception {
		mockMvc.perform(get("/productLists").sessionAttr("loggedUser", new UserDTO())).andExpect(status().isOk())
				.andExpect(view().name("ProductLists"));
	}

	@Test
	public void getProductListSessionUserNullThenViewLoginPageModelContainsError() throws Exception {
		mockMvc.perform(get("/productLists")).andExpect(status().isOk()).andExpect(view().name("Login"))
				.andExpect(model().attribute("ErrorMessage", is("Enter your credentials")));
	}

	@Test
	public void getProductListSessionUserFoundThenViewProductListsPageErrorMessageEmpty() throws Exception {
		mockMvc.perform(get("/productLists").sessionAttr("loggedUser", new UserDTO())).andExpect(status().isOk())
				.andExpect(view().name("ProductLists")).andExpect(model().attribute("ErrorMessage", is("")));
	}

	@Test
	public void addProductListSessionUserNullThenViewLoginPage() throws Exception {
		mockMvc.perform(get("/addList")).andExpect(status().isOk()).andExpect(view().name("Login"));
	}

	@Test
	public void addProductListSessionUserNullThenModelContainsErrorMessage() throws Exception {
		mockMvc.perform(get("/addList")).andExpect(status().isOk()).andExpect(view().name("Login"))
				.andExpect(model().attribute("ErrorMessage", is("Enter your credentials")));
	}

	@Test
	public void addProductListSessionUserFoundThenViewProductListsPage() throws Exception {
		mockMvc.perform(get("/addList").sessionAttr("loggedUser", new UserDTO())).andExpect(status().isOk())
				.andExpect(view().name("AddProductList"));
	}

	@Test
	public void addProductListSessionUserFoundThenViewProductListsPageErrorMessageEmpty() throws Exception {
		mockMvc.perform(get("/addList").sessionAttr("loggedUser", new UserDTO())).andExpect(status().isOk())
				.andExpect(view().name("AddProductList")).andExpect(model().attribute("ErrorMessage", is("")));
	}

	@Test
	public void addProductListSessionUserFoundThenModelHaveProductsList() throws Exception {
		mockMvc.perform(get("/addList").sessionAttr("loggedUser", new UserDTO())).andExpect(status().isOk())
				.andExpect(model().attribute("products", notNullValue()));
	}

	@Test
	public void addProductListSessionUserFoundThenModelHaveProductsDTO() throws Exception {
		mockMvc.perform(get("/addList").sessionAttr("loggedUser", new UserDTO())).andExpect(status().isOk())
				.andExpect(model().attribute("productListDTO", notNullValue()));
	}
}
