package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		System.out.println("/api/guestbook/list");
		
		List<GuestbookVo> gList = guestbookService.showList();
		System.out.println(gList);
		
		return gList;
	}
	
	@ResponseBody
	@RequestMapping("/write")
	public GuestbookVo write(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("/api/guestbook/write");
		System.out.println(guestbookVo);
		
		GuestbookVo vo = guestbookService.addGuest(guestbookVo);
		System.out.println("최종: "+vo);
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(@RequestParam("no") int no, @RequestParam("pw") String pw) {
		System.out.println("/api/guestbook/delete");
		
		int count = guestbookService.delete(no, pw);
		System.out.println(count);
		
		return count;
	}

}
