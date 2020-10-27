package com.bit.controller.userMypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.ResumeDao;
import com.bit.model.dto.ResumeDto;
import com.bit.model.dto.StudentDto;


@WebServlet("/myresume.bit")
public class MyResumeController extends HttpServlet {

   
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session= request.getSession();
		StudentDto bean=(StudentDto) session.getAttribute("stuBean");
		ResumeDao dao=new ResumeDao();
		List<ResumeDto>list=dao.getResumeList(bean.getStudentIdx());
		request.setAttribute("resumelist", list);
		
		request.getRequestDispatcher("myresume.jsp").forward(request, response);
		
		
	}


}
