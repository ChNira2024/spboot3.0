package com.karapada.security.model;

public class Employee {

	private Long id;
	private String name;
	private String address;
	private Long mobNumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getMobNumber() {
		return mobNumber;
	}
	public void setMobNumber(Long mobNumber) {
		this.mobNumber = mobNumber;
	}
	public Employee(Long id, String name, String address, Long mobNumber) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobNumber = mobNumber;
	}
	
}
