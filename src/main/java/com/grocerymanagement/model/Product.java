package com.grocerymanagement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name="ProductID",unique = true, nullable = false)
	private long productId;
	private String productName;
	private String description;
	private double price;
	private byte[] categoryImage;
	private byte[] productImage;
	private String encodedStringProductImg ;
	private String imagePath;
	
	private Date createDate;
	private Date updateDate;
	@ManyToMany(mappedBy="productList",fetch = FetchType.EAGER)
	private List<ProductList> productList;
	
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getEncodedStringProductImg() {
		return encodedStringProductImg;
	}
	public void setEncodedStringProductImg(String encodedStringProductImg) {
		this.encodedStringProductImg = encodedStringProductImg;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public byte[] getCategoryImage() {
		return categoryImage;
	}
	public void setCategoryImage(byte[] categoryImage) {
		this.categoryImage = categoryImage;
	}
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public byte[] getProductImage() {
		return productImage;
	}
	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", price=" + price + ", encodedStringProductImg=" + encodedStringProductImg + ", imagePath="
				+ imagePath  + "]";
	}
	

}
