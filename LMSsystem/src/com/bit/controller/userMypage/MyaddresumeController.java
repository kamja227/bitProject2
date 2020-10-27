package com.bit.controller.userMypage;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.ResumeDao;
import com.bit.model.dto.StudentDto;



@WebServlet("/myaddresume.bit")
public class MyaddresumeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("myaddresume.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//학번
		HttpSession session = request.getSession();
		StudentDto student = (StudentDto)session.getAttribute("stuBean");
		int writerIdx = student.getStudentIdx();
		//날짜
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	    Date time = new Date();
	    String writtenDate = format1.format(time);
	    //입력값
		String resumeTitle = request.getParameter("resumeTitle");//제목
		String resumeContent = request.getParameter("resumeContent");//내용
		try {
			ResumeDao dao = new ResumeDao();
			int result = dao.resumeInsert(writerIdx, writtenDate, resumeTitle, resumeContent);
			if(result<0){
				response.sendRedirect("myresume.bit?idx="+writerIdx);
			}else{
				response.sendRedirect("myresume.bit");
			}
			
		} catch (SQLException e) {
			
		}
	}

}
