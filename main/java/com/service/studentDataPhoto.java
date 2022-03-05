package com.service;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.dao.studentDataDao;
import com.dao.Impl.studentDataDaoImpl;
import com.entity.studentData;

public class studentDataPhoto {
	public static boolean uploadPhoto(String id,String fileName) {
//		String path = null;
//		String fileName = null;
		InputStream in = null;
		try {
			in = new FileInputStream(fileName);
//			in = new FileInputStream(path+'\\'+fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block		
			e.printStackTrace();
			return false;
		}
	    String sql="update student set photo = ? where id = ?";
	    Object[] param = {in,id};
	    studentDataDao studentDataDAO = new studentDataDaoImpl();
		int num = studentDataDAO.updateStudentData(sql, param);	
	    if (num > 0) {
	        System.out.println("Õº∆¨≤Â»Î≥…π¶£°");
	        return true;
	    } else {
	        System.out.println("Õº∆¨≤Â»Î ß∞‹£°");        
	    }
		
		return false;
	}
	public static InputStream selectPhoto(String id) {
		InputStream is = null;
		String sql="select photo from student where id = ?";
	    String[] param = {id};
	    studentDataDao studentDataDAO = new studentDataDaoImpl();
	    is = studentDataDAO.selectStudentPhoto(sql, param);
		return is;
	}
	public static int selectHasPhotoNum() {
		String sql="select count(*) from student where photo is not null";
	    String[] param = null;
	    studentDataDao studentDataDAO = new studentDataDaoImpl();
	    return studentDataDAO.selectHasPhotoNum(sql, param);
	}
	public static List<InputStream> compareFace() {
		List<InputStream> islist = new ArrayList<InputStream>();
		String sql="select photo from student where photo is not null order by id desc ";
	    String[] param = null;
	    studentDataDao studentDataDAO = new studentDataDaoImpl();
	    islist = studentDataDAO.selectStudentPhotos(sql, param);
	    
		return islist;
	}
	public static String getLimitStudent(int num) {
		String id = String.valueOf(num);
		List<studentData> studentdatalist = new ArrayList<studentData>();
		String sql="select * from student where photo is not null order by id desc LIMIT "+ id +",1";
	    String[] param = null;
	    studentDataDao studentDataDAO = new studentDataDaoImpl();
	    studentdatalist = studentDataDAO.selectStudentData(sql, param);
		return studentdatalist.get(0).getId();
	}
}
