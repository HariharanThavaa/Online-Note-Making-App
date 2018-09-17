var NoteService = app.service('NoteService', [ '$http', 'config', function($http, config) {
	this.getPublicRootNotes = function() {
	    return $http({
	        method : 'GET',
	        url : config.serverUrl + 'note/get_public_root_notes',
	    });
	}
	
	this.getNote = function( noteId) {
	    return $http({
	        method : 'GET',
	        url : config.serverUrl + 'note/get_note/'+noteId,
	    });
	}
	
	this.addNote = function addNote( note) {
	    return $http({
	        method : 'POST',
	        url : config.serverUrl + 'note/add_note',
	        data : note
	    });
	}
	
	this.getUserNote = function getUserNote(userId) {
	    return $http({
	        method : 'GET',
	        url : config.serverUrl + 'note/get_user_note/'+userId,
	    });
	}

	this.addQuestion = function(quest) {
	    return $http({
	        method : 'POST',
	        url : config.serverUrl + 'note/add_question',
	        data : quest
	    });
	}
	
	this.addKeyPoint = function( kpoint) {
	    return $http({
	        method : 'POST',
	        url : config.serverUrl + 'note/add_keypoint',
	        data : kpoint
	    });
	}
	
} ]);
