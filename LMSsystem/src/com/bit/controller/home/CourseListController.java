package com.bit.controller.home;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.ClassDao;
import com.bit.model.dto.ClassDto;

@WebServlet("/courselist.bit")
public class CourseListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 페이징 추가
		
		// 현재 페이지
		String strPageNo = req.getParameter("nowPage");

        int pageNo = -1;
        if (strPageNo == null) {
            pageNo = 1;
        } else {
            pageNo = Integer.parseInt(strPageNo);
        }
        System.out.println(pageNo);
        
        // 페이지 그룹의 첫 페이지
        int startPage = -1;
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
		try {
			// 마지막 페이지 값 구하기
			ClassDao dao = new ClassDao();
			int cnt = dao.rowCount();
			
			if(cnt % 20 == 0) lastPage = cnt/20;
			else lastPage = cnt/20 + 1;
			
			dao = new ClassDao();
			ArrayList<ClassDto> list = dao.selectAll(pageNo);
			req.setAttribute("list", list);
			req.setAttribute("nowPage", pageNo);
			req.setAttribute("startPage", startPage);
			req.setAttribute("lastPage", lastPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("courselist.jsp").forward(req, resp);
	}
}

