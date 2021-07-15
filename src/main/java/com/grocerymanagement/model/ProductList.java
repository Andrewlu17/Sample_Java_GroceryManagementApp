package com.grocerymanagement.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ProductList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7266225036457016736L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Product_List_Id",unique = true, nullable = false)
	private long itemlistId;
	private String listName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Product> productList;
	
	@ManyToOne
	private User user;
	public long getItemlistId() {
		return itemlistId;
	}
	public void setItemlistId(long itemlistId) {
		this.itemlistId = itemlistId;
	}
	
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	@Override
	public String toString() {
		return "ProductList [itemlistId=" + itemlistId + ", listName=" + listName  + "]";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
