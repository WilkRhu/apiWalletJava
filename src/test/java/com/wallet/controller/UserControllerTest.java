package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

	private static final Long ID = 1L;
	private static final String Email = "test@gamail.com";
	private static final String Nome = "Nome test";
	private static final String Password = "123456";
	private static final String Url = "/user";

	@MockBean
	UserService service;

	@Autowired
	MockMvc mvc;
	
	@Test
	public void testSave() throws Exception {
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
		mvc.perform(MockMvcRequestBuilders.post(Url).content(getJsonPayload(ID, Email, Nome, Password)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.email").value(Email))
		.andExpect(jsonPath("$.data.nome").value(Nome))
		.andExpect(jsonPath("$.data.password").value(Password));
	}
	
	@Test
	public void testSaveInvalidUser () throws JsonProcessingException, Exception {
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
		mvc.perform(MockMvcRequestBuilders.post(Url).content(getJsonPayload(ID, "email", Nome, Password)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.erros[0]").value("Email inválido"));
	}
	
	public User getMockUser() {
		User u = new User();
		u.setId(ID);
		u.setEmail(Email);
		u.setNome(Nome);
		u.setPassword(Password);

		return u;
	}

	public String getJsonPayload(Long id, String email, String nome, String password) throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setEmail(email);
		dto.setNome(nome);
		dto.setPassword(password);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);

	}

}
