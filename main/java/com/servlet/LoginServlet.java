package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Login;
import com.service.UserLogin;
//控制器层：接受view层请求，并分发给Model层
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理登录请求
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		Login login = new Login(uname,upwd); //放置用户名和密码
		//调用模型层的登录功能
		int result = UserLogin.login(login);
		if(result > 0) { //登录成功
			if(result == 2) { //游客登录
				int permission = 0;
				request.getSession().setAttribute("studentId", uname);
				request.getSession().setAttribute("permission", permission);
			}else if(result == 1) {  //管理员登录
				int permission = 1;
				request.getSession().setAttribute("studentId", uname);
				request.getSession().setAttribute("permission", permission);
			}
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}else {//登录失败
			request.getRequestDispatcher("/login.jsp").forward(request, response);
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
