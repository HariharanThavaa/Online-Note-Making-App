package microservices.tellme.usermodule.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import microservices.tellme.usermodule.domain.User;
import microservices.tellme.usermodule.repository.UserRepository;
import microservices.tellme.usermodule.util.Constants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	private UserServiceImpl userServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userServiceImpl = new UserServiceImpl(userRepository);
	}

	@Test
	public void existingUserTest() {

		String alias = "test";
		String mail = "test@test.com";
		String password = "test";

		User user = new User(alias, mail, password);

		// given (our mocked Random Generator service will return first 50, then 30)
		given(userRepository.mailExist(mail)).willReturn(2);

		// when
		String response = userServiceImpl.saveOrUpdateUser(user);

		// then
		assertThat(response).isEqualTo(Constants.EXISTING_USER);
	}

	@Test
	public void accountCreatedTest() {

		String alias = "test";
		String mail = "test@test.com";
		String password = "test";

		User user = new User(alias, mail, password);

		// given (our mocked Random Generator service will return first 50, then 30)
		given(userRepository.mailExist(mail)).willReturn(0);
		
		given(userRepository.save(user)).willReturn(user);

		// when
		String response = userServiceImpl.saveOrUpdateUser(user);

		// then
		assertThat(response).isEqualTo(String.valueOf(user.getId()));
	}
}
