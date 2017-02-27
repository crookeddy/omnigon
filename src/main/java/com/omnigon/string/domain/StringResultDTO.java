package com.omnigon.string.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StringResultDTO {
	private String string;
	@JsonIgnore
	private String longestWordString;
	private Integer longestWord;
	
	private StringResultDTO(){}
	
	public StringResultDTO(String string, String longestWordString) {
		this.string = string;
		this.longestWordString = longestWordString;
		this.longestWord = longestWordString.length();
	}

	public String getString() {
		return string;
	}

	public String getLongestWordString() {
		return longestWordString;
	}
	
	public Integer getLongestWord() {
		return longestWord;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof StringResultDTO)) {
			return false;
		}
		StringResultDTO other = (StringResultDTO)obj;
		
		return new EqualsBuilder()
				.append(this.string, other.string)
				//.append(this.longestWordString, other.longestWordString) //Can't compare on this field since it isn't marshalled into Json
				.append(this.longestWord, other.longestWord)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.string)
				.append(this.longestWordString)
				.append(this.longestWord)
				.hashCode();
	}
	
	@Override
	public String toString() {
	
		return new ToStringBuilder(this)
				.append(string)
				.append(longestWordString)
				.append(longestWord)
				.toString();
	}
}
