package com.example.expensemanger;


public class MyData {
	
	private Date date ;
	private Expenses expnses ;
	private int Tag ;
	
	
	public MyData(){
		this.Tag = 0 ;
		this.date = new Date() ;
		this.expnses = new Expenses() ;
	}
	public MyData(Date date, Expenses expense){
		this.date = new Date(date) ;
		this.expnses = new Expenses(expense) ;
	}
	public MyData(int tag ,Date date, Expenses expense){
		this.Tag = tag ;
		this.date = new Date(date) ;
		this.expnses = new Expenses(expense) ;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Expenses getExpnses() {
		return expnses;
	}
	public void setExpnses(Expenses expnses) {
		this.expnses = expnses;
	}
	public int getTag() {
		return Tag;
	}
	public void setTag(int tag) {
		Tag = tag;
	}
	
	
}


class Date{
	
	private int day ;
	private String month ;
	private long year ;
	
	public Date(){
		this.day = 0 ;
		this.month = " " ;
		this.year = 2000 ;
	}
	public Date(int day , String month, long year){
		this.day = day ;
		this.month = month ;
		this.year = year ;
	}
	public Date(Date date){
		this.day =  date.day ;
		this.month = date.month ;
		this.year = date.year ;
	}
	public Date(String date)
	{
		String temp[] = date.split("/") ;
		this.day =  Integer.valueOf(temp[0]) ;
		this.month = temp[1] ;
		this.year = Integer.valueOf(temp[2]) ;
	}
	
	public String getDate() {
		String temp = String.valueOf(this.day) + "/" + this.month + "/" + String.valueOf(this.year) ;
		return temp ;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	public Date ReturnDate(String date) {
		
		Date  tempdate = new Date() ;
		String temp[] = date.split("/") ;
		tempdate.day =  Integer.valueOf(temp[0]) ;
		tempdate.month = temp[1] ;
		tempdate.year = Integer.valueOf(temp[2]) ;
		
		return tempdate;
}	
}

class Expenses{
	
	private int food ;
	private int bill ;
	private int medical ;
	private int transport ;
	private int Others ;
	
	public Expenses() {
		this.food = 0; 
		this.bill = 0 ;
		this.medical = 0 ;
		this.transport = 0 ;
		this.Others = 0 ;
	}
	public Expenses(int food,int bill,int medical,int transport, int Others) {
		this.food = food; 
		this.bill = bill ;
		this.medical = medical ;
		this.transport = transport ;
		this.Others = Others ;
	}
	public Expenses(Expenses expenses) {
		this.food = expenses.food; 
		this.bill = expenses.bill ;
		this.medical = expenses.medical ;
		this.transport = expenses.transport ;
		this.Others = expenses.Others ;
	}
	
	public int getFood() {
		return food;
	}
	public void setFood(int food) {
		this.food = food;
	}
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
	public int getMedical() {
		return medical;
	}
	public void setMedical(int medical) {
		this.medical = medical;
	}
	public int getOthers() {
		return Others;
	}
	public void setOthers(int others) {
		Others = others;
	}
	public int getTransport() {
		return transport;
	}
	public void setTransport(int transport) {
		this.transport = transport;
	}
}

