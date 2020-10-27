package com.bit.controller.adminPage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.GradeDao;
import com.bit.model.dto.GradeDto;

@WebServlet("/admin/ascoreclass.bit")
public class AscoreClassController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int classIdx = Integer.parseInt(req.getParameter("classIdx"));
		String classTitle = req.getParameter("classTitle");

		GradeDao dao = new GradeDao();
		ArrayList<GradeDto> list = dao.classAll(classIdx);
		
		req.setAttribute("classIdx", classIdx);
		req.setAttribute("classTitle", classTitle);
		req.setAttribute("list", list);

		req.getRequestDispatcher("../ascoreclass.jsp").forward(req, resp);
	}
	

}
