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
	
	public List<BoardVo> showList(int page) {
		System.out.println("service.게시글 가져오기");
		
		Map<String, Integer> pageMap = new HashMap<>();
		
		pageMap.put("end", (1+(page-1)*5)+4);
		pageMap.put("start", 1+(page-1)*5);
		
		return dao.selectList(pageMap);
	}
	
	public int count(String keyword) {
		System.out.println("service.카운트");
		
		keyword = "%"+keyword+"%";
		
		return dao.count(keyword);
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
	
	public List<BoardVo> search(String keyword) {
		System.out.println("service.검색");
		
		keyword = "%"+keyword+"%";
		
		return dao.selectKeyword(keyword);
	}

}
