package com.bit.model.dto;

import java.sql.Date;

public class ApplicationDto {
	private int applicationIdx;
	private int applicantIdx;
	private int resumeIdx;
	private int recruitmentBoardIdx;
	private Date enrolledDate;
	private String status;
	private String studentName;
	private int deleted;
	
	public ApplicationDto(){}
	
	public ApplicationDto(int applicantIdx, int resumeIdx) {
		this.applicantIdx = applicantIdx;
		this.resumeIdx = resumeIdx;
	}
	
	public ApplicationDto(int applicationIdx, int applicantIdx, int recruitmentBoardIdx, int resumeIdx, Date enrolledDate, String status, int deleted) {
		this.applicationIdx = applicationIdx;
		this.applicantIdx = applicantIdx;
		this.resumeIdx = resumeIdx;
		this.recruitmentBoardIdx = recruitmentBoardIdx;
		this.enrolledDate = enrolledDate;
		this.status = status;
		this.deleted = deleted;
		
	}
	
	public ApplicationDto(int applicationIdx, int applicantIdx, int resumeIdx, Date enrolledDate, String studentName, String status, int deleted) {
		this.applicationIdx = applicationIdx;
		this.applicantIdx = applicantIdx;
		this.resumeIdx = resumeIdx;
		this.enrolledDate = enrolledDate;
		this.status = status;
		this.studentName = studentName;
		this.deleted = deleted;
	}

	public int getRecruitmentBoardIdx() {
		return recruitmentBoardIdx;
	}

	public void setRecruitmentBoardIdx(int recruitmentBoardIdx) {
		this.recruitmentBoardIdx = recruitmentBoardIdx;
	}

	public int getApplicationIdx() {
		return applicationIdx;
	}

	public void setApplicationIdx(int applicationIdx) {
		this.applicationIdx = applicationIdx;
	}

	public int getApplicantIdx() {
		return applicantIdx;
	}

	public void setApplicantIdx(int applicantIdx) {
		this.applicantIdx = applicantIdx;
	}

	public int getResumeIdx() {
		return resumeIdx;
	}

	public void setResumeIdx(int resumeIdx) {
		this.resumeIdx = resumeIdx;
	}

	public Date getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "ApplicationDto [applicationIdx=" + applicationIdx
				+ ", applicantIdx=" + applicantIdx + ", resumeIdx=" + resumeIdx
				+ ", recruitmentBoardIdx=" + recruitmentBoardIdx
				+ ", enrolledDate=" + enrolledDate + ", status=" + status
				+ ", studentName=" + studentName + ", deleted=" + deleted + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationDto other = (ApplicationDto) obj;
		if (applicantIdx != other.applicantIdx)
			return false;
		if (applicationIdx != other.applicationIdx)
			return false;
		if (deleted != other.deleted)
			return false;
		if (enrolledDate == null) {
			if (other.enrolledDate != null)
				return false;
		} else if (!enrolledDate.equals(other.enrolledDate))
			return false;
		if (resumeIdx != other.resumeIdx)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	
}
