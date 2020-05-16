package net.service.command;

import net.model.User;
import net.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceCommand {

	private final UserRepository userRepository;

	public UserServiceCommand(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(long id) {
		userRepository.deleteById(id);
	}

}
