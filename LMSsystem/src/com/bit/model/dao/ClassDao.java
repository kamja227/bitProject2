package com.bit.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bit.model.dto.ClassDto;
   
public class ClassDao {
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   public ClassDao() throws SQLException{
		
		try { // jdbc connect j 라이브러리 로딩 예외 처리
			 Class.forName("com.mysql.cj.jdbc.Driver"); // 해당 클래스가 메모리에 로드 및 실행
			 String url = "jdbc:mysql://localhost:3306/lmssystem?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
//	         String url = "jdbc:mysql://192.168.0.216:2080/lmssystem?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	          String user = "root";
//	         String user = "server3zo";
	         String password = "123456";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
		} 
		
	}
   
   public ArrayList<ClassDto> selectAll() {
	   String sql = "SELECT @RNUM := @RNUM + 1 AS rownum, a.* "
			   	+ " FROM (SELECT * FROM class where deleted=0 ORDER BY classIdx) a, "
			   	+ " ( SELECT @RNUM := 0 ) b";
	   
	   ArrayList<ClassDto> list = new ArrayList<ClassDto>();
	      try {
	    	  pstmt = conn.prepareStatement(sql);
	    	  rs = pstmt.executeQuery();
	          while(rs.next()){
	             ClassDto bean = new ClassDto();
	             bean.setClassIdx(rs.getInt("classIdx"));
	             bean.setClassTitle(rs.getString("classTitle"));
	             bean.setSubjectTitle(rs.getString("subjectTitle"));
	             bean.setStartDate(rs.getDate("startDate"));
	             bean.setEndDate(rs.getDate("endDate"));
	             bean.setCnt(rs.getInt("cnt"));
	             bean.setStatus(rs.getInt("status"));
	             bean.setRownum(rs.getInt("rownum"));
	             list.add(bean);
	          }
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
	       
	       return list;
    }
   
   public ArrayList<ClassDto> selectAll(int pageNo){
	   String sql = "select @RNUM := @RNUM + 1 AS rownum, a.* "
				+ " FROM (SELECT * FROM class where deleted=0 ORDER BY classIdx) a, "
				+ " ( SELECT @RNUM := 0 ) b order by rownum LIMIT ?, 20 ";
	  
      ArrayList<ClassDto> list = new ArrayList<ClassDto>();
      try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, 20 * (pageNo-1));

			rs = pstmt.executeQuery();
         while(rs.next()){
            ClassDto bean = new ClassDto();
            bean.setClassIdx(rs.getInt("classIdx"));
            bean.setClassTitle(rs.getString("classTitle"));
            bean.setSubjectTitle(rs.getString("subjectTitle"));
            bean.setStartDate(rs.getDate("startDate"));
            bean.setEndDate(rs.getDate("endDate"));
            bean.setCnt(rs.getInt("cnt"));
            bean.setStatus(rs.getInt("status"));
            bean.setRownum(rs.getInt("rownum"));
            list.add(bean);
         }
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
      
      return list;
   }

