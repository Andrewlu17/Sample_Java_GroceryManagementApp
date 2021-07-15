package com.grocerymanagement.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


public class GroceryHandlerDAO extends HibernateDaoSupport {
	public void save(Object entity) {
		getHibernateTemplate().save(entity);

	};

	public void update(Object entity) {
		getHibernateTemplate().update(entity);

	};

	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	};
	public void get(Object entity) {
		getHibernateTemplate().get(entity.getClass(),"");
	};
	
	
	public List<Object> getAll(Object object) {
		return null;
	}

}
