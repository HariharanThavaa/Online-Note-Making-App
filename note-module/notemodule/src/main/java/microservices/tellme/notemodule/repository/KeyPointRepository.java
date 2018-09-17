package microservices.tellme.notemodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import microservices.tellme.notemodule.domain.KeyPoint;

@Repository
public interface KeyPointRepository extends CrudRepository<KeyPoint, Long> {
	
	@Query("SELECT kp FROM KeyPoint kp WHERE kp.questId = :questId ")
	List<KeyPoint> getKeyPoints(@Param("questId") final Long questId);
}
