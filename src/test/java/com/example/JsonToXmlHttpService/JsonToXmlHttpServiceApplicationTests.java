package com.example.JsonToXmlHttpService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class JsonToXmlHttpServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	void helloWorldTest() throws Exception {
		mockMvc.perform(get("/helloWorld").accept(MediaType.ALL))
				.andDo(print())
				.andExpect(content().string("Hello World"));
	}

	@Test
	void jsonTest() throws Exception {
		mockMvc.perform(post("/convertJsonToXML")
				.content("{\"a\":{\"b\":\"data\"}}".getBytes()))
				.andDo(print())
				.andExpect(content().string(
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
								"<a>\n" +
								"  <b>data</b>\n" +
								"</a>"));
	}

	@Test
	void invalidJsonTest() throws Exception {
		mockMvc.perform(post("/convertJsonToXML")
				.content("invalid".getBytes()))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void xmlTest() throws Exception {
		mockMvc.perform(post("/convertXmlToJson")
				.content("<a><b>data</b></a>".getBytes()))
				.andDo(print())
				.andExpect(content().string(
						"{\n" +
								"  \"a\": {\n" +
								"    \"b\": \"data\"\n" +
								"  },\n" +
								"  \"#omit-xml-declaration\": \"yes\"\n" +
								"}"));
	}

	@Test
	void invalidXmlTest() throws Exception {
		mockMvc.perform(post("/convertXmlToJson")
				.content("Invalid".getBytes()))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

}
