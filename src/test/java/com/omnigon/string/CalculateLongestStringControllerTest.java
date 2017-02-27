package com.omnigon.string;

import static org.hamcrest.Matchers.equalTo;
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
    	mvc.perform(MockMvcRequestBuilders.post("/strings")
    		.content("{\"strings\": [\"aaa\"]}")
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON))
      		.andExpect(status().isOk())
      		.andExpect(content().string(equalTo("{\"result\":[{\"string\":\"aaa\",\"longestWord\":3}]}")));
    }
}
