package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo userVo) {
		System.out.println("userDao.insert");
		
		return sqlSession.insert("user.insert", userVo);
	}
	
	public UserVo selectUser(UserVo userVo) {
		System.out.println("userDao.selectUser");
		
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		System.out.println(authUser);
		
		return authUser;
	}
	
	public int update(UserVo userVo) {
		System.out.println("userDao.update");
		
		return sqlSession.update("user.update", userVo);
	}
	
	// 아이디 체크(ajax용)
	public UserVo selectUser(String id) {
		System.out.println("userDao.selectUser_By id");
		System.out.println(id);
		
		UserVo userVo = sqlSession.selectOne("user.selectById", id);
		
		return userVo;
	}
	
}
