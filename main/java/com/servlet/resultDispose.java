package com.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.studentData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class resultDispose
 */
@WebServlet("/resultDispose")
public class resultDispose extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnsupportedEncodingException {
//    	  int page = Integer.parseInt(request.getParameter("page"));
//        int number=Integer.parseInt(request.getParameter("displayNumber"));
//        int totalpage=Integer.parseInt(request.getParameter("totalPage"));
        ArrayList<studentData> list = new ArrayList<studentData>();
        List<String> resu = new ArrayList<String>();
        HttpSession session=(HttpSession)request.getSession();
        ServletContext application=(ServletContext)session.getServletContext();
        int currentpage= (int)session.getAttribute("currentPage");
        int number = (int)session.getAttribute("number");
        int totalpage = (int)session.getAttribute("totalpage");
        list = (ArrayList<studentData>) session.getAttribute("result");
//        int currentpage= (int)application.getAttribute("currentPage");
//        int number = (int)application.getAttribute("number");
//        int totalpage = (int)application.getAttribute("totalpage");
//        list = (ArrayList<studentData>) application.getAttribute("result");
        int page = 0;
        System.out.println("µ±Ç°Ò³Ãæ"+currentpage);
        if(currentpage == 1)
        	page = 1;
        else
        	page = Integer.parseInt(request.getParameter("page"));
        for(int i=(page-1)*number;i<(page-1)*number+number;i++)
        {
             if(i<list.size()) {
            	 resu.add(list.get(i).getId());
        		 resu.add(list.get(i).getName());
        		 resu.add(list.get(i).getPhone());
        		 resu.add(list.get(i).getQq());
        		 resu.add(list.get(i).getEmail());
             }
             else
                  break;
        }
//      String servletPath = getServletContext().getContextPath();
//      servletPath +="/resultDispose";
//      System.out.println(getServletContext().getRealPath("/"));
//      System.out.println(servletPath);
        request.setAttribute("table",resu);
        request.setAttribute("currentPage",page);
        request.setAttribute("number",number);
        request.setAttribute("totalpage",totalpage);
        request.getRequestDispatcher("/result.jsp").forward(request,response);

   }
}
