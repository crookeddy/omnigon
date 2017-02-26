package com.omnigon.string.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omnigon.string.domain.StringListDTO;
import com.omnigon.string.domain.StringResultDTO;

@RestController
public class CalculateLongestStringController {
    
    @RequestMapping(value="/strings", method = RequestMethod.POST)
    public ResponseEntity<List<StringResultDTO>> getStringWithLongestWord(@RequestBody StringListDTO sl) { 
        List<String> strings = sl.getStrings();
        Comparator<StringResultDTO> byMaximumWordLength = (string1, string2) -> Integer.compare(
                string1.getLongestWord(), string2.getLongestWord());
        List<StringResultDTO> results = strings.stream()
        						.map(string -> new StringResultDTO(string, calculateMax(string.split("\\b"))))
        							.sorted(byMaximumWordLength.reversed()).collect(Collectors.toList());
    	return new ResponseEntity<List<StringResultDTO>>(results, HttpStatus.OK); 
    }
    
    private int calculateMax(String[] words) {
    	List<String> wordsList = Arrays.asList(words);
		Map<String, Integer> results = wordsList.stream()
			.collect(
					Collectors.toMap(
							word -> word, 
							word -> word.length(),
							(word1, word2) -> word1
					)
			)
			.entrySet().stream()
				.sorted(Map.Entry.<String, Integer> comparingByValue()
						.reversed()
			)
			.collect(
    				Collectors.toMap(
    						word -> word.getKey(), 
							word -> word.getValue(),
							(word1, word2) -> word1,
							LinkedHashMap::new
					)
    		);
		
    	//System.out.println("The map is " + results);
    	return results.values().iterator().next();
    }
    
}