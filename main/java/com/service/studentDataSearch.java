package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.studentDataDao;
import com.dao.Impl.studentDataDaoImpl;
import com.entity.studentData;

public class studentDataSearch {
	public static List<studentData> selectStudentData(String type, String data){
		//String sql = "select * from student where "+ type +" like '%"+data+"%';";
		String sql = "select * from student where "+ type +" like ?;";
		//String[] param = {String.valueOf(type),String.valueOf(data)};
		String[] param = {"%"+data+"%"};
		studentDataDao studentDataDAO = new studentDataDaoImpl();
		List<studentData> studentDataList = new ArrayList<studentData>();
		studentDataList = studentDataDAO.selectStudentData(sql, param);
		return studentDataList;
	}
	public static studentData selectStudentID(String data){
		String sql = "select * from student where id = ?;";
		String[] param = {data};
		studentDataDao studentDataDAO = new studentDataDaoImpl();
		List<studentData> studentDataList = new ArrayList<studentData>();
		studentData student = null;
		studentDataList = studentDataDAO.selectStudentData(sql, param);
		if(!studentDataList.isEmpty()) {
			student = studentDataList.get(0);
		}
		return student;
	}
}
