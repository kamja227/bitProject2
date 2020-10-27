package com.bit.controller.adminPage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.dao.RecruitmentBoardDao;

@WebServlet("/admin/filedelete.bit")
public class FileDeleteController  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String filename=req.getParameter("file");
		int idx = Integer.parseInt(req.getParameter("idx"));
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("recruitFile");
		File file=new File(path + "/" + filename);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		if(file.exists()) {
			if(file.delete()){ 
				RecruitmentBoardDao dao = new RecruitmentBoardDao();
				try {
					dao.deleteFileSrc(idx);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('첨부파일 삭제가 완료되었습니다.');location.href='arecruitdetail.bit?idx=" + idx + "';</script>");
				out.flush();
				out.close();
				return;
			}else{ 
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('첨부파일 삭제에 실패했습니다. \\n다시 시도 해주세요.');location.href='arecruitdetail.bit?idx=" + idx + "';</script>");
				out.flush();
				out.close();
				return;
			} 
		}else{ 
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('첨부파일 삭제에 실패했습니다. \\n다시 시도 해주세요.');location.href='arecruitdetail.bit?idx=" + idx + "';</script>");
			out.flush();
			out.close();
			return;
		}
		
	}
}
