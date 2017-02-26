package com.omnigon.string.service;

import java.util.List;

import com.omnigon.string.domain.StringResultDTO;

public interface SortSentencesService {
	List<StringResultDTO> sortSentences(List<String> sentences, Integer numOfResultsToReturn); 
}
