package com.omnigon.string.domain;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class FinalStringSortResultDTO {
	private List<StringResultDTO> result;
	
	private FinalStringSortResultDTO() {}
	
	public FinalStringSortResultDTO(List<StringResultDTO> result) {
		this.result = result;
	}

	public List<StringResultDTO> getResult() {
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof FinalStringSortResultDTO)) {
			return false;
		}
		FinalStringSortResultDTO other = (FinalStringSortResultDTO)obj;
		
		return new EqualsBuilder().append(this.result, other.result).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.result).hashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(result).toString();
	}
}
