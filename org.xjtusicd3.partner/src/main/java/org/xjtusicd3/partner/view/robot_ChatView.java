package org.xjtusicd3.partner.view;

import java.util.List;

import org.xjtusicd3.database.model.NLP_Word;

public class robot_ChatView {
	private List<NLP_Word> nlp_Words;
	private String questionId;
	private String question;
	private String answer;
	private double value;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public List<NLP_Word> getNlp_Words() {
		return nlp_Words;
	}

	public void setNlp_Words(List<NLP_Word> nlp_Words) {
		this.nlp_Words = nlp_Words;
	}
	
}
