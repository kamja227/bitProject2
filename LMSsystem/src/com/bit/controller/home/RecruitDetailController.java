package com.bit.controller.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.RecruitmentBoardDao;
import com.bit.model.dao.ResumeDao;
import com.bit.model.dto.RecruitmentBoardDto;
import com.bit.model.dto.ResumeDto;
import com.bit.model.dto.StudentDto;

@WebServlet("/recruitdetail.bit")
public class RecruitDetailController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int recruitmentboardIdx = Integer.parseInt(req.getParameter("boardIdx"));
//		System.out.println(recruitmentboardIdx);
		RecruitmentBoardDao dao = new RecruitmentBoardDao();
		RecruitmentBoardDto bean = dao.selectOne(recruitmentboardIdx);
		req.setAttribute("bean", bean);

		req.getRequestDispatcher("recruitdetail.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int recruitmentboardIdx = Integer.parseInt(req.getParameter("boardIdx"));
		// 로그인 검열 (지원하기)
		HttpSession session = req.getSession();
		StudentDto student = (StudentDto)session.getAttribute("stuBean");
		int studentIdx = 0;
		if(student != null) {
			studentIdx = student.getStudentIdx();
		}
		Object admin = session.getAttribute("empBean");
		if(student != null) {
			ResumeDao resumedao = new ResumeDao();
			ArrayList<ResumeDto> resumeList = (ArrayList<ResumeDto>)resumedao.getResumeList(studentIdx);
			if(resumeList.size() <= 0 || resumeList == null) { // 지원하기
				// 이력서 없는 경우 // 이력서 작성 마이페이지 링크 첨부
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('저장된 이력서가 존재하지 않습니다.\\n이력서 작성페이지로 이동합니다.');location.href='myResume.bit?studentIdx=" + studentIdx + "'</script>");
				out.flush();
			}else { // 이력서 있는 경우, // 이력서 골라서 지원하기
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>location.href='recruitselectresume.bit?boardIdx=" + recruitmentboardIdx + "'</script>");
				out.flush();
				return;
			}
		} else if(admin != null) {
			// 관리자 회원
			//사용할 수 없는 메뉴 알림
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('관리자는 사용할 수 없는 기능입니다.');location.href='recruitdetail.bit?boardIdx=" + recruitmentboardIdx + "'</script>");
			out.flush();
			return;
		} else{
//			System.out.println("로그인 요청");
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('로그인이 필요한 메뉴입니다.');location.href='recruitdetail.bit?boardIdx=" + recruitmentboardIdx + "'</script>");
			out.flush();		
			return;
		}
	}
}
