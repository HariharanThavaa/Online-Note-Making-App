var userCtrl = app
		.controller(
				'userCtrl',
				[
						'$scope',
						'UserService',
						'NoteService',
						function($scope, UserService, NoteService) {
							$scope.addUser = function addUser() {
								if ($scope.signUpPwd
										&& $scope.signUpConfirmPwd
										&& $scope.signUpPwd == $scope.signUpConfirmPwd) {
									
									var alias = $scope.alias;
									var mail = $scope.signUpMail;
									
									UserService
											.addUser($scope.alias,
													$scope.signUpMail,
													$scope.signUpPwd)
											.then(
													function success(response) {
														addUserNote(
																alias,
																mail, response.data);
														$scope.message = 'User Details Successfully Added.';

													},
													function error(response) {
														$scope.errorMessage = 'Error adding user!';
														$scope.message = '';
													});
								} else {
									$scope.errorMessage = 'Confirm password should match password!';
									$scope.message = '';
								}

								$scope.alias = "";
								$scope.signUpMail = "";
								$scope.signUpPwd = "";
								$scope.signUpConfirmPwd = "";

							};

							function addUserNote(alias, mail, user) {
								var note = {
									title : alias,
									desc : mail,
									parentId : '1',
									userId : user,
									access : 'public',
									type : 'user',
									level : 'leaf'
								}
								NoteService.addNote(note).then(
										function success(response) {
											console.log('note added')
										}, function error(response) {
											console.log('Error adding note!')
										});
							}
						} ]);
