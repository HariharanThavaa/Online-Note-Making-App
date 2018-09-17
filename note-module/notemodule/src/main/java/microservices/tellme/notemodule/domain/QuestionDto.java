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
public class QuestionDto {
	
	Question quest;
	List<KeyPoint> keyPoints ;
	
	public QuestionDto(Question quest, List<KeyPoint> keyPoints) {
		this.quest = quest;
		this.keyPoints = keyPoints;
	}

}
