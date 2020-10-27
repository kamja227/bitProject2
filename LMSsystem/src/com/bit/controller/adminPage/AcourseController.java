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

import com.bit.model.dao.ClassDao;
import com.bit.model.dto.ClassDto;

@WebServlet("/admin/acourse.bit")
public class AcourseController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idx=0;
		
		if(request.getParameter("idx")!=null){
			idx=Integer.parseInt(request.getParameter("idx"));
		}
		
		
		ClassDao bean;
		try {
			bean = new ClassDao();
			List<ClassDto> list = bean.selectAlldesc();
			request.setAttribute("classlist", list);

			ClassDao classDao=new ClassDao();
			List<ClassDto> page = classDao.pagingList(idx);
			request.setAttribute("page",page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("../acourse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		List<Integer> list = new ArrayList<Integer>();
		Enumeration<String> enu = request.getParameterNames();
		int result=0;
		try {
			ClassDao classDao = new ClassDao();
			
			while(enu.hasMoreElements()){
				list.add(Integer.parseInt(enu.nextElement()));
			}
			for(Integer integer : list){
				ClassDao bean = new ClassDao();
				ClassDto classDto = bean.selectOne(integer);
				result=classDao.updateDeleted(integer,classDto.getTeacherId(),classDto.getManagerId(),classDto.getClassRoom());
			}
			if(result>0){
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('삭제가 완료되었습니다. \\n 교육 과정 관리 페이지로 이동합니다.');location.href=acourse.bit';</script>");
				out.flush();
				return;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
