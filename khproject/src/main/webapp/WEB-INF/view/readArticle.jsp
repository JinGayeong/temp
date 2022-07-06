<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ include file="include/header.jspf"%>

<style>
	.table-size {
		width: 600px;
		height: 200px;
	}

	.margin-space {
		margin-top: 40px;
		margin-left:450px;
		margin-bottom:40px;
	}
</style>

<table border="1" width="100%" class="margin-space table-size">
<tr>
	<td>번호</td>
	<td>${articleData.article.number}</td>
</tr>
<tr>
	<td>작성자</td>
	<td>${articleData.article.writer.name}</td>
</tr>
<tr>
	<td>제목</td>
	<td><c:out value='${articleData.article.title}' /></td>
</tr>
<tr>
	<td>내용</td>
	<td><u:pre value='${articleData.content}'/></td>
</tr>
<tr>
	<td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}" />
		<a href="list.do?pageNo=${pageNo}">[목록]</a>
		<c:if test="${authUser.id == articleData.article.writer.id}"> <!-- 로그인 한 아이디 = 글 작성자 아이디인지 확인 -->
		<a href="modify.do?no=${articleData.article.number}">[게시글수정]</a> <!-- 같을 경우에만 게시글 수정,삭제 가능 -->
		<a href="delete.do?no=${articleData.article.number}">[게시글삭제]</a> <!-- 아닌 경우는 보이지 않는다 -->
		</c:if>
	</td>
</tr>
</table>


<%@ include file="include/footer.jspf"%>