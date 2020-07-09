package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam("page") int page) {
		System.out.println("con.게시글보이기");
	
		List<BoardVo> bList = service.showList(page);
		
		int count = service.count("");
		model.addAttribute("count", count);
		
		count = (int)Math.ceil(count/5.0);
		
		int[] arr = new int[count];
		
		for(int i = 0; i < count; i++) {
			arr[i] = i+1;
		}
		
		model.addAttribute("bList", bList);
		model.addAttribute("arr", arr);
		
		return "board/list";
	}
	
	@RequestMapping("/read")
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("con.읽기");
		model.addAttribute("post", service.read(no));
		
		return "board/read";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "board/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("con.쓰기");
		
		System.out.println(boardVo);
		service.write(boardVo);
		
		return "redirect:/board/list?page=1";
	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("con.수정폼");
		
		model.addAttribute("post", service.read(no));
		
		return "board/modifyForm";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("con.수정");
		
		service.modify(boardVo);
		
		return "redirect:/board/read?no="+boardVo.getNo();
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") int no) {
		System.out.println("con.삭제");
		
		service.delete(no);
		
		return "redirect:/board/list?page=1";
	}
	
	@RequestMapping("/search")
	public String search(Model model, @RequestParam("keyword") String keyword) {
		
		List<BoardVo> bList = service.search(keyword);
		System.out.println(bList);
		
		int count = service.count(keyword);
		
		model.addAttribute("bList", bList);
		model.addAttribute("count", count);
		
		return "board/list";
		
	}

}