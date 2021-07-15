package com.grocerymanagement.service;

import static org.junit.Assert.assertEquals;
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

import com.grocerymanagement.dto.ProductDTO;
import com.grocerymanagement.dto.ProductListDTO;
import com.grocerymanagement.dto.UserDTO;
import com.grocerymanagement.exceptions.ServiceException;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-servlet.xml" })
public class ProductServiceTest {
	@Resource
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	@Autowired
	ProductService serviceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void createOrUpdateProductTest() {
		ProductDTO productDTO = new ProductDTO();
		String response;
		productDTO.setProductName("Test2");
		// serviceMock = mock(ProductService.class);
		assertNotNull(serviceMock);
		response = serviceMock.createOrUpdateProduct(productDTO);
		assertEquals(response, "success");

	}

	@Test
	public void createOrUpdateProductListTest() {
		ProductListDTO productListDTO = new ProductListDTO();
		UserDTO user = new UserDTO();

		productListDTO.setListName("TestList");

		assertNotNull(serviceMock);
		try {
			productListDTO = serviceMock.createOrUpdateProductList(productListDTO, user);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(productListDTO.getListId(), 0);

	}

}
