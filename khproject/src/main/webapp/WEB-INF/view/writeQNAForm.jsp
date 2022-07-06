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
	<section>		
		<div class="container">
			<div class="qnaform">
				<form action="${pageContext.request.contextPath}/info/QNAWrite.do" method="post">
					<div class="qnaform__category outside">					
						<span class="left">				
								분류		
						</span>
						<span class="right">
							<select id="category" name="category">
								<option value="1">전체</option>
								<option value="2">주문/결제</option>
								<option value="3">배송관련</option>
								<option value="4">취소/환불</option>
								<option value="4">반품/교환</option>
								<option value="4">로그인/회원정보</option>
								<option value="4">서비스/기타</option>
							</select>
						</span>					
					</div>
					<div class="qnaform__title outside">					
						<span class="left">제목</span>
						<span class="right"><input name="qnaTitle"></span>					
					</div>
					<div class="qnaform__content outside">				
						<span class="left">내용</span>
						<span class="right"><textarea name="qnaContent"></textarea></span>					
					</div>
					<div class="qnaform__submit">
						<button class="navbar-btn nav-button wow fadeInRight" data-wow-delay="0.48s" type="submit">
						제출
						</button>
					</div>			
				</form>
			</div>
		</div>
</section>		


    <!-- board search area -->
    




<%@ include file="include/footer.jspf" %>
        