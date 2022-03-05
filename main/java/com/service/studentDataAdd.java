package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.studentDataDao;
import com.dao.Impl.studentDataDaoImpl;
import com.entity.studentData;

public class studentDataAdd {
	public static boolean idIsExist(String id){
		String sql = "select * from student where id = ?;";
		//String[] param = {String.valueOf(type),String.valueOf(data)};
		String[] param = {id};
		studentDataDao studentDataDAO = new studentDataDaoImpl();
		List<studentData> studentDataDAOList = new ArrayList<studentData>();
		studentDataDAOList = studentDataDAO.selectStudentData(sql, param);
		if(studentDataDAOList.isEmpty()) {//列表中该学号未被占用
			return true;
		}
		return false;
	}
	public static int addStudentData(String id,String name,String phone,String qq,String email){
		String sql = "insert into student(`id`,`name`,`phone`,`qq`,`email`) values(?,?,?,?,?);";
		//String[] param = {String.valueOf(type),String.valueOf(data)};
		String[] param = {id,name,phone,qq,email};
		studentDataDao studentDataDAO = new studentDataDaoImpl();
		return studentDataDAO.updateStudentData(sql, param);	
	}
}
