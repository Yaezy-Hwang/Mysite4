<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css"
	rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/boardAside.jsp"></c:import>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="${pageContext.request.contextPath}/replyboard/search"
						method="get">
						<div class="form-group text-right">
							<input type="hidden" name="page" value="1">
							<input type="text" name="keyword">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bList}" var="post">
								<tr>
									<td>${count - (post.ro-1)}</td>
									<td class="text-left">
										<c:choose>
											<c:when test="${post.del eq 'true'}"> <!-- 삭제되었을 경우 -->
												삭제된 게시글입니다.
											</c:when>
											<c:when test="${post.depth eq 1}"> <!-- 댓글일 경우 -->
												<a href="${pageContext.request.contextPath}/replyboard/read?no=${post.no}"> Re: ${post.title}</a>
											</c:when>
											<c:otherwise> <!-- 일반 정상 게시물일 정우 -->
												<a href="${pageContext.request.contextPath}/replyboard/read?no=${post.no}">${post.title}</a>
											</c:otherwise>
										</c:choose>
									</td>
									<td>${post.name}</td>
									<td>${post.hit}</td>
									<td>${post.date}</td>
									<td><c:if test="${authUser.no eq post.userNo}">
											<!-- 로그인시 -->
											<a
												href="${pageContext.request.contextPath}/replyboard/delete?no=${post.no}">[삭제]</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div id="paging">
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/replyboard/list&page=1">◀</a></li>
							<c:forEach items="${arr}" var="page">

								<li <c:if test="${param.page eq page}"> class="active" </c:if>>
									<a href="${pageContext.request.contextPath}/replyboard/list?page=${page}">${page}</a>
								</li>

							</c:forEach>
							<li><a href="">▶</a></li>
						</ul>


						<div class="clear"></div>
					</div>
					<c:if test="${!empty authUser}"><!-- 로그인시 -->
						<a id="btn_write" href="${pageContext.request.contextPath}/replyboard/writeForm">글쓰기</a>
					</c:if>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<div id="footer">
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
