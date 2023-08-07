<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bitedu.bipa.KSG.vo.MemberVO"%>

<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>

<html>
<head>
 <style>
   .no-underline{
      text-decoration:none;
   }
 </style>
  <meta charset="UTF-8">
  <title>Menu</title>
</head>
<body>
     <% MemberVO member = (MemberVO)session.getAttribute("login");

		String memberId ="";
		String memberGrade = "";
		int memberSeq = 0;
		boolean memGrade = false;
		boolean flag = false;
		
		if(member !=null){
			memberGrade = member.getUserGrade();
			
			memGrade = memberGrade.equals("2");
			
			memberSeq = member.getUserSeq();
		}
		
		%>
	<h1>사이드 메뉴</h1>
	<h1>

          <%if(memGrade){ %>

            <a href="${contextPath}/member/list.do"  class="no-underline">회원관리</a><br>
			<% } else { %>
			<a href="${contextPath}/member/viewUpdate.do?memberSeq=<%=memberSeq%>"  class="no-underline">회원관리</a><br>
	        <% } %>

		<a href="${contextPath}/visitor/list.do"  class="no-underline">방명록</a><br>
	</h1>
	 
</body>
</html>