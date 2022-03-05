package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.studentDataDel;

/**
 * Servlet implementation class deleteServlet
 */
@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("deleteid");
        int count = 0;
        count = studentDataDel.delStudentData(id);
        request.getSession().setAttribute("deleteid", id);
        request.getSession().setAttribute("deleteCount", count);
//        PrintWriter out = response.getWriter();
//        out.print("helloworld");
//        out.print("<h2>已成功删除" + count + "条信息</h2><br/>");
//        out.print("<h2>删除的学号：" + id + "</h2>");
//        System.out.println("删除的学号："+id);
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //response.addHeader("refresh", "60;URL=delete.jsp");
        response.sendRedirect("delete.jsp");
        //request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
