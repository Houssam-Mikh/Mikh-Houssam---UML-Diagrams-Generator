package org.mql.java.lang.models;

public class Author extends Person{
	
	private String country ; 
	private String lang ; 
	private String AuthId;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getAuthId() {
		return AuthId;
	}
	public void setAuthId(String authId) {
		AuthId = authId;
	}
	public Author(String firstName, String lastName, int age, String country, String lang, String authId) {
		super(firstName, lastName, age);
		this.country = country;
		this.lang = lang;
		AuthId = authId;
	} 
	
}
