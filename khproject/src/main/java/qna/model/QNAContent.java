package qna.model;

public class QNAContent {

	private int qnaNo;
	private String qnaContent;
	private String answerContent;

	public QNAContent(int qnaNo, String qnaContent, String answerContent) {
		this.qnaNo = qnaNo;
		this.qnaContent = qnaContent;
		this.answerContent = answerContent;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	

}
