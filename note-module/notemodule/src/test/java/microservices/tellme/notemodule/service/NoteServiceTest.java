package microservices.tellme.notemodule.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import microservices.tellme.notemodule.domain.Note;
import microservices.tellme.notemodule.repository.KeyPointRepository;
import microservices.tellme.notemodule.repository.NoteRepository;
import microservices.tellme.notemodule.repository.QuestionRepository;
import microservices.tellme.notemodule.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteServiceTest implements Constants {
 
    
    private NoteServiceImpl noteService;
    
    @Mock
	private NoteRepository repository;
    
    @Mock
	private QuestionRepository qrepository;
    
    @Mock
	private KeyPointRepository kpRepository;
    
    @Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		noteService = new NoteServiceImpl( repository, qrepository, kpRepository);
	}

	@Test
	public void getRootNotes() throws Throwable {
		
		Note note = new Note("title","desc",1L,1L, NOTE_ACCESS_PUBLIC,
				NOTE_TYPE_DATA, NOTE_LEVEL_ROOT);
		
		// given public rootnotes from DB 
		given(repository.getPublicRootNotes(NOTE_ACCESS_PUBLIC, NOTE_LEVEL_ROOT))
		.willReturn(Arrays.asList(note));
		
		List<Note> actual = noteService.getPublicRootNotes();
		
		assertThat(actual).contains(note);
		
	}
	
	@Test
	public void addNoteTest() throws Throwable {
		
		Note note = new Note("title","desc",1L,1L, NOTE_ACCESS_PUBLIC,
				NOTE_TYPE_DATA, NOTE_LEVEL_ROOT);
		
		// given public rootnotes from DB 
		given(repository.save(note)).willReturn(note);
		
		String actual = noteService.saveOrUpdateNote(note);
		
		assertThat(actual).isEqualTo(MESSAGE_SAVE_NOTE_SUCCESS);
		
	}
	
	

	
}
