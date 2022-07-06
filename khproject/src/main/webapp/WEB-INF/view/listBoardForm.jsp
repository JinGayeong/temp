<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="include/header.jspf"%>

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


<div class="page-head">
	<div class="container">
		<div class="row">
			<div class="page-head-content">
				<h1 class="page-title">인테리어 견적 신청</h1>
			</div>
		</div>
	</div>
</div>

<!-- Content Wrapper. Contains page content -->
	<div class="container">
		<div class="row">
			<table class="table table-bordered margin-space" style="text-align: center; border: 1px solid #dddddd">
				<tbody>
					<tr>
						<th width="60" class="text-center">번호</th>
						<th width="130" class="text-center">닉네임</th>
						<th width="90"><select name="sch_category_2"
							id="category2_submit">
								<option value="">시공분야</option>
								<option value="A">종합</option>
								<option value="A1">도배</option>
								<option value="A2">장판</option>
								<option value="A3">마루</option>
								<option value="A4">욕실</option>
								<option value="A5">주방</option>
								<option value="A6">필름</option>
								<option value="A7">타일</option>
								<option value="A8">도어</option>
						</select></th>
						<th class="text-center">제목</th>
						<th width="140" class="text-center">지역</th>
						<th width="140" class="text-center">등록일</th>
						<!-- <th width="140" class="text-center">수정일</th> -->
						<th width="60" class="text-center">조회수</th>
					</tr>
	
					<c:if test="${boardPage.hasNoBoard()}">
						<tr>
							<td colspan="4">게시글이 없습니다.</td>
						</tr>
					</c:if>
	
					<c:forEach var="board" items="${boardPage.content}">
						<tr>
							<td>${board.boardNo}</td>
							<td>${board.writer.nickname}</td>
							<td>${board.part}</td>
							<!-- <td><span class="blue">종합</span></td> -->
							<td><a
								href="read.do?no=${board.boardNo}&pageNo=${boardPage.currentPage}">
									<c:out value="${board.title}" />
							</a></td>
							<td>${board.address}</td>
							<td><fmt:formatDate value="${board.regDate}"
									pattern="yyyy년 MM월 dd일" /></td>
							<%-- <td><fmt:formatDate value="${board.modifiedDate}"
									pattern="yyyy년 MM월 dd일" /></td>    목록에서 게시글 수정일 부분 --%>
							<td>${board.view_count}</td>
	
						</tr>
					</c:forEach>
	
					<c:if test="${boardPage.hasBoard()}">
						<tr>
							<td colspan="7"><c:if test="${boardPage.startPage > 5}">
									<a href="list.do?pageNo=${boardPage.startPage - 5}">[이전]</a>
								</c:if> <c:forEach var="pNo" begin="${boardPage.startPage}"
									end="${boardPage.endPage}">
									<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
								</c:forEach> <c:if test="${boardPage.endPage < boardPage.totalPages}">
									<a href="list.do?pageNo=${boardPage.startPage + 5}">[다음]</a>
								</c:if> 
					</c:if>  
				</tbody>
			</table>
	
			<div class="margin-space" style="text-align: right">
				<a href="write.do" class="btn btn-primary ">게시글 작성</a>
			</div>
			

	</div>
</div>

<%@ include file="include/footer.jspf"%>