package microservices.tellme.usermodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import microservices.tellme.usermodule.domain.User;
import microservices.tellme.usermodule.service.UserService;
import microservices.tellme.usermodule.util.Constants;

/**
 * This class implements a REST API for my user module.
 */
@Slf4j
@RestController
@RequestMapping("/user")
final class UserController implements Constants {

	private final UserService userService;
	
	private final int serverPort;
	
	@Autowired
	public UserController(final UserService userService,  @Value("${server.port}") int serverPort) {
		this.userService = userService;
		this.serverPort = serverPort;
	}

	@RequestMapping(method = RequestMethod.POST, value = SAVE_USER, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	String saveUser(@RequestBody User user) {
		return userService.saveOrUpdateUser(user);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = USER_LOGIN, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	public String login(@RequestBody User user) {
		log.info("UserController :: login :: input :: {} ", user.toString());
		String response = userService.login(user);
		log.info("UserController :: login :: response :: {} ", response);
		return response;
		
	}


}
