<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<!-- 부트스트랩 -->
<link
	href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/guestbookAside.jsp"></c:import>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>ajax방명록 </h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>방명록</li>
						<li class="last">ajax방명록</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/guestbook/write"
					method="post">
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></th>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></th>
								<td><input id="input-pass" type="password" name="pw"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5">방명록을 써주세유!</textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button id="btnSubmit" type="submit">등록</button></td>
							</tr>
						</tbody>

					</table>
					<!-- //guestWrite -->
				</form>

				<div id="guestbookListArea"></div>

			</div>
			<!-- //guestbook -->

		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->


	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="delmodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label> <input type="password" name="modalPassword" id="modalPassword"><br>
					<input type="text" name="modalNo" value="" id="modalNo"> <br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
</body>

<script type="text/javascript">
	$(document).ready(function() {

		fetchList()

	});

	
	//삭제버튼 클릭 시
	$("#guestbookListArea").on("click", "a", function(){
		console.log('리스트 지역 클릭');
		event.preventDefault();
		
		//pw, no 값 초기화
		$("#modalPassword").val("");
		$("#modalNo").val("");

		var no = $(this).data("delno");
		//모달창에 no값 넣기
		$("#modalNo").val(no);

		//창 넣기
		$("#delmodal").modal();
	});
	
	//모달창에서 삭제버튼 클릭 시
	$("#btnDel").on("click", function(){
		console.log("모달창>삭제버튼");
		
		
		//데이터 수집
		var password = $("#modalPassword").val();
		var no = $("#modalNo").val();
		
		//데이터 전송
		$.ajax({

			//보낼 때 옵션
			url : "${pageContext.request.contextPath}/api/guestbook/delete",
			type : "post",
			//contentType : "application/json",
			data : {pw: password, no: no},

			//받을 때 옵션
			dataType : "json",
			success : function(count) {
				console.log(count);
				
				if(count == 1){
					//리스트 지우기
					$("#t-"+no).remove();
				} 
				
				//모달창 닫기
				$("#delmodal").modal("hide");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
		
		
	});

	//글쓰기 버튼 클릭 시
	$("#btnSubmit").on("click", function() {
		console.log("click");
		event.preventDefault();

		//데이터 추출
		var uname = $("#input-uname").val();
		var pass = $("#input-pass").val();
		var content = $("[name='content']").val();

		var guestbookVo = {
			"name" : uname,
			"pw" : pass,
			"content" : content
		};

		console.log(guestbookVo);

		$.ajax({

			//보낼 때 옵션
			url : "${pageContext.request.contextPath}/api/guestbook/write",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(guestbookVo),

			//받을 때 옵션
			dataType : "json",
			success : function(vo) {
				console.log(vo);
				render(vo, "up");

				//받은 다음 form box 비우기
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
	});

	/* 전체 리스트 불러오기 */
	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath}/api/guestbook/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: ”홍길동"},

			dataType : "json",
			success : function(gList) {
				console.log(gList);
				for (var i = 0; i < gList.length; i++) {
					render(gList[i], "down");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
	};

	//리스트 html 1개
	function render(book, direction) {
		var str = "";
		str += "<table id='t-" + book.no + "' class='guestRead'>";
		str += "<colgroup>";
		str += "	<col style='width: 10%;'>";
		str += "	<col style='width: 30%;'>";
		str += "	<col style='width: 50%;'>";
		str += "	<col style='width: 10%;'>";
		str += "</colgroup>";
		str += "	<tr>";
		str += "		<td>" + book.ro + "</td>";
		str += "		<td>" + book.name + "</td>";
		str += "		<td>" + book.date + "</td>";
		str += "		<td><a href='' data-delno='"+ book.no +"'>[삭제]</a></td>";
		str += "	</tr>";
		str += "	<tr>";
		str += "		<td colspan=4 class='text-left'>" + book.content + "</td>";
		str += "	</tr>";
		str += "</table>";

		if (direction == "up") {
			$("#guestbookListArea").prepend(str);
		} else if (direction == "down") {
			$("#guestbookListArea").append(str);
		} else {
			console.log("direction값 오류");
		}
	};
</script>
</html>