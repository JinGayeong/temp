package qna.service;

import qna.model.QNA;
import qna.model.QNAContent;

public class QNAData {

	private QNA qna;
	private QNAContent content;

	public QNAData(QNA qna, QNAContent content) {
		this.qna = qna;
		this.content = content;
	}

	public QNA getQna() {
		return qna;
	}

	public QNAContent getContent() {
		return content;
	}

}
