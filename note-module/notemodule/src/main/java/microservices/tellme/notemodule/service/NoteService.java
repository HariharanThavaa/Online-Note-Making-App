package microservices.tellme.notemodule.service;

import java.util.List;

import microservices.tellme.notemodule.domain.KeyPoint;
import microservices.tellme.notemodule.domain.Note;
import microservices.tellme.notemodule.domain.Question;

public interface NoteService {
	
	List<Note> getPublicRootNotes();
	
	String saveOrUpdateNote(Note note);
	
	String saveOrUpdateQuestion(Question question);
	
	String saveOrUpdateKeyPoint(KeyPoint keyPoint);
	
	Note getUserNote(String userId);
	
	Note getNote(String noteId);
	
	List<Note> getChildNotes(Long noteId);
	
	List<Question> getQuestions(Long noteId);
	
	List<KeyPoint> getKeyPoints(Long questionId);
	

}
