package com.bit.controller.adminPage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.EmployeeDao;
import com.bit.model.dto.EmployeeDto;
import com.bit.model.dao.ResumeDao;
import com.bit.model.dto.ResumeDto;
import com.bit.model.dto.StudentDto;

@WebServlet("/admin/alistdetail.bit")
public class AlistDetailController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int employeeIdx = Integer.parseInt(req.getParameter("employeeIdx"));
		try {
			EmployeeDao employeedao = new EmployeeDao();
			EmployeeDto employeedto =employeedao.selectOne(employeeIdx);
			req.setAttribute("bean", employeedto);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		req.getRequestDispatcher("../alistdetail.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int employeeIdx = Integer.parseInt(req.getParameter("employeeIdx"));
		String name =  req.getParameter("name");
		String contact = req.getParameter("contact");
		String email = req.getParameter("email");
		String department = req.getParameter("department");
		int level = Integer.parseInt(req.getParameter("level"));
		
		
		try {
			EmployeeDao employeeDao = new EmployeeDao();
			EmployeeDto employeeDto = employeeDao.selectOne(employeeIdx);
			String password = req.getParameter("password");
			if(password==null||password.equals("")){
				password=employeeDto.getPassword();
			}
			
			int result=employeeDao.updateOne(employeeDto.getEmployeeIdx(), password,
							employeeDto.getName(), contact, email, department, level, employeeDto.getDeleted());
			if(result>0){
				resp.sendRedirect("alist.bit");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
