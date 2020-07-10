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
	
	public List<ReplyVo> showList(int page) {
		System.out.println("service.게시글 가져오기");
		
		Map<String, Object> pageMap = new HashMap<>();
		
		pageMap.put("end", (1+(page-1)*7)+6);
		pageMap.put("start", 1+(page-1)*7);
		pageMap.put("keyword", "");
		
		return dao.selectList(pageMap);
	}
	
	public int count(String keyword) {
		System.out.println("service.카운트");
		
		return dao.count(keyword);
	}
	
	public int[] countArr() {
		int count = count("");
		
		count = (int)Math.ceil(count/5.0);
		
		int[] arr = new int[count];
		
		for(int i = 0; i < count; i++) {
			arr[i] = i+1;
		}
		
		return arr;
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

	public int write(ReplyVo replyVo) {
		System.out.println("service.쓰기");
		
		return dao.insert(replyVo);
	}
	
	public List<ReplyVo> search(String keyword) {
		System.out.println("service.검색");
		
		return dao.selectKeyword(keyword);
	}

}
