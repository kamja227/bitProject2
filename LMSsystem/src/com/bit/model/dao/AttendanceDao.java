package com.bit.model.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bit.Mysql.MysqlConnection;
import com.bit.model.dto.AttendanceDto;

public class AttendanceDao {
  
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;


	public int rowStudentDataCnt(int studentIdx) {
	
		int cnt = 0;
		try {
			String sql="select count(*) as cnt from attendance "
					+ " where deleted=0 and studentIdx=? ";
			
			conn=MysqlConnection.getConnection();
		    pstmt=conn.prepareStatement(sql);
		    pstmt.setInt(1, studentIdx);

			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				cnt = rs.getInt("cnt");
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

		return cnt;
	}

	
	public List<AttendanceDto> getAttendanceList(int studentIdx, int pageNo){

		List<AttendanceDto> list= new ArrayList<AttendanceDto>();
		try {
			String sql = "select @RNUM := @RNUM + 1 AS rownum, a.* "
						+ " FROM (SELECT attendDate, attendanceStatus FROM attendance "
						+ " where deleted=0 and studentIdx=? ORDER BY attendanceIdx DESC ) a, "
						+ " ( SELECT @RNUM := 0 ) b order by rownum LIMIT ?, 10 ";
			
			conn=MysqlConnection.getConnection();
		    pstmt=conn.prepareStatement(sql);
		    pstmt.setInt(1, studentIdx);
		    pstmt.setInt(2, 10 * (pageNo - 1));
		    System.out.println(10 * (pageNo - 1));
			rs=pstmt.executeQuery();
			while (rs.next()) {
				AttendanceDto bean= new AttendanceDto();
				bean.setAttendDate(rs.getDate("attendDate"));
				bean.setAttendanceStatus(rs.getString("attendanceStatus"));
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
	
	// 선택한 반, 선택한 날짜의 출석 데이터 가져오기
	public ArrayList<AttendanceDto> getClassAttendanceList(String attendDate, int classIdx){
		   String sql = "select attendance.*, student.name as studentName "
		   		+ " from attendance join student on attendance.studentIdx = student.studentIdx "
		   		+ " where attendance.deleted=0 "
		   		+ " and attendance.studentIdx in (select studentIdx from student where "
		   		+ "	classIdx=? and student.deleted=0) "
		   		+ " and date_format(attendDate, '%Y-%m-%d')=?"
		   		+ " order by studentName";
		   
	      ArrayList<AttendanceDto> list = new ArrayList<AttendanceDto>();
	      try {

				conn=MysqlConnection.getConnection();
			    pstmt=conn.prepareStatement(sql);
	    		pstmt.setInt(1, classIdx);
	    		pstmt.setString(2, attendDate);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
		        	AttendanceDto bean = new AttendanceDto();
		        	
		            bean.setAttendanceIdx(rs.getInt("attendanceIdx"));
		            bean.setStudentIdx(rs.getInt("studentIdx"));
		            bean.setAttendDate(rs.getDate("attendDate"));
		            bean.setAttendanceStatus(rs.getString("attendanceStatus"));
		            bean.setDeleted(rs.getInt("deleted"));
		            bean.setStudentName(rs.getString("studentName"));
		            list.add(bean);
		            
				}
				return list;
		  } catch (ClassNotFoundException e) {
				e.printStackTrace();
	   
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally{
	         try {
	        	if(rs != null) rs.close();
	        	if(pstmt != null) pstmt.close();
	            if(conn!=null)conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      
	      return null;
	   }
	
	// 선택한 반, 선택한 날짜의 출석 데이터 가져오기
		public ArrayList<AttendanceDto> getStudentAttendanceList(int studentIdx){
			   String sql = "select * from Attendance "
			   		+ " where deleted=0 and studentIdx=? order by attendDate desc ";

		      ArrayList<AttendanceDto> list = new ArrayList<AttendanceDto>();
		      try {

					conn=MysqlConnection.getConnection();
				    pstmt=conn.prepareStatement(sql);
		    		pstmt.setInt(1, studentIdx);
					rs = pstmt.executeQuery();
					
					while(rs.next()){
			        	AttendanceDto bean = new AttendanceDto();
			        	
			            bean.setAttendanceIdx(rs.getInt("attendanceIdx"));
			            bean.setStudentIdx(rs.getInt("studentIdx"));
			            bean.setAttendDate(rs.getDate("attendDate"));
			            bean.setAttendanceStatus(rs.getString("attendanceStatus"));
			            bean.setDeleted(rs.getInt("deleted"));
			            list.add(bean);
			            
					}
					return list;
			  } catch (ClassNotFoundException e) {
					e.printStackTrace();
		   
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally{
		         try {
		        	if(rs != null) rs.close();
		        	if(pstmt != null) pstmt.close();
		            if(conn!=null)conn.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		      
		      return null;
		   }
		
	// 학생별 출석률 
	public ArrayList<Integer> getAttendanceCount(int studentIdx){
		
		 String sql = "SELECT " + 
		 		"COUNT(CASE WHEN studentIdx=? THEN 1 END " + 
		 		"AND CASE WHEN deleted=0 THEN 1 END) AS allCnt, " + 
		 		"COUNT(CASE WHEN studentIdx=? THEN 1 END " + 
		 		"AND CASE WHEN deleted=0 THEN 1 END " + 
		 		"AND CASE WHEN attendanceStatus='출석' THEN 1 END) AS attendCnt, " + 
		 		"COUNT(CASE WHEN studentIdx=? THEN 1 END " + 
		 		"AND CASE WHEN deleted=0 THEN 1 END " + 
		 		"AND CASE WHEN attendanceStatus='결석' THEN 1 END) AS absentCnt, " + 
		 		"COUNT(CASE WHEN studentIdx=? THEN 1 END " + 
		 		"AND CASE WHEN deleted=0 THEN 1 END " + 
		 		"AND CASE WHEN (attendanceStatus='지각' or attendanceStatus='조퇴') THEN 1 END) AS halfCnt " + 
		 		"from attendance";
		 		
			   
		 	ArrayList<Integer> AttendanceCount= new ArrayList<Integer>();
		      try {

					conn=MysqlConnection.getConnection();
				    pstmt=conn.prepareStatement(sql);
		    		pstmt.setInt(1, studentIdx);
		    		pstmt.setInt(2, studentIdx);
		    		pstmt.setInt(3, studentIdx);
		    		pstmt.setInt(4, studentIdx);
					rs = pstmt.executeQuery();
					
					if(rs.next()){
						AttendanceCount.add(rs.getInt("allCnt"));
						AttendanceCount.add(rs.getInt("attendCnt"));
						AttendanceCount.add(rs.getInt("absentCnt"));
			        	AttendanceCount.add(rs.getInt("halfCnt"));
			     
					}
					return AttendanceCount;
			  } catch (ClassNotFoundException e) {
					e.printStackTrace();
		   
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally{
		         try {
		        	if(rs != null) rs.close();
		        	if(pstmt != null) pstmt.close();
		            if(conn!=null)conn.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
	
		return AttendanceCount;
	} // getAttendanceCount end
	
	public int insertOne(int studentIdx, String attendDate, String attendanceStatus) throws SQLException {
		String sql = "insert into attendance(studentIdx, attendDate, attendanceStatus)"
				+ " values (?, ?, ?)";
//			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//			Date time = new Date();
//			String now = format1.format(time);
		try{
			conn=MysqlConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentIdx);
			pstmt.setString(2, attendDate);
			pstmt.setString(3, attendanceStatus);
			return pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return 0;

	}
	
	// 출석 상태 수정
	public int updateOne(int attendanceIdx, String attendanceStatus) throws SQLException {
		String sql = "update attendance set attendanceStatus=? where deleted=0 and attendanceIdx=?";
		try{
			conn=MysqlConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attendanceStatus);
			pstmt.setInt(2, attendanceIdx);
			System.out.println("attendanceIdx: " + attendanceIdx + ", attendanceStatus:" + attendanceStatus);
			return pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return 0;
	} // updateOne end

	public int selectCnt(){
		int result = 0;
		ResultSet rs=null;
		String sql = "select count(*) from attendance";
		
		try {
			conn=MysqlConnection.getConnection();
			  pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					result =rs.getInt(1);
				}
		}catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				
				if(rs!=null)	rs.close();
				if(pstmt!=null)	pstmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		}
		return result;

	}
	
	// 한 학생의 총 출석 데이터 개수 
	public int selectCnt(int studentIdx){
		int result = 0;
		ResultSet rs=null;
		String sql = "select count(*) from attendance where deleted=0 and studentIdx=?";
		
		try {
			conn=MysqlConnection.getConnection();
			  pstmt=conn.prepareStatement(sql);
			  pstmt.setInt(1, studentIdx);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					result =rs.getInt(1);
				}
		}catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				
				if(rs!=null)	rs.close();
				if(pstmt!=null)	pstmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		}
		return result;

	}
	
	
}
