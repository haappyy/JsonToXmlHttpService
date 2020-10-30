package com.example.JsonToXmlHttpService;

import com.github.underscore.lodash.Json;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXParseException;

import javax.management.modelmbean.XMLParseException;
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

    @ResponseBody
    @RequestMapping(value = "/convertXmlToJson", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> convertXmlToJson(@RequestBody String xml) {
        LOGGER.log(Level.INFO, "Should convert to json:" + xml);
        try {
        String json = jsonXmlConversionService.convertXMLtoJson(xml);
        LOGGER.log(Level.INFO, "Resulting json:" + json);
        return ResponseEntity.ok(json);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
