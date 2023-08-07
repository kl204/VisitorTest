<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="../resources/css/home.css" rel="stylesheet" type="text/css">
</head>
<body>

<table>
	<thead>
		<tr><td colspan="6"><h1>방명록</h1></td></tr>
		<tr>
			<td colspan="6">
				<select id="searchType">
					<option name="searchOption" value="selectAll">제목명</option>
				</select>
				<input type="text" id="searchText"/>
				<button type="button" id="search">조회</button>
				</td>
		</tr>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회횟수</th>
			<th>작성시간</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
<c:forEach var="text" items="${pd.list}">
		<tr>
			<td>${text.seq}</td>
			<td><a href="./content?textNum=${text.seq}">${text.title}</a></td>
			<td>${text.writer}</td>
			<td>${text.readCount}</td>
			<td>${text.createdDate}</td>
			<td><a href="delete?textNum=${text.seq}">삭제</a></td>
		</tr>
</c:forEach>
		<tr>
			<td colspan="6">${pd.getNavBar()}</td>
		</tr>


		<tr>
			<td colspan="6">
			<button type="button" id="regist-button">작성</button>

			</td>
		</tr>
	</tbody>
</table>
<script type="text/javascript">
$("#regist-button").click(function() {
	window.location.href = "regist_view";
});

$("#logout").click(function() {
	let logout = true;
	window.location.href = "logout?flag=true";
});

$("#search").click(function() {
	
	let text = document.getElementById("searchText").value;
	
 	window.location.href = "search?text=" + text ; 
});

	
</script>
<script type="text/javascript" src="../resources/js/book.js"></script> 
</body>
</html>
