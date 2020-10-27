package com.bit.controller.adminPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.GradeDao;
import com.bit.model.dao.StudentDao;
import com.bit.model.dto.StudentDto;

@WebServlet("/admin/addscoreinsert.bit")
public class AddScoreInsertController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int classIdx = Integer.parseInt(req.getParameter("classIdx"));
		String classTitle = req.getParameter("classTitle");

		StudentDao dao;
		try {
			dao = new StudentDao();
			ArrayList<StudentDto> list = dao.classAll(classIdx);
			req.setAttribute("classIdx", classIdx);
			req.setAttribute("classTitle", classTitle);
			req.setAttribute("list", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("../addscoreinsert.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 입력 받아야함.
		req.setCharacterEncoding("UTF-8");
		Enumeration e1 = req.getParameterNames();

		ArrayList<String> student = new ArrayList<String>();
		while(e1.hasMoreElements()) {
			 String element = (String) e1.nextElement();
			 if(element.contains("student")) {
				 student.add(element); // student1, student3, student4....
			 }
		}
		
		for(int i=0; i<student.size(); i++ ){
			// 점수 테이블 번호
			int studentIdx = Integer.parseInt(req.getParameter(student.get(i)));
			// 과목명
			String subjectTitle =  req.getParameter("subjectTitle" + studentIdx);
			// 학생 점수
			String score = req.getParameter("score" + studentIdx);
			// 시험 날짜
			String testDate = req.getParameter("testDate" + studentIdx);
			GradeDao dao = new GradeDao();
			try {
				dao.insertOne(studentIdx, subjectTitle, testDate, score);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		String classIdx= req.getParameter("classIdx");
		String classTitle = req.getParameter("classTitle");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<script>location.href='http://localhost:8080/LMSsystem/admin/ascoreclass.bit?classIdx=" + classIdx + "&classTitle=" + classTitle + "'</script>");
		out.flush();
	}
}
