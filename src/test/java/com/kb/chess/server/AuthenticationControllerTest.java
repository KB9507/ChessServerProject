package com.kb.chess.server;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AuthenticationControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void shouldReturnErrorStatusProcessRegistration() throws Exception {
		
		RegistrationForm registrationForm = new RegistrationForm("plojh@onet.pl","askudyncakui","afgdfbdf");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(registrationForm);
			
		mvc.perform(post("http://localhost:8080/register")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().string(containsString("SUCCESS")));

		LoginForm loginForm = new LoginForm("plojh@onet.pl","afgdfbdf");
		String json1 = mapper.writeValueAsString(loginForm);
			
		MvcResult result = mvc.perform(post("http://localhost:8080/login")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(json1)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
					.andExpect(status().isOk())
					.andReturn();
	}
}
