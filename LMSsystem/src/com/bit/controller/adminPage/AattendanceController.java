
package com.bit.controller.adminPage;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.ClassDao;
import com.bit.model.dto.ClassDto;
import com.bit.model.dto.StudentDto;

@WebServlet("/admin/aattendance.bit")
public class AattendanceController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 로그인 검열 (관리자 페이지)
		HttpSession session = req.getSession();
		
		Object admin = session.getAttribute("empBean");
		if(admin != null) {
			try {
				ClassDao dao = new ClassDao();
				ArrayList<ClassDto> list = dao.selectAll();
				req.setAttribute("list", list);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("../aattendance.jsp").forward(req, resp);
		} else{
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('사용 권한이 없는 페이지입니다.\\n 메인 페이지로 이동합니다.');location.href='../index.bit';</script>");
			out.flush();
			return;
		}
	}
	
}
