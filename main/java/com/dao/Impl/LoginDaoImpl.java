package com.dao.Impl;

import com.dao.BaseDao;
import com.dao.LoginDao;
import java.sql.*;
public class LoginDaoImpl extends BaseDao implements LoginDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集
	@Override
	public int login(String sql, String[] param) {
		int flag = -1; //登录结果默认值,-1:系统异常，0：用户名密码错误 1：登陆成功
		int result = -1;
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句
			if(rs.next()) {
				result = rs.getInt("identity");
			}
			if(result>0) {
				flag = result;
			}else {
				flag = 0; //用户名或者密码有误
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = -1;
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return flag;
	}

}
