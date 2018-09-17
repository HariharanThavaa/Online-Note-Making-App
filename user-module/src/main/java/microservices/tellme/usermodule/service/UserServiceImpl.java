package microservices.tellme.usermodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import microservices.tellme.usermodule.domain.User;
import microservices.tellme.usermodule.repository.UserRepository;
import microservices.tellme.usermodule.util.Constants;

@Slf4j
@Service
class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public UserServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String saveOrUpdateUser(User user) {
		String response = Constants.EXISTING_USER;
		int count = userRepository.mailExist(user.getMail());
		log.info("count is {} ", count);
		if( count <= 0) {
			User newUser = userRepository.save(user);
			response = String.valueOf(newUser.getId());
		}
		return response;
	}
	
	@Override
	public String login( User user) {
		 User userData= userRepository.getUser(user.getMail());
		if(userData != null && userData.getPassword().equalsIgnoreCase(user.getPassword())) {
			try {
				return  mapper.writeValueAsString(userData);
			} catch (JsonProcessingException e) {
				return e.getMessage();
			}
		}else {
			return Constants.INVALID_USER;
		}
	}

}
