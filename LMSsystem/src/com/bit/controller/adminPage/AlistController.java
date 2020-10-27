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

import com.bit.model.dao.EmployeeDao;
import com.bit.model.dto.EmployeeDto;


@WebServlet("/admin/alist.bit")
public class AlistController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
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

		// 마지막 페이지 값 구하기
	    EmployeeDao dao;
		try {
			dao = new EmployeeDao();
			int cnt = dao.rowCount();
			
			if(cnt % 20 == 0) lastPage = cnt/20;
			else lastPage = cnt/20 + 1;
			
			dao = new EmployeeDao();
			List<EmployeeDto>list=dao.selectAllPaging(pageNo);
			
			req.setAttribute("adminlist", list);
			req.setAttribute("nowPage", pageNo);
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("startPage", startPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("../alist.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		ArrayList<Integer> list2 = new ArrayList<Integer>();		
		Enumeration<String> enu = req.getParameterNames();
		int result=0;
		
		while(enu.hasMoreElements()){
			list2.add(Integer.parseInt(req.getParameter(enu.nextElement())));
		}
		
		for(int idx : list2){
			int employeeIdx = idx;
			try {
				EmployeeDao bean = new EmployeeDao();
				result = bean.deleteOne(employeeIdx);
				if(result > 0){
					PrintWriter out = resp.getWriter();
					out.println("<script>location.href='http://localhost:8080/LMSsystem/admin/alist.bit'</script>");
					out.flush();
					return;
				} else {
					System.out.println("삭제 실패...");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}				
		}
	}
}
