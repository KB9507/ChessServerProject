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

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RegistrationControllerTest {
	
	@Autowired
	private MockMvc mvc;
		
	@Test
	public void shouldReturnSuccessStatusProcessRegistration() throws Exception {

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
	}
	
	@Test
	public void shouldReturnExistingAccountStatusProcessRegistration() throws Exception {

		RegistrationForm registrationForm = new RegistrationForm("plojh@onet.pl","askudyncakui","afgdfbdf");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(registrationForm);
			
		mvc.perform(post("http://localhost:8080/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON));
		
		registrationForm = new RegistrationForm("plojh@onet.pl","fhsfdgfd","afrgbdf");
		json = mapper.writeValueAsString(registrationForm);
		
		mvc.perform(post("http://localhost:8080/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().string(containsString("EXISTING_ACCOUNT")));

	}
	
	@Test
	public void shouldReturnErrorStatusProcessRegistration() throws Exception {

		RegistrationForm registrationForm = new RegistrationForm("plojhonet.pl","askudyncakui","afgdfbdf");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(registrationForm);
			
		mvc.perform(post("http://localhost:8080/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().string(containsString("ERROR")));
	}
}

