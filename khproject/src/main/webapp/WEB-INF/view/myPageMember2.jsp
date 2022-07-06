<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jspf"%>
<%@ include file="include/myPageHeader.jsp"%>



<!-- <div class="blog-lst col-md-9"> -->

<section id="information" class="add-comments">
	<br>
	<h4 class="text-uppercase wow fadeInLeft animated"
		style="text-align: center;">회원정보 수정</h4>
	<form>


		<!-- 	<form>
			<div class="form-group row ">
				<label for="staticEmail" class="col-sm-2 col-form-label">아이디</label>
				<div class="col-sm-10">
					<input type="text" readonly class="form-control-plaintext"
						id="staticEmail" value="아이디 가져오기">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="inputPassword"
						placeholder="Password">
				</div>
			</div>
		</form> -->



		<div class="edit-user-info__wrap container">
			<div class="edit-user-info__form-item">
				<div class="form-group row ">
					<label for="staticEmail" class="col-sm-2 col-form-label">아이디</label>
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="voId" value=" %vo.getId()%">
					</div>
				</div>
			</div>

			<div class="edit-user-info__form-item">

				<div class="form-group row">
					<label for="inputtext" class="col-sm-2 col-form-label">닉네임</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputNickname"
							value="%= vo.getNickname() %">
					</div>
				</div>

			</div>

			<div class="edit-user-info__form-item">
				<div class="form-group row ">
					<label for="staticEmail" class="col-sm-2 col-form-label">이름</label>
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="staticEmail" value="%vo.getName()%">
					</div>
				</div>
			</div>


			<div class="edit-user-info__form-item">

				<div class="edit-user-info__form-item">
					<div class="form-group row ">
						<label for="staticEmail" class="col-sm-2 col-form-label">생년월일</label>
						<div class="col-sm-10">
							<div class="DayPickerInput">
								<input type="text" readonly class="form-control-plaintext"
									placeholder="YYYY-MM-DD" class="form-control date-input__text"
									id="staticEmail" value="%vo.getbirth%">
							</div>
						</div>
					</div>
				</div>


			</div>

			<div class="edit-user-info__form-item">

				<div class="form-group row">
					<label for="inputtext" class="col-sm-2 col-form-label">주소</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputNickname"
							value="%= vo.getAddress() %">
					</div>
				</div>

			</div>

			<div class="edit-user-info__form-item">
				<div class="form-group row ">
					<label for="staticEmail" class="col-sm-2 col-form-label">핸드폰
						번호</label>
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="staticEmail" value="%vo.getphoneNum()%">
					</div>
				</div>
			</div>



			<div class="edit-user-info__form-item">
				<div class="form-group row ">
					<label for="staticEmail" class="col-sm-2 col-form-label">핸드폰
						번호</label>
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="staticEmail" value="%vo.getphoneNum()%">
					</div>
				</div>
			</div>


			<div class="edit-user-info__form-item">
				<div class="form-group row ">
					<label for="staticEmail" class="col-sm-2 col-form-label">이메일
					</label>
					<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext"
							id="staticEmail" value="%vo.getEmail()%">
					</div>
				</div>
			</div>


			<%-- <div class="edit-user-info__form-item">
				<div class="edit-user-info__form-item__title">
					이메일
					<div class="edit-user-info__form-item__title__require">* 필수항목</div>
				</div>
				<div class="expert-form-group edit-user-info__form-item__group">
					<div class="expert-form-group__content">
						<div class="expert-form-group__input">
							<div class="edit-user-info__form-item__field">
								<div class="input-group email-input">
									<span class="email-input__local"><label><input
											class="form-control" placeholder="이메일" size="1"
											value="<!-- 여기에 가져와야됨 -->"><span
											class="form-control-blind">이메일 앞 주소 입력하기</span></label></span><span
										class="email-input__separator">@</span><span
										class="email-input__domain"><label><select
											class="form-control"><option value="" disabled="">선택해주세요</option>
												<option value="naver.com">naver.com</option>
												<option value="hanmail.net">hanmail.net</option>
												<option value="daum.net">daum.net</option>
												<option value="gmail.com">gmail.com</option>
												<option value="nate.com">nate.com</option>
												<option value="hotmail.com">hotmail.com</option>
												<option value="outlook.com">outlook.com</option>
												<option value="icloud.com">icloud.com</option>
												<option value="_manual">직접입력</option></select><span
											class="form-control-blind">이메일 도메인 선택하기</span></label></span>
								</div>
								<div class="edit-user-info__form-item__field__warning">이메일을
									변경하시려면 운영자에게 이메일을 보내주세요.</div>
							</div>
						</div>
					</div>
				</div>
			</div> --%>



			<!-- <div class="edit-user-info__form-item">
				<div class="edit-user-info__form-item__title">생년월일</div>
				<div class="expert-form-group edit-user-info__form-item__group">
					<div class="expert-form-group__content">
						<div class="expert-form-group__input">
							<div class="edit-user-info__form-item__field">
								<div class="DayPickerInput">
									<input placeholder="YYYY-MM-DD"
										class="form-control date-input__text" value="1994-04-19">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div> -->
			<div style="text-align: center;">
				<button type="button" style="display: inline-block;"
					class="btn btn-warning">회원번호 수정하기</button>
			</div>
	</form>
	</div>
	<div style="text-align: center;">

		<a class="edit-user-info__header__withdrawals" href="/withdrawals/new">비밀번호
			변경하기</a>
	</div>
	<div style="text-align: center;">
		<a class="edit-user-info__header__withdrawals" href="/withdrawals/new">탈퇴하기</a>
	</div>

	</form>
