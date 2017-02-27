package com.omnigon.string.service;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.omnigon.string.domain.FinalStringSortResultDTO;
import com.omnigon.string.domain.StringResultDTO;


public class SortSentencesServiceTest {
	
	@Test
	public void sentenceWithLongestWordGetsReturnedFirst() {
		SortSentencesService sss = new SortSentencesByMaxWordLengthImpl();
		List<String> sentences = new ArrayList<String>();
		sentences.add("All short here");
		sentences.add("The longest word here is longest with 7 chars");
		sentences.add("Some other stuff");
		FinalStringSortResultDTO result = sss.sortSentences(sentences, 5);
		List<StringResultDTO> stringResults = result.getResult();
		
		assertThat(stringResults.get(0).getString(), equalTo("The longest word here is longest with 7 chars"));
	}
	
	@Test
	public void lexicographicalAnalysisIsUsedToBreakTiesBetweenEqualLengthWords() {
		SortSentencesService sss = new SortSentencesByMaxWordLengthImpl();
		List<String> sentences = new ArrayList<String>();
		sentences.add("All shortest here");
		sentences.add("The longest here");
		sentences.add("Some other stuff");
		FinalStringSortResultDTO result = sss.sortSentences(sentences, 5);
		List<StringResultDTO> stringResults = result.getResult();
		
		assertThat(stringResults.get(0).getString(), equalTo("All shortest here"));
		assertThat(stringResults.get(1).getString(), equalTo("The longest here"));
	}
	
	@Test
	public void wordCountIsCorrect() {
		SortSentencesService sss = new SortSentencesByMaxWordLengthImpl();
		List<String> sentences = new ArrayList<String>();
		sentences.add("All shortest here");
		sentences.add("The longest here");
		sentences.add("Some other stuff");
		FinalStringSortResultDTO result = sss.sortSentences(sentences, 5);
		List<StringResultDTO> stringResults = result.getResult();
		
		assertThat(stringResults.get(0).getLongestWord(), equalTo(8));
		assertThat(stringResults.get(1).getLongestWord(), equalTo(7));
		assertThat(stringResults.get(2).getLongestWord(), equalTo(5));
	}
	
	@Test
	public void largestWordSelectedCorrectly() {
		SortSentencesByMaxWordLengthImpl sss = new SortSentencesByMaxWordLengthImpl();
		String[] words = {"The", "longest", "word", "here", "is", "longest"};
		String longestWord = sss.calculateLargestWord(words);
		assertThat(longestWord, equalTo("longest"));
	}
}
