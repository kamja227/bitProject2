package com.bit.model.dto;

import java.sql.Date;

public class ClassDto {
	private int rownum;
	private int classIdx;
	private String classTitle;
	private String subjectTitle;
	private int teacherId;
	private Date startDate;
	private Date endDate;
	private int managerId;
	private int classRoom;
	private int cnt;
	private int status;
	private String etc;
	private int deleted;
	
	public ClassDto(){}
	
	public ClassDto(String classTitle, String subjectTitle, int teacherId, Date startDate, Date endDate, int managerId, int classRoom, int status, String etc) {
		this.classTitle = classTitle;
		this.subjectTitle = subjectTitle;
		this.teacherId = teacherId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.managerId = managerId;
		this.classRoom = classRoom;
		this.status = status;
		this.etc = etc;
	}
	
	public ClassDto(int classIdx, String classTitle, String subjectTitle, int teacherId, Date startDate, Date endDate, int managerId, int classRoom, int cnt, int status, String etc, int deleted) {
		this.classIdx = classIdx;
		this.classTitle = classTitle;
		this.subjectTitle = subjectTitle;
		this.teacherId = teacherId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.managerId = managerId;
		this.classRoom = classRoom;
		this.cnt = cnt;
		this.status = status;
		this.etc = etc;
		this.deleted = deleted;
	}

	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public int getClassIdx() {
		return classIdx;
	}
	public void setClassIdx(int classIdx) {
		this.classIdx = classIdx;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getClassTitle() {
		return classTitle;
	}
	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(int classRoom) {
		this.classRoom = classRoom;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "ClassDto [classIdx=" + classIdx + ", classTitle=" + classTitle
				+ ", subjectTitle=" + subjectTitle + ", teacherId=" + teacherId
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", managerId=" + managerId + ", classRoom=" + classRoom
				+ ", cnt=" + cnt + ", status=" + status + ", etc=" + etc
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
		ClassDto other = (ClassDto) obj;
		if (classIdx != other.classIdx)
			return false;
		return true;
	}
	
}
