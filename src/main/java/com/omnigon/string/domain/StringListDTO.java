package com.omnigon.string.domain;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class StringListDTO {
	private List<String> strings;

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof StringListDTO)) {
			return false;
		}
		StringListDTO other = (StringListDTO)obj;
		
		return new EqualsBuilder().append(this.strings, other.strings).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.strings).hashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(strings).toString();
	}
}
