package board.model;

public class Writer {

	private String id;
	private String nickname;
	private int memNo;

	public Writer(String id, String nickname, int memNo) {
		this.id = id;
		this.nickname = nickname;
		this.memNo = memNo;

	}

	public String getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}
	public int getMemNo() {
		return memNo;
	}

}
