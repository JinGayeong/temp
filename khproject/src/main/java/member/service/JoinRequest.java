package member.service;

import java.util.Map;

public class JoinRequest {

	private String memno;
	private String nickname;
	private String id;
	private String phonenum;
	private String email;
	private String pw;
	private String confirmPw;
	private String name;
	private String address;
	private String companyno;
	private String birth;
	private String memimg;
	private String rank;
	
	public String getMemno() {
		return memno;
	}

	public void setMemno(String memno) {
		this.memno = memno;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getConfirmPw() {
		return confirmPw;
	}

	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyno() {
		return companyno;
	}

	public void setCompanyno(String companyno) {
		this.companyno = companyno;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getMemimg() {
		return memimg;
	}

	public void setMemimg(String memimg) {
		this.memimg = memimg;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public boolean isPasswordEqualToConfirm() {
		return pw != null && pw.equals(confirmPw);
	}

	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}

	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, nickname, "nickname");
		checkEmpty(errors, id, "id");
		checkEmpty(errors, phonenum, "phonenum");
		checkEmpty(errors, email, "email");
		checkEmpty(errors, pw, "pw");
		checkEmpty(errors, confirmPw, "confirmPw");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, address, "address");
		checkEmpty(errors, companyno, "companyno");
		checkEmpty(errors, birth, "birth");
		checkEmpty(errors, memimg, "memimg");
		checkEmpty(errors, rank, "rank");

		if (!errors.containsKey("confirmPw")) {
			if (!isPasswordEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}

}
