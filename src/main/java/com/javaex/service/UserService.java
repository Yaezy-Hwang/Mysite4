package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		System.out.println("userService:join");
		
		System.out.println(userVo);
		userDao.insert(userVo);
		
		return 0;
	}
	
	public UserVo login(UserVo userVo) {
		System.out.println("userService:login");
		
		return userDao.selectUser(userVo);
	}
	
	public int modify(UserVo userVo) {
		System.out.println("userService:modify");
		
		int count = userDao.update(userVo);
		
		return count;
	}
	
}
