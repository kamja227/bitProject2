package com.bit.controller.adminPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.ApplicationDao;
import com.bit.model.dao.ClassDao;
import com.bit.model.dao.StudentDao;
import com.bit.model.dto.ClassDto;
import com.bit.model.dto.StudentDto;

@WebServlet("/admin/astudent.bit")
public class AstudentController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 현재 페이지
		String strPageNo = req.getParameter("nowPage");
        int pageNo;
        if (strPageNo == null) {
            pageNo = 1;
        } else {
            pageNo = Integer.parseInt(strPageNo);
        }
        // 페이지 그룹의 첫 페이지
        int startPage;
        if (strPageNo == null) {
        	startPage = 1;
        } else {
        	if(pageNo <= 5) {
        		startPage = 1;
        	} else if(pageNo % 5 == 0) {
        		startPage = pageNo / 5 - 1;
        	} else {
        		startPage = pageNo / 5 + 5;
        	}
        }
        // 마지막 페이지
        int lastPage = 0;
        
        // 검색 값
		String temp1 = req.getParameter("search1");
		String temp2 = req.getParameter("search2");
		
		String search1 = "";
		String search2 = "";
		if(temp1 != "" && temp1 != null) {
			search1 = " and student.classIdx = " + temp1; // selectbox 반 선택 값
			
		}
		if(temp2 != "" && temp2 != null) {
			search2 = " and classTitle like '%" + temp2 + "%' or name like '%" + temp2 + "%' "; // 검색 값
		}
			try {
				// 마지막 페이지 값 구하기
				StudentDao studentDao = new StudentDao();
				int cnt = studentDao.countJoinAll(search1, search2);
				
				if(cnt % 20 == 0) lastPage = cnt/20;
				else lastPage = cnt/20 + 1;
				
				studentDao = new StudentDao();
				ArrayList<StudentDto> studentList = studentDao.selectJoinAll(search1, search2, pageNo);
				
				ClassDao classDao = new ClassDao();
				ArrayList<ClassDto> classList = classDao.selectAll();

				resp.setCharacterEncoding("text/html; charset=utf-8");
				req.setAttribute("studentList", studentList);
				req.setAttribute("classList", classList);
				req.setAttribute("search1", temp1);
				req.setAttribute("search2", temp2);
				
				req.setAttribute("nowPage", pageNo);
				req.setAttribute("lastPage", lastPage);
				req.setAttribute("startPage", startPage);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		req.getRequestDispatcher("../astudent.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 학생 삭제
		resp.setCharacterEncoding("utf-8");
		
		List<String> student = new ArrayList<String>();
		Enumeration enu = req.getParameterNames();
		
		int result=0;
		try {
			
			while(enu.hasMoreElements()){
				String element = (String) enu.nextElement();
				if(element.contains("selected")) 
				{
					student.add(element); // "selected1, selected3, ..."
				}
			}
			for(String studentNum : student){
				int studentIdx = Integer.parseInt(req.getParameter(studentNum));
				StudentDao dao = new StudentDao();
				result += dao.deleteOne(studentIdx);
				
			}
			if(student.size()==result) {
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('삭제가 완료되었습니다. \\n학생 관리 페이지로 이동합니다.');location.href='astudent.bit';</script>");
				out.flush();
				return;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
