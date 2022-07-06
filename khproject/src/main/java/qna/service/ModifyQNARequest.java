package qna.service;

import java.util.Map;

public class ModifyQNARequest {

	
	private int qnaNo;
	private String title;
	private String content;
	private String category;

	public ModifyQNARequest(int qnaNo, String title, String content, String category) {
		
		this.qnaNo = qnaNo;
		this.title = title;
		this.content = content;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	

	public int getQnaNo() {
		return qnaNo;
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
