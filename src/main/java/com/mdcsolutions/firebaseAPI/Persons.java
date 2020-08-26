package com.mdcsolutions.firebaseAPI;

import org.springframework.stereotype.Component;

@Component
public class Persons {

	private String userid;
	private String password;
	private String firstname;
	private String middlename;
	private String lastname;
	private String gender;
	private String city;
	private String mobno;
	private String altmobno;
	private String email;

	public Persons() {

	}

	public Persons(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	public String getAltmobno() {
		return altmobno;
	}

	public void setAltmobno(String altmobno) {
		this.altmobno = altmobno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
