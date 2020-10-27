package com.bit.controller.adminPage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.bit.model.dao.ClassDao;
import com.bit.model.dao.ClassRoomDao;
import com.bit.model.dao.EmployeeDao;
import com.bit.model.dto.ClassDto;
import com.bit.model.dto.ClassRoomDto;
import com.bit.model.dto.EmployeeDto;
@WebServlet("/admin/acoursedetail.bit")
public class AcoursedetailController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int classIdx=Integer.parseInt(request.getParameter("idx"));
		try {
			
			//classIdx에 해당하는 강좌 디테일
			ClassDao dao = new ClassDao();
			ClassDto bean = dao.selectOne(classIdx);
			
			//모든 강사리스트 뽑아오기
			EmployeeDao dao1 = new EmployeeDao();
			ArrayList<EmployeeDto> allgangsaList = dao1.departmentAll("강사");
			
			//모든 행정부 리스트 뽑아오기
			EmployeeDao dao2 = new EmployeeDao();
			ArrayList<EmployeeDto> managerList = dao2.departmentAll("행정부");
			
			
			EmployeeDao dao3 = new EmployeeDao();
			ArrayList<EmployeeDto> ablegangsaList = dao3.getChangeableGangsaList();
			
			ClassRoomDao classRoomDao= new ClassRoomDao();
			
			List<ClassRoomDto> classroom=classRoomDao.getClassRoom();
			
			
			
			
			request.setAttribute("classroom", classroom);
			
			request.setAttribute("classdetail", bean);
			
			request.setAttribute("allgangsaList", allgangsaList);
			
			request.setAttribute("ablegangsaList", ablegangsaList);
			
			request.setAttribute("managerList", managerList);
			
			

		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		request.getRequestDispatcher("../acoursedetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int classIdx = Integer.parseInt(request.getParameter("idx"));
		String classTitle=request.getParameter("classTitle");
		String subjectTitle=request.getParameter("subjectTitle");
		int teacherId=Integer.parseInt(request.getParameter("teacherId"));
		Date startDate= Date.valueOf(request.getParameter("startDate"));
		Date endDate= Date.valueOf(request.getParameter("endDate"));
		int managerId=Integer.parseInt(request.getParameter("managerId"));
		int classRoom=Integer.parseInt(request.getParameter("classRoom"));
		int status = Integer.parseInt(request.getParameter("status"));
		
		String etc = request.getParameter("etc");
		
		etc = etc.replace("\r\n","<br/>");
		try {
			ClassDao dao1 = new ClassDao();
			ClassDto bean = dao1.selectOne(classIdx);
			
			
			ClassDao dao2 = new ClassDao();
			
			
			int result = dao2.updateOne(classIdx, classTitle, subjectTitle, teacherId, startDate, endDate, managerId, classRoom, status, etc);
			if(result<0){
				response.sendRedirect("acoursedetail.bit?idx="+classIdx);
				
				
			}else{
				try{
					EmployeeDao employeeDao = new EmployeeDao();
					int result1=employeeDao.updateManagerGangsaManageClass(classIdx, teacherId, managerId);
					
					
					ClassRoomDao classRoomDao1 = new ClassRoomDao();
					int result2 = classRoomDao1.SelectedClassroomChange(bean.getClassRoom(), classRoom);
				}catch (SQLException e){
					e.printStackTrace();
				}
				response.sendRedirect("acourse.bit");
			}
			
		} catch (SQLException e) {
			
		}
	}
}
