package com.admin.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")

public class User {

	@Id
	private String id;
	private String name;
	private String age;
	private String gender;
	private String phone;
	private String location;
	private String userName;
	private String email;
	private String password;
	private String role;
	public User(String id, String name, String age, String gender, String phone, String location, String userName,
			String email, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.location = location;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public User() {
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}