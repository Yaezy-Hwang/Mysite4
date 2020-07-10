package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.ReplyVo;

@Repository
public class ReplyBoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<ReplyVo> selectList(Map<String, Object> pageMap) {
		System.out.println("dao.게시글 가져오기");
		
		return sqlSession.selectList("replyboard.selectList", pageMap);
	}
	
	public int count(String keyword) {
		System.out.println("dao.count");
		
		return sqlSession.selectOne("replyboard.count", keyword);
	}
	
	public ReplyVo select(int no) {
		System.out.println("dao.select");
		
		return sqlSession.selectOne("replyboard.select", no);
	}

	public int update(ReplyVo replyVo) {
		System.out.println("dao.update");
		
		return sqlSession.update("replyboard.update", replyVo);
	}
	
	public int delete(int no) {
		System.out.println("dao.delete");
		
		return sqlSession.delete("replyboard.delete", no);
	}

	public int insert(ReplyVo replyVo) {
		System.out.println("dao.insert");
		
		return sqlSession.insert("replyboard.insert", replyVo);
	}
	
	public List<ReplyVo> selectKeyword(String keyword) {
		System.out.println("dao.search");
		
		return sqlSession.selectList("replyboard.search", keyword);
	}
	
	public void hitUp(int no) {
		System.out.println("dao.hitup");
		
		sqlSession.update("replyboard.updateHit", no);
	}

}
