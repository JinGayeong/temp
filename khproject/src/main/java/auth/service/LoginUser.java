package auth.service;

public class LoginUser {
	
	private int memno;
	private String nickname;
	private String id;
	private int phonenum;
	private String email;
	private String pw;
	private String name;
	private String address;
	private String companyno;
	private int birth;
	private String memimg;
	private int rank;
	
	public LoginUser(int memno, String nickname, String id, int phonenum, String email, String pw, String name,
			String address, String companyno, int birth, String memimg, int rank) {
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

	public int getMemno() {
		return memno;
	}

	public String getNickname() {
		return nickname;
	}

	public String getId() {
		return id;
	}

	public int getPhonenum() {
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

	public int getBirth() {
		return birth;
	}

	public String getMemimg() {
		return memimg;
	}

	public int getRank() {
		return rank;
	}
	
}
