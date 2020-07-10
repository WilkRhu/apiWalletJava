package com.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.wallet.entity.User;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {
	private static final String Email = "email@test.com";
	@Autowired
	UserRepository repository;

	@Before
	public void setUp() {
		User u = new User();
		u.setNome("Set up user");
		u.setPassword("Senha123");
		u.setEmail(Email);
		
		repository.save(u);

	}

	@After
	public void tearDown() {
		repository.deleteAll();
	}

	@Test
	public void testSave() {
		User u = new User();
		u.setNome("Teste");
		u.setPassword("123456");
		u.setEmail("teste@test.com");

		User response = repository.save(u);

		assertNotNull(response);
	}
	
	public void testFindByEmail() {
		Optional<User> response = repository.findByEmailEquals(Email);
		
		assertTrue (response.isPresent());
		assertEquals(response.get().getEmail(), Email);
		
	}
}
