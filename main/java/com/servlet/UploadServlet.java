package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.service.studentDataPhoto;
/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
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
		response.setContentType("text/html;charset=utf-8");
		//检查提交的表单中的编码是否为Multipart
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String studentId = (String)request.getSession().getAttribute("studentId");
		if(isMultipart == true ) { //判断前台form是否有mutipart属性
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解析对象 通过paraseRequest解析form中所有的请求字段，保存到items集合中（即前台传递的sno sname spicture此时就保存在了items中）
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//遍历items
			//采用迭代器
			Iterator<FileItem> iter = items.iterator();
			while(iter.hasNext()) {
				FileItem item = iter.next();
				String itemName = item.getFieldName();
				//判断前台字段是普通form表单字段，还是文件字段
				if(item.isFormField()) {
					if(itemName.equals("id")) {//根据name属性判断
						studentId = item.getString("utf-8");
					}else {//根据name属性判断
						System.out.print("其他字段");
					}
				}else if(item.isFormField() == false && studentId != null){//spicture
					//文件上传
					//获取文件名,getFieldName是获取普通表单字段的Name值
					//getName()是获取文件名
					//String fileName = item.getName();
					
					String longFileName = item.getName();
                    String picType = longFileName.substring(longFileName.indexOf(".")+1);
                    //判断是否是图片，控制上传文件的类型
                    if(!("png".equals(picType)||"jpg".equals(picType)||"bmp".equals(picType))) {
                    	request.setAttribute("upLoadSuccess", "图片类型有误！");
                    	System.out.println("图片类型有误！");
                    	break;
                    }
                    
                    //设置上传文件时，用到的临时文件大小， 对象是DiskFileItemFactory
                    //factory.setSizeThreshold(10240);
                    //设置临时文件目录
                    //factory.setRepository(new File("d:\\uploadtemp"));
                    //控制上传单个文件的大小。单位是字节B，对象是ServletFileUpload
                    //upload.setSizeMax(20480);
                   
                    
					//获取文件内容并上传
					//定义文件路径,指定上传位置(服务器路径)
					//获取服务器路径				
					//boolean tryOnce = studentDataPhoto.uploadPhoto(studentId, longFileName);
					boolean tryOnce = false;
					//判断所用的浏览器
					String agent = request.getHeader("User-Agent");
					if(agent.toLowerCase().indexOf("firefox")!=-1 || agent.toLowerCase().indexOf("edg")!=-1) {
						//处理火狐上传文件名的问题
						String path = request.getSession().getServletContext().getRealPath("/upload");
						char indexChar = '\\';
	                    String fileName = item.getName().substring(longFileName.lastIndexOf(indexChar) + 1,longFileName.length());
	                    File file = new File(path,fileName);
	                    try {
							item.write(file);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} //上传
	                    tryOnce = studentDataPhoto.uploadPhoto(studentId, path+"\\"+fileName);
						System.out.print(fileName+"上传成功！");
						System.out.print(longFileName+"加入数据库成功！");
						if(tryOnce) {
							request.setAttribute("upLoadSuccess", "success");	
						}else {
							request.setAttribute("upLoadSuccess", "fail");
						}
						
					}else {
						tryOnce = studentDataPhoto.uploadPhoto(studentId, longFileName);
						if(tryOnce) {
							System.out.print(longFileName+"加入数据库成功！");
							request.setAttribute("upLoadSuccess", "success");
						}
						
					}					
				}		
			}
			//采用增强型for循环
//			for(FileItem fileitem: items) {
//				String itemName = fileitem.getFieldName();
//				if(itemName.equals("sno")) {//根据name属性判断
//					
//				}else if(itemName.equals("sname")) {//根据name属性判断
//					
//				}else if(itemName.equals("spicture")) {//根据name属性判断
//					
//				}
//			}
		}			
		request.getRequestDispatcher("uploadMenu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
