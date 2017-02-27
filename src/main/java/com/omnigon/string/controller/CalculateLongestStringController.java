package com.omnigon.string.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omnigon.string.domain.FinalStringSortResultDTO;
import com.omnigon.string.domain.StringListDTO;
import com.omnigon.string.service.SortSentencesService;

@RestController
public class CalculateLongestStringController {
	
	@Resource
	private SortSentencesService sortSentencesService;
	
	private final Integer NUMBER_OF_RESULTS_TO_RETURN = 5;
    
    @RequestMapping(value="/strings", method = RequestMethod.POST)
    public ResponseEntity<FinalStringSortResultDTO> getStringWithLongestWord(@RequestBody StringListDTO sl) { 
        List<String> sentences = sl.getStrings();
        FinalStringSortResultDTO results = sortSentencesService.sortSentences(sentences, NUMBER_OF_RESULTS_TO_RETURN);
    	return new ResponseEntity<FinalStringSortResultDTO>(results, HttpStatus.OK); 
    }
}