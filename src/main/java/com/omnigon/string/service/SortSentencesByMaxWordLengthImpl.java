package com.omnigon.string.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.omnigon.string.domain.FinalStringSortResultDTO;
import com.omnigon.string.domain.StringResultDTO;

@Service
public class SortSentencesByMaxWordLengthImpl implements SortSentencesService {

	@Override
	public FinalStringSortResultDTO sortSentences(List<String> sentences, Integer numOfResultsToReturn) {
		Comparator<StringResultDTO> byMaximumWordLength = (string1, string2) -> Integer.compare(
                string2.getLongestWordString().length(), string1.getLongestWordString().length());
        Comparator<StringResultDTO> byLexicographicalComparison = (string1, string2) -> 
                string2.getLongestWordString().compareTo(string1.getLongestWordString());
                
        return new FinalStringSortResultDTO( sentences.stream()
				.map(string -> new StringResultDTO(string, calculateLargestWord(string.split("\\b"))))
				.sorted(byMaximumWordLength.thenComparing(byLexicographicalComparison))
				.limit(numOfResultsToReturn)
				.collect(Collectors.toList()));
	}
	
	protected String calculateLargestWord(String[] words) {
    	List<String> wordsList = Arrays.asList(words);

		TreeMap<Integer, String> results = wordsList.stream()
			.collect(
					Collectors.toMap(
							word -> word.length(), 
							word -> word,
							(word1, word2) -> word1,
							TreeMap::new
					)
			);
		
    	return results.descendingMap().values().iterator().next();
    }
}
