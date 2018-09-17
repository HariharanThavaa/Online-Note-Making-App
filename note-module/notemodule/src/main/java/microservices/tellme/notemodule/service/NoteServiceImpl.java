package microservices.tellme.notemodule.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import microservices.tellme.notemodule.domain.KeyPoint;
import microservices.tellme.notemodule.domain.Note;
import microservices.tellme.notemodule.domain.Question;
import microservices.tellme.notemodule.repository.KeyPointRepository;
import microservices.tellme.notemodule.repository.NoteRepository;
import microservices.tellme.notemodule.repository.QuestionRepository;
import microservices.tellme.notemodule.util.Constants;

@Slf4j
@Service
public class NoteServiceImpl implements NoteService, Constants {

	private NoteRepository repository;
	private QuestionRepository qRepository;
	private KeyPointRepository kpRepository;

	@Autowired
	public NoteServiceImpl(final NoteRepository repository, 
			final QuestionRepository qRepository,
			final KeyPointRepository kpRepository) {
		this.repository = repository;
		this.qRepository = qRepository;
		this.kpRepository = kpRepository;
	}

	public List<Note> getPublicRootNotes() {

		// get All the root notes
		List<Note> rootNotes = StreamSupport.stream(repository.getPublicRootNotes(NOTE_ACCESS_PUBLIC, NOTE_LEVEL_ROOT).spliterator(), false)
				.collect(Collectors.toList());

		// get all the root public notes
		return rootNotes;

	}

	@Override
	public String saveOrUpdateNote(Note note) {
		log.info("NoteServiceImpl :: saveOrUpdateNote :: " + note.toString());
		repository.save(note);
		return MESSAGE_SAVE_NOTE_SUCCESS;

	}

	@Override
	public Note getUserNote(String userId) {
		return repository.getUserNote(Long.valueOf(userId), NOTE_TYPE_USER);
	}
	
	@Override
	public Note getNote(String noteId) {
		return repository.getNote(Long.valueOf(noteId));
	}
	
	@Override
	public List<Note> getChildNotes(Long noteId) {
		return StreamSupport.stream(repository.getChildNotes(noteId).spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public String saveOrUpdateQuestion(Question question) {
		log.info("NoteServiceImpl :: saveOrUpdateQuestion :: " + question.toString());
		qRepository.save(question);
		return MESSAGE_SAVE_QUESTION_SUCCESS;
	}

	@Override
	public String saveOrUpdateKeyPoint(KeyPoint keyPoint) {
		log.info("NoteServiceImpl :: saveOrUpdateKeyPoint :: " + keyPoint.toString());
		kpRepository.save(keyPoint);
		return MESSAGE_SAVE_KEYPOINT_SUCCESS;
	}

	@Override
	public List<Question> getQuestions(Long noteId) {
		return StreamSupport.stream(qRepository.getQuestions(noteId).spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public List<KeyPoint> getKeyPoints(Long questionId) {
		return StreamSupport.stream(kpRepository.getKeyPoints(questionId).spliterator(), false)
				.collect(Collectors.toList());
	}
  
}
