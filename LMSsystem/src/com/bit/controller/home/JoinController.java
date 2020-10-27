package com.bit.controller.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.StudentDao;

@WebServlet("/join.bit")
public class JoinController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		request.getRequestDispatcher("join.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String email = request.getParameter("email");

		if (!(userId == "" || password == "" || name == "" || contact == "" || email == "")) {
			try {
				StudentDao dao = new StudentDao();
				response.setContentType("text/html; charset=UTF-8");
				dao.insertOne(userId, password, name, contact, email);
				PrintWriter out = response.getWriter();
				out.print("<script>alert('회원가입이 완료되었습니다.');location.href='index.bit'</script>");
				out.flush();

			} catch (SQLException e) {

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('중복된아이디 입니다. 아이디 중복확인후 회원가입해주세요.');history.back()</script>");
				out.flush();
			}
			
		}	else if (userId != ""&&name==""&&contact==""&&email==""&&password=="") {
			try {
				com.bit.model.dao.StudentDao dao = new com.bit.model.dao.StudentDao();
				dao.joinIdCheck(userId);
				
				if (dao.joinIdCheck(userId) == 1) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.print("<script>alert('사용불가능한 아이디입니다.');location.href='join.bit'</script>");
					out.flush();
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.print("<script>alert('사용가능한 아이디입니다.');history.back()</script>");
					out.flush();
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
			

		    } 
		
		if(name==""&&password!=""||contact==""&&password!=""||email==""&&password!=""){
         	
		    	
		    	response.setContentType("text/html; charset=UTF-8"); PrintWriter out=
         			   response.getWriter();
         	   out.print("<script>alert('빈칸의입력을 완료해주세요');history.back()</script>");
         	   out.flush();
         	   
         	   
            }
		
	
		               

	}
		
	
		
}

