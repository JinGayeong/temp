<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="include/header.jspf"%>

<style>
.footer-area {
	width: 100%;
	height: 110px; /* 내용물에 따라 알맞는 값 설정 */
	position: absolute;
}

.expert-calculate__content {
	text-align: center;
}

.text-center padding-b-50 {
	margin-top: 50px;
}

.notice {
	margin: 0 auto;
	width: 60%;
	left: 30%;
	background-color: #f7f8fa;
	padding-left: 1%;
	padding-right: 1%;
	padding-bottom: 1%;
	padding-top: 1%;
	margin-bottom: 50px;
	margin-top: 50px;
}

.form-group {
	margin: 0 auto;
	width: 35%;
	margin-bottom: 50px;
	margin-top: 50px;
}

.area {
	margin: 0 auto;
	width: 30%;
	margin-bottom: 50px;
	margin-top: 50px;
}

.range-slider-input__content {
	position: relative;
	top: 7px;
	left: 12px;
	right: 12px;
	height: 10px;
}

.input-group {
	margin: 0 auto;
	width: 40%;
	margin-bottom: 50px;
	margin-top: 50px;
}

.cancel_btn {
	position: relative;
	width: 150px;
	height: 60px;
	margin-left: 50px;
	margin-right: 50px;
	margin-bottom: 100px;
	margin-left: 50px;
	background-color: #C2C2C2;
}

.submit_btn {
	position: relative;
	width: 150px;
	height: 60px;
	margin-left: 50px;
	margin-right: 50px;
	margin-bottom: 100px;
	background-color: #FDC600;
}

.box-title {
	text-align: center;
}

.box {
	width: 70%;
	margin: auto;
	text-align: center;
}

