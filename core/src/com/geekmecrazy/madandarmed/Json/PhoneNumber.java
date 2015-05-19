package com.geekmecrazy.madandarmed.Json;

public class PhoneNumber {

	public String name;
	public String number;

	public PhoneNumber(){
	}
	
	public PhoneNumber(String s1, String s2){
		name=s1;
		number=s2;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
