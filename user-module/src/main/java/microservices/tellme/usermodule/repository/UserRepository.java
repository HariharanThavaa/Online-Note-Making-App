package microservices.tellme.usermodule.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import microservices.tellme.usermodule.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	 @Query("SELECT Count(*) " +
		        "FROM User u " +
		        "WHERE u.mail = :mail ")
	int mailExist(@Param("mail") String mail);
	 
	 @Query("SELECT u " +
		        "FROM User u " +
		        "WHERE u.mail = :mail ")
	User getUser(@Param("mail") String mail);
}
