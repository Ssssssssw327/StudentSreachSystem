package com.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.swing.filechooser.FileSystemView;

import com.entity.studentData;
import com.service.studentDataSearch;

import java.io.*;
import java.util.*;
import java.nio.*;
/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        this.doPost(request,response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("utf-8");
         response.setContentType("text/html;charset=utf-8");
         String param = request.getParameter("param");
         String option = request.getParameter("option");
         String nu=request.getParameter("count");
         
         if(param == null || param.equals("")){
             response.sendRedirect(request.getRequestURI());
             return;
         }
         //分页数目默认为5
         int num=5;
         if(!"".equals(nu)) {
             num = Integer.parseInt(nu);
        }
         HttpSession session=(HttpSession)request.getSession();
         ServletContext application=(ServletContext)session.getServletContext();
         List<studentData> itemList=new ArrayList<studentData>();
         itemList = studentDataSearch.selectStudentData(option,param);
         int totalpage = itemList.size()/num;
         //向上取整
         if(itemList.size()%num!=0) {
             totalpage++;
         }
         
         if(itemList!=null) {
        	 session.setAttribute("result", itemList);
        	 //application.setAttribute("result", itemList);
         }
         //请求转发  
         
         session.setAttribute("currentPage",1);
         session.setAttribute("number",num);
         session.setAttribute("totalpage",totalpage);
         response.setCharacterEncoding("utf-8");
         response.setContentType("text/html;charset=utf-8");
         request.getRequestDispatcher("/resultDispose").forward(request,response);
	}

}
