package vehicalDemo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vehicalDemo.data.User;

@Component
public class LoginAuthenticator {


	@Autowired
	EntityManager em;

	public Optional<User> authenticate(String username, String password) {

		TypedQuery<User> query = em.createQuery("select u from User u where username=?1 and password=?2", User.class);

		query.setParameter(1, username);
		query.setParameter(2, password);

		List<User> results = query.getResultList();

		if (results.size() > 0)
			return Optional.of(results.get(0));
		else
			return Optional.empty();

	}
}
