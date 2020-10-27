package com.bit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bit.Mysql.MysqlConnection;
import com.bit.model.dto.ApplicationDto;

public class ApplicationDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<ApplicationDto> getSelectAll() {
		List<ApplicationDto> list= new ArrayList<ApplicationDto>();
		try {
			String sql="select *from Application where deleted=0";
			
			conn=MysqlConnection.getConnection();
		    pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				ApplicationDto bean= new ApplicationDto();
				bean.setApplicationIdx(rs.getInt("applicationIdx"));
				bean.setApplicantIdx(rs.getInt("applicantIdx"));
				bean.setResumeIdx(rs.getInt("resumeIdx"));
				bean.setRecruitmentBoardIdx(rs.getInt("recruitmentBoardIdx"));
				bean.setEnrolledDate(rs.getDate("enrolledDate"));
				bean.setStatus(rs.getString("status"));
				list.add(bean);
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
			
				if(rs!=null)	rs.close();
				if(pstmt!=null)	pstmt.close();
				if(conn!=null)	conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				
		}
		return list;
	} // getSelectAll end
	
	public ApplicationDto selectOne(int applicationIdx){

		ApplicationDto bean= new ApplicationDto();
		try {
			String sql="select application.*, student.name as studentName from Application "
					+ "join Student on application.applicantIdx = student.studentIdx "
					+ "where applicationIdx = ? and student.deleted=0 and application.deleted=0";
			
			conn=MysqlConnection.getConnection();
		    pstmt=conn.prepareStatement(sql);
		    pstmt.setInt(1, applicationIdx);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				bean= new ApplicationDto();
				bean.setApplicationIdx(rs.getInt("applicationIdx"));
				bean.setApplicantIdx(rs.getInt("applicantIdx"));
				bean.setResumeIdx(rs.getInt("resumeIdx"));
				bean.setRecruitmentBoardIdx(rs.getInt("recruitmentBoardIdx"));
				bean.setEnrolledDate(rs.getDate("enrolledDate"));
				bean.setStatus(rs.getString("status"));
				bean.setStudentName(rs.getString("studentName"));
				
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
			
				if(rs!=null)	rs.close();
				if(pstmt!=null)	pstmt.close();
				if(conn!=null)	conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		}
		return bean;
	} // selectOne end
	
	// 지원하기
	public int insertOne(int studentIdx, int resumeIdx, int recruitmentboardIdx) throws SQLException, ClassNotFoundException {
		String sql = "insert into Application(applicantIdx, resumeIdx, recruitmentboardIdx, enrolledDate) values (?, ?, ?, ?)";
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String now = format1.format(time);
		
		System.out.println(studentIdx + ", " + resumeIdx + ", " + recruitmentboardIdx + ", " + now);
		
		try{
			conn=MysqlConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentIdx);
			pstmt.setInt(2, resumeIdx);
			pstmt.setInt(3, recruitmentboardIdx);
			pstmt.setString(4, now);
			
			return pstmt.executeUpdate();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	} // insertOne end
	
	//업데이트 status
	public int updateStatus(String status, int applicationIdx) throws SQLException, ClassNotFoundException{
		String sql = "update application set status=? where deleted=0 and applicationIdx=?";
		
		try{
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, applicationIdx);
			return pstmt.executeUpdate();
			
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
	} // updateStatus end
	
	public List<ApplicationDto> pagingList(int rownum) throws ClassNotFoundException, SQLException{
		String sql="select * from application where deleted=0 order by applicationIdx desc limit ?,10";
		List<ApplicationDto> list = new ArrayList<ApplicationDto>();
		
		try{
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rownum);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ApplicationDto bean= new ApplicationDto();
				bean.setApplicationIdx(rs.getInt("applicationIdx"));
				bean.setApplicantIdx(rs.getInt("applicantIdx"));
				bean.setResumeIdx(rs.getInt("resumeIdx"));
				bean.setRecruitmentBoardIdx(rs.getInt("recruitmentBoardIdx"));
				bean.setEnrolledDate(rs.getDate("enrolledDate"));
				bean.setStatus(rs.getString("status"));
				list.add(bean);
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}
	
	//검색 하려는 네임을 받아 StudentDao에 있는 메서드를 이용하여 StudentIdx를 받아서 파라미터로 넣고, 페이지 시작점 넣기
	// 검색 한 name 10개씩 뽑는 메서드
	public List<ApplicationDto> searchingName(int applicantIdx,int rownum) throws ClassNotFoundException, SQLException{
		String sql="select * from application where deleted=0 and applicantIdx=? order by applicationIdx desc limit ?,10";
		
		List<ApplicationDto> list = new ArrayList<ApplicationDto>();
		try{
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, applicantIdx);
			pstmt.setInt(2, rownum);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ApplicationDto bean= new ApplicationDto();
				bean.setApplicationIdx(rs.getInt("applicationIdx"));
				bean.setApplicantIdx(rs.getInt("applicantIdx"));
				bean.setResumeIdx(rs.getInt("resumeIdx"));
				bean.setRecruitmentBoardIdx(rs.getInt("recruitmentBoardIdx"));
				bean.setEnrolledDate(rs.getDate("enrolledDate"));
				bean.setStatus(rs.getString("status"));
				list.add(bean);
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		return list;
	}
	
	public List<ApplicationDto> searchingAmount(int applicantIdx) throws ClassNotFoundException, SQLException{
		String sql = "select * from application where deleted=0 and applicantIdx=?";
		List<ApplicationDto> list = new ArrayList<ApplicationDto>();
		try{
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, applicantIdx);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ApplicationDto bean= new ApplicationDto();
				bean.setApplicationIdx(rs.getInt("applicationIdx"));
				bean.setApplicantIdx(rs.getInt("applicantIdx"));
				bean.setResumeIdx(rs.getInt("resumeIdx"));
				bean.setRecruitmentBoardIdx(rs.getInt("recruitmentBoardIdx"));
				bean.setEnrolledDate(rs.getDate("enrolledDate"));
				bean.setStatus(rs.getString("status"));
				list.add(bean);
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		
		return list;
	}
}
