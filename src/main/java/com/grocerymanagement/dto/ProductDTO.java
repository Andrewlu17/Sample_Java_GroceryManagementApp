package com.grocerymanagement.dto;

import org.springframework.stereotype.Component;

@Component
public class ProductDTO {
	private long productId;
	private String productName;
	private String description;
	private double price;
	private byte[] categoryImage;
	private byte[] productImage;
	private long userId;
	private String imagePath;
	
	private String encodedStringProductImg ;
	
	public void setProductId(long productId) {
		this.productId = productId;
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
	public long getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public byte[] getProductImage() {
		
		return productImage;
	}
	public void setProductImage(byte[] productImage) {
		
		
		
		this.productImage = productImage;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return productName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (productId ^ (productId >>> 32));
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
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
		ProductDTO other = (ProductDTO) obj;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}
	
	
}
