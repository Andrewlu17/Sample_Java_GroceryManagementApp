package com.grocerymanagement.dto;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class ProductListDTO {
	private long listId;
	private String listName;
	private long userid;
	private List<ProductDTO> productList;
	private java.lang.String[] selectedItems;
	private java.lang.String[]  unSelectedItems;
	
	public java.lang.String[] getUnSelectedItems() {
		return unSelectedItems;
	}
	public void setUnSelectedItems(java.lang.String[] unSelectedItems) {
		this.unSelectedItems = unSelectedItems;
	}
	public long getListId() {
		return listId;
	}
	public void setListId(long listId) {
		this.listId = listId;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public List<ProductDTO> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductDTO> productList) {
		this.productList = productList;
	}
	
	@Override
	public String toString() {
		return "ProductListDTO [listId=" + listId + ", listName=" + listName + ", userid=" + userid + ", productList="
				+ productList + "]";
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (listId ^ (listId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductListDTO other = (ProductListDTO) obj;
		if (listId != other.listId)
			return false;
		
		return true;
	}
	public java.lang.String[] getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(java.lang.String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	
}
