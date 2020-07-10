package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao dao;
	
	public List<BoardVo> showList(int page, String keyword) {
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
	
	public BoardVo read(int no) {
		System.out.println("service.읽기");
		
		dao.hitUp(no);
		
		return dao.select(no);
	}

	public int modify(BoardVo boardVo) {
		System.out.println("service.수정");
		
		return dao.update(boardVo);
	}

	public int delete(int no) {
		System.out.println("service.삭제");
		
		return dao.delete(no);
	}

	public int write(BoardVo boardVo) {
		System.out.println("service.쓰기");
		
		return dao.insert(boardVo);
	}
	
}
