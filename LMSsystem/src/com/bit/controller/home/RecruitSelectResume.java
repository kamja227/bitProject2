package com.bit.controller.home;

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

import com.bit.model.dao.ApplicationDao;
import com.bit.model.dao.ResumeDao;
import com.bit.model.dto.ResumeDto;
import com.bit.model.dto.StudentDto;

@WebServlet("/recruitselectresume.bit")
public class RecruitSelectResume extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int boardIdx = Integer.parseInt(req.getParameter("boardIdx"));
//		System.out.println(boardIdx);
		HttpSession session = req.getSession();
		StudentDto student = (StudentDto)session.getAttribute("stuBean");
		if(student != null) {
			ResumeDao resumeDao = new ResumeDao();
			ArrayList<ResumeDto> list = (ArrayList<ResumeDto>)resumeDao.getResumeList(student.getStudentIdx());
			req.setCharacterEncoding("utf-8");
			req.setAttribute("list", list);
			req.setAttribute("boardIdx", boardIdx);
			req.getRequestDispatcher("recruitselectresume.jsp").forward(req, resp);
		}else{
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('사용 권한이 없는 페이지입니다.\\n 메인 페이지로 이동합니다.');location.href='index.bit';</script>");
			out.flush();
			return;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException ,IOException {
		int recruitmentboardIdx = Integer.parseInt(req.getParameter("boardIdx"));
		int resumeIdx = Integer.parseInt(req.getParameter("selectedResume"));
		System.out.println("선택된 이력서 번호: " + resumeIdx);
		HttpSession session = req.getSession();
		StudentDto student = (StudentDto)session.getAttribute("stuBean");
		if(student != null) {
			ApplicationDao dao = new ApplicationDao();
			try {
				int result = dao.insertOne(student.getStudentIdx(), resumeIdx, recruitmentboardIdx);
				if(result >=1 ) {
					req.setCharacterEncoding("utf-8");
					resp.setContentType("text/html;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.println("<script>alert('지원이 완료되었습니다. \\n나의 이력서 페이지로 이동합니다.');location.href='myresume.bit';</script>");
					out.flush();
					return;
				} else {
					req.setCharacterEncoding("utf-8");
					resp.setContentType("text/html;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.println("<script>alert('Error');location.href='recruitselectresume.bit?boardIdx=" + recruitmentboardIdx + "';</script>");
					out.flush();
					return;
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
