<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jspf"%>
<%@ include file="include/myPageHeader.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>


<section id="" class="">
	<br>
	<h4 class="text-uppercase wow fadeInLeft animated"
		style="text-align: center;">회원정보 수정</h4>
	
	
	<form action="read.do" method="post">

		<div class="edit-user-info__wrap container">
				<div class="form-group row ">
					<label for="staticEmail" class="col-sm-2 col-form-label">아이디</label>
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="id" value="${param.id}">
					</div>
				</div>

				<div class="form-group row">
					<label for="inputtext" class="col-sm-2 col-form-label">닉네임</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="nickname"
							value="${param.nickname}">
							<!-- 공백에러시 사용하는 태그  닉네임 에러 추가해야됨-->
							<c:if test="${errors.nickname}">닉네임을 입력하세요.</c:if>
							<!-- 닉네임 중복확인 에러  -->
							<c:if test="${errors.duplicateId}">이미 사용중인 닉네임 입니다.</c:if>
					</div>
				</div>


				<div class="form-group row ">
					<label for="staticEmail" class="col-sm-2 col-form-label">이름</label>
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="name" value="${param.name}">
					</div>
				</div>



					<div class="form-group row ">
						<label for="staticEmail" class="col-sm-2 col-form-label">생년월일</label>
						<div class="col-sm-10">
							<div class="DayPickerInput">
								<input type="text" readonly class="form-control-plaintext"
									placeholder="YYYY-MM-DD" class="form-control date-input__text"
									id="birth" value="${param.birth}">
							</div>
						</div>
					</div>




				<div class="form-group row">
					<label for="inputtext" class="col-sm-2 col-form-label">주소</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="address"
							value="${param.address}">
							<!-- 공백에러시 사용하는 태그  닉네임 에러 추가해야됨-->
							<c:if test="${errors.address}">주소를 입력하세요.</c:if>
					</div>
				</div>


				<div class="form-group row ">
					<label for="staticEmail" class="col-sm-2 col-form-label">핸드폰
						번호</label>
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="phoneNum" value="${param.phoneNum}">
					</div>
				</div>




				<div class="form-group row ">
					<label for="staticEmail" class="col-sm-2 col-form-label">이메일
					</label>
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="email" value="${param.email}">
					</div>
				</div>


			<div style="text-align: center;">
				<button type="button" style="display: inline-block;"
					class="btn btn-warning">회원번호 수정하기</button>
			</div>
			
			</div>
	</form>
	
	<div style="text-align: center;">

		<a class="edit-user-info__header__withdrawals" href="/withdrawals/new">비밀번호
			변경하기</a>
	</div>
	<div style="text-align: center;">
		<a class="edit-user-info__header__withdrawals" href="/withdrawals/new">탈퇴하기</a>
	</div>

	</form>
</section>
<br>
<br>
<br>
<br>



<%@ include file="include/footer.jspf"%>