.form-group {
	text-align-last: center;
	text-align: center;
}
</style>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$('document')
			.ready(
					function() {
						var area0 = [ "시/도 선택", "서울특별시", "인천광역시", "대전광역시",
								"광주광역시", "대구광역시", "울산광역시", "부산광역시", "경기도",
								"강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도",
								"경상남도", "제주도" ];
						var area1 = [ "강남구", "강동구", "강북구", "강서구", "관악구", "광진구",
								"구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구",
								"마포구", "서대문구", "서초구", "성동구", "성북구", "송파구",
								"양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" ];
						var area2 = [ "계양구", "남구", "남동구", "동구", "부평구", "서구",
								"연수구", "중구", "강화군", "옹진군" ];
						var area3 = [ "대덕구", "동구", "서구", "유성구", "중구" ];
						var area4 = [ "광산구", "남구", "동구", "북구", "서구" ];
						var area5 = [ "남구", "달서구", "동구", "북구", "서구", "수성구",
								"중구", "달성군" ];
						var area6 = [ "남구", "동구", "북구", "중구", "울주군" ];
						var area7 = [ "강서구", "금정구", "남구", "동구", "동래구", "부산진구",
								"북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구",
								"중구", "해운대구", "기장군" ];
						var area8 = [ "고양시", "과천시", "광명시", "광주시", "구리시", "군포시",
								"김포시", "남양주시", "동두천시", "부천시", "성남시", "수원시",
								"시흥시", "안산시", "안성시", "안양시", "양주시", "오산시",
								"용인시", "의왕시", "의정부시", "이천시", "파주시", "평택시",
								"포천시", "하남시", "화성시", "가평군", "양평군", "여주군", "연천군" ];
						var area9 = [ "강릉시", "동해시", "삼척시", "속초시", "원주시", "춘천시",
								"태백시", "고성군", "양구군", "양양군", "영월군", "인제군",
								"정선군", "철원군", "평창군", "홍천군", "화천군", "횡성군" ];
						var area10 = [ "제천시", "청주시", "충주시", "괴산군", "단양군",
								"보은군", "영동군", "옥천군", "음성군", "증평군", "진천군", "청원군" ];
						var area11 = [ "계룡시", "공주시", "논산시", "보령시", "서산시",
								"아산시", "천안시", "금산군", "당진군", "부여군", "서천군",
								"연기군", "예산군", "청양군", "태안군", "홍성군" ];
						var area12 = [ "군산시", "김제시", "남원시", "익산시", "전주시",
								"정읍시", "고창군", "무주군", "부안군", "순창군", "완주군",
								"임실군", "장수군", "진안군" ];
						var area13 = [ "광양시", "나주시", "목포시", "순천시", "여수시",
								"강진군", "고흥군", "곡성군", "구례군", "담양군", "무안군",
								"보성군", "신안군", "영광군", "영암군", "완도군", "장성군",
								"장흥군", "진도군", "함평군", "해남군", "화순군" ];
						var area14 = [ "경산시", "경주시", "구미시", "김천시", "문경시",
								"상주시", "안동시", "영주시", "영천시", "포항시", "고령군",
								"군위군", "봉화군", "성주군", "영덕군", "영양군", "예천군",
								"울릉군", "울진군", "의성군", "청도군", "청송군", "칠곡군" ];
						var area15 = [ "거제시", "김해시", "마산시", "밀양시", "사천시",
								"양산시", "진주시", "진해시", "창원시", "통영시", "거창군",
								"고성군", "남해군", "산청군", "의령군", "창녕군", "하동군",
								"함안군", "함양군", "합천군" ];
						var area16 = [ "서귀포시", "제주시", "남제주군", "북제주군" ];

						//alert("${modReq.address}");
						var address = "${modReq.address}".split(' ');
						//alert(address[0]);

						// 시/도 선택 박스 초기화

						$("select[name^=sido]")
								.each(
										function() {
											$selsido = $(this);
											$
													.each(
															eval(area0),
															function(index,
																	value) {
																$selsido
																		.append("<option value='"+this+"'>"
																				+ value
																				+ "</option>");
															});
											$selsido
													.next()
													.append(
															"<option value=''>구/군 선택</option>");
										});

						// 시/도 선택시 구/군 설정
						$selsido.val(address[0]).prop("selected", true);
						var num = area0.indexOf(address[0]);

						var $gugun = $selsido.next();
						var area = "area" + num;
						//alert(area);
						$.each(eval(area), function() {
							$gugun.append("<option value='"+this+"'>" + this
									+ "</option>");
						});
						$gugun.val(address[1]).prop("selected", true);
						//alert(address[1]);

						$("select[name^=sido]")
								.change(
										function() {
											var area = "area"
													+ $("option", $(this))
															.index(
																	$(
																			"option:selected",
																			$(this))); // 선택지역의 구군 Array
											var $gugun = $(this).next(); // 선택영역 군구 객체
											$("option", $gugun).remove(); // 구군 초기화

											if (area == "area0")
												$gugun
														.append("<option value=''>구/군 선택</option>");
											else {
												$
														.each(
																eval(area),
																function() {
																	$gugun
																			.append("<option value='"+this+"'>"
																					+ this
																					+ "</option>");
																});
											}
										});

					});

	$(document).ready(function() {
		$('#category').val('${modReq.category}').prop("selected", true);
		$('#part').val('${modReq.part}').prop("selected", true);

		if ('${modReq.budget}' == -1) {
			$("input:checkbox[id='undefined_budget']").prop("checked", true);
			$('#budget').val(" ");
		} else {
			$('#budget').val("${modReq.budget}");
		}

		if ('${modReq.startDate}' == "0001-01-01 00:00:00.0") {
			$("input:checkbox[id='undefined_start']").prop("checked", true);
			$('#startDate').val(" ");
		} else {
			var str = "${modReq.startDate}";
			var tmp = str.substring(0, 10);
			$('#startDate').val(tmp);

		}
		if ('${modReq.endDate}' == "0001-01-01 00:00:00.0") {
			$("input:checkbox[id='undefined_end']").prop("checked", true);
			$('#endDate').val(" ");
		} else {
			var str = "${modReq.endDate}";
			var tmp = str.substring(0, 10);
			$('#endDate').val(tmp);

		}

	});
	$(document).ready(
			function() {
				$("form")
						.submit(
								function() {
									if ($("#title").val() == "") {
										alert("시공내용을 간략히 입력해주세요");
										$("#title").focus();

										return false;
									}

									if ($("#category").val() == null) {
										alert("주거유형을 선택해주세요");
										$("#category").focus();
										return false;
									}

									if ($("#sido1").val() == "") {
										alert("주소를 선택해주세요");
										$("#title").focus();
										return false;
									}

									if ($("#gugun1").val() == "") {
										alert("주소를 선택해주세요");
										$("#gugun1").focus();
										return false;
									}

									if (!$.isNumeric($(
											'input[type=number][name=area]')
											.val())) {
										alert("면적을 입력해주세요(면적은 숫자만 입력 가능합니다)");
										$("#area").focus();
										return false;
									}

									if ($("#startDate").val() == ""
											&& $("#undefined_start").is(
													":checked") == false) {
										alert("시공 시작일을 입력해주세요");
										$("#startDate").focus();
										return false;
									}

									if ($("#endDate").val() == ""
											&& $("#undefined_end").is(
													":checked") == false) {
										alert("시공 종료일을 입력해주세요");
										$("#endDate").focus();
										return false;
									}

									if (!$.isNumeric($(
											'input[type=number][name=budget]')
											.val())
											&& $("#undefined_budget").is(
													":checked") == false) {
										alert("예산을 입력해주세요");
										$("#budget").focus();
										return false;
									}
									if ($("#part").val() == null) {
										alert("인테리어 분야를 선택해주세요");
										$("#part").focus();
										return false;
									}

									if ($("#require").val() == "undefined") {
										alert("상세 요청사항을 입력해주세요");
										$("#require").focus();
										return false;
									}
								}); // submit() end
			}); // ready() end
</script>

<div class="page-head">
	<div class="container">
		<div class="row">
			<div class="page-head-content">
				<h1 class="page-title">인테리어 견적 신청</h1>
			</div>
		</div>
	</div>
