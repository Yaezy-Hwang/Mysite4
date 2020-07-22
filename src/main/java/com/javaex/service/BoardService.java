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
		countMap.put("count", (int)Math.ceil(countMap.get("countAll")/7.0));
		
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
	
	//게시판 페이징
	public Map<String, Object> list2(int crtPage) {
		System.out.println("service.페이징");
		
		//페이지당 글 갯수
		int listCnt = 10;
		
		//현재 페이지 계산
		crtPage = (crtPage>0) ? crtPage : (crtPage=1);
		
		//startRnum
		int startRnum = (crtPage-1)*listCnt;
		
		//endRnum
		int endRnum = startRnum+listCnt;
		
		//리스트 가져오기
		List<BoardVo> boardList = dao.select2(startRnum, endRnum);
		
		//총 게시물 갯수
		int totalCount = dao.count("");
		
		// 하단 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		// 오른쪽 화살표 표시 유무
		boolean next = false;
		if(endPageBtnNo*listCnt < totalCount) {
			next = true;
		}else {
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}
		
		// 왼쪽 화살표 표시 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		
		return pMap;
	}

	
	
}
