package com.bit.controller.userMypage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.ResumeDao;
import com.bit.model.dto.ResumeDto;



@WebServlet("/myresumedetail.bit")
public class MyResumeDetailController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = req.getParameter("resumeIdx");
//		System.out.println(">>>"+msg);
		int resumeIdx=Integer.parseInt(msg);
		ResumeDao dao=new ResumeDao();
//		System.out.println("Idx="+ resumeIdx);
		List<ResumeDto>list=dao.getResumeDetailList(resumeIdx);
		
		req.setAttribute("ResumeDetailList", list);
//		System.out.println(list);
		req.getRequestDispatcher("myresumedetail.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//idx
		int resumeIdx=Integer.parseInt(req.getParameter("resumeIdx"));
		//����
		String resumeTitle =req.getParameter("resumeTitle").trim();
		//����
		String resumeContent =req.getParameter("resumeContent").trim();
		// �ð�
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	    Date time = new Date();
	    String writtenDate = format1.format(time);
	    
		ResumeDao dao=new ResumeDao();
		try {
			dao.resumeUpdate(writtenDate, resumeIdx, resumeTitle, resumeContent);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		resp.sendRedirect("myresume.bit");
	}

}
