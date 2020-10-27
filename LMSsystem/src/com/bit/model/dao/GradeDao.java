package com.bit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bit.Mysql.MysqlConnection;
import com.bit.model.dto.GradeDto;

public class GradeDao {

private Connection conn;	
private PreparedStatement pstmt;	
private ResultSet rs;
	
	public List<GradeDto>getGradeList( int studentIdx){
		 
		List<GradeDto> list= new ArrayList<GradeDto>();
		
		try {
			String sql="select testDate,subjectTitle,score from grade where studentIdx=?";
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,studentIdx);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				GradeDto bean =new GradeDto();
				bean.setTestDate(rs.getDate("testDate"));
				bean.setSubjectTitle(rs.getString("subjectTitle"));
				bean.setScore(rs.getString("score"));
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
	}
	
	public ArrayList<GradeDto> classAll(int classIdx) {
		String sql = "select Grade.*, student.name as studentName from Grade "
				+ "join Student on grade.studentIdx = student.studentIdx "
				+ "where Grade.deleted=0 and student.deleted=0 and student.classIdx=? "
				+ "order by testDate desc, studentName asc";
		
		ArrayList<GradeDto> list = new ArrayList<GradeDto>();
		try {
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,classIdx);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				GradeDto bean =new GradeDto();
				bean.setGradeIdx(rs.getInt("gradeIdx"));
				bean.setStudentIdx(rs.getInt("studentIdx"));
				bean.setSubjectTitle(rs.getString("subjectTitle"));
				bean.setTestDate(rs.getDate("testDate"));
				bean.setScore(rs.getString("score"));
				bean.setStudentName(rs.getString("studentName"));
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
	}

	// 성적 입력
	public int insertOne(int studentIdx, String subjectTitle, String testDate, String score) throws SQLException {
		String sql = "Insert into Grade(studentIdx, subjectTitle, testDate, score) values (?, ?, ?, ?)";
		try{
			conn=MysqlConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentIdx);
			pstmt.setString(2, subjectTitle);
			pstmt.setString(3, testDate);
			pstmt.setString(4, score);
			return pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return 0;
	} // insertOne end
	
	// 성적 수정
	public int updateOne(int gradeIdx, String subjectTitle, String testDate, String score) throws SQLException {
		String sql = "update Grade set subjectTitle=?, testDate=?, score=? where deleted=0 and gradeIdx=?";
		try{
			conn=MysqlConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subjectTitle);
			pstmt.setString(2, testDate);
			pstmt.setString(3, score);
			pstmt.setInt(4, gradeIdx);
//			System.out.println("attendanceIdx: " + attendanceIdx + ", attendanceStatus:" + attendanceStatus);
			return pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return 0;
	} // updateOne end
}
