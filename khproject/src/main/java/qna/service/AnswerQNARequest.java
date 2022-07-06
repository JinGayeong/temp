package qna.service;

import java.util.Date;
import java.util.Map;

public class AnswerQNARequest {

	
	private int qnaNo;
	private String nickname;
	private String title;
	private String content;
	private String answer;
	private String category;
	private String qnaDate;
	private String answerDate;
	

	public AnswerQNARequest(int qnaNo,String nickname, String title, String content, String category, String answer, String qnaDate, String answerDate) {
		
		this.qnaNo = qnaNo;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.category = category;
		this.answer = answer;
		this.qnaDate = qnaDate;
		this.answerDate = answerDate;
	}

	public String getQnaDate() {
		return qnaDate;
	}

	public String getAnswerDate() {
		return answerDate;
	}

	public String getNickname() {
		return nickname;
	}

	public String getAnswer() {
		return answer;
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
