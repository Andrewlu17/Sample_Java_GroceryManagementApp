package com.grocerymanagement.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.grocerymanagement.dto.ProductDTO;
import com.grocerymanagement.dto.ProductListDTO;
import com.grocerymanagement.dto.UserDTO;
import com.grocerymanagement.exceptions.ServiceException;
import com.grocerymanagement.service.ProductService;
import com.grocerymanagement.service.UserService;

@Controller
@SessionAttributes("loggedUser")
public class ProductController {
	public static final Logger log = Logger.getLogger(ProductController.class);
	@Autowired
	ModelAndView modelAndView;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/productLists", method = RequestMethod.GET)
	public ModelAndView product(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserDTO user) throws ServiceException {
		UserDTO loggedUser = (UserDTO) httpSession.getAttribute("loggedUser");
		ModelAndView modelAndView = new ModelAndView();
		if (loggedUser != null) {

			modelAndView.addObject("ErrorMessage", "");
			modelAndView.addObject("Message", "");
			for (ProductListDTO p : productService.getProductLists(loggedUser.getUserName())) {
				loggedUser.getProductListDTO().put(p.getListId(), p);
			}
			modelAndView.addObject("productLists", productService.getProductLists(loggedUser.getUserName()));
			modelAndView.setViewName("ProductLists");

		} else {
			modelAndView.addObject("ErrorMessage", "Enter your credentials");
			modelAndView.addObject("Message", "");

			modelAndView.setViewName("Login");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/addList", method = RequestMethod.GET)
	public ModelAndView addList(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		UserDTO loggedUser = (UserDTO) httpSession.getAttribute("loggedUser");
		ModelAndView modelAndView = new ModelAndView();
		if (loggedUser != null) {

			modelAndView.addObject("ErrorMessage", "");
			modelAndView.addObject("Message", "");
			modelAndView.addObject("productLists", productService.getProductLists(loggedUser.getUserName()));

			List<ProductDTO> products = new ArrayList<ProductDTO>();

			ProductDTO product = new ProductDTO();
			List<ProductDTO> productList = productService.searchProduct(product);
			log.info("----" + productList);
			modelAndView.addObject("allProductList", productList);

			modelAndView.addObject("products", products);
			ProductListDTO productListDTO = new ProductListDTO();
			productListDTO.setProductList(products);
			modelAndView.addObject("productListDTO", productListDTO);
			modelAndView.setViewName("AddProductList");

		} else {
			modelAndView.addObject("ErrorMessage", "Enter your credentials");
			modelAndView.addObject("Message", "");

			modelAndView.setViewName("Login");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/addItems", method = RequestMethod.POST)
	public ModelAndView addItems(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("productList") ProductListDTO updatedProductListDTO) throws ServiceException {
		UserDTO loggedUser = (UserDTO) httpSession.getAttribute("loggedUser");
		ModelAndView modelAndView = new ModelAndView();
		if (loggedUser != null) {

			modelAndView.addObject("ErrorMessage", "");
			modelAndView.addObject("Message", "");
			ProductListDTO productListDTO = null;
			if (Long.parseLong(request.getParameter("listId")) != 0)
				productListDTO = productService.getProductList(Long.parseLong(request.getParameter("listId")));
			if (productListDTO == null) {
				productListDTO = updatedProductListDTO;

			}
			if (updatedProductListDTO.getSelectedItems() != null) {
				if (productListDTO.getProductList() == null)
					productListDTO.setProductList(new ArrayList<ProductDTO>());
				for (String product : updatedProductListDTO.getSelectedItems()) {
					ProductDTO productDTO = productService.getProductFromName(product);

					productListDTO.getProductList().add(productDTO);
				}
			}
			productListDTO = productService.createOrUpdateProductList(productListDTO, loggedUser);

			ProductDTO product = new ProductDTO();
			List<ProductDTO> allProducts = productService.searchProduct(product);
			log.info("----" + allProducts);
			modelAndView.addObject("allProductList", allProducts);

			modelAndView.addObject("products", productListDTO);

			updatedProductListDTO.setProductList(productListDTO.getProductList());
			modelAndView.addObject("productListDTO", updatedProductListDTO);
			modelAndView.setViewName("AddProductList");

		} else {
			modelAndView.addObject("ErrorMessage", "Enter your credentials");
			modelAndView.addObject("Message", "");

			modelAndView.setViewName("Login");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/removeItems", method = RequestMethod.POST)
	public ModelAndView removeItems(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("productList") ProductListDTO updatedProductListDTO) throws ServiceException {
		UserDTO loggedUser = (UserDTO) httpSession.getAttribute("loggedUser");
		ModelAndView modelAndView = new ModelAndView();
		if (loggedUser != null) {

			modelAndView.addObject("ErrorMessage", "");
			modelAndView.addObject("Message", "");
			ProductListDTO productListDTO = null;
			if (Long.parseLong(request.getParameter("listId")) != 0) {
				productListDTO = productService.getProductList(Long.parseLong(request.getParameter("listId")));

				if (updatedProductListDTO.getUnSelectedItems() != null) {
					if (productListDTO.getProductList() != null) {
						for (String product : updatedProductListDTO.getUnSelectedItems()) {
							ProductDTO productDTO = productService.getProductFromName(product);

							productListDTO.getProductList().remove(productDTO);
						}
					}
				}
				productListDTO = productService.createOrUpdateProductList(productListDTO, loggedUser);
			}
			ProductDTO product = new ProductDTO();
			List<ProductDTO> allProducts = productService.searchProduct(product);
			log.info("----" + allProducts);
			modelAndView.addObject("allProductList", allProducts);

			modelAndView.addObject("products", productListDTO);

			updatedProductListDTO.setProductList(productListDTO.getProductList());
			modelAndView.addObject("productListDTO", updatedProductListDTO);
			modelAndView.setViewName("AddProductList");

		} else {
			modelAndView.addObject("ErrorMessage", "Enter your credentials");
			modelAndView.addObject("Message", "");

			modelAndView.setViewName("Login");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/viewList", method = RequestMethod.GET)
	public ModelAndView viewList(HttpServletRequest request, HttpServletResponse response) throws ServiceException, NumberFormatException {
		UserDTO loggedUser = (UserDTO) httpSession.getAttribute("loggedUser");
		ModelAndView modelAndView = new ModelAndView();
		if (loggedUser != null) {

			modelAndView.addObject("ErrorMessage", "");
			modelAndView.addObject("Message", "");

			ProductListDTO productListDTO = productService
					.getProductList(Long.parseLong(request.getParameter("listId")));
			log.info("productListDTO====" + productListDTO.getProductList());
			log.info("productListDTO====List by id" + productListDTO.getProductList());
			List<ProductDTO> allProductList = productService.searchProduct(new ProductDTO());

			modelAndView.addObject("allProductList", allProductList);
			// modelAndView.addObject("productLists", productListDTO);
			modelAndView.addObject("products", productListDTO.getProductList());
			modelAndView.addObject("productListDTO", productListDTO);

			modelAndView.setViewName("AddProductList");

		} else {
			modelAndView.addObject("ErrorMessage", "Enter your credentials");
			modelAndView.addObject("Message", "");

			modelAndView.setViewName("Login");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/addProductList", method = RequestMethod.POST)
	public ModelAndView addProductList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("productList") ProductListDTO productListDTO) throws ServiceException {
		UserDTO loggedUser = (UserDTO) httpSession.getAttribute("loggedUser");
		ModelAndView modelAndView = new ModelAndView();
		if (loggedUser != null) {
			ProductListDTO savedProductListDTO = null;
			try {
				if (Long.parseLong(request.getParameter("listId")) != 0)
					savedProductListDTO = productService.getProductList(Long.parseLong(request.getParameter("listId")));
				if (savedProductListDTO == null)
					savedProductListDTO = new ProductListDTO();
				savedProductListDTO.setListId(productListDTO.getListId());

				productListDTO = productService.createOrUpdateProductList(savedProductListDTO, loggedUser);

				log.info("productListDTO====" + productListDTO.getProductList());
				log.info("productListDTO====List by id" + productListDTO.getProductList());
				List<ProductDTO> allProductList = productService.searchProduct(new ProductDTO());

				modelAndView.addObject("allProductList", allProductList);
				// modelAndView.addObject("productLists", productListDTO);
				modelAndView.addObject("products", productListDTO.getProductList());
				modelAndView.addObject("productListDTO", productListDTO);

				modelAndView.setViewName("AddProductList");

				modelAndView.addObject("ErrorMessage", "");
				modelAndView.addObject("Message", "Saved Successfully");
			} catch (Exception e) {

				log.error(e.getMessage());
				modelAndView.addObject("ErrorMessage", e.getMessage());
				modelAndView.addObject("Message", "");
			}

		} else {
			modelAndView.addObject("ErrorMessage", "Enter your credentials");
			modelAndView.addObject("Message", "");

			modelAndView.setViewName("Login");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/productPage", method = RequestMethod.GET)
	public ModelAndView product(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("product") ProductDTO product,
			@RequestParam(value = "productID", required = false) Long productID) {
		log.info(product + "" + productID);
		if (productID != null && productID != 0)
			product = productService.getProductFromId(productID);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ErrorMessage", "");
		modelAndView.addObject("Message", "");
		modelAndView.addObject("product", product);
		modelAndView.setViewName("CreateProducts");
		return modelAndView;

	}

	@RequestMapping("/product")
	public ModelAndView createProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("productImage2") MultipartFile file, @ModelAttribute("product") ProductDTO product)
			throws IOException {

		product.setProductImage(file.getBytes());
		product.setImagePath(file.getOriginalFilename());
		byte[] encoded = Base64.encodeBase64(file.getBytes());
		String encodedString = new String(encoded);

		product.setEncodedStringProductImg(encodedString);

		try {
			UserDTO loggedUser = (UserDTO) httpSession.getAttribute("loggedUser");
			product.setUserId(loggedUser.getUserId());
		} catch (Exception e) {
			log.error(e.getMessage());
			
		}
		log.info(product);
		ModelAndView modelAndView = new ModelAndView();

		String serviceResponse = productService.createOrUpdateProduct(product);

		if ("success".equals(serviceResponse))
			modelAndView.addObject("Message", " Product saved successfully");
		else
			modelAndView.addObject("ErrorMessage", serviceResponse);

		modelAndView.setViewName("CreateProducts");
		return modelAndView;

	}

	@RequestMapping("/searchProduct")
	public ModelAndView searchProduct(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("product") ProductDTO product) throws ServiceException {

		UserDTO loggedUser = (UserDTO) httpSession.getAttribute("loggedUser");
		if (loggedUser != null)
			product.setUserId(loggedUser.getUserId());

		log.info(product);

		ModelAndView modelAndView = new ModelAndView();

		List<ProductDTO> productList = productService.searchProduct(product);
		modelAndView.addObject("productList", productList);
		log.info("productList  " + productList);
		modelAndView.setViewName("SearchProducts");
		return modelAndView;

	}

}
