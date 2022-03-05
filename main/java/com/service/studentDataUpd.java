package com.service;

import com.dao.studentDataDao;
import com.dao.Impl.studentDataDaoImpl;

public class studentDataUpd {
	public static int updStudentData(String id,String name,String phone, String qq, String email){
		String sql = "update student set name=?,phone=?,qq=?,email=? where id=?;";
		String[] param = {name,phone,qq,email,id};
		studentDataDao studentDataDAO = new studentDataDaoImpl();
		return studentDataDAO.updateStudentData(sql, param);	
	}
}
