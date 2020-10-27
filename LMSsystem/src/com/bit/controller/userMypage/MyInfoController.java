package com.bit.controller.userMypage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.StudentDao;
import com.bit.model.dto.StudentDto;


@WebServlet("/myinfo.bit")
public class MyInfoController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		StudentDto bean=(StudentDto)session.getAttribute("stuBean");
	   
		
		try {
			StudentDao dao=new StudentDao();
			List<StudentDto> list=dao.getStudentInfo(bean.getStudentIdx());
			 request.setAttribute("student", list);
		} catch (Exception e) {
		e.printStackTrace();
		}
	
		request.getRequestDispatcher("myinfo.jsp").forward(request, response);

		
		
	}
	
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	HttpSession session=req.getSession();
	StudentDto bean=(StudentDto)session.getAttribute("stuBean");

	
	String name=req.getParameter("name").trim();
	String contact=req.getParameter("contact").trim();
	String email=req.getParameter("email");
	String password=req.getParameter("password").trim();
	int studentIdx=bean.getStudentIdx();
	try {
		StudentDao dao=new  StudentDao();
		StudentDto dto=new  StudentDto();
if(password==""||password==null){
			
			password=dto.getPassword();
			
		}
		
		dao.updateinfoOne(name, contact, email, password,studentIdx);

	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	resp.sendRedirect("mypage.bit");
	

	
}
	
	
	
	
	


}
