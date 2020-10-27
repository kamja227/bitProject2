// class EmployeeDao

package com.bit.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bit.Mysql.MysqlConnection;
import com.bit.model.dto.EmployeeDto;

public class EmployeeDao {
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   public EmployeeDao() throws SQLException{
      
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

   public EmployeeDto login(String userId, String password) {
      String sql = "select * from Employee where deleted=0 and userId=? and password=?";
      EmployeeDto bean = null;
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, userId);
         pstmt.setString(2, password);
         rs = pstmt.executeQuery();
         if(rs.next()){
            bean = new EmployeeDto();
            bean.setEmployeeIdx(rs.getInt("employeeIdx"));
            bean.setUserId(rs.getString("userId"));
            bean.setPassword(rs.getString("password"));
            bean.setName(rs.getString("name"));
            bean.setContact(rs.getString("contact"));
            bean.setEmail(rs.getString("email"));
            bean.setDepartment(rs.getString("department"));
            bean.setLevel(rs.getInt("level"));
            bean.setDeleted(rs.getInt("deleted"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally{
         try {
            if(rs != null) rs.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return bean;
   } // login end
   
// 관리자 추가
	public int InsertOne(String userId, String password,String name, String contact,String email,String department,int level) throws SQLException{
		String sql = "insert into Employee(userId, password, name, contact, email, department, level) values ( ?, ?, ?, ?, ?, ?, ?)";

			try {
				conn=MysqlConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				pstmt.setString(2, password);
				pstmt.setString(3, name);
				pstmt.setString(4, contact);
				pstmt.setString(5,email);
				pstmt.setString(6,department);
				pstmt.setInt(7,level);
				
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
	
	

	// 관리자 추가
	public int adminInsert(String userId, String password,String name, String contact,String email,String department,int manageClass,int level) throws SQLException{
		String sql = "insert into resume(userId, password, name, contact, email, department, manageClass, level) values ( ?, ?, ?, ?, ?, ?, ?, ?)";

			try {
				conn=MysqlConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				pstmt.setString(2, password);
				pstmt.setString(3, name);
				pstmt.setString(4, contact);
				pstmt.setString(5,email);
				pstmt.setString(6,department);
				pstmt.setInt(7,manageClass);
				pstmt.setInt(8,level);
				
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
	
  // 계정 삭제
     public int deleteOne(int employeeIdx) throws SQLException {
        String sql = "update Employee set deleted=1 where deleted=0 and employeeIdx=?";
        try{
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, employeeIdx);
           
           return pstmt.executeUpdate();
        }finally{
           if(pstmt != null) pstmt.close();
           if(conn != null) conn.close();
        }
     } // deleteOne end
	
   public ArrayList<EmployeeDto> selectAll(){      
      ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
      String sql = "select * from Employee where deleted = 0 order by employeeIdx asc";
      try {
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();      
         while(rs.next()){
            list.add(new EmployeeDto(rs.getInt("employeeIdx"), rs.getString("userId"), rs.getString("password"), 
                  rs.getString("name"), rs.getString("contact"), rs.getString("email"), 
                  rs.getString("department"), rs.getInt("manageClass"),rs.getInt("level"), rs.getInt("deleted")));
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
   
   public ArrayList<EmployeeDto> selectAllPaging(int pageNo){
	   ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
//	      String sql = "select * from Employee where deleted = 0 order by employeeIdx asc";
	      String sql = "select @RNUM := @RNUM + 1 AS rownum, a.* "
					+ " FROM (select * from Employee where deleted = 0 order by department asc, employeeIdx asc) a, "
					+ " ( SELECT @RNUM := 0 ) b order by rownum LIMIT ?, 20 ";
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, 20 * (pageNo-1));
	         rs = pstmt.executeQuery();      
	         while(rs.next()){
	            list.add(new EmployeeDto(rs.getInt("employeeIdx"), rs.getString("userId"), rs.getString("password"), 
	                  rs.getString("name"), rs.getString("contact"), rs.getString("email"), 
	                  rs.getString("department"), rs.getInt("manageClass"),rs.getInt("level"), rs.getInt("deleted")));
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
   } // selectAllPaging end
   
   public ArrayList<EmployeeDto> departmentAll(String department){ // 부서별
      ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
      String sql = "select * from Employee where deleted = 0 and department=? order by employeeIdx asc";
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, department);
         rs = pstmt.executeQuery();
         
         while(rs.next()){
            list.add(new EmployeeDto(rs.getInt("employeeIdx"), rs.getString("userId"), rs.getString("password"), 
                  rs.getString("name"), rs.getString("contact"), rs.getString("email"), 
                  rs.getString("department"), rs.getInt("manageClass"),rs.getInt("level"), rs.getInt("deleted")));
         }
         return list;
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
      return null;
   }
      
      // 강사
      public ArrayList<EmployeeDto> getGangsaList(){
         ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
         String sql = "select * from Employee where deleted = 0 and level=2 order by employeeIdx asc";
         try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
               list.add(new EmployeeDto(rs.getInt("employeeIdx"), rs.getString("userId"), rs.getString("password"), 
                     rs.getString("name"), rs.getString("contact"), rs.getString("email"), 
                     rs.getString("department"), rs.getInt("manageClass"),rs.getInt("level"), rs.getInt("deleted")));
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
   } // getGangsaList end
      
      public ArrayList<EmployeeDto> getChangeableGangsaList(){
          ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
          String sql = "select * from employee where deleted=0 and manageclass=0 and department='강사'";
          try {
             pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();
             
             while(rs.next()){
                list.add(new EmployeeDto(rs.getInt("employeeIdx"), rs.getString("userId"), rs.getString("password"), 
                      rs.getString("name"), rs.getString("contact"), rs.getString("email"), 
                      rs.getString("department"), rs.getInt("manageClass"),rs.getInt("level"), rs.getInt("deleted")));
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
    } // getChangeableManagerList end
          
       // 행정부 중 manageClass가 0인 행정부를 가져오는 메서드 입니다.
       public ArrayList<EmployeeDto> getAbleManagerList(){
    	   String sql= "select * from employee where department='행정부' and deleted=0 and manageClass=0";
    	   ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
    	   try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				EmployeeDto bean = new EmployeeDto(rs.getInt("employeeIdx"), rs.getString("userId"), rs.getString("password"), 
	                      rs.getString("name"), rs.getString("contact"), rs.getString("email"), 
	                      rs.getString("department"), rs.getInt("manageClass"),rs.getInt("level"), rs.getInt("deleted"));
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
       } // getNewManagerList end
          
          
       public int updateManagerGangsaManageClass(int classIdx, int teacherId, int managerId) throws SQLException {
          // 원래 강사와 매니저의 manageClass 값을 0으로 바꾸고
          String sql1 = "update Employee set manageClass=0 where deleted=0 and department in ('강사', '행정부') and manageClass=?";
          // 선택된 강사와 매니저의 manageClass 값을 전달 받은 classsIdx 값으로 바꾼다.
          String sql2= "update Employee set manageClass=? where deleted=0 and employeeIdx=? or employeeIdx=?";
          
          try{
             int result = 0;
             pstmt = conn.prepareStatement(sql1);
             pstmt.setInt(1, classIdx);
             result += pstmt.executeUpdate();
             pstmt.close();
             pstmt = conn.prepareStatement(sql2);
             pstmt.setInt(1, classIdx);
             pstmt.setInt(2, teacherId);
             pstmt.setInt(3, managerId);
             
             result += pstmt.executeUpdate();
             return result;
          }finally{
             if(pstmt != null) pstmt.close();
             if(conn != null) conn.close();
          }
       } // updateManageClass end   
   
       
       
   public EmployeeDto selectOne(int employeeIdx) {
      EmployeeDto bean = null;
      String sql = "select * from Employee where deleted = 0 and employeeIdx = ?";
      try {
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, employeeIdx);
         rs = pstmt.executeQuery();
         
         if(rs.next()){
            bean = new EmployeeDto(rs.getInt("employeeIdx"), rs.getString("userId"), rs.getString("password"), 
                    rs.getString("name"), rs.getString("contact"), rs.getString("email"), 
                    rs.getString("department"), rs.getInt("manageClass"),rs.getInt("level"), rs.getInt("deleted"));
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
   

   public int updateOne(int employeeIdx, String password, String name, String contact, String email, String department, int level, int deleted) throws SQLException {
      String sql = "update Employee set password=?, name=?, contact=?, email=?, department=?, level=?, "
            + "deleted=? where employeeIdx=?";
      try{
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, password);
         pstmt.setString(2, name);
         pstmt.setString(3, contact);
         pstmt.setString(4, email);
         pstmt.setString(5, department);
         pstmt.setInt(6, level);
         pstmt.setInt(7, deleted);
         pstmt.setInt(8, employeeIdx);
         
         return pstmt.executeUpdate();
      }finally{
         if(pstmt != null) pstmt.close();
         if(conn != null) conn.close();
      }
   } // updateOne end
   
      
   // 아이디 찾기
  	public String findUserId(String name, String contact) {
  		String sql = "select userId from Employee where deleted=0 and name=? and contact=?";
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
  	
  	// 비밀번호 찾기 내 계정 확인 
  	public String findPassword(String id, String name, String contact) {
		String sql = "select userId from Employee where deleted=0 and userId=? name=? and contact=?";
		
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
		String sql = "update Employee set password=? where deleted=0 and userId=?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(1, userId);
			
			return pstmt.executeUpdate();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
  	
   public int rowCount() throws SQLException{
      String sql = "select count(*) as cnt from Employee where deleted=0";
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
   
   
   public int updateManageIdx(int manageClass,int teacherId,int managerId) throws SQLException{
	   
	   String sql="update employee set manageClass=? where deleted=0 and employeeIdx in(?,?)";
	   try{
		  pstmt= conn.prepareStatement(sql);
		  pstmt.setInt(1, manageClass);
		  pstmt.setInt(2, teacherId);
		  pstmt.setInt(3, managerId);
		  return pstmt.executeUpdate();
	   }finally{
		   if(pstmt!=null)pstmt.close();
		   if(conn!=null)conn.close();
		   
	   }
	   
   }
   

}