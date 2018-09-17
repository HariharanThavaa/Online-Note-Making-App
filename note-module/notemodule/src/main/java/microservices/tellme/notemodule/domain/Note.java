package microservices.tellme.notemodule.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Stores information of Note.
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class Note {

	@Id
    @GeneratedValue
    @Column(name = "NOTE_ID")
    private Long id;
	
	
	private final String title;
	private final String desc;
	private final Long parentId;
	private final Long userId;
	private final String access; //public, private
	private final String type; // user, data
	private final String level; // root, leaf
	

	// Empty constructor for JSON (de)serialization
	protected Note() {
		title = null;
		desc = null;
		userId = null;
		parentId = null;
		access = null;
		type = null;
		level = null;
	}

}
