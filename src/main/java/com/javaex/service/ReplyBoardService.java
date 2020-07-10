package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.ReplyBoardDao;
import com.javaex.vo.ReplyVo;

@Service
public class ReplyBoardService {
	
	@Autowired
	private ReplyBoardDao dao;
	
	public List<ReplyVo> showList(int page, String keyword) {
		System.out.println("service.게시글 가져오기");
		
		Map<String, Object> pageMap = new HashMap<>();
		
		pageMap.put("end", (1+(page-1)*7)+6);
		pageMap.put("start", 1+(page-1)*7);
		pageMap.put("keyword", keyword);
		
		return dao.selectList(pageMap);
	}
	
	public Map<String, Integer> count(String keyword) {
		System.out.println("service.카운트");
		
		Map<String, Integer> countMap= new HashMap<>();
		countMap.put("countAll", dao.count(keyword));
		countMap.put("count", (int)Math.ceil(countMap.get("countAll")/5.0));
		
		return countMap;
	}
	
	public ReplyVo read(int no) {
		System.out.println("service.읽기");
		
		dao.hitUp(no);
		
		return dao.select(no);
	}

	public int modify(ReplyVo replyVo) {
		System.out.println("service.수정");
		
		return dao.update(replyVo);
	}

	public int delete(int no) {
		System.out.println("service.삭제");
		
		return dao.delete(no);
	}

	public int write(ReplyVo replyVo, int groupNo) {
		int result = 0;
		
		if(groupNo == 0) {
			System.out.println("service.쓰기");
			
			replyVo.setGroupNo(dao.selectGroupNo()+1);
			replyVo.setOrderNo(1);
			replyVo.setDepth(0);
			
			result = dao.insert(replyVo);
			
		} else {
			System.out.println("service.댓글 쓰기");
			
			replyVo.setGroupNo(groupNo);
			replyVo.setOrderNo(dao.selectOrderNo(groupNo)+1);
			replyVo.setDepth(1);
		
			result = dao.insert(replyVo);
		}
		
		return result;
	}
	
	
}
