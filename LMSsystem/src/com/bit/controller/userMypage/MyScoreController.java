package com.bit.controller.userMypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.AttendanceDao;
import com.bit.model.dao.GradeDao;
import com.bit.model.dto.AttendanceDto;
import com.bit.model.dto.GradeDto;
import com.bit.model.dto.StudentDto;


@WebServlet("/myscore.bit")
public class MyScoreController extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		StudentDto bean= (StudentDto ) session.getAttribute("stuBean");
		GradeDao dao=new GradeDao();
		List<GradeDto> list=dao.getGradeList(bean.getStudentIdx());
request.setAttribute("myscore", list);
		
		
		request.getRequestDispatcher("myscore.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

}
