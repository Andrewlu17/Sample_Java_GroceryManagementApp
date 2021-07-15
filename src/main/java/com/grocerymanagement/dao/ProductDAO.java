package com.grocerymanagement.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import com.grocerymanagement.dto.ProductDTO;
import com.grocerymanagement.dto.ProductListDTO;
import com.grocerymanagement.exceptions.DAOException;
import com.grocerymanagement.model.Product;
import com.grocerymanagement.model.ProductList;
import com.grocerymanagement.util.ProductConverter;

public class ProductDAO extends GroceryHandlerDAO {
	Logger log = Logger.getLogger(ProductDAO.class);

	public List<Product> getProductDetail(String productName) throws DAOException {
		List<Product> product = null;
		Session session = null;
		try {
			log.info("Fetching detail of product" + productName);
			session = getHibernateTemplate().getSessionFactory().openSession();

			String hql = "FROM com.grocerymanagement.model.Product where productName=:productName";
			Query query = session.createQuery(hql);
			query.setParameter("productName", productName);
			product = query.list();

			session.close();

		} catch (Exception e) {
			log.error(e.getMessage());
			if (session != null)
				session.close();
			throw new DAOException(e.getMessage());
		}

		return product;
	}

	public Product saveProduct(Product product) throws DAOException {
		Date date = new Date();
		if (product != null)
			try {
				Session session = getHibernateTemplate().getSessionFactory().openSession();

				session.beginTransaction();
				Product prodFromDB = session.get(Product.class, product.getProductId());
				if (prodFromDB != null) {
					prodFromDB = ProductConverter.productToProduct(product, prodFromDB);

					if (prodFromDB.getCreateDate() == null)
						prodFromDB.setCreateDate(date);
					prodFromDB.setUpdateDate(date);
					session.update(prodFromDB);
				} else {
					product.setCreateDate(date);
					product.setUpdateDate(date);
					session.save(product);
				}
				session.getTransaction().commit();
				session.close();

			} catch (Exception e) {
				log.error(e.getMessage());
				throw new DAOException(e.getMessage());
			}
		return product;
	}

	public ProductList saveProductList(ProductList productList) throws DAOException {
		Date date = new Date();
		ProductList prodListFromDB=null;
		if (productList != null)
			try {
				Session session = getHibernateTemplate().getSessionFactory().openSession();

				session.beginTransaction();
				 prodListFromDB = session.get(ProductList.class, productList.getItemlistId());
				if (prodListFromDB == null)
					prodListFromDB = new ProductList();
				prodListFromDB.setItemlistId(productList.getItemlistId());
				prodListFromDB.setListName(productList.getListName());
				prodListFromDB.setUser(productList.getUser());
				List<Product> prodList = new ArrayList<Product>();
				if (productList.getProductList() != null)
					for (Product product : productList.getProductList()) {
						List<Product> prodFromDB = getProductDetail(product.getProductName());
						if (prodFromDB != null && prodFromDB.size() > 0) {
							prodList.add(prodFromDB.get(0));
						}

					}
				prodListFromDB.setProductList(prodList);
				session.save(prodListFromDB);
				session.getTransaction().commit();
				session.close();

			} catch (Exception e) {
				log.error(e.getMessage());
				throw new DAOException(e.getMessage());
			}
		return prodListFromDB;
	}

	public Product getProduct(Long productId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Product product = session.get(Product.class, productId);
		session.close();
		return product;
	}

	public ProductList getProductList(Long productListId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		ProductList productList = (ProductList) session.get(ProductList.class, productListId);
		session.close();
		return productList;
	}

	public List<ProductList> getProductLists(String userId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String hql = "FROM com.grocerymanagement.model.ProductList where user.userName=:userName";
		Query query = session.createQuery(hql);
		query.setParameter("userName", userId);
		List<ProductList> productList = query.list();
		session.close();
		return productList;
	}

	public ProductListDTO getProductListDTO(Long productListId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		ProductList productList = (ProductList) session.get(ProductList.class, productListId);
		if (productList != null)
			log.info(productList.getProductList());
		ProductListDTO productListDTO = ProductConverter.productListToProductDTOListConversion(productList,
				new ProductListDTO());
		session.close();
		return productListDTO;
	}

	public List<Product> getProductDetail(ProductDTO productDTO) throws DAOException {
		List<Product> productList = null;
		Session session = null;
		try {
			log.info("Search Product Detail" + productDTO);
			session = getHibernateTemplate().getSessionFactory().openSession();

			String qry = "FROM com.grocerymanagement.model.Product as product where product.productName like :productName";
			Query query = null;

			if (productDTO != null && productDTO.getProductName() != null) {
				query = session.createQuery(qry);
				query.setParameter("productName", "%" + productDTO.getProductName() + "%");
			} else {
				query = session.createQuery(qry);
				query.setParameter("productName", "%%");
			}
			if (query != null)
				productList = query.list();

			session.close();

		} catch (Exception e) {
			log.error(e.getMessage());
			if (session != null)
				session.close();
			throw new DAOException(e.getMessage());
		}

		return productList;
	}
	
	

}
