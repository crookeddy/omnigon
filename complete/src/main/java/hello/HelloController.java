package hello;

import org.springframework.web.bind.annotation.RestController;

import com.omnigon.string.domain.StringList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HelloController {
    
    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getStringWithLongestWord(@RequestBody StringList sl) {
    	List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add(sl.getStrings().get(1));
        return new ResponseEntity<List<String>>(strings, HttpStatus.OK);
    }
    
}