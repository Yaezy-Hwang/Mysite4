package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao dao;

	 public List<GalleryVo> showList() { 
		System.out.println("galService.갤러리 보이기");
		
	 	return dao.selectList(); 
	 }

	public void upload(GalleryVo vo, MultipartFile file) {
		System.out.println("galService.사진 올리기");

		String saveDir = "C:\\javaStudy\\upload";

		//오리지날 이름
		vo.setOrgName(file.getOriginalFilename());

		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		// 저장 파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		vo.setSaveName(saveName);

		// 파일 경로
		String filePath = saveDir + "\\" + saveName;
		vo.setFilePath(filePath);

		// 파일 사이즈
		long fileSize = file.getSize();
		vo.setFileSize(fileSize);

		// 파일을 서버에 복사
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//db저장
		dao.insert(vo);
	}

	public int delete(int no) {
		System.out.println("galService.사진 삭제");
		
		return dao.delete(no);
	}

	public GalleryVo showImgInfo(int imgNo) {
		System.out.println("galService.사진 1개 정보 가져오기");
		
		return dao.selectImgInfo(imgNo);
	}

}
