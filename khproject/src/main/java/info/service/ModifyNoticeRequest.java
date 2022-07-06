package info.service;

import java.util.Map;

public class ModifyNoticeRequest {

	
	private int noticeNo;
	private String title;
	private String content;
	private String category;

	public ModifyNoticeRequest(int noticeNo, String title, String content) {
		
		this.noticeNo = noticeNo;
		this.title = title;
		this.content = content;

	}

	public String getCategory() {
		return category;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}

}
