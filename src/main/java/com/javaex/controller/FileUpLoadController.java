package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUpLoadService;

@Controller
@RequestMapping("/fileupload")
public class FileUpLoadController {
	
	@Autowired
	private FileUpLoadService fileUpLoadService;
	
	@RequestMapping("/form")
	public String form() {
		System.out.println("/fileuploadController/form");
		
		return "fileupload/form";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("/fileuploadController/upload");
		System.out.println(file.getOriginalFilename());
		
		String saveName = fileUpLoadService.restore(file);
		model.addAttribute("saveName", saveName);
		
		return "fileupload/result";
	}
	
	
	
}
