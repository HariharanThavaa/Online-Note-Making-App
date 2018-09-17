package microservices.tellme.notemodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import microservices.tellme.notemodule.domain.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
	
	@Query("SELECT q FROM Question q WHERE q.noteId = :noteId ")
	List<Question> getQuestions(@Param("noteId") final Long noteId);
	
}
