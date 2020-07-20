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

	//방명록 글 저장(ajax)
	public GuestbookVo addGuest(GuestbookVo guestbookVo) {
		System.out.println("service.ajax 글저장");
		
		//저장
		dao.insertSelectKey(guestbookVo);
		
		//방금 저장된 no 값 가져오기
		int no = guestbookVo.getNo();
		System.out.println("select 키로 받은 no:" + no);
		
		//저장한 data가져오기
		return dao.selectByNo(no);
	}
	
}
