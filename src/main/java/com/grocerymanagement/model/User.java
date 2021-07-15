package com.grocerymanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "Users_Detail")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "userName" }) })
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8582946561225441565L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserID",unique = true, nullable = false)
	private long id;
	@Column(unique = true)
	private String userName;
	private String password;
	private String firstName;
	private String LastName;
	private String mobile;
	private String skypeId;
	private String email;
	private String phone;
	private boolean active;

	@OneToMany(mappedBy="user",fetch = FetchType.EAGER)
	private Set<ProductList> productList;

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

	public String getSkypeId() {
		return skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<ProductList> getProductList() {
		return productList;
	}

	public void setProductList(Set<ProductList> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", email=" + email + ", phone=" + phone
				+ ", permanantAddress=" + "]";
	}
	

}
