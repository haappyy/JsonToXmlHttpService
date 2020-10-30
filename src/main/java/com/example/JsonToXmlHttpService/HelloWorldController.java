package com.example.JsonToXmlHttpService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/")
public class HelloWorldController {

    private final static Logger LOGGER = Logger.getLogger(HelloWorldController.class.getName());

    @ResponseBody
    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> helloWorld() {
        LOGGER.log(Level.INFO,
                "Request to HelloWorld");
        return ResponseEntity.ok("Hello World");
    }

}