</div>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<!-- 	<section class="content-header"> -->
	<!-- 		<h2>인테리어 간편 견적신청</h2> -->

	<!-- 	</section> -->
	<!-- Main content -->

	<div class="register-area"
		style="background-color: rgb(249, 249, 249);">

		<!-- Default box -->
		<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">신청서 수정</h3>
			</div>
			<div class="box-body">

				<!-- 				<form action="write.do" method="post" enctype="Multipart/form-data"> -->
				<form action="modify.do" method="post">

					<div class="form-row">

						<!-- 							Title 작성 -->
						<div class="form-group">
							<label for="inputAddress">제목</label> <input type="text"
								class="form-control" name="title" id="title"
								placeholder="시공내용을 간략하게 적어주세요 (20자 이내)" value="${modReq.title}">
						</div>



						<!-- 							주거 타입 작성 -->
						<div class="form-group">
							<label for="exampleFormControlSelect1">주거유형</label> <select
								class="form-control" name="category" id="category">
								<option value="" disabled selected>주거유형</option>
								<option value="아파트">아파트</option>
								<option value="빌라">빌라</option>
								<option value="카페/식당">카페/식당</option>
								<option value="사무/오피스">사무/오피스</option>
								<option value="매장/상업">매장/상업</option>
								<option value="병원/의료">병원/의료</option>
								<option value="기타">기타</option>
							</select>
						</div>



						<div class="form-group" style="width: 400px;">
							<label for="exampleFormControlSelect1">주소</label> <br> <select
								class="form-control" name="sido1"
								style="width: 160px; display: inline-block;"></select> <select
								class="form-control" name="gugun1" id="gugun1"
								style="width: 160px; display: inline-block;"></select>
						</div>


						<!-- 							평수 입력 -->
						<div class="form-row">
							<label>면적</label>
							<div class="input-group"
								style="width: 320px; margin-top: 0px; margin-bottom: 0px;">
								<div class="input-group-addon">평수</div>
								<input type="number" class="form-control" name="area"
									value="${modReq.area}" />
								<div class="input-group-addon">평</div>
							</div>
						</div>




						<div class="form-row">
							<div class="form-group">
								<label for="start">시공 시작일</label> <input type="date"
									class="form-control" name="startDate" id="startDate"
									value="${modReq.startDate}">
							</div>
							<div class="form-group">
								<label for="end">시공 종료일</label> <input type="date"
									class="form-control" name="endDate" id="endDate"
									value="${modReq.endDate}">
							</div>
						</div>

						<label>시공 시작일 미정</label> <input type="checkbox"
							id="undefined_start" name="undefined_start"
							style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;">
						<label>시공 종료일 미정</label> <input type="checkbox" id="undefined_end"
							name="undefined_end"
							style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;">




						<div class="form-group">
							<div class="form-row">
								<div>
									<label for="start">희망 예산</label>
								</div>
								<div class="input-group"
									style="width: 320px; margin-top: 0px; margin-bottom: 0px;">
									<input type="number" class="form-control" name="budget"
										id="budget" value="${modReq.budget}" />
									<div class="input-group-addon">만원</div>
								</div>
							</div>
						</div>


						<label>업체 협의</label> <input type="checkbox" id="undefined_budget"
							name="undefined_budget"
							style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;">






						<div class="form-group">
							<label for="exampleFormControlSelect1">인테리어 분야</label> <select
								class="form-control" name="part" id="part">
								<option value="" disabled selected>인테리어 분야</option>
								<option value="종합">종합</option>
								<option value="도배">도배</option>
								<option value="마루">마루</option>
								<option value="욕실">욕실</option>
								<option value="필름">필름</option>
								<option value="타일">타일</option>
								<option value="도어">도어</option>
								<option value="주방">주방</option>
								<option value="기타">기타</option>
							</select>
						</div>



						<div class="form-group">
							<label for="exampleFormControlTextarea1">상세 요청사항</label>
							<textarea class="form-control" name="require"
								id="exampleFormControlTextarea1" rows="7">${modReq.require}</textarea>
						</div>
						<!-- textarea는 value에 값을 넣는것이 아니고 괄호 바깥쪽에 넣는다 -->

						<div class="form-group">
							<input type="file" name="image"
								accept="image/gif,image/jpeg,image/png"
								onchange='chk_file_type(this)'>
						</div>
				</form>

				<div class="col-md-offset-2"
					style="margin-left: 0px; margin-top: 50px;">
					<input type="button" class="cancel_btn" name="cancel" value="취소"
						onClick="location.href='/oracle/board/list.do'"
						style="display: inline-block;"> <input type="submit"
						class="submit_btn" name="submit" value="수정"
						style="display: inline-block;">
				</div>
			</div>

			<!-- /.box-body -->
		</div>
	</div>
</div>
</div>
<!-- /.box -->
</section>
<!-- /.content -->
</div>
</div>





<%@ include file="include/footer.jspf"%>