package com.grocerymanagement.service;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocerymanagement.dao.ProductDAO;
import com.grocerymanagement.dao.UserDAO;
import com.grocerymanagement.dto.UserDTO;
import com.grocerymanagement.exceptions.ServiceException;
import com.grocerymanagement.model.User;
import com.grocerymanagement.util.UserConverter;
@Service
@Transactional
public class UserService {
	@Autowired
	UserDAO userDAO;
	@Autowired
	ProductDAO productDAO;
	
	Logger log = Logger.getLogger(UserService.class);
	public UserDTO createOrUpdateUser(UserDTO userDto) throws ServiceException {

		userDto = userDAO.saveUser(userDto);
		userDto.setServiseResponse("success");
		return userDto;
	}

	public String updateUser(UserDTO userDto) throws ServiceException {
		User user = null;
		try {
			user = userDAO.getHibernateTemplate().get(User.class, userDto.getUserId());
			if (user == null) {
				user = new User();
				user = UserConverter.convertUserDTOToUser(userDto, user);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		userDAO.update(user);
		return "success";
	}

	public String deleteUser(UserDTO userDto) {

		return "success";
	}

	

	public UserDTO changePassword(UserDTO userDto) throws ServiceException {
		User user = userDAO.changePassword(userDto.getUserId(), userDto.getPassword());
		log.info(user);

		return userDto;
	}

	public UserDTO getUserDetail(UserDTO userDto) throws ServiceException {
		
		return userDAO.getUserDetail(userDto.getUserName());
		
		
	}

	

	

}
