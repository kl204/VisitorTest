<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="../resources/css/home.css" rel="stylesheet" type="text/css">
 <script type="text/javascript">
$(document).ready(function(){
	$('#login').on('click', function(){

		$('#frm').attr('action','./login.do');
		$('#frm').submit();
	});
	
	
});

</script>
</head>
<body>
${param.flag=='true'?"<script>alert('아이디 혹은 비번 확인하세요');</script>":""}
${param.flag=='loginPlz'?"<script>alert('로그인이 필요합니다!');</script>":""}

<form action="" method="post" id="frm">
<table>
	<tbody>
		<tr>
			<td colspan="3"><h1>로그인</h1></td>
		</tr>
		<tr>			
			<td><input type="text" name="id" placeholder="아이디를 입력하시오" /><br>
			<input type="text" name="pass" placeholder="비밀번호를 입력하시오" /><br>
			<button type="submit" id="login">로그인</button>
		</tr>
	
	</tbody>

</table>
</form>
</body>
</html>