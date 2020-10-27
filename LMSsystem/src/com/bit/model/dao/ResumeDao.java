package com.bit.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bit.Mysql.MysqlConnection;
import com.bit.model.dto.ResumeDto;

public class ResumeDao {
  
	private static ResumeDao instance;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<ResumeDto> getwriterIdxAll() throws ClassNotFoundException, SQLException{
		List<ResumeDto> list = new ArrayList<ResumeDto>();
		
		String sql="select writerIdx from resume where deleted=0;";
		try{
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ResumeDto resumeDto = new ResumeDto();
				resumeDto.setWriterIdx(rs.getInt("writerIdx"));
				list.add(resumeDto);
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		return list;
		
	} // getwriterIdxAll end
	
	public List<ResumeDto> getResumeList(int writerIdx){
		List<ResumeDto> list=new ArrayList<ResumeDto>();
		
		//리스트 목록
		 String sql = "SELECT @RNUM := @RNUM + 1 AS rownum, a.* "
				   	+ " FROM (SELECT * FROM resume where deleted=0 and writerIdx = ? ORDER BY resumeIdx DESC  ) a, "
				   	+ " ( SELECT @RNUM := 0 ) b";
//		String sql="select resumeIdx,resumeTitle,writtenDate from resume where writerIdx = ? and deleted = 0";
		try {
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, writerIdx);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ResumeDto bean= new ResumeDto();
				bean.setResumeIdx(rs.getInt("resumeIdx"));
				bean.setResumeTitle(rs.getString("resumeTitle"));
				bean.setWrittenDate(rs.getDate("writtenDate"));
				bean.setRownum(rs.getInt("rownum"));
				list.add(bean);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return list;
	} // getResumeList end
	
	
	//상세정보
	public List<ResumeDto> getResumeDetailList(int resumeIdx){
//		System.out.println(resumeIdx);
		List<ResumeDto> list=new ArrayList<ResumeDto>();
		String sql="select * from resume where resumeIdx=? and deleted = 0";
		try {
			conn=MysqlConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, resumeIdx);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ResumeDto bean= new ResumeDto();
				bean.setResumeIdx(rs.getInt("resumeIdx"));
				bean.setResumeTitle(rs.getString("resumeTitle"));
				bean.setResumeContent(rs.getString("resumeContent"));
				bean.setWrittenDate(rs.getDate("writtenDate"));
				list.add(bean);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return list;
	} // getResumeDetailList end
	
	
	//상세정보
		public ResumeDto getresumeDetail(int resumeIdx){
			String sql="select * from resume where resumeIdx=? and deleted = 0";
			ResumeDto resumeDto = new ResumeDto();
			try {
				conn=MysqlConnection.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, resumeIdx);
				rs=pstmt.executeQuery();
				if(rs.next()){
					resumeDto.setWriterIdx(rs.getInt("writerIdx"));
					resumeDto.setWrittenDate(rs.getDate("writtenDate"));
					resumeDto.setResumeTitle(rs.getString("resumeTitle"));
					resumeDto.setResumeContent(rs.getString("resumeContent"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
//			System.out.println(list);
			return resumeDto;
		}
		//상세정보
		
	// 이력서 입력
	public int resumeInsert(int writerIdx, String writtenDate, String resumeTitle, String resumeContent) throws SQLException{
		String sql = "insert into resume(writerIdx, writtenDate, resumeTitle, resumeContent) values (?, STR_TO_DATE(?, '%Y-%m-%d'), ?, ?)";

			try {
				conn=MysqlConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, writerIdx);
				pstmt.setString(2, writtenDate);
				pstmt.setString(3, resumeTitle);
				pstmt.setString(4, resumeContent);
				
				return pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
			
				try {
					if(pstmt != null)pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			}
			return -1;
		}
	public static ResumeDao getInstance(){
        if(instance == null )instance = new ResumeDao();
        return instance;
    } // resumeInsert end
	
	
	//이력서 수정
	public int resumeUpdate(String writtenDate, int resumeIdx,String resumeTitle,String resumeContent) throws SQLException{
		String sql="update resume set writtenDate=STR_TO_DATE(?, '%Y-%m-%d'), resumeTitle=?,resumeContent=? where resumeIdx=?";
		try {
			try {
				conn=MysqlConnection.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(4, resumeIdx);
				pstmt.setString(1, writtenDate);
				pstmt.setString(2, resumeTitle);
				pstmt.setString(3, resumeContent);

				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return pstmt.executeUpdate();
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
	} // resumeUpdate end
	
	
	  // 계정 삭제
    public int deleteOne(int resumeIdx) throws SQLException {
       String sql = "update resumeIdx set deleted=1 where deleted=0 and resumeIdx=?";
       try{
          pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1, resumeIdx);
          
          return pstmt.executeUpdate();
       }finally{
          if(pstmt != null) pstmt.close();
          if(conn != null) conn.close();
       }
    } // deleteOne end
}
