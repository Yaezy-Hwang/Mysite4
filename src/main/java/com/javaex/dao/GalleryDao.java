package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> selectList() {
		System.out.println("galDao.사진 가져오기");
		
		return sqlSession.selectList("gallery.selectList");
	}

	public void insert(GalleryVo vo) {
		System.out.println("galDao.사진 db저장");
		
		int inCount = sqlSession.insert("gallery.insert", vo);
		
		System.out.println(inCount+"개 저장");
	}

	public int delete(int no) {
		System.out.println("galDao.사진 db저장");
		
		int delCount = sqlSession.delete("gallery.delete", no);
		
		System.out.println(delCount+"개 삭제");
		
		return delCount;
	}

	public GalleryVo selectImgInfo(int imgNo) {
		System.out.println("galDao.사진 1개 정보 가져오기");
		
		return sqlSession.selectOne("gallery.selectImgInfo", imgNo);
	}



}
