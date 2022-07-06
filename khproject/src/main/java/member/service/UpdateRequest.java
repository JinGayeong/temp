package member.service;

import java.util.Map;


public class UpdateRequest {

	private String nickname;
	private String address;
	private String confirmPassword;

	public UpdateRequest(String nickname, String address, String confirmPassword) {
		this.nickname = nickname;
		this.address = address;
		this.confirmPassword = confirmPassword;
	}


	
	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}



	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}



	//전달 값이 비어있으면 에러가 나도록 하는 메소드
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, nickname, "nickname");
		checkEmpty(errors, address, "address");
		checkEmpty(errors, confirmPassword, "confirmPassword");
		
		//지금은 비번을 여기서 검증하는게 아니고 db에 있는 패스워드와 검증하는거니깐.
		// db에 있는 패스워드가 아니고
		// 세션에 있는 패스워드와 검증하면 되지 않나?????????@@@@@@@@@@@@@ 나중에 체크
		/*
		 * if (!errors.containsKey("confirmPassword")) { if
		 * (!isPasswordEqualToConfirm()) { errors.put("notMatch", Boolean.TRUE); } }
		 */
	}

	private void checkEmpty(Map<String, Boolean> errors, 
			String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}
