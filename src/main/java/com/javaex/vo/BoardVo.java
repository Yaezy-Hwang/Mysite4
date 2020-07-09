package com.javaex.vo;

public class BoardVo {

	private int ro;
	private int no;
	private String title;
	private String content;
	private int hit;
	private String date;
	private int userNo;
	private String name;
	
	public BoardVo() {}

	public BoardVo(int ro, int no, String title, int hit, String date, int userNo, String name) {
		this.ro = ro;
		this.no = no;
		this.title = title;
		this.hit = hit;
		this.date = date;
		this.userNo = userNo;
		this.name = name;
	}
	
	public BoardVo(int no, String name, int hit, String date, String title, String content, int userNo) {
		this.no = no;
		this.name = name;
		this.hit = hit;
		this.date = date;
		this.title = title;
		this.content = content;
		this.userNo = userNo;
	}


	public int getRo() {
		return ro;
	}

	public void setRo(int ro) {
		this.ro = ro;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BoardVo [ro=" + ro + ", no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit
				+ ", date=" + date + ", userNo=" + userNo + ", name=" + name + "]";
	}

}
