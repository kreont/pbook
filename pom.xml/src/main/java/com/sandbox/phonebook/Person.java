package com.sandbox.phonebook;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@Entity
@Table(name = "person")
public class Person implements Serializable {

	private static final long serialVersionUID = -8577039305816169297L;

	/**
	 * Уникальный идентификатор записи
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * ФИО
	 */
	@Column(name = "full_name")
	private String fullName;
	
	/**
	 * Номер телефона
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * Адрес
	 */
	@Column(name = "address")
	private String address;

	public Person() {

	}

	public Person(String fullName, String phone, String address) {
		this.fullName = fullName;
		this.phone = phone;
		this.address = address;
	}

	public Person(Integer id) {
		this.id = id;
	}

	public Person(Integer id, String fullName, String phone, String address) {
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.address = address;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTid(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", fullName=" + fullName + ", phone="
				+ phone + ", address=" + address + "]";
	}

}