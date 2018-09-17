package microservices.tellme.notemodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import microservices.tellme.notemodule.domain.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

	@Query("SELECT n " + "FROM Note n WHERE n.access = :access AND n.level = :level  ")
	List<Note> getPublicRootNotes(@Param("access") final String access, @Param("level") final String level);

	@Query("SELECT n " + "FROM Note n " + "WHERE n.userId = :userId " + "AND n.type = :type ")
	Note getUserNote(@Param("userId") final Long userId, @Param("type") final String type);
	
	@Query("SELECT n FROM Note n WHERE n.id = :noteId")
	Note getNote(@Param("noteId") final Long noteId);

	@Query("SELECT n FROM Note n WHERE n.parentId = :parentId ")
	List<Note> getChildNotes(@Param("parentId") final Long parentId);

}
