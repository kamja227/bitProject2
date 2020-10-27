package com.bit.model.dto;

import java.sql.Date;

public class GradeDto {
	private int gradeIdx;
	private int studentIdx;
	private String subjectTitle;
	private Date testDate;
	private String score;
	private String studentName;
	private int deleted;
	
	public GradeDto(){}
	
	public GradeDto(int studentIdx, String subjectTitle, Date testDate, String score) {
		this.studentIdx = studentIdx;
		this.subjectTitle = subjectTitle;
		this.testDate = testDate;
		this.score = score;
	}
	
	public int getGradeIdx() {
		return gradeIdx;
	}
	public void setGradeIdx(int gradeIdx) {
		this.gradeIdx = gradeIdx;
	}
	public int getStudentIdx() {
		return studentIdx;
	}
	public void setStudentIdx(int studentIdx) {
		this.studentIdx = studentIdx;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public String toString() {
		return "GradeDto [gradeIdx=" + gradeIdx + ", studentIdx=" + studentIdx
				+ ", subjectTitle=" + subjectTitle + ", testDate=" + testDate
				+ ", score=" + score + ", deleted=" + deleted + "]";
	}
	
}
