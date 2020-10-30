package com.first_sprint.swe;

public class User {
	private String ID;
	private String nickname;
	private String password;
	private String country;
	private String city;
	private String street;
	
	private String idtype;
	private String idnumber;
	private String mobilePhone;
	private String homePhone;
	private String name;
	private String surname;
	
	public User(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
		
	}
	
	public User(String username, String name, String surname, String city, String country, String mobile_phone) {
		this.nickname = username;
		this.name = name;
		this.surname = surname;
		this.city = city; 
		this.country = country;
		this.mobilePhone = mobile_phone;
	}
	public User(String username, String name, String surname, String idtype, String idnumber, String country, String city, String street, String home, String mobile) {
		this.nickname = username;
		this.name = name;
		this.surname = surname;
		this.city = city; 
		this.country = country;
		this.mobilePhone = mobile;
		this.street = street;
		this.homePhone = home;
		this.idnumber = idnumber;
		this.idtype = idtype;
		
	}
	
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}



