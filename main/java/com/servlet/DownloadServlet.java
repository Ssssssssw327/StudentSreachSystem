package com.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.studentDataPhoto;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		//响应编码
		response.setCharacterEncoding("utf-8");
		response.addHeader("content-Type", "application/octet-stream"); //MIME类型为二进制，任意文件
		String id = request.getParameter("id");
		InputStream is = studentDataPhoto.selectPhoto(id);
		ServletOutputStream out = response.getOutputStream();
		if(is == null) {
			System.out.println("空文件!!!!");
			out.close();
			return;
		}else {
			byte[] bs = new byte[10];
			int len = -1;
			while((len = is.read(bs))!=-1) {
				out.write(bs, 0, len);
			}
			out.close();
			is.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
