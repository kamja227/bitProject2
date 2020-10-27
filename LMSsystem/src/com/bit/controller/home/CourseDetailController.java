package com.bit.controller.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.ClassDao;
import com.bit.model.dto.ClassDto;
import com.bit.model.dao.EmployeeDao;
import com.bit.model.dto.EmployeeDto;
import com.bit.model.dao.StudentDao;
import com.bit.model.dto.StudentDto;

@WebServlet("/coursedetail.bit")
public class CourseDetailController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int classIdx = Integer.parseInt(req.getParameter("classIdx"));
		System.out.println(classIdx);
		try {
			ClassDao dao = new ClassDao();
			ClassDto dto = dao.selectOne(classIdx);
			EmployeeDao empDao = new EmployeeDao();
			EmployeeDto empDto = empDao.selectOne(dto.getTeacherId());
			
			req.setAttribute("bean", dto);
			req.setAttribute("teacher", empDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("coursedetail.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int classIdx = Integer.parseInt(req.getParameter("classIdx"));
		int cnt = Integer.parseInt(req.getParameter("cnt"));
		// 로그인 검열 (수강신청)
		HttpSession session = req.getSession();
		StudentDto student = (StudentDto)session.getAttribute("stuBean");
		int studentIdx = 0;
		if(student != null) studentIdx = student.getStudentIdx();
		Object admin = session.getAttribute("empBean");
		if(student != null) {
			try {
				StudentDao stuDao = new StudentDao();
				ClassDao claDao = new ClassDao();
				if(student.getClassIdx() == 0) { // 수강신청
					// 수강신청시, student의 classIdx 수정
					int enroll = stuDao.enrollClass(student.getStudentIdx(), classIdx);
					System.out.println("학생번호:" + student.getStudentIdx() + ", " + "교육과정번호:" + classIdx);
					// class의 cnt(수강신청인원) 증가
					int enrolled = claDao.enrolledStudent(classIdx, cnt);
					resp.setContentType("text/html; charset=utf-8");
					resp.setCharacterEncoding("utf-8");
					if(enroll==1 && enrolled==1) {
						req.getSession().invalidate();
						stuDao = new StudentDao();
						StudentDto stuBean = stuDao.selectOne(studentIdx);
						if(stuBean != null) {
							session = req.getSession();
							session.setAttribute("stuBean", stuBean);
							System.out.println(stuBean.getName());
							PrintWriter out = resp.getWriter();
							out.println("<script>alert('수강신청이 완료되었습니다.');location.href='coursedetail.bit?classIdx=" + classIdx + "';</script>");
							out.flush();
							out.close();
							return;
						}
					}
					else{
						PrintWriter out = resp.getWriter();
						out.println("<script>alert('error');</script>");
						out.flush();
						out.close();
						return;
					}	
					
				} else if(student.getClassIdx() != 0 && student.getClassIdx() != classIdx){
					resp.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = resp.getWriter();
					out.println("<script>alert('이미 다른 교육에 수강 등록 중에있습니다. \\n수강 취소 후에 이용해 주세요.');location.href='coursedetail.bit?classIdx=" + classIdx + "'</script>");
					out.flush();
					out.close();
					return;
				}else { // 지정된 반이 있는 경우(classIdx != 0) 수강취소
					// 수강취소시, student의 classIdx 수정
					int cancel = stuDao.cancelClass(student.getStudentIdx());
					System.out.println("학생번호:" + student.getStudentIdx() + ", " + "교육과정번호:" + classIdx);
					// class의 cnt(수강신청인원) 증가 
					int canceled = claDao.canceledStudent(classIdx, cnt);
					resp.setContentType("text/html; charset=UTF-8");
					PrintWriter out = resp.getWriter();
					if(cancel==1 && canceled==1) {
						req.getSession().invalidate();
						stuDao = new StudentDao();
						StudentDto stuBean = stuDao.selectOne(studentIdx);
						if(stuBean != null) {
							session = req.getSession();
							session.setAttribute("stuBean", stuBean);
//							System.out.println(stuBean.getName());
							out.println("<script>alert('수강신청 취소가 완료되었습니다.');location.href='coursedetail.bit?classIdx=" + classIdx + "';</script>");
							out.flush();
							out.close();
							return;
						}
					}
					else{
						out.println("<script>alert('error');</script>");
						out.flush();
						out.close();
						return;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(admin != null) {
			// 관리자 회원
			//사용할 수 없는 메뉴 알림
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('관리자는 사용할 수 없는 기능입니다.');location.href='coursedetail.bit?classIdx=" + classIdx + "'</script>");
			out.flush();
			out.close();
			return;
		} else{
			System.out.println("로그인 요청");
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('로그인이 필요한 메뉴입니다.');location.href='coursedetail.bit?classIdx=" + classIdx + "'</script>");
			out.flush();		
			out.close();
			return;
		}
	}
}
