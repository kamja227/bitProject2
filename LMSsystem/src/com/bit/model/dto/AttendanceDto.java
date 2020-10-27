package com.bit.model.dto;

import java.sql.Date;

public class AttendanceDto {
	private int attendanceIdx;
	private int studentIdx;
	private Date attendDate;
	private String attendanceStatus;
	private String studentName;
	private Double attendanceRate;
	private int deleted;
	
	public AttendanceDto(){}
	
	public AttendanceDto(int studentIdx, Date attendDate, String attendanceStatus) {
		this.studentIdx = studentIdx;
		this.attendDate = attendDate;
		this.attendanceStatus = attendanceStatus;
	}
	
	public AttendanceDto(int attendanceIdx, int studentIdx, Date attendDate, String attendanceStatus, int deleted) {
		this.attendanceIdx = attendanceIdx;
		this.studentIdx = studentIdx;
		this.attendDate = attendDate;
		this.attendanceStatus = attendanceStatus;
		this.deleted = deleted;
	}
	
	public int getAttendanceIdx() {
		return attendanceIdx;
	}
	public void setAttendanceIdx(int attendanceIdx) {
		this.attendanceIdx = attendanceIdx;
	}
	public int getStudentIdx() {
		return studentIdx;
	}
	public void setStudentIdx(int studentIdx) {
		this.studentIdx = studentIdx;
	}
	public Date getAttendDate() {
		return attendDate;
	}
	public void setAttendDate(Date attendDate) {
		this.attendDate = attendDate;
	}
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Double getAttendanceRate() {
		return attendanceRate;
	}

	public void setAttendanceRate(Double attendanceRate) {
		this.attendanceRate = attendanceRate;
	}

	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public String toString() {
		return "AttendanceDto [attendanceIdx=" + attendanceIdx
				+ ", studentIdx=" + studentIdx + ", attendDate=" + attendDate
				+ ", attendanceStatus=" + attendanceStatus + ", deleted="
				+ deleted + "]";
	}
	
}
