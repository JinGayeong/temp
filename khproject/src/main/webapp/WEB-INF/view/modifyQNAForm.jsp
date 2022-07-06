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
				<form action="modifyQNA.do" method="post">
					<div class="qnaform__category outside">					
						<span class="left">				
								분류		
						</span>
						<input type="hidden" name="no" value="${modQNAReq.qnaNo}">
						<span class="right">
							<select id="category" name="category">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
							</select>
						</span>
											
					</div>
					<div class="qnaform__title outside">					
						<span class="left">제목</span>
						<span class="right"><input name="qnaTitle" value="${modQNAReq.title}"></span>					
					</div>
					<div class="qnaform__content outside">				
						<span class="left">내용</span>
						<span class="right"><textarea name="qnaContent">${modQNAReq.content}</textarea></span>					
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
        