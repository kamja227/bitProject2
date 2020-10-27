package com.bit.model.dto;

public class StudentDto {
	private int rownum;
	private int studentIdx;
	private String userId;
	private String password;
	private String name;
	private String contact;
	private String email;
	private int classIdx;
	private String memo;
	private String classTitle;
	private int deleted;
	
	public StudentDto(){}
	
	public StudentDto(String userId, String password, String name, String contact){
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.contact = contact;
	}
	
	public StudentDto(int studentIdx, String userId, String password, String name, String contact, String email, int classIdx, String memo, int deleted) {
		this.studentIdx = studentIdx;
		this.userId = userId; 
		this.password = password;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.classIdx = classIdx;
		this.memo = memo;
		this.deleted = deleted;
	}
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getStudentIdx() {
		return studentIdx;
	}
	public void setStudentIdx(int studentIdx) {
		this.studentIdx = studentIdx;
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
	public int getClassIdx() {
		return classIdx;
	}
	public void setClassIdx(int classIdx) {
		this.classIdx = classIdx;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getClassTitle() {
		return classTitle;
	}
	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}

	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public String toString() {
		return "StudentDto [studentIdx=" + studentIdx + ", userId=" + userId
				+ ", password=" + password + ", name=" + name + ", contact="
				+ contact + ", email=" + email + ", classIdx=" + classIdx
				+ ", memo=" + memo + ", deleted=" + deleted + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDto other = (StudentDto) obj;
		if (studentIdx != other.studentIdx)
			return false;
		return true;
	}
	
	
}
