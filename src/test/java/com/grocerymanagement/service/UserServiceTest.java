package com.grocerymanagement.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.grocerymanagement.dto.UserDTO;
import com.grocerymanagement.exceptions.ServiceException;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-servlet.xml" })
public class UserServiceTest {
	@Resource
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	@Autowired
	UserService serviceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void createOrUpdateProduct() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("DUMMY_USER");
		userDTO.setPassword("DUMMY");
		// serviceMock = mock(ProductService.class);
		assertNotNull(serviceMock);
		try {
			userDTO = serviceMock.createOrUpdateUser(userDTO);
		} catch (ServiceException e) {

			e.printStackTrace();
		}
		assertNotEquals(userDTO.getUserId(), 0);

	}

}
