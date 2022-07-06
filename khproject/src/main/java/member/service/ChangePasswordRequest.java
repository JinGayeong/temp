package member.service;

import java.util.Map;

public class ChangePasswordRequest {
	
	private String newCompanyno;
	private String newNickname;
	private String newPw;
	private String confirmNewPw;
	private String newMemimg;
	private String newAddress;
	private String newPhonenum;
	private String newEmail;

	public String getNewCompanyno() {
		return newCompanyno;
	}

	public void setNewCompanyno(String newCompanyno) {
		this.newCompanyno = newCompanyno;
	}

	public String getNewNickname() {
		return newNickname;
	}

	public void setNewNickname(String newNickname) {
		this.newNickname = newNickname;
	}

	public String getNewPw() {
		return newPw;
	}

	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}

	public String getConfirmNewPw() {
		return confirmNewPw;
	}

	public void setConfirmNewPw(String confirmNewPw) {
		this.confirmNewPw = confirmNewPw;
	}

	public String getNewMemimg() {
		return newMemimg;
	}

	public void setNewMemimg(String newMemimg) {
		this.newMemimg = newMemimg;
	}

	public String getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}

	public String getNewPhonenum() {
		return newPhonenum;
	}

	public void setNewPhonenum(String newPhonenum) {
		this.newPhonenum = newPhonenum;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public boolean isNewPwEqualToConfirmNewPw() {
		return newPw != null && newPw.equals(confirmNewPw);
	}

	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}

	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, newCompanyno, "newCompanyno");
		checkEmpty(errors, newNickname, "newNickname");
		checkEmpty(errors, newPw, "newPw");
//		checkEmpty(errors, confirmNewPw, "confirmNewPw");
		checkEmpty(errors, newMemimg, "newMemimg");
		checkEmpty(errors, newAddress, "newAddress");
		checkEmpty(errors, newPhonenum, "newPhonenum");
		checkEmpty(errors, newEmail, "newEmail");

		if (!errors.containsKey("confirmNewPw")) {
			if (!isNewPwEqualToConfirmNewPw()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}

}
