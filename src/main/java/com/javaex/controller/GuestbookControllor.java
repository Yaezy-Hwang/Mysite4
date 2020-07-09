package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookControllor {
	
	@Autowired
	private GuestbookService service;
	
	@RequestMapping("/addList")
	public String list(Model model) {
		System.out.println("con.방명록 보이기");
		
		List<GuestbookVo> gList = service.showList();

		model.addAttribute("gList", gList);
		System.out.println(gList);
		
		return "guestbook/addList";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute GuestbookVo gVo) {
		System.out.println("con.방명록쓰기");
		
		int count = service.write(gVo);
		System.out.println(count);
		
		return "redirect:/guestbook/addList";
	}
	
	@RequestMapping("deleteForm")
	public String deleteForm() {
		System.out.println("con.삭제폼 이동");
		
		return "guestbook/deleteForm";
	}
	
	@RequestMapping("delete")
	public String delete(@RequestParam("no") int no, @RequestParam("pw") String pw) {
		System.out.println("con.삭제");
		
		int count = service.delete(no, pw);
		System.out.println(count);
		
		if(count != 0) { //비번 일치
			return "redirect:/guestbook/addList";
		} else {//비번 불일치
			return "redirect:/guestbook/deleteForm?result=fail";
		}
	}
	

}
