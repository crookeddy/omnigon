package com.omnigon.string.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StringResultDTO {
	private String sentence;
	@JsonIgnore
	private String longestWordString;
	private Integer longestWord;
	
	public StringResultDTO(String sentence, String longestWord) {
		this.sentence = sentence;
		this.longestWordString = longestWord;
		this.longestWord = longestWord.length();
	}

	public String getSentence() {
		return sentence;
	}

	public String getLongestWordString() {
		return longestWordString;
	}
	
	public Integer getLongestWord() {
		return longestWord;
	}
}
