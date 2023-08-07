<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 작성</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="../resources/css/home.css" rel="stylesheet" type="text/css">

</head>
<body>

<table>
	<thead>
		<tr>
			<th colspan ="4"><h1>방명록 조회</h1></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>글번호 : ${list.seq }</td>
			<td>작성자 : ${list.writer }</td>
			<td>글제목 : ${list.title }</td>
			<td>작성일 : ${list.createdDate}</td>
		</tr>
		<tr>
			<td style="width:80%" colspan ="4"><c:out value="${list.content}" /></td>
		</tr>	
		<tr>
			<td colspan="4">파일 : ${list.attatchData} <br> <a href="download.do?fileName=${list.attatchData}">다운로드</a></td>
		</tr>		
		<tr>
			<td style="width:10%">댓글 작성</td>
			<td colspan="2"> 작성자 : <input type="text" size=10 id="commentWriter"/><br>
							 내용 : <input type="text" size=40 id="commentContent"/></td>
							 <td><button type="button" id="registComment">등록</button></td>
		</tr>
		<tr>
			<td colspan="4">전체 댓글 ${commentLists.size()} 개</td>
		</tr>
		<!-- 여기다 댓글 데이터 뿌리기 -->

<c:forEach var="comList" items="${commentLists}">		
		<tr id="commentList">	
			<td id="seq">${comList.seq}</td>
			<td style="width:10%">${comList.writer}</td>
			<td colspan="2">${comList.content}</td>
			<td><button type="button" id="like">좋아요</button> : ${comList.commentLike} <button type="button" id="hate">싫어요</button> : ${comList.commentHate} <button>삭제</button></td>		
		</tr>
</c:forEach>

		<tr>
			<td colspan ="4"><button type="button" id="returnList">방명록 돌아가기</button></td>
		</tr>
	</tbody>
	
</table>
<script type="text/javascript">
$(document).ready(function(){
	$("#returnList").click(function() {
		window.location.href = "./list.do";		
	});
	
	$("#registComment").click(function() {
		
		let writer = $('#commentWriter').val();
		let content = $('#commentContent').val();
		let boardSeq = ${list.seq};
		
		$.ajax({
			  type: "GET", 
			  url: "../comment/regist?writer=" + writer + "&content=" + content + "&boardSeq=" + boardSeq, 
			  dataType: "json",
			  success: function (data){
			  console.log("success");
			  console.log(data);			 
			  
			  window.location.href = "./content?textNum=" + ${list.seq};		
			       
			  },error: function(e){				    	    	
				   	console.log(e);			    	  
		   		}

		});
	
	});
	
	$("#like").click(function() {
			
/* 		let seq =${comList.seq};
		
		console.log("like : " + seq);

		$.ajax({
			  type: "GET", 
			  url: "../comment/like?seq=" + seq, 
			  dataType: "json",
			  success: function (data){
			  console.log("success");
			  console.log(data);			 
			  
			  window.location.href = "./content?textNum=" + ${list.seq};		
			       
			  },error: function(e){				    	    	
				   	console.log(e);			    	  
		   		}

		}); */
		

	
	});
	
	
	
	
});
</script>
</body>
</html>