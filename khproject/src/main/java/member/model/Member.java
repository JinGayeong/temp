package member.model;


public class Member {
	
	private String memno;
	private String nickname;
	private String id;
	private String phonenum;
	private String email;
	private String pw;
	private String name;
	private String address;
	private String companyno;
	private String birth;
	private String memimg;
	private String rank;
	
	public Member(String memno, String nickname, String id, String phonenum, String email, String pw, String name,
			String address, String companyno, String birth, String memimg, String rank) {
		this.memno = memno;
		this.nickname = nickname;
		this.id = id;
		this.phonenum = phonenum;
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.companyno = companyno;
		this.birth = birth;
		this.memimg = memimg;
		this.rank = rank;
	}

	public String getMemno() {
		return memno;
	}

	public String getNickname() {
		return nickname;
	}

	public String getId() {
		return id;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public String getEmail() {
		return email;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getCompanyno() {
		return companyno;
	}

	public String getBirth() {
		return birth;
	}

	public String getMemimg() {
		return memimg;
	}

	public String getRank() {
		return rank;
	}
	
	public boolean matchPw(String pw) {
		return pw.equals(pw);
	}
	
	//회원정보 변경
	public void changeCompanyno(String newCompanyno) {
		this.companyno = newCompanyno;
	}
	
	public void changeNickname(String newNicknamem) {
		this.nickname = newNicknamem;
	}
	
	public void changePw(String newPw) {
		this.pw = newPw;
	}
	
	public void changeMemimg(String newMemimg) {
		this.memimg = newMemimg;
	}
	
	public void changeAddress(String newAddress) {
		this.address = newAddress;
	}
	
	public void changePhonenum(String newPhonenum) {
		this.phonenum = newPhonenum;
	}
	
	public void changeEmail(String newEmail) {
		this.email = newEmail;
	}

	@Override
	public String toString() {
		return "Member [memno=" + memno + ", nickname=" + nickname + ", id=" + id + ", phonenum=" + phonenum
				+ ", email=" + email + ", pw=" + pw + ", name=" + name + ", address=" + address + ", companyno="
				+ companyno + ", birth=" + birth + ", memimg=" + memimg + ", rank=" + rank + "]";
	}
	
	
}
