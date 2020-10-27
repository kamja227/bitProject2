package com.bit.controller.userMypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.AttendanceDao;
import com.bit.model.dto.AttendanceDto;
import com.bit.model.dto.StudentDto;



@WebServlet("/myattendance.bit")
public class MyAttendanceController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		StudentDto bean = (StudentDto) session.getAttribute("stuBean");

		// 현재 페이지
		String strPageNo = request.getParameter("page");

        int pageNo;
        if (strPageNo == null) {
            pageNo = 1;
        } else {
            pageNo = Integer.parseInt(strPageNo);
        }

        // 페이지 그룹의 첫 페이지
        int startPage;
        if (strPageNo == null) {
        	startPage = 1;
        } else {
        	if(pageNo <= 5) {
        		startPage = 1;
        	} else if(pageNo % 5 == 0) {
        		startPage = pageNo / 5 - 1;
        	} else {
        		startPage = pageNo / 5 + 5;
        	}
        }
        // 마지막 페이지
        int lastPage = 0;

        // 마지막 페이지 값 구하기
		AttendanceDao dao = new AttendanceDao();
		int cnt = dao.rowStudentDataCnt(bean.getStudentIdx());
		
		if(cnt % 10 == 0) lastPage = cnt / 10;
		else lastPage = cnt / 10 + 1;
		System.out.println(pageNo);
		
		dao = new AttendanceDao();
		ArrayList<AttendanceDto> list = (ArrayList<AttendanceDto>)dao.getAttendanceList(bean.getStudentIdx(), pageNo);
		
		request.setAttribute("attendancelist", list);
		request.setAttribute("page", pageNo);
		request.setAttribute("startPage", startPage);
		request.setAttribute("lastPage", lastPage);
		request.getRequestDispatcher("myattendance.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
