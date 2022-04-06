package services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import vehicalDemo.ServoApplication;
import vehicalDemo.data.User;
import vehicalDemo.services.LoginAuthenticator;

@SpringBootTest
@ContextConfiguration(classes = ServoApplication.class)
@RunWith(SpringRunner.class)
class LoginAuthenticatorTest {

	@Autowired
	LoginAuthenticator la;

	@Test
	void login_authenticator_fetch_match() {
		Optional<User> user = la.authenticate("liya", "liya");

		assertTrue(user.isPresent());
		if (user.isPresent()) {
			assertEquals("liya", user.get().getUsername());
		}
	}

}
