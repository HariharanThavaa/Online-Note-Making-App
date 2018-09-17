package microservices.tellme.usermodule.service;

import microservices.tellme.usermodule.domain.User;

public interface UserService {
	
	String saveOrUpdateUser(User user);
	
	String login( User user);

}
