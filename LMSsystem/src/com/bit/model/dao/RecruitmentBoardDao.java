package com.bit.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bit.Mysql.MysqlConnection;
import com.bit.model.dto.RecruitmentBoardDto;

public class RecruitmentBoardDao {
	
	private Connection conn;	
	private PreparedStatement pstmt;	
	private ResultSet rs;
	
	// 모집 공고 전체 리스트 가져오기 (no paging)
	public ArrayList<RecruitmentBoardDto> selectAll() {
		 String sql = "SELECT @RNUM := @RNUM + 1 AS rownum, a.* "
				   	+ " FROM (SELECT Recruitmentboard.*, Employee.name as writerName "
				   	+ "FROM Recruitmentboard join Employee on writerIdx = employeeIdx "
				   	+ "where recruitmentboard.deleted=0 and employee.deleted=0 "
				   	+ "ORDER BY recruitmentboardIdx DESC  ) a, "
				   	+ " ( SELECT @RNUM := 0 ) b";
		ArrayList<RecruitmentBoardDto> list = new ArrayList<RecruitmentBoardDto>();
		try {
			
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				RecruitmentBoardDto bean =new RecruitmentBoardDto();
				bean.setRecruitmentboardIdx(rs.getInt("recruitmentboardIdx"));
				bean.setWriterIdx(rs.getInt("writerIdx"));
				bean.setWrittenDate(rs.getDate("writtenDate"));
				bean.setPostTitle(rs.getString("postTitle"));
				bean.setPostContent(rs.getString("postContent"));
				bean.setDeleted(rs.getInt("deleted"));
				bean.setWriterName(rs.getString("writerName"));
				bean.setRownum(rs.getInt("rownum"));
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
	} // selectAll end
	
	
	// 모집 공고 전체 (메인 페이지 - 모집 공고 목록 페이지 with paging)
		public ArrayList<RecruitmentBoardDto> selectAll(int pageNo) {
			
			 String sql = "select @RNUM := @RNUM + 1 AS rownum, a.* "
						+ " FROM ( SELECT Recruitmentboard.*, Employee.name as writerName "
						+ " FROM Recruitmentboard join Employee on writerIdx = employeeIdx "
						+ " where recruitmentboard.deleted=0 and employee.deleted=0 "
						+ " ORDER BY recruitmentboardIdx DESC ) a, "
						+ " ( SELECT @RNUM := 0 ) b order by rownum LIMIT ?, 20";
			 
			ArrayList<RecruitmentBoardDto> list = new ArrayList<RecruitmentBoardDto>();
			
			try {
				conn = MysqlConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 20 * (pageNo-1));
				rs = pstmt.executeQuery(); 
				while(rs.next()) {
					RecruitmentBoardDto bean =new RecruitmentBoardDto();
					bean.setRecruitmentboardIdx(rs.getInt("recruitmentboardIdx"));
					bean.setWriterIdx(rs.getInt("writerIdx"));
					bean.setWrittenDate(rs.getDate("writtenDate"));
					bean.setPostTitle(rs.getString("postTitle"));
					bean.setPostContent(rs.getString("postContent"));
					bean.setDeleted(rs.getInt("deleted"));
					bean.setWriterName(rs.getString("writerName"));
					bean.setRownum(rs.getInt("rownum"));
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
		} // selectAll(int pageNo) end
		
		
	
	// 모집 공고 상세 (메인 페이지 - 모집 공고 상세 페이지 recruitDetail.bit에서 사용)
	public RecruitmentBoardDto selectOne(int recruitmentboardIdx) {
		RecruitmentBoardDto bean = null;
		String sql="select RecruitmentBoard.*, employee.name as writerName from RecruitmentBoard join employee on writerIdx=employeeIdx "
				+ " where recruitmentboard.deleted=0 and employee.deleted=0 and recruitmentboardIdx=?";
		try {
			
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, recruitmentboardIdx);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				bean =new RecruitmentBoardDto();
				bean.setRecruitmentboardIdx(rs.getInt("recruitmentboardIdx"));
				bean.setWriterIdx(rs.getInt("writerIdx"));
				bean.setWrittenDate(rs.getDate("writtenDate"));
				bean.setPostTitle(rs.getString("postTitle"));
				bean.setPostContent(rs.getString("postContent"));
				bean.setDeleted(rs.getInt("deleted"));
				bean.setWriterName(rs.getString("writerName"));
				bean.setFileSrc(rs.getString("fileSrc")); // 첨부파일명
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
	
	// 한 계정의 이력서 전체 (myResumeDetail.bit)
	public List<RecruitmentBoardDto>getSelectAll(int studentIdx){
		 
		List<RecruitmentBoardDto> list= new ArrayList<RecruitmentBoardDto>();
		
		try {
			String sql="select * from RecruitmentBoard where deleted=0 and studentIdx=? order by recruitmentboardIdx desc";
			
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,studentIdx);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				RecruitmentBoardDto bean =new RecruitmentBoardDto();
				bean.setRecruitmentboardIdx(rs.getInt("recruitmentboardIdx"));
				bean.setWriterIdx(rs.getInt("writerIdx"));
				bean.setWrittenDate(rs.getDate("writtenDate"));
				bean.setPostTitle(rs.getString("postTitle"));
				bean.setPostContent(rs.getString("postContent"));
				bean.setDeleted(rs.getInt("deleted"));
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
	
	
	//모집 공고 등록 매서드 입니다. 파라미터로 employeeIdx 넣어주시고(세션에서 받아서) postTitle,postContent 넣어주세요.
	public int insertRecruit(int writerIdx, String postTitle,String postContent, String fileSrc) throws ClassNotFoundException, SQLException{
		String sql="INSERT INTO recruitmentboard (writerIdx, writtenDate, postTitle, postContent, fileSrc)"
				+ " VALUES (? ,now(), ?, ?, ?)";
		
		if(fileSrc.length() == 0) {
			fileSrc = null;
		}
		
		try{
			
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, writerIdx);
			pstmt.setString(2, postTitle);
			pstmt.setString(3, postContent);
			pstmt.setString(4, fileSrc);
			return pstmt.executeUpdate();
			
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
	} // insertRecruit end
		
	// 모집공고 수정
	public int updateRecruit(int writerIdx, String postTitle, String postContent, Date writtenDate, String fileSrc, int recruitmentboardIdx) throws ClassNotFoundException, SQLException{
		String sql="update recruitmentboard set writerIdx=?, postTitle=?, postContent=?, writtenDate=?, fileSrc=? where recruitmentboardIdx=?";
		
		if(fileSrc.length() == 0) {
			fileSrc = null;
		}
		
		try{
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, writerIdx);
			pstmt.setString(2, postTitle);
			pstmt.setString(3, postContent);
			pstmt.setDate(4, writtenDate);
			pstmt.setNString(5, fileSrc);
			pstmt.setInt(6, recruitmentboardIdx);
			
			return pstmt.executeUpdate();
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}		
	} // updateRecruit end
	
	
	public List<RecruitmentBoardDto> pagingList(int rownum) throws ClassNotFoundException, SQLException{
		String sql="select * from recruitmentboard "
				+ "where deleted=0 order by recruitmentboardIdx desc limit ?,10";
		List<RecruitmentBoardDto> list = new ArrayList<RecruitmentBoardDto>();
		try{
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rownum);
			rs=pstmt.executeQuery();
			while(rs.next()){
				RecruitmentBoardDto bean =new RecruitmentBoardDto();
				bean.setRecruitmentboardIdx(rs.getInt("recruitmentboardIdx"));
				bean.setWriterIdx(rs.getInt("writerIdx"));
				bean.setWrittenDate(rs.getDate("writtenDate"));
				bean.setPostTitle(rs.getString("postTitle"));
				bean.setPostContent(rs.getString("postContent"));
				list.add(bean);
			}
			
		}finally{
			if(rs!=null)	rs.close();
			if(pstmt!=null)	pstmt.close();
			if(conn!=null)	conn.close();
		}
		return list;
	}
	
	// 새로 생길 데이터의 idx 값 가져오기
	public int getTopIdx() throws ClassNotFoundException, SQLException {
		String sql = "select max(recruitmentboardIdx) as topIdx from recruitmentboard";
		
		int topIdx = 0;
		
		try{
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				topIdx = rs.getInt("topIdx");
				
			}
		}finally{
			if(rs!=null)	rs.close();
			if(pstmt!=null)	pstmt.close();
			if(conn!=null)	conn.close();
		}
		return topIdx;
	}
	
	
	// 첨부파일 첨부하기
	public int addFileSrc(int recruitmentboardIdx, String fileSrc) throws ClassNotFoundException, SQLException {
		String sql = "update recruitmentboard set fileSrc=? where recruitmentboardIdx=?";
		int result = 0;
		try{
			
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, fileSrc);
			pstmt.setInt(2, recruitmentboardIdx);

			result = pstmt.executeUpdate();
			
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		return result;
		
	}
	
	// 첨부파일 삭제하기
	public int deleteFileSrc(int recruitmentboardIdx) throws ClassNotFoundException, SQLException {
		String sql = "update recruitmentboard set fileSrc=null where recruitmentboardIdx=?";
		int result = 0;
		try{
			
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, recruitmentboardIdx);

			result = pstmt.executeUpdate();
			
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		return result;
	}
	
	// 모집공고 게시물 개수 구하기 (페이징용)
	public int rowCount() throws ClassNotFoundException, SQLException {
		String sql = "select count(*) as cnt from recruitmentboard where deleted=0";
		int cnt = 0;
		try{
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				cnt= rs.getInt("cnt");
			}
		}finally{
			if(rs!=null)	rs.close();
			if(pstmt!=null)	pstmt.close();
			if(conn!=null)	conn.close();
		}
		return cnt;
	}
	
}
