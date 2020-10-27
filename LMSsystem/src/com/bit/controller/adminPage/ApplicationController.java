package com.bit.controller.adminPage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

/**
 */
@WebServlet("/admin/application.bit")
public class ApplicationController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name = null;
		int rownum=0;
		
		if(request.getParameter("name")!=null){
			name = request.getParameter("name").trim();
		}
		
		// 페이징 파라미터 idx가 비어있으면 디폴트0
		if(request.getParameter("idx")!=null){
			rownum=(Integer.parseInt(request.getParameter("idx"))*10);
		}
		
		
		ResumeDao resumeDao = new ResumeDao();
		try {
			//이력서 내역 writerIdx 전부 뽑아보냄
			List<ResumeDto> resumeWriterIdxAll=resumeDao.getwriterIdxAll();
			request.setAttribute("resumeIdxAll", resumeWriterIdxAll);
			//student 모조리 뽑아 보냄
			StudentDao studentDao = new StudentDao();
			List<StudentDto> studentSelectAll=studentDao.selectAll();
			request.setAttribute("studentAll", studentSelectAll);
			//recruitmentboard 모조리 뽑아 보냄
			RecruitmentBoardDao recruitmentBoardDao = new RecruitmentBoardDao();
			List<RecruitmentBoardDto> recruitmentAll =recruitmentBoardDao.selectAll();
			request.setAttribute("recruitmentAll", recruitmentAll);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//페이징 작업
		try {
			//name이 null일때(default 페이지 요청)
			if(request.getParameter("name")==null||request.getParameter("name").equals("null")){
				ApplicationDao applicationDao = new ApplicationDao();
				List<ApplicationDto> appselectAll;
				appselectAll = applicationDao.pagingList(rownum);
				request.setAttribute("applicationAll", appselectAll);
			//검색 파라미터 name을 전달받았을때
			}else if(request.getParameter("name")!=null){
				StudentDao studentDao = new StudentDao();
				ApplicationDao applicationDao = new ApplicationDao();
				int applicantIdx = studentDao.searchingResult(name);
				
				List<ApplicationDto> list=applicationDao.searchingName(applicantIdx, rownum);
				ApplicationDao applicationDao1 = new ApplicationDao();
				
				List<ApplicationDto> list1=applicationDao1.searchingAmount(applicantIdx);
				
				request.setAttribute("Amount", list1);;
				request.setAttribute("applicationAll", list);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// jsp에서 application applicantIdx에 해당하는 studentIdx를 뽑아 dto의 getName한다.
		
		
		
		ApplicationDao dao = new ApplicationDao();
		List<ApplicationDto> selectAll=dao.getSelectAll();
		
		
		request.setAttribute("applicationSelectAll", selectAll);
		
		
		request.getRequestDispatcher("../application.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
