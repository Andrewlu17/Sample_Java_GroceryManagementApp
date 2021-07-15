package com.grocerymanagement.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.grocerymanagement.dto.UserDTO;
import com.grocerymanagement.exceptions.DAOException;
import com.grocerymanagement.model.Product;
import com.grocerymanagement.model.ProductList;
import com.grocerymanagement.model.User;
import com.grocerymanagement.util.UserConverter;

public class UserDAO extends GroceryHandlerDAO {
	@Autowired
	private ProductDAO productDAO;
	Logger log = Logger.getLogger(UserDAO.class);

	public UserDTO getUserDetail(String userId) throws DAOException {

		List<User> user = null;
		UserDTO dto = new UserDTO();
		Session session = null;
		try {
			log.info("Logging user id" + userId);
			session = getHibernateTemplate().getSessionFactory().openSession();

			String hql = "FROM com.grocerymanagement.model.User where userName=:userName";
			Query query = session.createQuery(hql);
			query.setParameter("userName", userId);
			user = query.list();
			if (user != null && user.size() > 0)
				dto = UserConverter.convertUserToUserDTO(user.get(0), dto);
			;
			session.close();

		} catch (Exception e) {
			log.error(e.getMessage());
			if (session != null)
				session.close();
			throw new DAOException(e.getMessage());
		}

		return dto;
	}

	public User getUserByUserID(String userId) throws DAOException {

		List<User> userList = null;
		User user = null;

		UserDTO dto = new UserDTO();
		Session session = null;
		try {
			log.info("Logging user id" + userId);
			session = getHibernateTemplate().getSessionFactory().openSession();

			String hql = "FROM com.grocerymanagement.model.User where userName=:userName";
			Query query = session.createQuery(hql);
			query.setParameter("userName", userId);
			userList = query.list();
			if (userList != null && userList.size() > 0)
				user = userList.get(0);

			session.close();

		} catch (Exception e) {
			log.error(e.getMessage());
			if (session != null)
				session.close();
			throw new DAOException(e.getMessage());
		}
		return user;
	}

	public UserDTO saveUser(UserDTO userDto) throws DAOException {
		if (userDto != null)
			try {
				Session session = getHibernateTemplate().getSessionFactory().openSession();
				session.beginTransaction();
				User user = null;
				if (userDto.getUserId() != 0)
					user = session.get(User.class, userDto.getUserId());
				else
					user = new User();
				log.info("User Detail before Save---" + user.getProductList());
				user = UserConverter.convertUserDTOToUser(userDto, user);
				log.info("User Detail before after converter---" + user.getProductList());

				if (user.getProductList() != null && user.getProductList().size() > 0) {
					Iterator itr = user.getProductList().iterator();
					ProductList productListToSave;
					while (itr.hasNext()) {
						ProductList productList = (ProductList) itr.next();

						log.info("listProductListDTO-----Size------" + productList.getItemlistId());

						if (productList.getItemlistId() != 0) {

							productListToSave = productDAO.getProductList(productList.getItemlistId());
							productListToSave.setListName(productList.getListName());

							List<Product> products = productList.getProductList();

							productListToSave.setProductList(products);
						} else {
							productListToSave = productList;
							productListToSave.setUser(user);
						}

						session.saveOrUpdate(productListToSave);

						user.getProductList().add(productListToSave);

					}

				}

				log.info(user);
				log.info(user.getProductList());

				session.saveOrUpdate(user);

				session.getTransaction().commit();
				session.close();
				userDto = UserConverter.convertUserToUserDTO(user, userDto);

			} catch (Exception e) {
				log.error(e.getMessage());
				throw new DAOException(e.getMessage());
			}
		return userDto;
	}

	public User changePassword(Long userId, String password) throws DAOException {
		User user = null;

		log.info("DAO" + userId + password);
		try {
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			session.beginTransaction();
			user = session.get(User.class, userId);

			user.setPassword(password);
			session.update(user);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return user;
	}

	public User getUser(Long userId) throws DAOException {
		User user = null;

		try {
			Session session = getHibernateTemplate().getSessionFactory().openSession();

			user = session.get(User.class, userId);

			session.close();

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return user;
	}

}
