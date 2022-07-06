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
        

    <div class="ArticleContentBox">
      <div class="container">
        <div class="header">
          <div class="header__title">
            
            <span><h2><c:out value="${ansQNAReq.title}" /></h2></span>
          </div>
          <div class="header__nickname">
            <span>${ansQNAReq.nickname}</span>
          </div>
          <div class="header__date">
          	<span><h6>${ansQNAReq.qnaDate}</h6></span>
          	<span>        	
          	<input id="minus" type="button" value="-">
          	<input id="plus" type="button" value="+">          	
          	</span>
          </div>
        </div>
        <form action="answerQNA.do" method="post">
        <input type="hidden" name="no" value="${ansQNAReq.qnaNo}">
        <div class="content">
         	 ${ansQNAReq.content}
          <hr>
          <textarea name="answerContent">#${ansQNAReq.category}</textarea>
        </div>
        <div class="center">
        <button class="navbar-btn nav-button wow fadeInRight" data-wow-delay="0.48s" type="submit">
						제출
						</button>
		</form>
		</div>
      </div>
    </div>
        
<script>
const minus = document.getElementById("minus");
const plus = document.getElementById("plus");


let fontsize = 16;


function onMinusClick() {
	if(fontsize > 4){
		fontsize -= 2;
  document.querySelector(".content").style.fontSize = fontsize + "px";
  }
}
function onPlusClick() {
  if(fontsize < 50){
		fontsize += 2;
  document.querySelector(".content").style.fontSize = fontsize + "px";
  }
}

minus.addEventListener("click", onMinusClick);
plus.addEventListener("click", onPlusClick);
</script>

<%@ include file="include/footer.jspf" %>        