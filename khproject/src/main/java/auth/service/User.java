package auth.service;

public class User {
	
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
	
	public User(String memno, String nickname, String id, String phonenum, String email, String pw, String name,
			String address, String companyno, String birth, String memimg, String rank) {
		super();
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
	
}