   public ArrayList<ClassDto> selectAlldesc(){
	   String sql = "select A.* from ( SELECT @rownum := @rownum + 1 AS rownum, class.* from class, (select @rownum := 0) r ) A where deleted=0 order by classIdx desc";
//      String sql="select @rownum=@rownum+1 as rownum, * from class where deleted=0 order by startDate";
	   
	   ArrayList<ClassDto> list = new ArrayList<ClassDto>();
	   try {
		   pstmt = conn.prepareStatement(sql);
		   rs = pstmt.executeQuery();
		   while(rs.next()){
			   ClassDto bean = new ClassDto();
			   bean.setClassIdx(rs.getInt("classIdx"));
			   bean.setClassTitle(rs.getString("classTitle"));
			   bean.setSubjectTitle(rs.getString("subjectTitle"));
			   bean.setStartDate(rs.getDate("startDate"));
			   bean.setEndDate(rs.getDate("endDate"));
			   bean.setCnt(rs.getInt("cnt"));
			   bean.setStatus(rs.getInt("status"));
			   bean.setRownum(rs.getInt("rownum"));
			   list.add(bean);
		   }
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
	   
	   return list;
   }
   
   public ClassDto selectOne(int classIdx){
	      String sql="select * from class where deleted=0 and classIdx=?";
	      ClassDto bean = null;
	      
	      try {
	    		pstmt = conn.prepareStatement(sql);
	    		pstmt.setInt(1, classIdx);
				rs = pstmt.executeQuery();
	         if(rs.next()){
	            bean = new ClassDto(rs.getInt("classIdx"), rs.getString("classTitle"), rs.getString("subjectTitle"), 
	            		rs.getInt("teacherId"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getInt("managerId"), 
	            		rs.getInt("classRoom"), rs.getInt("cnt"), rs.getInt("status"), rs.getString("etc"), rs.getInt("deleted"));
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally{
	         try {
	        	if(rs != null) rs.close();
	        	if(pstmt != null) pstmt.close();
	            if(conn != null)conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      
	      return bean;
  } // selectOne end
   
// 정보수정 및 학생 관리 수정
	public int  updateOne(int classIdx, String classTitle, String subjectTitle, int teacherId, Date startDate, 
			Date endDate, int managerId, int classRoom, int status, String etc) throws SQLException {
		String sql = "update Class set classTitle=?, subjectTitle=?, teacherId=?, startDate=?, endDate=?, managerId=?, "
				+ "classRoom=?, status=?, etc=? where classIdx=?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classTitle);
			pstmt.setString(2, subjectTitle);
			pstmt.setInt(3, teacherId);
			pstmt.setDate(4, startDate);
			pstmt.setDate(5, endDate);
			pstmt.setInt(6, managerId);
			pstmt.setInt(7, classRoom);
			pstmt.setInt(8, status);
			pstmt.setString(9, etc);
			pstmt.setInt(10, classIdx);
			
			return pstmt.executeUpdate();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	} // updateOne end
	
	// 교육 등록
	public int insertOne(String classTitle, String subjectTitle, int teacherId, Date startDate, Date endDate, 
			int managerId, int classRoom, int status, String etc) throws SQLException {
		String sql = "insert into Class(classTitle, subjectTitle, teacherId, startDate, endDate, "
				+ "managerId, classRoom, status, etc) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classTitle);
			pstmt.setString(2, subjectTitle);
			pstmt.setInt(3, teacherId);
			pstmt.setDate(4, startDate);
			pstmt.setDate(5, endDate);
			pstmt.setInt(6, managerId);
			pstmt.setInt(7, classRoom);
			pstmt.setInt(8, status);
			pstmt.setString(9, etc);
			
			return pstmt.executeUpdate();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	} // insertOne end
	
	// 수강신청시, cnt 칼럼 값 증가
	public int enrolledStudent(int classIdx, int cnt) throws SQLException {
		String sql = "update Class set cnt=cnt+1 where deleted=0 and classIdx=?";
		if(cnt >= 29) {
			sql = "update Class set cnt=30, status=2 where deleted=0 and classIdx=?";
		}
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classIdx);
			
			return pstmt.executeUpdate();
		} finally{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	} // enrolledStudent end
	
	// 수강취소시, cnt 칼럼 값 감소
	public int canceledStudent(int classIdx, int cnt) throws SQLException {
		String sql = "update Class set cnt=cnt-1 where deleted=0 and classIdx=?";
		if(cnt <= 1) return -1;
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classIdx);
			return pstmt.executeUpdate();
		} finally{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	} // canceledStudent end
	
	public int rowCount() throws SQLException{
		String sql = "select count(*) as cnt from Class where deleted=0";
		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getInt("cnt");
		} finally{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return 0;
	} // rowCount end
	
	
	//아예 다 바꿔버리는거니 조심히 사용해주세요~ 삭제할 classIdx와 그에 해당하는 teacherId,managerId,roomNum 넣어주세요
	public int updateDeleted(int classIdx,int teacherId,int managerId,int roomNum) throws SQLException{
		String sql1="update class set deleted=1,classRoom=0,teacherId=0,managerId=0 where classIdx=?";
		String sql2="update employee set manageclass=0 where employeeIdx=?";
		String sql3="update employee set manageclass=0 where employeeIdx=?";
		String sql4="update classroom set inUse=1 where roomNum=?";
			pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, classIdx);
			int result=pstmt.executeUpdate();
			pstmt.close();
			pstmt=conn.prepareStatement(sql2);
			pstmt.setInt(1, teacherId);
			result+=pstmt.executeUpdate();
			pstmt.close();
			pstmt=conn.prepareStatement(sql3);
			pstmt.setInt(1, managerId);
			result+=pstmt.executeUpdate();
			pstmt.close();
			pstmt=conn.prepareStatement(sql4);
			pstmt.setInt(1, roomNum);
			return result+=pstmt.executeUpdate();
	}
		
		
		public List<ClassDto> pagingList(int rownum) throws SQLException{
			String sql = "select * from class where deleted=0 order by classIdx desc limit ?,10";
			List<ClassDto> list = new ArrayList<ClassDto>();
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, rownum);
				rs=pstmt.executeQuery();
				while(rs.next()){
					ClassDto bean = new ClassDto();
		            bean.setClassIdx(rs.getInt("classIdx"));
		            bean.setClassTitle(rs.getString("classTitle"));
		            bean.setSubjectTitle(rs.getString("subjectTitle"));
		            bean.setStartDate(rs.getDate("startDate"));
		            bean.setEndDate(rs.getDate("endDate"));
		            bean.setCnt(rs.getInt("cnt"));
		            bean.setStatus(rs.getInt("status"));
		            list.add(bean);
				}
				
			}finally{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}
			return list;
		}	
}