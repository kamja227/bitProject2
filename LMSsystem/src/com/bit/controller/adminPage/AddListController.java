package com.bit.controller.adminPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.EmployeeDao;


@WebServlet("/admin/addlist.bit")
public class AddListController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("../addlist.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String contact = req.getParameter("contact");
		String email = req.getParameter("email");
		String department = req.getParameter("department");
		int level = Integer.parseInt(req.getParameter("level"));
		
		EmployeeDao employeeDao;
		try {
			employeeDao = new EmployeeDao();
			int result=employeeDao.InsertOne(userId, password, name, contact, email, department, level);
			if(result>0){
				resp.sendRedirect("alist.bit");
			}else {
				resp.setContentType("text/html; charset=UTF-8");
		         PrintWriter out = resp.getWriter();
		         out.println("<script>alert('잘못 입력하셨습니다.');location.href='addcourse.bit" + "'</script>");
		         out.flush();  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e){
			resp.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = resp.getWriter();
	        out.println("<script>alert('잘못 입력하셨습니다.');location.href='addcourse.bit" + "'</script>");
	        out.flush();   
		}
	}
}
