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
	<section>		
		<div class="container">
			<div class="qnaform">
				<form action="${pageContext.request.contextPath}/noticewrite.do" method="post">
					
					<div class="qnaform__title outside">					
						<span class="left">제목</span>
						<span class="right"><input name="title"></span>					
					</div>
					<div class="qnaform__content outside">				
						<span class="left">내용</span>
						<span class="right"><textarea name="content"></textarea></span>					
					</div>
					<div class="qnaform__submit">
						<button class="navbar-btn nav-button wow fadeInRight" data-wow-delay="0.48s" type="submit">
						업로드
						</button>
					</div>			
				</form>
			</div>
		</div>
</section>		


    <!-- board search area -->
    




<%@ include file="include/footer.jspf" %>
        