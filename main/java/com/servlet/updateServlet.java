package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.studentDataDel;
import com.service.studentDataUpd;

/**
 * Servlet implementation class updateServlet
 */
@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
	    String qq = request.getParameter("qq");
	    String email = request.getParameter("email");
	    int count = 0;
        count = studentDataUpd.updStudentData(id, name, phone, qq, email);
        request.getSession().setAttribute("updateid", id);
        request.getSession().setAttribute("updateCount", count);
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //response.addHeader("refresh", "60;URL=delete.jsp");
        response.sendRedirect("updBack.jsp");
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
