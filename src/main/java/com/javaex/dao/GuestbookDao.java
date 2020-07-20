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
 
	//방명록 글 저장(ajax)
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("dao.ajax 방명록삭제");
		
		sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		
		return guestbookVo.getNo();
	}
	
	//방명록 글 가져오기(ajax)
	public GuestbookVo selectByNo(int no) {
		System.out.println("dao.ajax 방명록가져오기");
		
		return sqlSession.selectOne("guestbook.selectByNo", no);
	}

}
