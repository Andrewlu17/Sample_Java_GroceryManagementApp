package com.grocerymanagement.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;

import com.grocerymanagement.dto.ProductDTO;
import com.grocerymanagement.dto.ProductListDTO;
import com.grocerymanagement.dto.UserDTO;
import com.grocerymanagement.exceptions.ServiceException;
import com.grocerymanagement.model.Product;
import com.grocerymanagement.model.ProductList;
import com.grocerymanagement.model.User;

public class UserConverter {
	public static final Logger log = Logger.getLogger(UserConverter.class);

	public static UserDTO convertUserToUserDTO(User user, UserDTO userDTO) throws Exception {

		try {
			if (user != null) {
				userDTO.setUserId(user.getId());
				userDTO.setEmail(user.getEmail());
				userDTO.setPassword(user.getPassword());
				userDTO.setPhone(user.getPhone());
				userDTO.setUserName(user.getUserName());
				userDTO.setFirstName(user.getFirstName());
				userDTO.setLastName(user.getLastName());
				userDTO.setSkypeId(user.getSkypeId());
				userDTO.setMobile(user.getMobile());

				Set<ProductList> productList = user.getProductList();
				Iterator itr = productList.iterator();
				Set<ProductListDTO> productListdto = new HashSet<ProductListDTO>();

				while (itr.hasNext()) {
					ProductList list = (ProductList) itr.next();

					ProductListDTO dto = new ProductListDTO();
					dto.setListName(list.getListName());
					dto.setListId(list.getItemlistId());
					List<ProductDTO> productListDTO = dto.getProductList();
					if (productListDTO == null)
						productListDTO = new ArrayList<ProductDTO>();
					List<Product> produList = list.getProductList();
					Iterator itr2 = produList.iterator();
					while (itr2.hasNext()) {
						Product product = (Product) itr2.next();

						ProductDTO productDTO = new ProductDTO();
						productDTO.setProductName(product.getProductName());
						productDTO.setProductId(product.getProductId());
						productListDTO.add(productDTO);
					}
					dto.setProductList(productListDTO);
					productListdto.add(dto);
				}
				for (ProductListDTO p : productListdto) {
					userDTO.getProductListDTO().put(p.getListId(), p);
				}

			}
		}  catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e.getMessage());
		}

		return userDTO;

	}

	public static User convertUserDTOToUser(UserDTO userDTO, User user) throws ServiceException {

		try {
			if (userDTO != null) {
				user.setId(userDTO.getUserId());

				user.setEmail(userDTO.getEmail());
				if (userDTO.getPassword() != null && !"".equals(userDTO.getPassword()))
					user.setPassword(userDTO.getPassword());

				user.setPhone(userDTO.getPhone());
				user.setUserName(userDTO.getUserName());
				user.setFirstName(userDTO.getFirstName());
				user.setLastName(userDTO.getLastName());
				user.setSkypeId(userDTO.getSkypeId());
				user.setMobile(userDTO.getMobile());
				Set<ProductList> productListSet=ProductConverter.productDTOListToProductList(userDTO.getProductListDTO(), new HashSet<ProductList>());
				user.setProductList(productListSet);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return user;

	}

}
