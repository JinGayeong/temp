package info.model;

public class Writer {

	private int memNo;
	private String nickname;

	public Writer(int memNo, String nickname) {
		this.memNo = memNo;
		this.nickname = nickname;
	}

	public int getMemNo() {
		return memNo;
	}

	public String getNickname() {
		return nickname;
	}

}
