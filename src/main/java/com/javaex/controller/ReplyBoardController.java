package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.ReplyBoardService;
import com.javaex.vo.ReplyVo;

@Controller
@RequestMapping("/replyboard")
public class ReplyBoardController {
	
	@Autowired
	private ReplyBoardService service;
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam("page") int page) {
		System.out.println("re.con.게시글보이기");
	
		List<ReplyVo> bList = service.showList(page, "");
		Map<String, Integer> countMap= service.count("");
		
		model.addAttribute("bList", bList);
		model.addAttribute("countMap", countMap);
		
		return "replyboard/list";
	}
	
	@RequestMapping("/read")
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("re.con.읽기");
		
		ReplyVo vo = service.read(no);
		System.out.println(vo);
		
		model.addAttribute("post", vo);
		
		return "replyboard/read";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		System.out.println("re.con.쓰기폼");
		
		return "replyboard/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute ReplyVo replyVo,
						@RequestParam("groupNo") int groupNo,
						@RequestParam("orderNo") int orderNo) {
		System.out.println("re.con.쓰기");
		
		System.out.println(replyVo);
		
		int no = service.write(replyVo, groupNo, orderNo);
		System.out.println(no);
		
		return "redirect:/replyboard/list?page=1";
	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("re.con.수정폼");
		
		model.addAttribute("post", service.read(no));
		
		return "replyboard/modifyForm";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute ReplyVo replyVo) {
		System.out.println("re.con.수정");
		
		service.modify(replyVo);
		
		return "redirect:/replyboard/read?no="+replyVo.getNo();
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") int no) {
		System.out.println("re.con.삭제");
		
		service.delete(no);
		
		return "redirect:/replyboard/list?page=1";
	}
	
	@RequestMapping("/search")
	public String search(Model model, @RequestParam("page") int page, @RequestParam("keyword") String keyword) {
		
		List<ReplyVo> bList = service.showList(page, keyword);
		System.out.println(bList);
		
		Map<String, Integer> countMap= service.count(keyword);
		
		model.addAttribute("bList", bList);
		model.addAttribute("countMap", countMap);
		
		return "replyboard/list";
		
	}

}