package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> selectList(Map<String, Object> pageMap) {
		System.out.println("dao.게시글 가져오기");
		
		return sqlSession.selectList("board.selectList", pageMap);
	}
	
	public int count(String keyword) {
		System.out.println("dao.count");
		
		return sqlSession.selectOne("board.count", keyword);
	}
	
	public BoardVo select(int no) {
		System.out.println("dao.select");
		
		return sqlSession.selectOne("board.select", no);
	}

	public int update(BoardVo boardVo) {
		System.out.println("dao.update");
		
		return sqlSession.update("board.update", boardVo);
	}
	
	public int delete(int no) {
		System.out.println("dao.delete");
		
		return sqlSession.delete("board.delete", no);
	}

	public int insert(BoardVo boardVo) {
		System.out.println("dao.insert");
		
		return sqlSession.insert("board.insert", boardVo);
	}
	
	public void hitUp(int no) {
		System.out.println("dao.hitup");
		
		sqlSession.update("board.updateHit", no);
	}
	
	//게시판 페이징
	public List<BoardVo> select2(int startRnum, int endRnum) {
		System.out.println("dao.게시글 가져오기");
		
		Map<String, Integer> map = new HashMap<>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		return sqlSession.selectList("board.select2", map);
	}

}
