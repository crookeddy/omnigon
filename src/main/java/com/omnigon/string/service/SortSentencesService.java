package com.omnigon.string.service;

import java.util.List;

import com.omnigon.string.domain.FinalStringSortResultDTO;

public interface SortSentencesService {
	FinalStringSortResultDTO sortSentences(List<String> sentences, Integer numOfResultsToReturn); 
}
