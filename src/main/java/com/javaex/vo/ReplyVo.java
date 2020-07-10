package com.javaex.vo;

public class ReplyVo {

	private int ro;
	private int no;
	private int userNo;
	private String title;
	private String name;
	private String content;
	private int hit;
	private String date;
	private int groupNo;
	private int orderNo;
	private int depth;
	private String del;
	
	public ReplyVo() {}
	
	//게시글 read
	public ReplyVo(int no, int userNo, String title, String name,String content, int hit, String date, String del) {
		this.no = no;
		this.userNo = userNo;
		this.title = title;
		this.name = name;
		this.content = content;
		this.hit = hit;
		this.date = date;
		this.del = del;
	}

	//게시글 리스트 출력
	public ReplyVo(int no, int userNo, String title, int hit, String date, int groupNo, int orderNo, int depth, String del) {
		this.no = no;
		this.userNo = userNo;
		this.title = title;
		this.hit = hit;
		this.date = date;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.del = del;
	}

	public int getRo() {
		return ro;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "ReplyBoardVo [no=" + no + ", userNo=" + userNo + ", title=" + title + ", content=" + content + ", hit="
				+ hit + ", date=" + date + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth
				+ ", del=" + del + "]";
	}
	
}
