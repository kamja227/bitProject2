package com.bit.controller.adminPage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.AttendanceDao;
import com.bit.model.dto.AttendanceDto;

@WebServlet("/admin/aattendancestudent.bit")
public class AattendanceStudentController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String classTitle  = req.getParameter("classTitle");
		int studentIdx = Integer.parseInt(req.getParameter("studentIdx"));
		String studentName = req.getParameter("studentName");

		AttendanceDao dao = new AttendanceDao();
		ArrayList<AttendanceDto> list = dao.getStudentAttendanceList(studentIdx);
		
		dao = new AttendanceDao();
		ArrayList<Integer> attendanceData = dao.getAttendanceCount(studentIdx);
		System.out.println(attendanceData);
		int allCnt = attendanceData.get(0);
		int attendCnt = attendanceData.get(1);
		int absentCnt = attendanceData.get(2);
		int halfCnt = attendanceData.get(3);
		double attendRate = (double)((attendCnt - absentCnt - halfCnt/3) * 100.0 / allCnt);
		attendRate = Math.round((attendRate) * 100) / 100.0;
		
		req.setAttribute("list", list);
		req.setAttribute("classTitle", classTitle);
		req.setAttribute("studentName", studentName);
		req.setAttribute("attendRate", attendRate);
//		resp.setCharacterEncoding("text/html; charset=utf-8");
		
		req.getRequestDispatcher("../aattendancestudent.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
