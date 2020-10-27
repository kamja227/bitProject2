package com.bit.controller.adminPage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.RecruitmentBoardDao;
import com.bit.model.dto.RecruitmentBoardDto;

@WebServlet("/admin/arecruit.bit")
public class ArecruitController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idx=0;
		if(request.getParameter("idx")!=null){
			idx=Integer.parseInt(request.getParameter("idx"));
		}
		
		RecruitmentBoardDao dao = new RecruitmentBoardDao();
		
		List<RecruitmentBoardDto> bean = dao.selectAll();
		RecruitmentBoardDao recruitmentBoardDao = new RecruitmentBoardDao();
		try {
			List<RecruitmentBoardDto> page = recruitmentBoardDao.pagingList(idx);
			request.setAttribute("page", page);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("recruitlist", bean);
		
		request.getRequestDispatcher("../arecruit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
