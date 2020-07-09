package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao dao;
	
	public List<GuestbookVo> showList() {
		System.out.println("service.방명록보이기");
		
		List<GuestbookVo> gList = dao.selectList();
		System.out.println(gList);
		
		return gList;
	}
	
	public int write(GuestbookVo gVo) {
		System.out.println("service.방명록쓰기");
		
		return dao.insert(gVo);
	}

	public int delete(int no, String pw) {
		System.out.println("service.삭제");
		
		Map<String, Object> gMap = new HashMap<>();
		gMap.put("no", no);
		gMap.put("pw", pw);
		
		return dao.delete(gMap);
	}
	
}
