package com.example.JsonToXmlHttpService;

import com.github.underscore.lodash.U;
import org.springframework.stereotype.Service;

@Service
public class JsonXmlConversionService {

    /**
     * Converts the provided JSON into XML.
     * @s https://www.json.org/json-en.html
     * @param json the JSON to be converted into XML.
     * @ a valid XML document representing the specified JSON.
     * @see <a href="https://www.json.org/json-en.html">More information about JSON</a>
     * @see <a href="https://www.w3.org/TR/2006/REC-xml11-20060816/">More information about XML</a>
     * @see <a href="https://github.com/javadev/underscore-java">Conversion rules</a>
     */
    public String convertJsonToXML(String json){
        return U.jsonToXml(json);
    }

    /**
     * Converts the provided XML into JSON.
     * @s https://www.json.org/json-en.html
     * @param xml the XML to be converted into JSON.
     * @ a valid JSON document representing the specified XML.
     * @see <a href="https://www.json.org/json-en.html">More information about JSON</a>
     * @see <a href="https://www.w3.org/TR/2006/REC-xml11-20060816/">More information about XML</a>
     * @see <a href="https://github.com/javadev/underscore-java">Conversion rules</a>
     */
    public String convertXMLtoJson(String xml){
        return U.xmlToJson(xml);
    }
}
