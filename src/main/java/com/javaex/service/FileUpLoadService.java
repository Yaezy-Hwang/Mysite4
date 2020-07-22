package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUpLoadService {
	

	public String restore(MultipartFile file) {
		System.out.println("/fileuploadService/restore");
		//우리 컴퓨터로 파일 카피
		String saveDir = "C:\\javaStudy\\upload";
		
		//원래 파일 이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: "+orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName: "+exName);
		
		//저장 파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName: "+saveName);
		
		//파일 경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: "+filePath);
		
		//파일 사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize: "+fileSize);
		
		//파일을 서버에 복사
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//파일에서 정보 추출 >> db저장
		//no
		//orgName: ogusays02.jpg
		//exName: .jpg
		//saveName: 1595320356900108caa4b-35b1-4d0c-a72f-fdc7cf79f781.jpg
		//filePath: C:\javaStudy\\upload\1595320356900108caa4b-35b1-4d0c-a72f-fdc7cf79f781.jpg
		//fileSize: 21865
		
		return saveName;
	}
	
}
