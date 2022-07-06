<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="./include/header.jspf"%>
<%@ include file="./include/myPageHeader.jsp"%>

<div class="register-area" style="background-color: rgb(249, 249, 249);">
	<div class="container">
		<div class="col-md-6">
			<div class="box-for overflow">
				<div class="col-md-12 col-xs-12 register-blocks">
					<h2>회원정보 관리 :</h2>
					<form name="insForm" action="changePwd.do" method="post">
						<div class="form-group">
							<label for="newRank">회원분류</label>
							<input type="text" class="form-control" id="newRank" name="newRank" value="${authUser.rank}" readonly="readonly">
						</div>
						<div class="form-group">
							<label for="newCompanyno">사업자번호</label>
							<input type="text" class="form-control" id="newCompanyno" name="newCompanyno" value="${authUser.companyno}">
						</div>
						<div class="form-group">
							<label for="newId">아이디</label>
							<input type="text" class="form-control" id="newId" name="newId" value="${authUser.id}" readonly="readonly">
						</div>
						<div class="form-group">
							<label for="newNickname">닉네임</label>
							<input type="text" class="form-control" id="newNickname" name="newNickname" value="${authUser.nickname}">
						</div>
						<div class="form-group">
							<label for="newPw">비밀번호</label>
							<input type="password" class="form-control" id="newPw" name="newPw" value="${authUser.pw}">
						</div>
						<%-- <div class="form-group">
							<label for="confirmNewPw">비밀번호 확인</label>
							<input type="password" class="form-control" id="confirmNewPw" name="confirmNewPw" value="${authUser.pw}">
						</div> --%>
						<div class="form-group">
							<label for="newName">이름</label>
							<input type="text" class="form-control" id="newName" name="newName" value="${authUser.name}" readonly="readonly">
						</div>
						<div class="form-group">
							<label for="newBirth">생년월일</label>
							<input type="text" class="form-control" id="newBirth" name="newBirth" value="${authUser.birth}" readonly="readonly"
								onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" />
						</div>
						<div class="form-group">
							<label for="newAddress">주소</label>
							<input type="text" class="form-control" id="newAddress" name="newAddress" value="${authUser.address}">
						</div>
						<div class="form-group">
							<label for="newPhonenum">전화번호</label>
							<input type="text" class="form-control" id="newPhonenum" name="newPhonenum" value="${authUser.phonenum}"
								onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" />
						</div>
						<div class="form-group">
							<label for="newEmail">이메일</label>
							<input type="text" class="form-control" id="newEmail" name="newEmail" value="${authUser.email}">
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-default" style="float: left;">회원정보 변경</button>
							<a onclick="return confirm('정말로 탈퇴 하시겠습니까?')" href="${pageContext.request.contextPath}/member/delete.do?no=${authUser.id}" 
							class="btn btn-default" style="float: right;">회원탈퇴</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="./include/footer.jspf"%>