package com.bit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bit.Mysql.MysqlConnection;
import com.bit.model.dto.ClassRoomDto;

public class ClassRoomDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public ClassRoomDao() {
		try {
			conn=MysqlConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//모든 클래스룸 정보 가져오기
	public List<ClassRoomDto> getClassRoom(){
		String sql="select * from classroom";
		
		List<ClassRoomDto> list = new ArrayList<ClassRoomDto>();
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ClassRoomDto bean = new ClassRoomDto(rs.getInt("roomNum"), rs.getInt("inUse"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//룸 넘버를 사용 중으로 바꿔주는 메서드 (룸넘버 파라미터 넣어주세요)
	public int UpdateInUse(int roomNum) throws SQLException{
		String sql = "update classroom set inUse=0 where roomNum=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			return pstmt.executeUpdate();
		}finally{
			
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
	}
	
	// class테이블의 저장되어 있는 Classroom을 사용 가능한 roomnum으로 바꿔주고 inUse=0으로 바꿔주고 이미 저장되어 있었던
	// 클래스 룸은 inUse=1으로 만들어주는 메서드 입니다.
	// ClassNum은 이미 지정되어 있는 강의실의 ClassNum 넣어주시고, roomNum은 바꿀 강의실을 넣어주시면 됩니다.
	public int SelectedClassroomChange(int ClassNum,int roomNum) throws SQLException{
		String sql1 = "update classroom set inUse=1 where roomNum=?";
		String sql2 = "update classroom set inUse=0 where roomNum=?";
		
		try{
			pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, ClassNum);
			int result=pstmt.executeUpdate();
			pstmt.close();
			pstmt=conn.prepareStatement(sql2);
			pstmt.setInt(1, roomNum);
			return result+= pstmt.executeUpdate();
			
		}finally{
			
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
	}

}
