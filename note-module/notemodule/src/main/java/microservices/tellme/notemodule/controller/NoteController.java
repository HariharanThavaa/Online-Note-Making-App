package microservices.tellme.notemodule.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import microservices.tellme.notemodule.domain.KeyPoint;
import microservices.tellme.notemodule.domain.Note;
import microservices.tellme.notemodule.domain.NoteDto;
import microservices.tellme.notemodule.domain.Question;
import microservices.tellme.notemodule.domain.QuestionDto;
import microservices.tellme.notemodule.service.NoteService;
import microservices.tellme.notemodule.util.Constants;

/**
 * This class implements a REST API for note module.
 */
@Slf4j
@RestController
@RequestMapping("/note")
public class NoteController implements Constants {

	private final NoteService noteService;

	private final int serverPort;

	@Autowired
	public NoteController(final NoteService noteService, @Value("${server.port}") int serverPort) {
		this.noteService = noteService;
		this.serverPort = serverPort;
	}

	@RequestMapping(method = RequestMethod.GET, value = PATH_GET_PUBLIC_ROOT_NOTES, produces = MediaType.APPLICATION_JSON_VALUE)
	List<Note> getPublicRootNotes() {
		List<Note> list = Collections.emptyList();

		list = noteService.getPublicRootNotes();

		log.info("NoteController :: getPublicRootNotes :: {} ", list);
		return list;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = PATH_ADD_NOTE, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	String saveNote(@RequestBody Note note) {
		log.info("NoteController :: saveNote :: response :: " + note.toString() );
		String response = noteService.saveOrUpdateNote(note);
		log.info("NoteController :: saveNote :: response :: " + response );
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = PATH_ADD_QUESTION, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	String saveQuestion(@RequestBody Question question) {
		log.info("NoteController :: saveQuestion :: response :: " + question.toString() );
		String response = noteService.saveOrUpdateQuestion(question);
		log.info("NoteController :: saveQuestion :: response :: " + response );
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = PATH_ADD_KEYPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	String saveKeyPoint(@RequestBody KeyPoint keypoint) {
		log.info("NoteController :: saveKeyPoint :: response :: " + keypoint.toString() );
		String response = noteService.saveOrUpdateKeyPoint(keypoint);
		log.info("NoteController :: saveKeyPoint :: response :: " + response );
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = PATH_GET_USER_NOTE, produces = MediaType.APPLICATION_JSON_VALUE)
	NoteDto getUserNote(@PathVariable String userId) {
		Note note = null;
		if(userId == null || userId.isEmpty() || userId.equalsIgnoreCase("undefined")) {
			note =  new Note("Root Note",
					"Root Note Description",1L,1L, 
					NOTE_ACCESS_PUBLIC,
					NOTE_TYPE_DATA, 
					NOTE_LEVEL_ROOT);
			
			return new NoteDto( note, new ArrayList<Note>(), new ArrayList<QuestionDto>());
		}
		note = noteService.getUserNote(userId);
		List<Note> childNotes = noteService.getChildNotes(note.getId());
		
		List<QuestionDto> questionDtos = noteService.getQuestions(note.getId()).stream()
				.map(q -> new QuestionDto( q, noteService.getKeyPoints(q.getId())))
				.collect(Collectors.toList());
		
		log.info("NoteController :: getUserNote :: {} ", note);
		return new NoteDto( note, childNotes, questionDtos);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = PATH_GET_NOTE, produces = MediaType.APPLICATION_JSON_VALUE)
	NoteDto getNote(@PathVariable String noteId) {
		Note note = null;
		if(noteId == null || noteId.isEmpty() || noteId.equalsIgnoreCase("undefined")) {
			note =  new Note("Root Note",
					"Root Note Description",1L,1L, 
					NOTE_ACCESS_PUBLIC,
					NOTE_TYPE_DATA, 
					NOTE_LEVEL_ROOT);
		}
		note = noteService.getNote(noteId);
		List<Note> childNotes = noteService.getChildNotes(note.getId());
		
		List<QuestionDto> questionDtos = noteService.getQuestions(note.getId()).stream()
				.map(q -> new QuestionDto( q, noteService.getKeyPoints(q.getId())))
				.collect(Collectors.toList());
		
		log.info("NoteController :: getUserNote :: {} ", note);
		return new NoteDto( note, childNotes, questionDtos);
	}

}
