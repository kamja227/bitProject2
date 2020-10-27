package com.bit.controller.adminPage;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.AttendanceDao;
import com.bit.model.dto.AttendanceDto;

@WebServlet("/admin/aattendanceclass.bit")
public class AattendanceClassController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int classIdx = Integer.parseInt(req.getParameter("classIdx"));
		String classTitle = req.getParameter("classTitle");

		String today = req.getParameter("today"); // 오늘 날짜 변수로 설정

		AttendanceDao dao = new AttendanceDao();
		ArrayList<AttendanceDto> list = dao.getClassAttendanceList(today, classIdx);
		for(AttendanceDto attendance : list) {
			dao = new AttendanceDao();
			ArrayList<Integer> data = dao.getAttendanceCount(attendance.getStudentIdx());
			int allCnt = data.get(0);
			int attendCnt = data.get(1);
			int absentCnt = data.get(2);
			int halfCnt = data.get(3);
			double attendRate = (double)((attendCnt - absentCnt - halfCnt/3) * 100.0 / allCnt);
			attendRate = Math.round((attendRate) * 100) / 100.0;
			attendance.setAttendanceRate(attendRate);
		}
		req.setAttribute("classIdx", classIdx);
		req.setAttribute("classTitle", classTitle);
		req.setAttribute("list", list);
		req.setAttribute("today", today);

		req.getRequestDispatcher("../aattendanceclass.jsp").forward(req, resp);

	}
	
}
