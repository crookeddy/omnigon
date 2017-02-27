package com.omnigon.string.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.omnigon.string.domain.FinalStringSortResultDTO;
import com.omnigon.string.domain.StringListDTO;
import com.omnigon.string.domain.StringResultDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculateLongestStringControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/strings");
    }

    @Test
    public void getSortedStrings() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
    	StringListDTO strings = new StringListDTO();
    	List<String> stringList = new ArrayList<>();
    	stringList.add("Sound boy proceed to blast into the galaxy");
    	stringList.add("Go back rocket man into the sky you'll see");
    	stringList.add("Hear it all the time, come back rewind");
    	stringList.add("Aliens are watching up in the sky");
    	stringList.add("Sound boy process to blast into the galaxy");
    	stringList.add("No one gonna harm you");
		stringList.add("They all want you to play I watch the birds of prey");
    	strings.setStrings(stringList);
    	
    	HttpEntity<StringListDTO> entity = new HttpEntity<StringListDTO>(strings, headers);
    	ResponseEntity<FinalStringSortResultDTO> response = template.exchange(base.toString(), HttpMethod.POST, entity, FinalStringSortResultDTO.class);
    	
    	List<StringResultDTO> resultsList = new ArrayList<StringResultDTO>();
    	
    	resultsList.add(new StringResultDTO("Aliens are watching up in the sky", "watching"));
    	resultsList.add(new StringResultDTO("Sound boy process to blast into the galaxy", "process"));
    	resultsList.add(new StringResultDTO("Sound boy proceed to blast into the galaxy", "proceed"));
    	resultsList.add(new StringResultDTO("Go back rocket man into the sky you'll see", "rocket"));
    	resultsList.add(new StringResultDTO("Hear it all the time, come back rewind", "rewind"));
    	
    	FinalStringSortResultDTO finalResult = new FinalStringSortResultDTO(resultsList);
    	
    	assertThat(response.getBody(), equalTo(finalResult));
    }
    
    @Test
    public void getSortedStringsWithDupes() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
    	StringListDTO strings = new StringListDTO();
    	List<String> stringList = new ArrayList<>();
    	stringList.add("Sound Sound Sound Sound Sound Sound");
    	stringList.add("Sound Sound Sound Sound Sound Sound");
    	strings.setStrings(stringList);
    	
    	HttpEntity<StringListDTO> entity = new HttpEntity<StringListDTO>(strings, headers);
    	ResponseEntity<FinalStringSortResultDTO> response = template.exchange(base.toString(), HttpMethod.POST, entity, FinalStringSortResultDTO.class);
    	
    	List<StringResultDTO> resultsList = new ArrayList<StringResultDTO>();
    	resultsList.add(new StringResultDTO("Sound Sound Sound Sound Sound Sound", "Sound"));
    	resultsList.add(new StringResultDTO("Sound Sound Sound Sound Sound Sound", "Sound"));
    	
    	FinalStringSortResultDTO finalResult = new FinalStringSortResultDTO(resultsList);
    	
    	assertThat(response.getBody(), equalTo(finalResult));
    }
    
}
