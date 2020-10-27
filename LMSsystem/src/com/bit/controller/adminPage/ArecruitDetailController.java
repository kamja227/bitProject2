package com.bit.controller.adminPage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.model.dao.EmployeeDao;
import com.bit.model.dao.RecruitmentBoardDao;
import com.bit.model.dto.EmployeeDto;
import com.bit.model.dto.RecruitmentBoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/admin/arecruitdetail.bit")
public class ArecruitDetailController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int recruitmentboardIdx=Integer.parseInt(request.getParameter("idx"));
		
		RecruitmentBoardDao recruitmentBoardDao = new RecruitmentBoardDao();
		RecruitmentBoardDto recruitmentBoardDto = recruitmentBoardDao.selectOne(recruitmentboardIdx);
		
		EmployeeDao employeeDao;
		try {
			employeeDao = new EmployeeDao();
			EmployeeDto employeeDto = employeeDao.selectOne(recruitmentBoardDto.getWriterIdx());
			request.setAttribute("employee", employeeDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("bean", recruitmentBoardDto);
		
		
		request.getRequestDispatcher("../arecruitdetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		String realFolder = ""; //파일경로를 알아보기위한 임시변수를 하나 만들고,
		String saveFolder = "recruitFile"; //파일저장 폴더명을 설정한 뒤에...
		String encType = "utf-8"; //인코딩방식도 함께 설정한 뒤,
		int maxSize = 10*1024*1024; //파일 최대용량까지 지정해주자.(현재 10메가)

		ServletContext context = getServletContext();
		realFolder = context.getRealPath(saveFolder);
		System.out.println("경로 " + realFolder);

		String postTitle = "", postContent = "" , realFileName = "" ;
		Date writtenDate = null;
		int recruitmentboardIdx=0, writerIdx=0;
		
		try {
			//멀티파트생성과 동시에 파일은 저장 된다.
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, maxSize, encType,new DefaultFileRenamePolicy());
			// 이 시점에 파일은 이미 저장이 되었다.
		  
			// 폼으로 넘어왔던파일 파라메터들을 가져오기
			Enumeration params = multi.getParameterNames();
			   
			//그리고 가져온 파라메터를 꺼내는 방법...
			   while(params.hasMoreElements()) {
			      String paramName = params.nextElement().toString();//파라메터이름을 가져온뒤
//			      System.out.println(paramName);
			      if(paramName.equals("recruitmentboardIdx"))
			    	  recruitmentboardIdx = Integer.parseInt(multi.getParameter("recruitmentboardIdx"));
			      else if(paramName.contentEquals("writerIdx"))
			    	  writerIdx = Integer.parseInt(multi.getParameter("writerIdx"));
			      else if(paramName.contentEquals("postTitle"))
			    	  postTitle = multi.getParameter("postTitle");
			      else if(paramName.contentEquals("writtenDate"))
			    	  writtenDate = Date.valueOf(multi.getParameter("writtenDate"));
			      else if(paramName.contentEquals("postContent"))
			    	  postContent = multi.getParameter("postContent");
			   }
			
			postContent = postContent.replace("\r\n","<br/>");
			
			//이번엔 파일과 관련된 파라메터를 가져온다.
			Enumeration files = multi.getFileNames();
			//이번엔 파일관련 파라메터를 꺼내본다...
			while(files.hasMoreElements()){
				String fname = (String)files.nextElement();//파라메터이름을 가져온뒤
		      
			    String filename = multi.getFilesystemName(fname);//이름을 이용해 저장된 파일이름을 가져온다
			    String original = multi.getOriginalFileName(fname);//이름을이용해 본래 파일이름도 가져온다.
			    String type = multi.getContentType(fname);//파일타입도 가져올수있다.
			    File f = multi.getFile(fname);//파일 용량을 알아보기위해서는 이렇게...
			    
			    System.out.println("실제 파일 이름 : " + original +"<br>");
			    System.out.println("저장된 파일 이름 : " + filename +"<br>");
			    System.out.println("파일 타입 : " + type +"<br>");
			      
			    if(f!=null){
			       System.out.println("크기 : " + f.length());
			       System.out.println("<br>");
			    }
			      
			    int i = -1;
			    i = filename.lastIndexOf("."); // 파일 확장자 위치
			      
			    realFileName = "recruit" + recruitmentboardIdx + filename.substring(i, filename.length());
			    // 파일명 : "recruit" + 게시판 번호
				       
			    File oldFile = new File(realFolder + "/" +  filename);
			    File newFile = new File(realFolder + "/" + realFileName);
			     
			    // 파일명 변경
			    oldFile.renameTo(newFile); 
			    System.out.println(realFileName);
		   }	
			   
		}catch(IOException ioe){
			 System.out.println(ioe);
		}catch(Exception ex){
			 System.out.println(ex);
		}
		
		postContent = postContent.replace("\r\n","<br/>");
		try {
			RecruitmentBoardDao recruitmentBoardDao = new RecruitmentBoardDao();
			int result=recruitmentBoardDao.updateRecruit(writerIdx, postTitle, postContent, writtenDate, realFileName, recruitmentboardIdx);
			if(result>0){
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정이 완료 되었습니다.');location.href='arecruitdetail.bit?idx=" + recruitmentboardIdx + "';</script>");
				out.flush();
				out.close();
				return;
			}else{
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
