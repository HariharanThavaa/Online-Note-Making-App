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
 * Stores information of Question.
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class KeyPoint {
	
	@Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    private Long id;
	
	private Long questId;
	private final String point;
	private final boolean isPublic;

	// Empty constructor for JSON (de)serialization
	protected KeyPoint() {
		questId = null;
		point = null;
		isPublic = false;
	}

}
