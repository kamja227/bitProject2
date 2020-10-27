package com.bit.controller.adminPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.ApplicationDao;
import com.bit.model.dao.RecruitmentBoardDao;
import com.bit.model.dao.ResumeDao;
import com.bit.model.dao.StudentDao;
import com.bit.model.dto.ApplicationDto;
import com.bit.model.dto.RecruitmentBoardDto;
import com.bit.model.dto.ResumeDto;
import com.bit.model.dto.StudentDto;

@WebServlet("/admin/applicationdetail.bit")
public class ApplicationDetailController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///파라미터로 application 테이블의 applicantIdx를 받아옴.
		int applicantIdx = Integer.parseInt(request.getParameter("idx"));
		int applicationIdx = Integer.parseInt(request.getParameter("aidx"));
		try {
			StudentDao studentDao = new StudentDao();
			StudentDto studentDto=studentDao.selectOne(applicantIdx);
			request.setAttribute("studentname", studentDto);
			///idx로 뽑아온 스투던트 네임을 던져줌
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ApplicationDao applicationDao = new ApplicationDao();
		//해당 어플리케이션Idx를 넣어 정보를 뽑아서 스테이터스만 씀.applicationIdx도 씀
		ApplicationDto applicationDto = applicationDao.selectOne(applicationIdx);
		RecruitmentBoardDao recruitmentDao= new RecruitmentBoardDao();
		RecruitmentBoardDto recruitmentDto = recruitmentDao.selectOne(applicationDto.getRecruitmentBoardIdx());
		
		ResumeDao resumeDao = new ResumeDao();
		//어플리케이션 테이블의 글쓴이를 던져 빈을 받아옴 -> resume 테이블 정보 쓸것임
		ResumeDto resumeDto=resumeDao.getresumeDetail(applicationDto.getResumeIdx());
		
		request.setAttribute("content", resumeDto);
		request.setAttribute("title", recruitmentDto);
		request.setAttribute("application", applicationDto);
		
		
		request.getRequestDispatcher("../applicationdetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		int applicationIdx=Integer.parseInt(request.getParameter("applicationIdx"));
//		String status = new String(request.getParameter("status").trim().getBytes("iso-8859-1"), "utf-8");
		String status = request.getParameter("status").trim();
		System.out.println(applicationIdx + ", " + status);
		try {
			ApplicationDao applicationDao = new ApplicationDao();
			try {
				int result=applicationDao.updateStatus(status, applicationIdx);
				if(result>0){
					PrintWriter out = response.getWriter();
					out.println("<script>location.href='application.bit';</script>");
					out.flush();
					return;
				} else {
					System.out.println("변경 실패");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
