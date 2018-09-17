var noteCtrl = app
		.controller(
				'noteCtrl',
				[
						'$scope',
						'$rootScope',
						'NoteService',
						function($scope, $rootScope, NoteService) {
							$scope.accordion = {
								current : null,
								quest : null
							};

							NoteService.getPublicRootNotes().then(
									function success(response) {
										console.log(response.data);
										$scope.notes = response.data;
										$scope.noteTitle = $scope.notes.title;
									}, function error(response) {
										// $scope.login-errorMessage = 'Invalid
										// User!';

									});

							NoteService
									.getUserNote($rootScope.userLoggedIn.id)
									.then(
											function success(response) {
												console.log(response.data);
												$scope.mainNote = response.data.mainNote;
												console.log("childNotes "
														+ $scope.childNotes);
												$scope.childNotes = response.data.childNotes;
												$scope.questions = response.data.questions;
											}, function error(response) {
												// $scope.login-errorMessage =
												// 'Invalid User!';

											});

							// Add Child Notes
							$scope.addChildNote = function() {
								if ($scope.addchildNoteTitle !== "") {
									var note = {
										title : $scope.addchildNoteTitle,
										desc : $scope.addChildNoteDesc,
										parentId : $scope.mainNote.id,
										userId : $rootScope.userLoggedIn.id,
										access : 'private',
										type : 'data',
										level : 'leaf'
									}
									NoteService.addNote(note).then(
													function success(response) {
														console.log('child Note added')
														NoteService
																.getNote(
																		$scope.mainNote.id)
																.then(
																		function success(
																				response) {
																			$scope.mainNote = response.data.mainNote;
																			$scope.childNotes = response.data.childNotes;
																			$scope.questions = response.data.questions;
																		},
																		function error(
																				response) {
																			// $scope.login-errorMessage
																			// =
																			// 'Invalid
																			// User!';

																		});

													},
													function error(response) {
														console
																.log('Error adding note!')
													});

								}

								$scope.addchildNoteTitle = "";
								$scope.addChildNoteDesc = "";

							};

							//Step in to the Node
							$scope.stepInToNote = function(noteId) {
								NoteService
										.getNote(noteId)
										.then(
												function success(response) {
													$scope.mainNote = response.data.mainNote;
													$scope.childNotes = response.data.childNotes;
													$scope.questions = response.data.questions;
												}, function error(response) {
													// $scope.login-errorMessage = 'Invalid User!';

												});
							};
							
							$scope.deleteNote = function(noteId) {
								console.log(noteId)
							};
							
							//Add Questions
							$scope.addQuestion = function() {
								if($scope.addQuestionTxt !== ""){
									var quest = {
											noteId : $scope.mainNote.id,
											quest : $scope.addQuestionTxt,
											isPublic : false,
										}
									NoteService.addQuestion(quest).then(
											function success(response) {
												console.log('Question added')
												NoteService.getNote($scope.mainNote.id)
												.then(
														function success(response) {
															$scope.mainNote = response.data.mainNote;
															$scope.childNotes = response.data.childNotes;
															$scope.questions = response.data.questions;
														}, function error(response) {
															// $scope.login-errorMessage = 'Invalid User!';

														});
											}, function error(response) {
												// $scope.login-errorMessage = 'Invalid User!';

											});
									$scope.addQuestionTxt = "";
								}
							};
							
							//Add Questions
							$scope.addKeyPoint = function( id, newkeyPoint) {
								if(id !== "" && newkeyPoint !== ""){
									var keypoint = {
											questId : id,
											point : newkeyPoint,
											isPublic : false,
										}
							
									NoteService.addKeyPoint(keypoint).then(
											function success(response) {
												console.log('Key Point added' + $scope.mainNote.id)
												NoteService.getNote($scope.mainNote.id)
												.then(
														function success(response) {
															console.log(response.data);
															$scope.mainNote = response.data.mainNote;
															$scope.childNotes = response.data.childNotes;
															$scope.questions = response.data.questions;
															console.log(response.data.questions)
														}, function error(response) {
															// $scope.login-errorMessage = 'Invalid User!';

														});
											}, function error(response) {
												// $scope.login-errorMessage = 'Invalid User!';

											});
									$scope.newkeyPoint = "";
								}
							};

						} ]);
