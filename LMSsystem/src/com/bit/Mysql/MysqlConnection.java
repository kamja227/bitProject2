package com.bit.Mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection {
   public static Connection conn = null;
   
    private static String url = "jdbc:mysql://localhost:3306/lmssystem?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
//    private static String url = "jdbc:mysql://192.168.0.216:2080/lmssystem?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
    private static String user = "root";
//    private static String user = "server3zo";
    private static String password="123456";
    private static String driver="com.mysql.cj.jdbc.Driver";
   public MysqlConnection() {
   }
   
   
   public static Connection getConnection() throws ClassNotFoundException, SQLException{
      Class.forName(driver);
      conn=DriverManager.getConnection(url,user,password);
      return conn;
   }
      

     
      

 

}