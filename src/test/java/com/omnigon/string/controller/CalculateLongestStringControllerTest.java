package com.omnigon.string.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalculateLongestStringControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGettingLongestString() throws Exception {
    	String jsonInput = "{\"strings\": [" +
    			"\"Sound boy proceed to blast into the galaxy\","+
    			"\"Go back rocket man into the sky you'll see\","+
    			"\"Hear it all the time, come back rewind\","+
    			"\"Aliens are watching up in the sky\","+
    			"\"Sound boy process to blast into the galaxy\","+
    			"\"No one gonna harm you\","+
    			"\"They all want you to play I watch the birds of prey\""+
    			"]}";
    	
    	String expectedjsonOutput = "{\"result\": [" +
    			"{"+
    				"\"string\": \"Aliens are watching up in the sky\","+
    				"\"longestWord\": 8"+
    			"},"+
    			"{"+
    				"\"string\": \"Sound boy process to blast into the galaxy\"," +
    				"\"longestWord\": 7"+
    			"},"+
    			"{"+
    				"\"string\": \"Sound boy proceed to blast into the galaxy\"," +
    				"\"longestWord\": 7"+
    			"},"+
    			"{" +
    				"\"string\": \"Go back rocket man into the sky you'll see\"," +
    				"\"longestWord\": 6" +
    			"},"+
    			"{" +
    				"\"string\": \"Hear it all the time, come back rewind\", " +
    				"\"longestWord\": 6" +
    			"}" +
    			"]}"; 
    	
    	mvc.perform(MockMvcRequestBuilders.post("/strings")
    		.content(jsonInput)
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON))
      		.andExpect(status().isOk())
      		.andExpect(content().json(expectedjsonOutput));
    }
    
    @Test
    public void testGettingLongestStringWithDupes() throws Exception {
    	String jsonInput = "{\"strings\": [" +
    			"\"Sound Sound Sound Sound Sound\","+
    			"\"Sound Sound Sound Sound Sound\""+
    			"]}";
    	
    	String expectedjsonOutput = "{\"result\": [" +
    			"{"+
    				"\"string\": \"Sound Sound Sound Sound Sound\","+
    				"\"longestWord\": 5"+
    			"}," +
    			"{"+
					"\"string\": \"Sound Sound Sound Sound Sound\","+
					"\"longestWord\": 5"+
				"}" +
    			"]}";
    	
    	mvc.perform(MockMvcRequestBuilders.post("/strings")
        		.content(jsonInput)
        		.contentType(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON))
          		.andExpect(status().isOk())
          		.andExpect(content().json(expectedjsonOutput));
    }
    
    @Test
    public void testLexicographicalComparissonAsTieBreakerForEqualLengthWords() throws Exception {
    	String jsonInput = "{\"strings\": [" +
    			"\"aaaaa aa ds\","+
    			"\"bbbbb aa ds\""+
    			"]}";
    	
    	String expectedjsonOutput = "{\"result\": [" +
    			"{"+
    				"\"string\": \"bbbbb aa ds\","+
    				"\"longestWord\": 5"+
    			"}," +
    			"{"+
					"\"string\": \"aaaaa aa ds\","+
					"\"longestWord\": 5"+
				"}" +
    			"]}"; 
    	
    	mvc.perform(MockMvcRequestBuilders.post("/strings")
        		.content(jsonInput)
        		.contentType(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON))
          		.andExpect(status().isOk())
          		.andExpect(content().json(expectedjsonOutput));
    }
}
