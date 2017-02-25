package com.omnigon.string.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omnigon.string.domain.StringList;

@RestController
public class HelloController {
    
    @RequestMapping(value="/strings", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Integer>> getStringWithLongestWord(@RequestBody StringList sl) {
    	Map<String, Integer> stringAndMaxWordSize = 
    			sl.getStrings().stream()
    			.collect(
    					Collectors.toMap(
    							string -> string, 
    							string -> calculateMax(
    										string.split("\\b")
    									)
    					)
				 );
    	
        return new ResponseEntity<Map<String,Integer>>(stringAndMaxWordSize, HttpStatus.OK);
    }
    
    private int calculateMax(String[] words) {
    	List<String> wordsList = Arrays.asList(words);
    	Map<String, Integer> wordSize = 
    			wordsList.stream()
    			.collect(
    					Collectors.toMap(
    							word -> word, 
    							word -> word.length(),
    							(word1, word2) -> word1
    					)
    			);//.entrySet().stream().sorted()
//    			.collect(
//    					Collectors.toMap(
//    							entry -> entry.getKey(), 
//    							entry -> entry.getValue()
//    					)
//    			);
    	System.out.println("The map is " + wordSize);
    	return wordSize.values().iterator().next();
    }
    
}