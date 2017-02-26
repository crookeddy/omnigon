package com.omnigon.string.domain;

public class StringResultDTO {
	private String string;
	private Integer longestWord;
	
	public StringResultDTO(String string, Integer longestWord) {
		this.string = string;
		this.longestWord =longestWord;
	}

	public String getString() {
		return string;
	}

	public Integer getLongestWord() {
		return longestWord;
	}
}
