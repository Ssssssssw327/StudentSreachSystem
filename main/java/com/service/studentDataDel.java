package com.service;

import com.dao.studentDataDao;
import com.dao.Impl.studentDataDaoImpl;

public class studentDataDel {
	public static int delStudentData(String id){
		String sql = "delete from student where id = ?;";
		//String[] param = {String.valueOf(type),String.valueOf(data)};
		String[] param = {id};
		studentDataDao studentDataDAO = new studentDataDaoImpl();
		return studentDataDAO.updateStudentData(sql, param);	
	}
}
