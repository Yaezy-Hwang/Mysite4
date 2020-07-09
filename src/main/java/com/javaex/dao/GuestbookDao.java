package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> selectList() {
		System.out.println("dao.방명록가져오기");
		
		List<GuestbookVo> gList = sqlSession.selectList("guestbook.selectList");
		System.out.println(gList);
		
		return gList;
	}
	
	public int insert(GuestbookVo gVo) {
		System.out.println("dao.방명록쓰기");
		
		return sqlSession.insert("guestbook.insert", gVo);
	}
	
	public int delete(Map<String, Object> gMap) {
		System.out.println("dao.방명록삭제");
		
		return sqlSession.delete("guestbook.delete", gMap);
	}

}
