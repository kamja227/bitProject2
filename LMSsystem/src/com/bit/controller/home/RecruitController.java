package com.bit.controller.home;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.RecruitmentBoardDao;
import com.bit.model.dto.RecruitmentBoardDto;

@WebServlet("/recruit.bit")
public class RecruitController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 페이징 추가
		
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
        System.out.println(pageNo);
        // 마지막 페이지
        int lastPage = 0;

		// 마지막 페이지 값 구하기
        ArrayList<RecruitmentBoardDto> list = null;
		try {
			RecruitmentBoardDao dao = new RecruitmentBoardDao();
			int cnt = dao.rowCount();
			
			if(cnt % 20 == 0) lastPage = cnt/20;
			else lastPage = cnt/20 + 1;
			
			dao = new RecruitmentBoardDao();
			list = dao.selectAll(pageNo);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("list", list);
		req.setAttribute("nowPage", pageNo);
		req.setAttribute("startPage", startPage);
		req.setAttribute("lastPage", lastPage);
		
		req.getRequestDispatcher("recruit.jsp").forward(req, resp);
	}

}