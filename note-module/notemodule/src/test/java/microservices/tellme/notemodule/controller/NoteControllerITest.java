package microservices.tellme.notemodule.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import microservices.tellme.notemodule.domain.Note;
import microservices.tellme.notemodule.service.NoteService;
import microservices.tellme.notemodule.util.Constants;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteControllerITest implements Constants {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private NoteService noteService;
	
	private final static String PATH_NOTE_API = "/note";

	// This object will be magically initialized by the initFields method below.
	private JacksonTester<Note> json;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void testGetPublicRootNotes() throws Throwable {
		
		Note note = new Note("title","desc",1L,1L, NOTE_ACCESS_PUBLIC,
				NOTE_TYPE_DATA, NOTE_LEVEL_ROOT);
		
		// given
		given(noteService.getPublicRootNotes()).willReturn(Arrays.asList(note));

		// when
        MockHttpServletResponse response = mvc.perform(
                get(PATH_NOTE_API.concat(PATH_GET_PUBLIC_ROOT_NOTES)).contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).contains(((new ObjectMapper())
				.writeValueAsString(Arrays.asList(note))));
	}
	
	@Test
	public void testSaveNote() throws Exception {
		
		Note note = new Note("title","desc",1L,1L, NOTE_ACCESS_PUBLIC,
				NOTE_TYPE_DATA, NOTE_LEVEL_ROOT);
		
		log.info(json.write(note).getJson());
		
		// given
		given(noteService.saveOrUpdateNote(note)).willReturn("success");

		// when
        MockHttpServletResponse response = mvc.perform(
                post("/note/add_note").contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(note).getJson()))
                .andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo("success");
	}


}
