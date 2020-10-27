package com.bit.controller.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.EmployeeDao;
import com.bit.model.dao.StudentDao;

@WebServlet("/findid.bit")
public class FindIdController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("findid.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userName = req.getParameter("name");
		String contact = req.getParameter("contact");
		String userId ="";
		try {
			StudentDao dao = new StudentDao();
			userId = dao.findUserId(userName, contact);
			if(userId != null && userId != "") {
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('" + userName + "님의 아이디는 \"" + userId + "\" 입니다.');</script>");
				out.flush();
			} else {
				EmployeeDao empDao = new EmployeeDao();
				userId = empDao.findUserId(userName, contact);
				
				if(userId != null && userId != "") {
					resp.setContentType("text/html; charset=UTF-8");
					PrintWriter out = resp.getWriter();
					out.println("<script>alert('관리자 " + userName + "님의 아이디는 \"" + userId + "\" 입니다.');</script>");
					out.flush();
				} else {
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('존재하지 않는 계정입니다.\n 다시 한번 확인 바랍니다.');</script>");
				out.flush();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
