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
import com.bit.model.dto.AttendanceDto;

@WebServlet("/admin/aattendanceedit.bit")
public class AattendanceEditController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int classIdx = Integer.parseInt(req.getParameter("classIdx"));
		String classTitle = req.getParameter("classTitle");
		String today = req.getParameter("today");
		
		AttendanceDao dao = new AttendanceDao();
		req.setAttribute("today", today);
		ArrayList<AttendanceDto> list = dao.getClassAttendanceList(today, classIdx);

		req.setAttribute("classIdx", classIdx);
		req.setAttribute("classTitle", classTitle);
		req.setAttribute("list", list);
		req.setAttribute("today", today);

		req.getRequestDispatcher("../aattendanceedit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		Enumeration e1 = req.getParameterNames();

		ArrayList<String> attendance = new ArrayList<String>();
		while(e1.hasMoreElements()) {
			 String element = (String) e1.nextElement();
			 if(element.contains("Attendance")) {
				 attendance.add(element); // student1, student3, student4....
			 }
		}
		
		for(int i=0; i<attendance.size(); i++ ){
			int attendanceIdx = Integer.parseInt(req.getParameter(attendance.get(i)));
			String status = req.getParameter("status" + attendanceIdx);
			AttendanceDao dao = new AttendanceDao();
			try {
				dao.updateOne(attendanceIdx, status);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		String today = req.getParameter("today");
		String classIdx= req.getParameter("classIdx");
		String classTitle = req.getParameter("classTitle");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<script>location.href='http://localhost:8080/LMSsystem/admin/aattendanceclass.bit?classIdx=" + classIdx + "&classTitle=" + classTitle + "&today=" + today +"'</script>");
		out.flush();
		
	}
}
