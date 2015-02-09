package com.example.emergencynumbers;

import java.io.Serializable;

public class CallNumbers implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Category ;
	private String Name ;
	private String Number ;
	
	public CallNumbers(){
		this.Category = "" ;
		this.Name = "" ;
		this.Number = "" ;
	}
	
	public CallNumbers(String Category,String Name,String Number){
		this.Category = Category ;
		this.Name = Name ;
		this.Number = Number ;
	}
	
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	

}
