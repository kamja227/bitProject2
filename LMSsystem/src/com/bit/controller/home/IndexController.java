package com.bit.controller.home;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.ClassDao;
import com.bit.model.dto.ClassDto;

@WebServlet("/index.bit")
public class IndexController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ClassDao classDao;
		try {
			classDao = new ClassDao();
			List<ClassDto> classAll=classDao.selectAlldesc();
			
			req.setAttribute("classAll", classAll);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	
}
