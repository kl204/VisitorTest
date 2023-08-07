<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 작성</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="../resources/css/home.css" rel="stylesheet" type="text/css">

</head>
<body>
<form action="" method="post" id="frm" accept-charset="UTF-8"  enctype="multipart/form-data">
<table>
	<thead>
		<tr>
			<th colspan="2"><h1>방명록 글쓰기</h1></th>
		</tr>

	</thead>
	<tbody>
		<tr>
			<td>작성자 : <input type="text" name="writer"/></td>
			<td> 글제목 : <input type="text" name="title"/></td>
		</tr>
		<tr>
			<td colspan="2"><textarea rows="30" cols="60" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="file" name="dataImage"/>
			</td>
		</tr>
		<tr>
			<td colspan="2"><button type="button" id="regist-button">작성</button>
							<button type="button" id="return-button">리스트로 돌아가기</button>
			
			</td>
		</tr>
	</tbody>	
</table>

</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#regist-button").click(function() {
		$('#frm').attr('action','regist');
		$('#frm').submit();
		});	
	
	$("#return-button").click(function() {
		window.location.href = "./list.do";		
	});
});

</script>
</body>
</html>