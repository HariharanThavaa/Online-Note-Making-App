package microservices.tellme.notemodule.domain;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Data object to UI
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class NoteDto {
	Note mainNote;
	List<Note> childNotes;
	List<QuestionDto> questions;
	
	public NoteDto(Note mainNote, List<Note> childNotes, List<QuestionDto> questions) {
		this.mainNote = mainNote;
		this.childNotes = childNotes;
		this.questions = questions;
	}
}
