package com.bit.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bit.Mysql.MysqlConnection;
import com.bit.model.dto.StudentDto;

public class StudentDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static final int PAGE_SIZE = 5;
	
	public StudentDao() throws SQLException{
		
		try { // jdbc connect j 라이브러리 로딩 예외 처리
			 Class.forName("com.mysql.cj.jdbc.Driver"); // 해당 클래스가 메모리에 로드 및 실행
	          String url = "jdbc:mysql://localhost:3306/lmssystem?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
//	         String url = "jdbc:mysql://192.168.0.216:2080/lmssystem?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	          String user = "root";
//	          String user = "server3zo";
	         String password = "123456";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
		} 
		
	}
	
	public StudentDto login(String userId, String password) {
		String sql = "select * from Student where deleted=0 and userId=? and password=?";
		StudentDto bean = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean = new StudentDto();
				bean.setStudentIdx(rs.getInt("studentIdx"));
				bean.setUserId(rs.getString("userId"));
				bean.setPassword(rs.getString("password"));
				bean.setName(rs.getString("name"));
				bean.setContact(rs.getString("contact"));
				bean.setEmail(rs.getString("email"));
				bean.setClassIdx(rs.getInt("classIdx"));
				bean.setMemo(rs.getString("memo"));
				bean.setDeleted(rs.getInt("deleted"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bean;
	} // login end
	
	
	public ArrayList<StudentDto> selectAll(){		
		ArrayList<StudentDto> list = new ArrayList<StudentDto>();
		String sql = "select * from Student where deleted = 0 order by studentIdx asc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				list.add(new StudentDto(rs.getInt("studentIdx"), rs.getString("userId"), rs.getString("password"), 
						rs.getString("name"), rs.getString("contact"), rs.getString("email"), 
						rs.getInt("classIdx"), rs.getString("memo"), rs.getInt("deleted")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	} // selectAll end
	
	
	public int countJoinAll(String search1, String search2 ) {
		String sql = "select count(*) as cnt from Student "
				+ "join Class on student.classIdx = class.classIdx "
				+ "where Student.deleted=0 and Class.deleted=0 "
				+ search1 + " "
				+ search2 + " ";
		int cnt = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) cnt = rs.getInt("cnt");
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}
	public ArrayList<StudentDto> selectJoinAll(String search1, String search2, int pageNo) {
		String sql = "select @RNUM := @RNUM + 1 AS rownum, a.* "
				+ " FROM (SELECT Student.*, class.classTitle as classTitle from Student "
				+ "join Class on student.classIdx = class.classIdx "
				+ "where Student.deleted=0 and Student.classIdx<>0 and Class.deleted=0"
				+ search1 + " "
				+ search2 + " "
				+ "order by name asc) a, "
				+ " ( SELECT @RNUM := 0 ) b order by rownum LIMIT ?, 20 ";
		ArrayList<StudentDto> list = new ArrayList<StudentDto>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 20 * (pageNo-1));
			rs = pstmt.executeQuery();
		
			while(rs.next()){
				StudentDto bean = new StudentDto();
				bean.setStudentIdx(rs.getInt("studentIdx"));
				bean.setUserId(rs.getString("userId"));
				bean.setPassword(rs.getString("password"));
				bean.setName(rs.getString("name"));
				bean.setContact(rs.getString("contact"));
				bean.setEmail(rs.getString("email"));
				bean.setClassIdx(rs.getInt("classIdx"));
				bean.setMemo(rs.getString("memo"));
				bean.setDeleted(rs.getInt("deleted"));
				bean.setClassTitle(rs.getString("classTitle"));
				
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	} // selectJoinAll end
	
	// 반별 학생 데이터 가져오기
	public ArrayList<StudentDto> classAll(int classIdx){
		ArrayList<StudentDto> list = new ArrayList<StudentDto>();
		String sql = "select * from Student where deleted = 0 and classIdx=? order by name";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classIdx);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				list.add(new StudentDto(rs.getInt("studentIdx"), rs.getString("userId"), rs.getString("password"), 
						rs.getString("name"), rs.getString("contact"), rs.getString("email"), 
						rs.getInt("classIdx"), rs.getString("memo"), rs.getInt("deleted")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	} // classAll end
	
	public StudentDto selectOne(int studentIdx){
		StudentDto bean = null;
		String sql = "select Student.*, class.classTitle as classTitle from Student join Class on Student.classIdx = Class.classIdx where student.deleted = 0 and class.deleted=0 and studentIdx = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentIdx);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bean = new StudentDto(rs.getInt("studentIdx"), rs.getString("userId"), rs.getString("password"), 
						rs.getString("name"), rs.getString("contact"), rs.getString("email"), 
						rs.getInt("classIdx"), rs.getString("memo"), rs.getInt("deleted"));
				bean.setClassTitle(rs.getString("classTitle"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bean;
	} // selectOne end
	
	// 정보수정 및 학생 관리 수정
	public int updateOne(String name, String contact, String memo, int studentIdx) throws SQLException {
		String sql = "update Student set name=?, contact=?, memo=? "
				+ " where studentIdx=?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name.trim());
			pstmt.setString(2, contact.trim());
			pstmt.setString(3, memo);
			pstmt.setInt(4, studentIdx);
			
			return pstmt.executeUpdate();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	} // updateOne end
	
	
	//마이페이지 학생정보수정
	public int updateinfoOne(String name,String contact,String email,String password,int studentIdx){
		String sql ="update student set name=?,contact=?,email=?,password=? where studentIdx=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, contact);
			pstmt.setString(3, email);
			pstmt.setString(4,password);
			pstmt.setInt(5, studentIdx);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(conn != null)conn.close();
					if(pstmt != null) pstmt.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
		}
		return -1;
		
	} // updateinfoOne end
	//마이페이지 학생정보수정 end
	
	//마이페이지 학생정보수정 디폴트값
	   public ArrayList<StudentDto> getStudentInfo(int studentIdx){
		 String sql="select name,contact,email,password from student where studentIdx=?";
		 
		 ArrayList<StudentDto> list=new ArrayList<StudentDto>();  
		 try {
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,studentIdx);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StudentDto bean=new StudentDto();
				bean.setName(rs.getString("name"));
				bean.setContact((rs.getString("contact")));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				list.add(bean);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	   } // getStudentInfo end
	 //마이페이지 학생정보수정 디폴트값 end
	
	   
	// 회원 가입
	public int insertOne(String userId, String password, String name, String contact, String email) throws SQLException {
		String sql = "insert into Student(userId, password, name, contact, email) values (?, ?, ?, ?, ?)";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, contact);
			pstmt.setString(5, email);
			
			return pstmt.executeUpdate();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	} // insertOne end
	
	
	//회원가입 아이디중복검사
		public int joinIdCheck(String userId) {
			String sql="select userId from student where userId=?";
			
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					
					return 1;
				}
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

			return -1;
			
		}
		// joinIdCheckend
		
	
	// 수강신청
	public int enrollClass(int studentIdx, int classIdx) throws SQLException {
			String sql = "update Student set classIdx=? where deleted=0 and studentIdx=?";
			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, classIdx);
				pstmt.setInt(2, studentIdx);
				
				return pstmt.executeUpdate();
			}finally{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}
		} // enrolledClass end
	
	
	// 수강취소
	public int cancelClass(int studentIdx) throws SQLException {
		String sql = "update Student set classIdx=0 where deleted=0 and studentIdx=?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentIdx);
			
			return pstmt.executeUpdate();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	} // enrolledClass end
		
	
	public int rowCount() throws SQLException{
		String sql = "select count(*) as cnt from Student where deleted=0";
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
	
	// 아이디 찾기
	public String findUserId(String name, String contact) {
		String sql = "select userId from Student where deleted=0 and name=? and contact=?";
		String userId = "";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, contact);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				userId = rs.getString("userId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userId;
	} // findUserId end
	
	// 비밀번호 찾기
	public String findPassword(String id, String name, String contact) {
		String sql = "select userId from Student where deleted=0 and userId=? name=? and contact=?";
		String userId = "";
		try {
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, contact);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				userId = rs.getString("userId");
				return userId;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userId;
	}
	
	public int updatePassword(String userId, String newPassword) throws SQLException {
		String sql = "update Student set password=? where deleted=0 and userId=?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(1, userId);
			
			return pstmt.executeUpdate();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	} // findPassword end
	
	// 회원 탈퇴
	public int deleteOne(int studentIdx) throws SQLException {
		String sql = "update Student set deleted=1 where deleted=0 and studentIdx=?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentIdx);
			
			return pstmt.executeUpdate();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	
	
	// 이름 검색 결과
	public int searchingResult(String name) throws SQLException{
		String sql= "select studentIdx from student where deleted=0 and name=?";
		int studentIdx=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()){
				studentIdx=rs.getInt("studentIdx");
			}
		}finally{
			
		}
		return studentIdx;
		
	}

	
}
