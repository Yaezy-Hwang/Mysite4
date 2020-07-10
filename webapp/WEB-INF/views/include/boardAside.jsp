<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		<div id="aside">
			<h2>게시판</h2>
			<ul>
				<li><a href="${pageContext.request.contextPath}/board/list?page=1">일반게시판</a></li>
				<li><a href="${pageContext.request.contextPath}/replyboard/list?page=1">댓글게시판</a></li>
			</ul>
		</div>