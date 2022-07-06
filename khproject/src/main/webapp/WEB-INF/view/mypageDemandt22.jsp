<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jspf"%>
<%@ include file="include/myPageHeader.jsp"%>




<section id="information" class="add-comments">

	<div class="container">
		<div class="row">
			<table class="table table-striped table-bordered"
				style="text-align: center; border: 1px solid #dddddd">

				<thead>
					<tr>
						<th
							style="width: 100px; background-color: #eeeeee; text-align: center;">의뢰
							번호</th>
						<th
							style="width: 400px; background-color: #eeeeee; text-align: center;">문의
							제목</th>
						<th
							style="width: 70px; background-color: #eeeeee; text-align: center;">조회수</th>
						<th
							style="width: 50px; background-color: #eeeeee; text-align: center;">답변
							여부</th>
						<th
							style="width: 100px; background-color: #eeeeee; text-align: center;">답변 보기</th>

					</tr>
				</thead>
				<tbody>


					<tr>
						<td>1</td>
						<td><a herf>거실 인테리어 견적 요청</a></td>
						<td>10</td>
						<td>null or nn</td>
						<td><button>link</button></td>
					</tr>
					
					<tr>
						<td>2</td>
						<td><a herf>주방 인테리어 견적 요청</a></td>
						<td>1999</td>
						<td>null or nn</td>
						<td><button>link</button></</td>
					</tr>




				</tbody>
			</table>


			<a href="write.jsp" class="btn btn-primary pull-right">버튼</a>



		</div>

	</div>



	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>

</section>
<br>
<br>
<br>
<br>



<%@ include file="include/footer.jspf"%>
