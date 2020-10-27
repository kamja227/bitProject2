package com.bit.controller.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.ClassDao;
import com.bit.model.dao.EmployeeDao;
import com.bit.model.dao.StudentDao;
import com.bit.model.dto.ClassDto;
import com.bit.model.dto.EmployeeDto;
import com.bit.model.dto.StudentDto;

@WebServlet("/login.bit")
public class LoginController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/xml;charset=utf-8");
		String id = req.getParameter("id"); // 아이디
		String password = req.getParameter("password"); // 비밀번호
		
		StudentDao stuDao = null; // 학생 테이블에 존재하는 계정인지 확인하기 위한 클래스 생성
		StudentDto stuBean = null;
		
		EmployeeDao empDao = null; // 직원 테이블에 존재하는 계정인지 확인하기 위한 클래스 생성
		EmployeeDto empBean = null;
		
		try {
			stuDao = new StudentDao();
			stuBean = stuDao.login(id, password); // 학생 테이블 로그인 확인
			
			if(stuBean == null) {
				empDao = new EmployeeDao();
				empBean = empDao.login(id, password); // 직원 테이블 로그인 확인
			}
			
			if(stuBean == null && empBean == null) {
				System.out.println("로그인 실패");
				try{
					resp.getWriter().write("<login><fail>loginFail</fail></login>");
					return;
				} catch (IOException e) {
	                e.printStackTrace();
	            }
			} else {	
				HttpSession session = req.getSession();
				session.setAttribute("stuBean", stuBean);
				session.setAttribute("empBean", empBean);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ClassDao classDao;
		try {
			classDao = new ClassDao();
			List<ClassDto> classAll=classDao.selectAlldesc();
			
			req.setAttribute("classAll", classAll);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		///exception 발생 이유?????
		try{
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
		}catch(Exception e){
			resp.sendRedirect("index.bit");
		}
	}

}
