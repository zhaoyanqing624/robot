package org.xjtusicd3.database.model;

import java.util.List;


public class NLP_Word {
    private String text;
    private String acronymPinYin;
    private String fullPinYin;
    //词性
    private String partOfSpeech;
    private int frequency;
    private List<NLP_Word> synonym = null;
    private List<NLP_Word> antonym = null;
    //权重，用于词向量分析
    private Float weight;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAcronymPinYin() {
		return acronymPinYin;
	}
	public void setAcronymPinYin(String acronymPinYin) {
		this.acronymPinYin = acronymPinYin;
	}
	public String getFullPinYin() {
		return fullPinYin;
	}
	public void setFullPinYin(String fullPinYin) {
		this.fullPinYin = fullPinYin;
	}
	public String getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public List<NLP_Word> getSynonym() {
		return synonym;
	}
	public void setSynonym(List<NLP_Word> synonym) {
		this.synonym = synonym;
	}
	public List<NLP_Word> getAntonym() {
		return antonym;
	}
	public void setAntonym(List<NLP_Word> antonym) {
		this.antonym = antonym;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
    
    
}
