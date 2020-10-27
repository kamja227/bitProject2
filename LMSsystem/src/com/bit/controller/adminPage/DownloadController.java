package com.bit.controller.adminPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.bit")
public class DownloadController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String filename=req.getParameter("file");
		String mainpage = req.getParameter("recruitboardIdx");
		String adminpage = req.getParameter("recruitIdx");
		String rootPath = "";
		if (mainpage != null) // 메인 페이지 접근인 경우
			rootPath = "recruitdetail.bit?boardIdx=" + mainpage;
		if(adminpage != null) // 관리자 페이지 접근인 경우
			rootPath = "/admin/arecruitdetail.bit?idx=" + adminpage;
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("recruitFile");
		File file=new File(path + "/" + filename);
		
	
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+filename);
		OutputStream out = resp.getOutputStream();
		if(file.exists()){
			FileInputStream fis=new FileInputStream(file);
			int cnt=-1;
			while((cnt=fis.read())!=-1){
				out.write(cnt);
			}
			out.flush();
			
		}
		else {
			if(out != null) out.close();
			PrintWriter pout = resp.getWriter();
			resp.setContentType("text/html;charset=utf-8");
			pout.println("<script>alert('첨부파일이 존재하지 않습니다.');location.href='" + rootPath + "';</script>");
			pout.flush();
			return;
		}
		
	}
}
