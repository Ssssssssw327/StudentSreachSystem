package com.dao;

import java.io.InputStream;
import java.util.List;

import com.entity.studentData;

public interface studentDataDao {
	/**
	 * 根据查询语句查询学生相关信息
	 */
	public List<studentData> selectStudentData(String sql, String[] param);
	/**
	 * 根据查询语句查询学生相关照片
	 */
	public InputStream selectStudentPhoto(String sql, String[] param);
	/**
	 * 更新学生相关信息
	 */
	public  int updateStudentData(String sql, Object[] param);
	/**
	 * 根据查询语句查询拥有照片的学生数量
	 */
	public int selectHasPhotoNum(String sql, String[] param);
	/**
	 * 根据查询语句查询多张学生相关照片
	 */
	public List<InputStream> selectStudentPhotos(String sql, String[] param);
}
