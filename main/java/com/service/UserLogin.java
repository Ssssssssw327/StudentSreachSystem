package com.service;

import com.dao.LoginDao;
import com.dao.Impl.LoginDaoImpl;
import com.entity.Login;

public class UserLogin {
	public static int login(Login log) {
		String sql = "select identity from administrator where name = ? and password = ?";
		String[] param = {String.valueOf(log.getUname()),String.valueOf(log.getUpwd())};
		LoginDao loginDao = new LoginDaoImpl();
		return loginDao.login(sql, param);
	}
}
