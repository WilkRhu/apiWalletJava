package com.wallet;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class HelloWordTests {
	@Test
	public void testHelloWorld() {
		assertEquals(1, 1);
	}
}
