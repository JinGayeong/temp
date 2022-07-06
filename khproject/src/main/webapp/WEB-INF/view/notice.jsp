<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-head"> 
            <div class="container">
                <div class="row">
                    <div class="page-head-content">
                        <h1 class="page-title">공지사항</h1>  
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
               	 <div class="collapse navbar-collapse yamm" id="navigation">
						<div class="button navbar-right">
							<a href="<%=request.getContextPath()%>/noticewrite.do"
								class="navbar-btn nav-button wow fadeInRight"
								data-wow-delay="0.48s">공지사항 작성</a>
						</div>
					</div>
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
                    <th scope="col" class="th-date">등록일</th>
                    
                    
<%-- 	<%if(rank.equals(1)) {
		%> --%>

					
					
<%-- 		<% } %> --%>


				</tr>
                </thead>
                <tbody>
                <c:if test="${noticePage.hasNoArticles()}">
                	<tr>
						<td colspan="4">게시글이 없습니다.</td>
					</tr>
                </c:if>
                <c:forEach var="notice" items="${noticePage.content}">
                <tr>
                    <td><c:out value="${notice.noticeNo}"/></td>
                    <th class="title">
                      <a href="noticeread.do?no=${notice.noticeNo}&pageNo=${noticePage.currentPage}">
                     <!--  noticeread.do 로 하면 되려나 -->
                     
                      <c:out value="${notice.noticeTitle}" />
                      </a>
                    </th>
                    <td class="date">
                  	  <fmt:formatDate pattern = "yy-MM-dd" value="${notice.date}"/>
                    </td>
                </tr>
		</c:forEach>
                <c:if test="${noticePage.hasArticles()}">
	<tr>
		<td colspan="4">
			<c:if test="${noticePage.startPage > 5}">
			<a href="notice.do?pageNo=${noticePage.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" 
					   begin="${noticePage.startPage}" 
					   end="${noticePage.endPage}">
			<a href="notice.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${noticePage.endPage < noticePage.totalPages}">
			<a href="notice.do?pageNo=${noticePage.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
                </tbody>
            </table>
        </div>
    </div>




<%@ include file="include/footer.jspf" %>
        