package com.example.JsonToXmlHttpService;

import com.github.underscore.lodash.Json;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/")
public class ConversionController {

    private final static Logger LOGGER = Logger.getLogger(ConversionController.class.getName());

    @Autowired
    JsonXmlConversionService jsonXmlConversionService;

    @ResponseBody
    @RequestMapping(value = "/convertJsonToXML", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> convertJsonToXML(@RequestBody String json) {
        LOGGER.log(Level.INFO, "Should convert to xml:" + json);
        try {
            String xml = jsonXmlConversionService.convertJsonToXML(json);
            LOGGER.log(Level.INFO, "Resulting xml:" + xml);
            return ResponseEntity.ok(xml);
        } catch (Json.ParseException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
