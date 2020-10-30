package com.example.JsonToXmlHttpService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JsonToXmlApplicationTests {

    @Autowired
    JsonXmlConversionService conversionService;

    @Test
    public void jsonToXMLConversion() {
        assertEquals(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<a>\n" +
                "  <b>data</b>\n" +
                "</a>",
                conversionService.convertJsonToXML("{\"a\":{\"b\":\"data\"}}"));
    }
}
