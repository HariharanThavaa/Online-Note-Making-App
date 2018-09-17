package microservices.tellme.notemodule.util;

public interface Constants {
	
	String PARAM_PARENT_ID = "parent_id";
	
	public static final String PATH_ADD_NOTE = "/add_note" ;
	
	public static final String PATH_ADD_QUESTION = "/add_question" ;
	
	public static final String PATH_ADD_KEYPOINT = "/add_keypoint" ;
	
	public static final String PATH_GET_PUBLIC_ROOT_NOTES = "/get_public_root_notes";
	
	public static final String PATH_GET_USER_NOTE = "/get_user_note/{userId}";
	
	public static final String PATH_GET_NOTE = "/get_note/{noteId}";
	
	//public static final String GET_CHILD_NOTES = "/get_child_notes/{" + PARAM_PARENT_ID + "}";
	
	//Messages
	public static final String MESSAGE_SAVE_NOTE_SUCCESS = "Note Saved Success";
	
	public static final String MESSAGE_SAVE_QUESTION_SUCCESS = "Question Saved Success";
	
	public static final String MESSAGE_SAVE_KEYPOINT_SUCCESS = "Keypoint Saved Success";
	
	public static final String MESSAGE_SAVE_NOTE_FAILED = "Note Save Failed";
	
	public static final String MESSAGE_EMPTY_STRING = "";
	
	public static final String MESSAGE_SAVE_NOTE_TEST = "Note Saved Test";
	
	
	//Note Constants
	//access
	public static final String NOTE_ACCESS_PUBLIC = "public";
	public static final String NOTE_ACCESS_PRIVATE = "private";
	
	//type
	public static final String NOTE_TYPE_USER = "user";
	public static final String NOTE_TYPE_DATA = "data";
	
	//level
	public static final String NOTE_LEVEL_ROOT = "root";
	public static final String NOTE_LEVEL_LEAF = "leaf";
	

}
