package com.omnigon.string.controller;

import java.util.Arrays;
import java.util.HashMap;
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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;
import com.omnigon.string.domain.StringList;

@RestController
public class CalculateLongestStringController {
    
    @RequestMapping(value="/strings", method = RequestMethod.POST)
    public ResponseEntity<Multimap<String,Integer>> getStringWithLongestWord(@RequestBody StringList sl) { 
    	//Using Google's multimap (a collection that allows multiple keys) because we might get duplicate strings
    	Multimap<String, Integer> stringAndMaxWordSize = TreeMultimap.create(); 
		
    	sl.getStrings()
			.stream()
			.forEach(string -> stringAndMaxWordSize
					.put(string, calculateMax(string.split("\\b"))));
		
    	System.out.println("The map is " + stringAndMaxWordSize);
        return new ResponseEntity<Multimap<String,Integer>>(stringAndMaxWordSize, HttpStatus.OK);
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