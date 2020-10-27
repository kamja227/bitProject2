package com.bit.model.dto;

import java.sql.Date;

public class ResumeDto {
	private int rownum;
	private int resumeIdx;
	private int writerIdx;
	private Date writtenDate;
	private String resumeTitle;
	private String resumeContent;
	private int deleted;
	
	public ResumeDto() {}
	
	public ResumeDto(int writerIdx, String resumeTitle, String resumeContent) {
		this.writerIdx = writerIdx;
		this.resumeTitle = resumeTitle;
		this.resumeContent = resumeContent;
	}
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getResumeIdx() {
		return resumeIdx;
	}
	public void setResumeIdx(int resumeIdx) {
		this.resumeIdx = resumeIdx;
	}
	public int getWriterIdx() {
		return writerIdx;
	}
	public void setWriterIdx(int writerIdx) {
		this.writerIdx = writerIdx;
	}
	public Date getWrittenDate() {
		return writtenDate;
	}
	public void setWrittenDate(Date writtenDate) {
		this.writtenDate = writtenDate;
	}
	public String getResumeTitle() {
		return resumeTitle;
	}
	public void setResumeTitle(String resumeTitle) {
		this.resumeTitle = resumeTitle;
	}
	public String getResumeContent() {
		return resumeContent;
	}
	public void setResumeContent(String resumeContent) {
		this.resumeContent = resumeContent;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public String toString() {
		return "ResumeDto [resumeIdx=" + resumeIdx + ", writerIdx=" + writerIdx
				+ ", writtenDate=" + writtenDate + ", resumeTitle="
				+ resumeTitle + ", resumeContent=" + resumeContent
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
		ResumeDto other = (ResumeDto) obj;
		if (deleted != other.deleted)
			return false;
		if (resumeContent == null) {
			if (other.resumeContent != null)
				return false;
		} else if (!resumeContent.equals(other.resumeContent))
			return false;
		if (resumeIdx != other.resumeIdx)
			return false;
		if (resumeTitle == null) {
			if (other.resumeTitle != null)
				return false;
		} else if (!resumeTitle.equals(other.resumeTitle))
			return false;
		if (writerIdx != other.writerIdx)
			return false;
		if (writtenDate == null) {
			if (other.writtenDate != null)
				return false;
		} else if (!writtenDate.equals(other.writtenDate))
			return false;
		return true;
	}
	
	
}
