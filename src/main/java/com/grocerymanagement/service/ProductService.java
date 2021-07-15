package com.grocerymanagement.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocerymanagement.dao.ProductDAO;
import com.grocerymanagement.dao.UserDAO;
import com.grocerymanagement.dto.ProductDTO;
import com.grocerymanagement.dto.ProductListDTO;
import com.grocerymanagement.dto.UserDTO;
import com.grocerymanagement.exceptions.DAOException;
import com.grocerymanagement.exceptions.ServiceException;
import com.grocerymanagement.model.Product;
import com.grocerymanagement.model.ProductList;
import com.grocerymanagement.model.User;
import com.grocerymanagement.util.ProductConverter;
import com.grocerymanagement.util.ProductListConverter;
@Service
@Transactional
public class ProductService {
	public static final Logger log = Logger.getLogger(ProductService.class);

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private UserDAO userDAO;

	public String createOrUpdateProduct(ProductDTO productDTO) {
		Logger log = Logger.getLogger(ProductService.class);
		try {
			Product product = ProductConverter.productDTOToProduct(productDTO, new Product());
			log.info("Data before DB insert--" + product);
			User user = userDAO.getUser(productDTO.getUserId());

			product = productDAO.saveProduct(product);
		} catch (Exception e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
		return "success";
	}

	public ProductListDTO createOrUpdateProductList(ProductListDTO productListDTO, UserDTO user)
			throws ServiceException {
		ProductList productList = null;
		Logger log = Logger.getLogger(ProductService.class);
		try {
			User userModel = userDAO.getUserByUserID(user.getUserName());

			productList = ProductListConverter.convertDTOtoBean(productListDTO, new ProductList(), userModel);
			log.info("Data before DB insert--" + productList);

			productList = productDAO.saveProductList(productList);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		productListDTO = ProductConverter.productListToProductDTOListConversion(productList, productListDTO);
		return productListDTO;
	}

	public ProductDTO getProductFromId(Long productId) {
		Product product = productDAO.getProduct(productId);
		ProductDTO dto = ProductConverter.productToProductDTO(product, new ProductDTO());
		return dto;
	}

	public ProductDTO getProductFromName(String productName) throws DAOException {
		ProductDTO dto = null;
		List<Product> product = productDAO.getProductDetail(productName);
		if (product != null && product.size() > 0)
			dto = ProductConverter.productToProductDTO(product.get(0), new ProductDTO());
		return dto;
	}

	public List<ProductDTO> searchProduct(ProductDTO productDTO) throws ServiceException {
		List<ProductDTO> productList = null;
		try {

			List<Product> product = productDAO.getProductDetail(productDTO);
			productList = ProductConverter.productListToProductDTOList(product, new ArrayList<ProductDTO>());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return productList;
	}

	public ProductListDTO getProductList(Long listId) throws ServiceException {
		ProductListDTO productListDTO = null;
		try {

			productListDTO = productDAO.getProductListDTO(listId);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return productListDTO;
	}

	public Set<ProductListDTO> getProductLists(String userId) throws ServiceException {
		Set<ProductListDTO> productListDTO = new HashSet<ProductListDTO>();
		try {

			List<ProductList> list = productDAO.getProductLists(userId);
			Iterator itr = list.iterator();
			while (itr.hasNext()) {

				productListDTO.add(ProductConverter.productListToProductDTOListConversion((ProductList) itr.next(),
						new ProductListDTO()));
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return productListDTO;
	}
}