</section>

<!-- 
<section id="request" class="add-comments">
	<h4 class="text-uppercase wow fadeInLeft animated">의뢰 정보</h4>
	<div class="proerty-th">
		<div class="col-sm-6 col-md-3 p0">
			<div class="box-two proerty-item">
				<div class="item-thumb">
					<a href="property-1.html"><img
						src="assets/img/demo/property-1.jpg"></a>
				</div>
				<div class="item-entry overflow">
					<h5>
						<a href="property-1.html">Super nice villa </a>
					</h5>
					<div class="dot-hr"></div>
					<span class="pull-left"><b>Area :</b> 120m </span> <span
						class="proerty-price pull-right">$ 300,000</span>
				</div>
			</div>
		</div>
 -->
<!-- <div class="col-sm-6 col-md-3 p0">
			<div class="box-two proerty-item">
				<div class="item-thumb">
					<a href="property-2.html"><img
						src="assets/img/demo/property-2.jpg"></a>
				</div>
				<div class="item-entry overflow">
					<h5>
						<a href="property-2.html">Super nice villa </a>
					</h5>
					<div class="dot-hr"></div>
					<span class="pull-left"><b>Area :</b> 120m </span> <span
						class="proerty-price pull-right">$ 300,000</span>
				</div>
			</div>
		</div>

		<div class="col-sm-6 col-md-3 p0">
			<div class="box-two proerty-item">
				<div class="item-thumb">
					<a href="property-3.html"><img
						src="assets/img/demo/property-3.jpg"></a>

				</div>
				<div class="item-entry overflow">
					<h5>
						<a href="property-3.html">Super nice villa </a>
					</h5>
					<div class="dot-hr"></div>
					<span class="pull-left"><b>Area :</b> 120m </span> <span
						class="proerty-price pull-right">$ 300,000</span>
				</div>
			</div>
		</div>

		<div class="col-sm-6 col-md-3 p0">
			<div class="box-two proerty-item">
				<div class="item-thumb">
					<a href="property-1.html"><img
						src="assets/img/demo/property-4.jpg"></a>

				</div>
				<div class="item-entry overflow">
					<h5>
						<a href="property-1.html">Super nice villa </a>
					</h5>
					<div class="dot-hr"></div>
					<span class="pull-left"><b>Area :</b> 120m </span> <span
						class="proerty-price pull-right">$ 300,000</span>
				</div>
			</div>
		</div>


		<div class="col-sm-6 col-md-3 p0">
			<div class="box-two proerty-item">
				<div class="item-thumb">
					<a href="property-3.html"><img
						src="assets/img/demo/property-2.jpg"></a>
				</div>
				<div class="item-entry overflow">
					<h5>
						<a href="property-3.html">Super nice villa </a>
					</h5>
					<div class="dot-hr"></div>
					<span class="pull-left"><b>Area :</b> 120m </span> <span
						class="proerty-price pull-right">$ 300,000</span>
				</div>
			</div>
		</div>

		<div class="col-sm-6 col-md-3 p0">
			<div class="box-two proerty-item">
				<div class="item-thumb">
					<a href="property-2.html"><img
						src="assets/img/demo/property-4.jpg"></a>
				</div>
				<div class="item-entry overflow">
					<h5>
						<a href="property-2.html">Super nice villa </a>
					</h5>
					<div class="dot-hr"></div>
					<span class="pull-left"><b>Area :</b> 120m </span> <span
						class="proerty-price pull-right">$ 300,000</span>
				</div>
			</div>
		</div>

		<div class="col-sm-6 col-md-3 p0">
			<div class="box-two proerty-item">
				<div class="item-thumb">
					<a href="property-1.html"><img
						src="assets/img/demo/property-3.jpg"></a>
				</div>
				<div class="item-entry overflow">
					<h5>
						<a href="property-1.html">Super nice villa </a>
					</h5>
					<div class="dot-hr"></div>
					<span class="pull-left"><b>Area :</b> 120m </span> <span
						class="proerty-price pull-right">$ 300,000</span>
				</div>
			</div>
		</div>

		<div class="col-sm-6 col-md-3 p0">
			<div class="box-tree more-proerty text-center">
				<div class="item-tree-icon">
					<i class="fa fa-th"></i>
				</div>
				<div class="more-entry overflow">
					<h5>
						<a href="property-1.html">CAN'T DECIDE ? </a>
					</h5>
					<h5 class="tree-sub-ttl">Show all properties</h5>
					<button class="btn border-btn more-black" value="All properties">All
						properties</button>
				</div>
			</div>
		</div>
	</div>
</section>
 -->
<br>
<br>
<br>
<br>
<!-- </div> -->



<%@ include file="include/footer.jspf"%>
