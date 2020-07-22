package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService service;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("con.갤러리보이기");
		
		List<GalleryVo> galList = service.showList();
		
		model.addAttribute("galList", galList);
		
		return "gallery/list";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, @ModelAttribute GalleryVo vo) {
		System.out.println("con.사진올리기");
		
		service.upload(vo, file);
		
		return "redirect:/gallery/list";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(int no) {
		System.out.println("con.사진삭제하기");
		
		return service.delete(no);
	}
	
	@ResponseBody
	@RequestMapping("/read")
	public GalleryVo read(@RequestParam("no") int imgNo) {
		System.out.println("con.모달창 사진 보이기");
		
		return service.showImgInfo(imgNo);
	}
	

}
