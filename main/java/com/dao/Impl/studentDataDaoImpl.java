package com.dao.Impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.studentDataDao;
import com.entity.studentData;

public class studentDataDaoImpl extends BaseDao implements studentDataDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询到的学生信息历史集
	@Override
	public List<studentData> selectStudentData(String sql, String[] param) {
		// TODO Auto-generated method stub
		List<studentData> studentDataList = new ArrayList<studentData>();
		studentData studentdata = null;
		try {
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
			}
		}
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				studentdata = new studentData();
				studentdata.setId(rs.getString("id"));
				studentdata.setName(rs.getString("name"));
				studentdata.setPhone(rs.getString("phone"));
				studentdata.setQq(rs.getString("qq"));
				studentdata.setEmail(rs.getString("email"));
				studentDataList.add(studentdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return studentDataList;
	}

	@Override
	public int updateStudentData(String sql, Object[] param) {
		// TODO Auto-generated method stub
		int count = super.executeSQL(sql, param);
		return count;
	}

	@Override
	public InputStream selectStudentPhoto(String sql, String[] param) {
		// TODO Auto-generated method stub
		InputStream is = null;
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句
			if (rs.next()) {
				is = rs.getBlob("photo").getBinaryStream();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return is;
	}

	@Override
	public int selectHasPhotoNum(String sql, String[] param) {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句
				if (rs.next()) {
					count = rs.getInt("count(*)");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				super.closeAll(conn, pstmt, rs);
			}
		return count;
	}

	@Override
	public List<InputStream> selectStudentPhotos(String sql, String[] param) {
		// TODO Auto-generated method stub
		List<InputStream> islist = new ArrayList<InputStream>();
		InputStream is = null;
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句
			while(rs.next()) {
				is = rs.getBlob("photo").getBinaryStream();
				islist.add(is);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return islist;
	}

}
