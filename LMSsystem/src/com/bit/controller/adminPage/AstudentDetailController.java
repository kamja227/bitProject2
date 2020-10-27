package com.bit.controller.adminPage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.StudentDao;
import com.bit.model.dto.StudentDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/admin/astudentdetail.bit")
public class AstudentDetailController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		int studentIdx = Integer.parseInt(req.getParameter("studentIdx"));
     
		try {
			StudentDao studentDao = new StudentDao();
			StudentDto bean = studentDao.selectOne(studentIdx);
			req.setAttribute("bean", bean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("../astudentdetail.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 학생 정보 수정
		req.setCharacterEncoding("UTF-8");
	
		int studentIdx = Integer.parseInt(req.getParameter("studentIdx"));
		String name = req.getParameter("name");
		String contact = req.getParameter("contact");
		String memo = req.getParameter("memo");

		StudentDao studentDao;
		try {
			studentDao = new StudentDao();
			
			int result = studentDao.updateOne(name, contact, memo, studentIdx);
			if(result>0){
				resp.setContentType("text/html; charset=UTF-8");
				resp.setCharacterEncoding("UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('학생의 정보가 수정되었습니다.');location.href='http://localhost:8080/LMSsystem/admin/astudentdetail.bit?studentIdx=" + studentIdx + "'</script>");
				out.flush();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
