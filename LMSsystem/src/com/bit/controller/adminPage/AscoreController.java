package com.bit.controller.adminPage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.ClassDao;
import com.bit.model.dto.ClassDto;

@WebServlet("/admin/ascore.bit")
public class AscoreController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		try {
			ClassDao dao = new ClassDao();
			ArrayList<ClassDto> list = dao.selectAll();
			req.setAttribute("list", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("../ascore.jsp").forward(req, resp);
	
	}
	
}
