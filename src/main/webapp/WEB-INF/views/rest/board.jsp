<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<%-- handlebars : JSON 형식으로 표현된 JavaScript 객체를 전달받아 HTML 코드로 변환하는
기능을 제공하는 자바스트크립 템플릿 라이브러리 --%>
<%-- => https://cdnjs.com 사이트에서 handlebars 라이브러리를 검색하여 포함 --%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<style type="text/css">
#btnDiv { margin: 10px; }

#insertDiv, #updateDiv {
	width: 240px;
	height: 80px;
	border: 2px solid black;
	background-color: gray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -40px;
	margin-left: -120px;
	padding: 5px;
	z-index: 100;
	display: none;
}
</style>
</head>
<body>
	<h1>RestBoard</h1>
	<div id="btnDiv">
		<button type="button" id="writeBtn">글쓰기</button>
	</div>
	
	<%-- 게시글 목록을 출력하는 영역 --%>
	<div id="restBoardListDiv"></div>
	
	<%-- 페이지 번호를 출력하는 영역 --%>
	<div id="pageNumDiv"></div>
	
	<%-- 신규 게시글을 입력하는 영역 --%>
	<div id="insertDiv">
		<table>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" id="insertWriter" class="insert"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="content" id="insertContent" class="insert"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" id="insertBtn">저장</button>
					<button type="button" id="cancelInsertBtn">취소</button>
				</td>
			</tr>
		</table>
	</div>
	
	<%-- 변경 게시글을 입력하는 영역 --%>
	<div id="updateDiv">
		<input type="hidden" name="num" id="updateNum">
		<table>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" id="updateWriter" class="update"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name="content" id="updateContent" class="update"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" id="updateBtn">변경</button>
					<button type="button" id="cancelUpdateBtn">취소</button>
				</td>
			</tr>
		</table>
	</div>	
	
	<%-- Handlebars 템플릿 코드(HTML) 작성 --%>
	<%-- => {{each}} 표현식을 이용하여 Array 객체에 대한 반복 처리 --%>
	<%-- => {{propertyName}} 표현식으로 객체 속성값을 표현 --%>
	<script id="template" type="text/x-handlebars-template">
	<table border="1" cellspacing="0" cellpadding="3">
		<tr>	
			<th width="50">번호</th>
			<th width="100">작성자</th>
			<th width="350">내용</th>
			<th width="200">작성일</th>
			<th width="50">변경</th>
			<th width="50">삭제</th>
		</tr>	
		
		{{#each .}}
		<tr>	
			<td align="center">{{num}}</td>
			<td align="center">{{writer}}</td>
			<td>{{content}}</td>
			<td align="center">{{regdate}}</td>
			<td align="center"><button type="button" onclick="modify({{num}});">변경</button></td>
			<td align="center"><button type="button" onclick="remove({{num}});">삭제</button></td>
		</tr>
		{{/each}}
	</table>
	</script>
	
	<script type="text/javascript">
	var page=1;//현재 요청 페이지 번호를 저장하는 전역변수
	boardDisplay(page);
	
	//게시글 목록을 요청하여 응답하는 함수
	function boardDisplay(pageNum) {
		page=pageNum;
		$.ajax({
			type: "get",
			url: "board_list?pageNum="+pageNum,
			dataType: "json",
			success: function(json) {
				if(json.restBoardList.length==0) {
					$("#restBoardListDiv").html("검색된 게시글이 존재하지 않습니다.");
					return;
				}
				
				//템플릿 코드를 반환받아 저장
				var source=$("#template").html();
				//템플릿 코드를 전달하여 템플릿 객체 생성
				var template=Handlebars.compile(source);
				//템플릿 객체에 자바스크립트 객체를 전달하여 HTML 코드로 변환하여 반환받아 응답 처리
				$("#restBoardListDiv").html(template(json.restBoardList));
				
				pageDisplay(json.pager);
			}, 
			error: function(xhr) {
				alert("에러코드 = "+xhr.status);
			}
		});
	}
	
	//페이지 번호를 응답하는 함수
	function pageDisplay(pager) {
		var html="";
		if(pager.startPage>pager.blockSize) {
			html+="<a href='javascript:boardDisplay(1);'>[처음]</a>";
			html+="<a href='javascript:boardDisplay("+pager.prevPage+");'>[이전]</a>";
		} else {
			html+="[처음][이전]";
		}
		
		for(var i=pager.startPage;i<=pager.endPage;i++) {
			if(pager.pageNum!=i) {
				html+="<a href='javascript:boardDisplay("+i+");'>["+i+"]</a>";
			} else {
				html+="["+i+"]";
			}
		}
		
		if(pager.endPage!=pager.totalPage) {
			html+="<a href='javascript:boardDisplay("+pager.nextPage+");'>[다음]</a>";
			html+="<a href='javascript:boardDisplay("+pager.totalPage+");'>[마지막]</a>";
		} else {
			html+="[다음][마지막]";
		}
		
		$("#pageNumDiv").html(html);
	}
	
	//글쓰기 버튼을 클릭한 경우 호출되는 이벤트 핸들러 함수 등록 - 게시글을 입력받아 저장
	$("#writeBtn").click(function() {
		//게시글 변경 관련 영역 초기화
		$(".update").val("");
		$("#updateDiv").hide();
		
		//게시글 입력 관련 영역 출력
		$("#insertDiv").show(300);
	});
	
	$("#insertBtn").click(function() {
		//입력태그의 입력값을 반환받아 저장
		var writer=$("#insertWriter").val();
		var content=$("#insertContent").val();
		
		if(writer=="") {
			alert("작성자를 입력해 주세요.");
			return;
		}
		
		if(content=="") {
			alert("내용을 입력해 주세요.");
			return;
		}
		
		
		//게시글 저장을 요청하여 응답 처리
		$.ajax({
			type: "post",
			url: "board_add",

			//headers : 요청 자원의 헤더정보를 변경하기 위한 속성
			// => content-type 속성으로 전달값에 대한 전송 형식 변경 
			//headers: {"content-type":"application/json"},
			contentType: "application/json",
			
			//JSON.stringify(Object object) : 자바스트립트 객체를 JSON 형식의 문자값으로 변경하는 메소드
			// => JSON 형식의 문자값을 Java 객체의 필드값으로 전달받아 저장
			data: JSON.stringify({"writer":writer, "content":content}),
			dataType: "text",
			success: function(text) {
				if(text=="success") {
					//게시글 입력 관련 영역 초기화
					$(".insert").val("");
					$("#insertDiv").hide(300);
					boardDisplay(1);
				}				
			}, 
			error: function(xhr) {
				alert("에러코드 = "+xhr.status);
			}
		});
	});
	
	$("#cancelInsertBtn").click(function() {
		$(".insert").val("");
		$("#insertDiv").hide(300);
	});
	
	//게시글의 [변경]을 누른 경우 호출되는 이벤트 핸들러 함수
	function modify(num) {
		//alert("num = "+num);
		$(".insert").val("");
		$("#insertDiv").hide();
		
		$("#updateDiv").show(300);
		
		//글번호를 전달하여 게시글을 반환받아 입력태그에 출력되도록 AJAX 요청
		$.ajax({
			type: "get",
			url: "board_view/"+num,
			dataType: "json",
			success: function(json) {
				$("#updateNum").val(json.num);
				$("#updateWriter").val(json.writer);
				$("#updateContent").val(json.content);
			},
			error: function() {
				alert("에러코드 = "+xhr.status);
			}
		});
	}
	
	$("#updateBtn").click(function() {
		//입력태그의 입력값을 반환받아 저장
		var num=$("#updateNum").val();
		var writer=$("#updateWriter").val();
		var content=$("#updateContent").val();
		
		if(writer=="") {
			alert("작성자를 입력해 주세요.");
			return;
		}
		
		if(content=="") {
			alert("내용을 입력해 주세요.");
			return;
		}
		
		$.ajax({
			type: "put",
			url: "board_modify",
			contentType: "application/json",
			data: JSON.stringify({"num":num, "writer":writer, "content":content}),
			dataType: "text",
			success: function(text) {
				$(".update").val("");
				$("#updateDiv").hide(300);
				boardDisplay(page);
			},
			error: function() {
				alert("에러코드 = "+xhr.status);
			}
		});
	});
	
	$("#cancelUpdateBtn").click(function() {
		$(".update").val("");
		$("#updateDiv").hide(300);
	});
	
	//게시글의 [삭제]을 누른 경우 호출되는 이벤트 핸들러 함수
	function remove(num) {
		if(confirm("정말로 게시글을 삭제 하시겠습니까?")) {
			$.ajax({
				type: "delete",
				url: "board_remove/"+num,
				dataType: "text",
				success: function(text) {
					boardDisplay(1);
				},
				error: function() {
					alert("에러코드 = "+xhr.status);
				}
			});
		}
	}
	</script>
</body>
</html>










