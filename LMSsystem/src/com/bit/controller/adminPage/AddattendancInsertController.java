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

import com.bit.model.dao.AttendanceDao;
import com.bit.model.dao.StudentDao;
import com.bit.model.dto.AttendanceDto;
import com.bit.model.dto.StudentDto;

@WebServlet("/admin/addattendanceinsert.bit")
public class AddattendancInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int classIdx = Integer.parseInt(req.getParameter("classIdx"));
		String classTitle = req.getParameter("classTitle");
		String today = req.getParameter("today"); // 오늘 날짜 변수로 설정

		try {
			
			AttendanceDao attendDao = new AttendanceDao();
			ArrayList<AttendanceDto> attendList = attendDao.getClassAttendanceList(today, classIdx);

			if(attendList.size() > 0) {
				req.setCharacterEncoding("utf-8");
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('이미 출석일이 등록된 반입니다. \\n수정 페이지로 이동합니다.');location.href='aattendanceedit.bit?"
						+ "classIdx=" + classIdx + "&classTitle= " + classTitle + "&today=" + today + "';</script>");
				out.flush();
				return;
			} else {
				StudentDao dao = new StudentDao();
				ArrayList<StudentDto> list = dao.classAll(classIdx);
				req.setAttribute("classIdx", classIdx);
				req.setAttribute("classTitle", classTitle);
				req.setAttribute("list", list);
				req.setAttribute("today", today);
				req.getRequestDispatcher("../addattendanceinsert.jsp").forward(req, resp);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String today = req.getParameter("today");
	
		Enumeration e1 = req.getParameterNames();

		ArrayList<String> student = new ArrayList<String>();
		while(e1.hasMoreElements()) {
			 String element = (String) e1.nextElement();
			 if(element.contains("student")) {
				 student.add(element); // student1, student3, student4....
			 }
		}
		
		for(int i=0; i<student.size(); i++ ){
			int studentIdx = Integer.parseInt(req.getParameter(student.get(i)));
			String status = req.getParameter("status" + studentIdx);
			AttendanceDao dao = new AttendanceDao();
			try {
				dao.insertOne(studentIdx, today, status);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
		String classIdx= req.getParameter("classIdx");
		String classTitle = req.getParameter("classTitle");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<script>alert('출석 데이터 입력이 완료 되었습니다.');location.href='http://localhost:8080/LMSsystem/admin/aattendanceclass.bit?classIdx=" + classIdx + "&classTitle=" + classTitle + "&today=" + today +"'</script>");
		out.flush();
		
	}
	
}
