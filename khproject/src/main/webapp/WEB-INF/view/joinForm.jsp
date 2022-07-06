<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="include/header.jspf"%>


<div class="page-head">
	<div class="container">
		<div class="row">
			<div class="page-head-content">
				<h1 class="page-title">Home New account / Sign in</h1>
			</div>
		</div>
	</div>
</div>
<!-- End page header -->

<script type="text/javascript">
function changeRank(){
    var r = document.getElementById("rank");
    var innerR = r.options[r.selectedIndex].value;

    if(innerR == 2){
    document.getElementById("compInput").innerHTML = '<label for="companyno">사업자번호</label><input type="text" class="form-control" id="companyno" name="companyno" placeholder="사업자번호">';
    }else{
        document.getElementById("compInput").innerHTML="";
    }
}

	function enterkey() {
		if (window.event.keyCode == 13) {
			fn_reg();    	
	    }
	}
	
	function checkNumber(event) {
		  if(event.key >= 0 && event.key <= 9) {
		    return true;
		  }
		  
		  return false;
	}

	

	function fn_reg(){
		var form = document.insForm;
		var companyno = form.companyno.value;
		var id = form.id.value;
		var nickname = form.nickname.value;
		var pw = form.pw.value;
		var confirmPw = form.confirmPw.value;
		var memimg = form.memimg.value;
		var name = form.name.value;
		var birth = form.birth.value;
		var address = form.address.value;
		var phonenum = form.phonenum.value;
		var email = form.email.value;
		var phonenum = form.phonenum.value;
		var selectOption = document.getElementById("rank");
		var rank = selectOption.options[selectOption.selectedIndex].value;
		
		if(rank == ""){
			alert("회원분류를 선택하십시오.");
			$("#rank").focus();
			return false;
		}else if(companyno == ""){
			alert("사업자번호를 입력하세요");
			$("#companyno").focus();
			return false;
		}else if(id == ""){
			alert("아이디를 입력하세요");
			$("#id").focus();
			return false;
		}else if(nickname == ""){
			alert("닉네임을 입력하세요");
			$("#nickname").focus();
			return false;
		}else if(pw == ""){
			alert("비밀번호를 입력하세요");
			$("#pw").focus();
			return false;
		}else if(memimg == ""){
			alert("회원이미지를 입력하세요");
			$("#memimg").focus();
			return false;
		}else if(name == ""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}else if(birth == ""){
			alert("생년월일 입력하세요");
			$("#birth").focus();
			return false;
		}else if(address == ""){
			alert("주소를 입력하세요");
			$("#address").focus();
			return false;
		}else if(phonenum == ""){
			alert("전화번호를 입력하세요");
			$("#phonenum").focus();
			return false;
		}else if(email == ""){
			alert("이메일을 입력하십시오.");
			$("#email").focus();
			return false;
		}else if(confirmPw == ""){
			alert("비밀번호확인란을 입력하십시오.");
			$("#confirmPw").focus();
			return false;
		}else if(confirmPw != pw){
			alert("비밀번호 입력값과 비밀번호 확인값이 서로 상이합니다.");
			$("#confirmPw").focus();
			return false;
		}else{
			form.submit();
		}
	}

</script>

<!-- register-area -->
<div class="register-area" style="background-color: rgb(249, 249, 249);">
    <div class="container">
        <div class="col-md-6">
            <div class="box-for overflow">
                <div class="col-md-12 col-xs-12 register-blocks">
                    <h2>New account :</h2>
                    <form name="insForm" action="join.do" method="post" onsubmit="fn_reg();">
                        <div class="form-group">
                        	<label for="rank">회원분류</label> 
                        		<select id="rank" name ="rank" class="selectpicker show-tick form-control" onchange="changeRank()">
       								<option value="1">1:일반회원</option> 
       								<option value="2">2:기업회원</option> 
       								<option value="3">3:관리자</option> 
       							</select>
                        </div>
                        <div class="form-group" id="compInput">
                            
                        </div>
                        <div class="form-group">
                            <label for="id">아이디</label>
                            <input type="text" class="form-control" id="id" name="id" placeholder="ID">
                        </div>
                        <div class="form-group">
                            <label for="nickname">닉네임</label>
                            <input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임">
                        </div>
                        <div class="form-group">
                            <label for="pw">비밀번호</label>
                            <input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호">
                        </div>
                        <div class="form-group">
                            <label for="pw">비밀번호 확인</label>
                            <input type="password" class="form-control" id="confirmPw" name="confirmPw" placeholder="비밀번호 확인">
                        </div>
                        <div class="form-group">
                            <label for="name">이름</label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="이름">
                        </div>
                        <div class="form-group">
                            <label for="birth">생년월일</label>
                            <input type="text" class="form-control" id="birth" name="birth" onkeydown="return checkNumber(event);" placeholder="생년월일">
                        </div>
                        <div class="form-group">
                            <label for="address">주소</label>
                            <input type="text" class="form-control" id="address" name="address" placeholder="주소">
                        </div>
                        <div class="form-group">
                            <label for="phonenum">전화번호</label>
                            <input type="text" class="form-control" id="phonenum" name="phonenum" onkeydown="return checkNumber(event);" placeholder="전화번호">
                        </div>
                        <div class="form-group">
                            <label for="email">이메일</label>
                            <input type="text" class="form-control" id="email" name="email" placeholder="이메일">
                        </div>
                        <div class="text-center">
                            <button type="button" class="btn btn-default" onclick="fn_reg();">Register</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="include/footer.jspf"%>