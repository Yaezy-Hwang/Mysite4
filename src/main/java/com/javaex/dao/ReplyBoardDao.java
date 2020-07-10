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
		System.out.println("re.dao.게시글 가져오기");
		
		return sqlSession.selectList("replyboard.selectList", pageMap);
	}
	
	public int count(String keyword) {
		System.out.println("re.dao.count");
		
		return sqlSession.selectOne("replyboard.count", keyword);
	}
	
	public ReplyVo select(int no) {
		System.out.println("re.dao.select");
		
		return sqlSession.selectOne("replyboard.select", no);
	}

	public int update(ReplyVo replyVo) {
		System.out.println("re.dao.update");
		
		return sqlSession.update("replyboard.update", replyVo);
	}
	
	public int delete(int no) {
		System.out.println("re.dao.delete");
		
		return sqlSession.update("replyboard.upDelete", no);
	}

	public int insert(ReplyVo replyVo) {
		System.out.println("re.dao.insert");
		
		System.out.println("dao다오"+replyVo);
		
		return sqlSession.insert("replyboard.insert", replyVo);
	}
	
	public void hitUp(int no) {
		System.out.println("re.dao.hitup");
		
		sqlSession.update("replyboard.updateHit", no);
	}
	
	public int selectGroupNo() {
		System.out.println("re.dao.그룹넘버");
		
		return sqlSession.selectOne("replyboard.selectGroupNo");
	}
	
	public int updateOrderNo(ReplyVo replyVo) {
		System.out.println("re.dao.그룹넘버");
		
		return sqlSession.update("replyboard.updateOrderNo", replyVo);
	}

}
