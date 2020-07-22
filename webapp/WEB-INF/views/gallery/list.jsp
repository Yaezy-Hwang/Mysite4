<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
				
					<c:if test="${!empty authUser}"> <!-- 로그인 시에만 버튼 보이기 -->
						<button id="btnImgUpload">이미지올리기</button>
						<div class="clear"></div>
					</c:if>
			
					<ul id="viewArea">
						
						<c:forEach items="${galList}" var="post">
							<li id="img-${post.no}">
								<div class="view" >
									<img class="imgItem" src="${pageContext.request.contextPath}/upload/${post.saveName}" data-imgno="${post.no}">
									<div class="imgWriter">작성자: <strong>${post.name}</strong></div>
								</div>
							</li>
						</c:forEach>
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath}/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
							<div id="errorMsg"><font color="red">파일을 선택해주세요</font></div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
						<input type="hidden" name="userNo" value="${authUser.no}">
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src ="" height="400px"> <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
						<p id="imgInfo"></p>
					</div>
					
				</div>
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger" id="btnDel" data-no="">삭제</button>
					<input type="hidden" id="authUserNo" name="authUserNo" value="${authUser.no}">
					</div>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">

	//업로드 버튼 클릭 시 모달창 up
	$("#btnImgUpload").on("click", function(){
		//오류메세지 숨기기
		$("#errorMsg").hide();

		console.log('업로드 버튼 클릭');

		$("#addModal").modal();
	})
	
	//업로드 모달창의 등록버튼 오류(파일 없음)
	$("#btnUpload").on("click", function(){
		if($("#file").val() == ""){
			console.log("이미지 없음");
			
			$("#errorMsg").show();
			
			return false;
		}
	});
	
	
	//이미지 클릭시 모달창 up
	$(".view").on("click", "img", function(){
		console.log('이미지 클릭');
		
		//src값 읽어오기
		var src = $(this).attr("src");
		$("#viewModelImg").attr("src", src);
		
		//사진 no 읽어오기
		var imgNo = $(this).data("imgno");
		
		//현재 로그인한 user no 읽어오기
		var authUserNo = $("#authUserNo").val();
		
		//데이터 전송
		$.ajax({

			//보낼 때 옵션
			url : "${pageContext.request.contextPath}/gallery/read",
			type : "post",
			data : {no: imgNo},

			//받을 때 옵션
			dataType : "json",
			success : function(imgVo) {
				console.log(imgVo);

				//메모 값 넣기
				$("#viewModelContent").text(imgVo.content);
				
				//이미지 정보 넣기
				var info = imgVo.orgName+" ("+imgVo.fileSize+" byte)";
				$("#imgInfo").text(info);

				//로그인 사용자 == 게시물주인 이면 삭제버튼 show
				if(authUserNo == imgVo.userNo){
					$("#btnDel").show();
					//삭제버튼에 data값 넣기
					$("#btnDel").data("no", imgNo);
					
				} else{
					$("#btnDel").hide();
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
		
		$("#viewModal").modal();
	})
	
	//모달창 삭제버튼 클릭 시
	$("#btnDel").on("click", function(){
		console.log('삭제 클릭');
		
		//데이터 수집
		var delNo = $("#btnDel").data("no");
		console.log("delNo: "+delNo);
		
		//데이터 전송
		$.ajax({

			//보낼 때 옵션
			url : "${pageContext.request.contextPath}/gallery/delete",
			type : "post",
			data : {no: delNo},

			//받을 때 옵션
			dataType : "json",
			success : function(count) {
				console.log(count);
				
				if(count == 1){
					//리스트 지우기
					$("#img-"+delNo).remove();
				} 
				
				//모달창 닫기
				$("#viewModal").modal("hide");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
		
	});


</script>

</html>

