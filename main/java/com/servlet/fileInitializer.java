package com.servlet;

import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class fileInitializer
 *
 */
@WebListener
public class fileInitializer implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public fileInitializer() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	ServletContext context = sce.getServletContext();
    	String path = context.getRealPath("./WEB-INF/contact/data.txt");
    	System.out.println(path);
    	//String path = "/D:/data.txt";
    	//FileInputStream fis = new FileInputStream(path);
    	//System.out.print(path);
    	txtReader t=new txtReader();
    	context.setAttribute("data", t.getFileContext(path));
    	
    }
	
}
