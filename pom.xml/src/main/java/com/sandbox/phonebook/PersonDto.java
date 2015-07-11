package com.sandbox.phonebook;

import java.io.Serializable;

public class PersonDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 191262958612848074L;
	private Integer id;
	private String fullName;
	private String phone;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}