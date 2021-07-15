package com.grocerymanagement.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class UserDTO {
	private long userId;
	private String userName;
	private String password;
	private String firstName;
	private String LastName;
	private String mobile;
	private String phone;
	private String email;
	private String skypeId;
	private Map<Long,ProductListDTO> productListDTO =new HashMap<Long,ProductListDTO>();
	private String serviseResponse;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSkypeId() {
		return skypeId;
	}
	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}
	public Map<Long, ProductListDTO> getProductListDTO() {
		return productListDTO;
	}
	public void setProductListDTO(Map<Long, ProductListDTO> productListDTO) {
		this.productListDTO = productListDTO;
	}
	public String getServiseResponse() {
		return serviseResponse;
	}
	public void setServiseResponse(String serviseResponse) {
		this.serviseResponse = serviseResponse;
	}
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", LastName=" + LastName + ", mobile=" + mobile + ", phone=" + phone + ", email=" + email
				+ ", skypeId=" + skypeId + ", productListDTO=" + productListDTO + ", serviseResponse=" + serviseResponse
				+ "]";
	}
	
	

	
	

}
