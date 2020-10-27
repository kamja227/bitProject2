package com.bit.model.dto;

import java.sql.SQLException;

public class EmployeeDto {
	private int employeeIdx;
	private String userId;
	private String password;
	private String name;
	private String contact;
	private String email;
	private String department;
	private int manageClass;
	private int level;
	private int deleted;
	
	public EmployeeDto(){}
	
	public EmployeeDto(String userId, String password, String name, String contact, String department, int level){
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.contact = contact;
		this.department = department;
		this.level = level;
	}
	
	public EmployeeDto(int employeeIdx, String userId, String password, String name, 
			String contact, String email, String department, int manageClass, int level, int deleted) {
		this.employeeIdx = employeeIdx;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.department = department;
		this.manageClass = manageClass;
		this.level = level;
		this.deleted = deleted;
	}
	
	public int getEmployeeIdx() {
		return employeeIdx;
	}
	public void setEmployeeIdx(int employeeIdx) {
		this.employeeIdx = employeeIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public int getManageClass() {
		return manageClass;
	}

	public void setManageClass(int manageClass) {
		this.manageClass = manageClass;
	}

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "EmployeeDto [employeeIdx=" + employeeIdx + ", userId=" + userId
				+ ", password=" + password + ", name=" + name + ", contact="
				+ contact + ", email=" + email + ", department=" + department
				+ ", manageClass=" + manageClass + ", level=" + level
				+ ", deleted=" + deleted + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDto other = (EmployeeDto) obj;
		if (employeeIdx != other.employeeIdx)
			return false;
		return true;
	}

}
