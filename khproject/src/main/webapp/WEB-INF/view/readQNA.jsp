<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

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
            
            <span><h2><c:out value="${qnaData.qna.qnaTitle}" /></h2></span>
          </div>
          <div class="header__nickname">
            <span>${qnaData.qna.writer.nickname}</span>
          </div>
          <div class="header__date">
          	<span><h6><fmt:formatDate pattern = "yyyy-MM-dd hh:mm" value="${qnaData.qna.qnaDate}"/></h6></span>
          	<span>        	
          	<a href="${pageContext.request.contextPath}/info/deleteQNA.do?no=${qnaData.qna.qnaNo}" onclick="confirm('삭제하시겠습니까?')">삭제</a>
          	<a href="modifyQNA.do?no=${qnaData.qna.qnaNo}">수정</a>
          	<input id="minus" type="button" value="-">
          	<input id="plus" type="button" value="+">          	
          	</span>
          </div>
        </div>
        <div class="content">
          <u:pre value="${qnaData.content.qnaContent}" />
          <c:if test="${qnaData.content.answerContent != null}">
          <hr>
          ${qnaData.content.answerContent}
          </c:if>
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



function deleteConfirm(){
	var confirmDelete = confirm("삭제하시겠습니까?");
	if(confirmDelete){
		location.href = "${pageContext.request.contextPath}/info/deleteQNA.do?no=${qnaData.qna.qnaNo}";
	}else{
		return false;
	}
}

function modifyConfirm(){
	
		location.href = "${pageContext.request.contextPath}/info/modifyQNA.do?no=${qnaData.qna.qnaNo}";
	
}


</script>        
<%@ include file="include/footer.jspf" %>
        