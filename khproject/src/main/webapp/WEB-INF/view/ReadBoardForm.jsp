<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="include/header.jspf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 
<%
// 줄바꿈     
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
%> 

<style>
.margin-space {
	margin-top: 40px;
	margin-left: 15px;
	margin-bottom: 40px; 
}

.text-center {
	text-align: center;
}

.btn-primary {
	color: #fff;
	background-color: #fdc600;
	border-color: #fdc600;
	border-radius: 1px;
	padding: 10px 20px;
	font-weight: 600;
}
</style>


<!-- Content Wrapper. Contains page content -->
<!-- Content Header (Page header) -->

<div class="container">
	<div class="row">
		<table class="table table-bordered margin-space"
			style="border: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="3" class="text-center">시공 의뢰 게시글</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="90" class="text-center">번호</th>
					<td>${boardData.board.boardNo}</td>
				</tr>
				<tr>
					<th class="text-center">제목</th>
					<td><c:out value='${boardData.board.title}' /></td>
				</tr>
				<tr>
					<th class="text-center">작성자</th>
					<td>${boardData.board.writer.nickname}</td>
				</tr>
				<tr>
					<th class="text-center">시공분야</th>
					<td><u:pre value='${boardData.board.part}' /></td>
				</tr>
				<tr>
					<th class="text-center">주거유형</th>
					<td><u:pre value='${boardData.board.category}' /></td>
				</tr>
				<tr>
					<th class="text-center">주소</th>
					<td><u:pre value='${boardData.board.address}' /></td>
				</tr>
				<tr>
					<th class="text-center">평수(면적)</th>
					<td><c:out value='${boardData.board.area}' /></td>
				</tr>
				<tr>
					<th width="140" class="text-center">시공 시작일</th>

					<fmt:parseDate var='startDate' pattern="yyyy-MM-dd"
						value="${boardData.board.startDate}" scope="page" />

					<c:choose>
						<c:when test="${startDate eq 'Sat Jan 01 00:00:00 KST 1'}">
							<td><c:out value='미정' /></td>
						</c:when>
						<c:otherwise>       
						<td><fmt:formatDate value="${startDate}"
									pattern="yyyy년 MM월 dd일" /></td>
   				 		</c:otherwise>
					</c:choose>

				</tr>
				<tr>
					<th class="text-center">시공 마감일</th>
					<fmt:parseDate var='endDate' pattern="yyyy-MM-dd"
						value="${boardData.board.endDate}" scope="page" />
					<c:choose>
						<c:when test="${endDate eq 'Sat Jan 01 00:00:00 KST 1'}">
							<td><c:out value='미정' /></td>
						</c:when>
						<c:otherwise>       
						<td><fmt:formatDate value="${endDate}"
									pattern="yyyy년 MM월 dd일" /></td>
   				 		</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<th class="text-center">예산</th>
					<c:choose>
						<c:when test="${boardData.board.budget eq '-1'}">
							<td><c:out value='업체 협의' /></td>
						</c:when>
						<c:otherwise>       
						<td><u:pre value='${boardData.board.budget}' /></td>
   				 		</c:otherwise>
					</c:choose>

				</tr>
				<tr>
					<th class="text-center">이미지</th>
						<c:choose>
						<c:when test="${boardData.board.image eq '등록된 이미지가 없습니다.'}">
							<td><c:out value='등록된 이미지가 없습니다.' /></td>
						</c:when>
						<c:otherwise>       
							<td><a href="fileDown.do?file_name=${boardData.board.image}">${boardData.board.image}</a></p>
							
											<c:forEach var="file" items="${tblAttachList.files}">

<!-- 								<p> -->
<%-- 									파일명 : <a href="fileDown.do?file_name=${file.fullName}">${file.fullName}</a> --%>
<!-- 								</p> -->

								<c:set var="fname" value="${file.fullName}" />
								<c:choose>
									<c:when test="${fn:endsWith(fname, '.jpg')==true}">
										<img src="/khproject/upload/${file.fullName}" width="500" />
									</c:when>
									<c:when test="${fn:endsWith(fname, '.png')==true}">
										<img src="/khproject/upload/${file.fullName}" width="500" />
									</c:when>
									<c:when test="${fn:endsWith(fname, '.gif')==true}">
										<img src="/khproject/upload/${file.fullName}" width="500" />
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>

							</c:forEach></td>
   				 		</c:otherwise>
					</c:choose>


<%-- 					<td><a href="fileDown.do?file_name=${boardData.board.image}">${boardData.board.image}</a></p></td> --%>
							
<!-- 이미지 출력기능  -->
			</td>

				</tr>
				<tr>
					<th class="text-center">요청사항</th>
					<%-- 					<td><c:out value='${boardData.board.require}' /></td> --%>
					<td>${fn:replace(boardData.board.require, cn, br)}</td>

				</tr>
			</tbody>
		</table>


		<div class="margin-space" style="text-align: right">
			<c:set var="pageNo"
				value="${empty param.pageNo ? '1' : param.pageNo}" />
				
			<a href="list.do?pageNo=${pageNo}" class="btn btn-primary ">목록</a> 
			<a href="modify.do?no=${boardData.board.boardNo}" class="btn btn-primary">수정</a> 
			<a onclick="return confirm('정말로 삭제하시겠습니까?')"	href="delete.do?no=${boardData.board.boardNo}" class="btn btn-primary">삭제</a>
		</div>
		<br>

	</div>
</div>


<%@ include file="include/footer.jspf"%>
