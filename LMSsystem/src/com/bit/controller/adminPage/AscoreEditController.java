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
import com.bit.model.dto.GradeDto;

@WebServlet("/admin/ascoreedit.bit")
public class AscoreEditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int classIdx = Integer.parseInt(req.getParameter("classIdx"));
		String classTitle = req.getParameter("classTitle");

		
		GradeDao dao = new GradeDao();
		ArrayList<GradeDto> list = dao.classAll(classIdx);

		req.setAttribute("classIdx", classIdx);
		req.setAttribute("classTitle", classTitle);
		req.setAttribute("list", list);

		req.getRequestDispatcher("../ascoreedit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		Enumeration e1 = req.getParameterNames();

		ArrayList<String> grade = new ArrayList<String>();
		while(e1.hasMoreElements()) {
			 String element = (String) e1.nextElement();
			 System.out.println(element);
			 if(element.contains("grade")) {
				 grade.add(element); // grade1, grade2, grade5...
			 }
		}
		
		for(int i=0; i<grade.size(); i++ ){
			// 점수 테이블 번호
			int gradeIdx = Integer.parseInt(req.getParameter(grade.get(i)));
			// 과목명
			String subjectTitle = req.getParameter("subjectTitle" + gradeIdx);
			// 시험 날짜
			String testDate = req.getParameter("testDate" + gradeIdx);
			// 학생 점수
			String score = req.getParameter("score" + gradeIdx);
			GradeDao dao = new GradeDao();
			try {
				dao.updateOne(gradeIdx, subjectTitle, testDate, score);
				
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
