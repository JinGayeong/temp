<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-head"> 
            <div class="container">
                <div class="row">
                    <div class="page-head-content">
                        <h1 class="page-title">1:1문의</h1>  
                </div>
            </div>
        </div>
        </div>
        <!-- End page header -->




    <!-- board search area -->
    <div id="board-search">
        <div class="container">
            <div class="search-window">
                <form action="">
                    <div class="search-wrap">  
 				      <label for="search" class="blind">공지사항 내용 검색</label>
		                 <input id="search" type="search" name="" placeholder="검색어를 입력해주세요." value="">
                        <button type="submit" class="btn btn-dark">검색</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
   
  <!-- board list area -->
    <div id="board-list">
        <div class="container">
            <table class="board-table">
                <thead>
                <tr>
                    <th scope="col" class="th-num">번호</th>
                    <th scope="col" class="th-title">제목</th>
                    <th scope="col" class="th-100">닉네임</th>
                    <th scope="col" class="th-100">등록일</th>
                    <th scope="col" class="th-100">상태</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${qnaPage.hasNoArticles()}">
                	<tr>
						<td colspan="4">게시글이 없습니다.</td>
					</tr>
                </c:if>
                <c:forEach var="qna" items="${qnaPage.content}">
                <tr>
                    <td><c:out value="${qna.qnaNo}"/></td>
                    <th class="title">
                      <a href="read.do?no=${qna.qnaNo}&pageNo=${qnaPage.currentPage}">
                      <c:out value="${qna.qnaTitle}" />
                      </a>
                    </th>
                    <td class="nickname">
                    	<c:out value="${qna.writer.nickname}" />
                    </td>
                    <td class="date">
                  	  <fmt:formatDate pattern = "yy-MM-dd" value="${qna.qnaDate}"/>
                    </td>
                    <c:if test="${qna.status == 0}">
                    	<td class="wating td-100">답변대기</td>
                    </c:if>
                    <c:if test="${qna.status == 1}">
                    	<td class="complete td-100">답변완료</td>
                    </c:if>
                </tr>
			</c:forEach>
                <c:if test="${qnaPage.hasArticles()}">
	<tr>
		<td colspan="4">
			<c:if test="${qnaPage.startPage > 5}">
			<a href="info.do?pageNo=${qnaPage.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" 
					   begin="${qnaPage.startPage}" 
					   end="${qnaPage.endPage}">
			<a href="info.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${qnaPage.endPage < qnaPage.totalPages}">
			<a href="info.do?pageNo=${qnaPage.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
                </tbody>
            </table>
        </div>
    </div>




<%@ include file="include/footer.jspf" %>
        