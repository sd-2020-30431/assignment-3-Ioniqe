package net.service.query;

import net.model.User;
import net.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceQuery {
	private final UserRepository userRepository;

	public UserServiceQuery(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findUser(String username, String password) {
		return userRepository.findUserByUsernameAndPassword(username, password);
	}

	public User findUserByUsername(String username){return userRepository.findUserByUsername(username);}

}
