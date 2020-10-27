package com.bit.model.dto;

import java.sql.Date;

public class RecruitmentBoardDto {
	private int rownum;
	private int recruitmentboardIdx;
	private int writerIdx;
	private Date writtenDate;
	private String postTitle;
	private String postContent;
	private String fileSrc;
	private String writerName;
	private int deleted;
	
	public RecruitmentBoardDto(){}
	
	public RecruitmentBoardDto(int writerIdx, Date writtenDate, String postTitle, String postContent) {
		this.writerIdx = writerIdx;
		this.writtenDate = writtenDate;
		this.postTitle = postTitle;
		this.postContent = postContent;
	}
	
	public RecruitmentBoardDto(int recruitmentboardIdx, int writerIdx, Date writtenDate, String postTitle, String postContent, int deleted) {
		this.recruitmentboardIdx = recruitmentboardIdx;
		this.writerIdx = writerIdx;
		this.writtenDate = writtenDate;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.deleted = deleted;
	}
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getRecruitmentboardIdx() {
		return recruitmentboardIdx;
	}
	public void setRecruitmentboardIdx(int recruitmentboardIdx) {
		this.recruitmentboardIdx = recruitmentboardIdx;
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
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getFileSrc() {
		return fileSrc;
	}
	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public String toString() {
		return "RecruitmentBoard [recruitmentboardIdx=" + recruitmentboardIdx
				+ ", writerIdx=" + writerIdx + ", writtenDate=" + writtenDate
				+ ", postTitle=" + postTitle + ", postContent=" + postContent
				+ ", fileSrc=" + fileSrc + ", deleted=" + deleted + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecruitmentBoardDto other = (RecruitmentBoardDto) obj;
		if (recruitmentboardIdx != other.recruitmentboardIdx)
			return false;
		return true;
	}
	
	
}
