var loginCtrl = app.controller('loginCtrl', ['$scope','$rootScope', 'LoginService', 
	  function ($scope, $rootScope, LoginService ) {
		$scope.login = function () {
			console.log('mail :: ' + $scope.email);
			console.log('pwd :: ' + $scope.pwd);
			LoginService.login( $scope.email, $scope.pwd )
				.then (function success(response){
					if(response.data == 'invalidUser'){
						$rootScope.isLoggedIn = false;
						$scope.invalidUser = response.data;
					}else{
						$rootScope.isLoggedIn = true;
						$rootScope.userLoggedIn = response.data;
					}					
					console.log(response.data);
				},
				function error(response){
					//$scope.login-errorMessage = 'Invalid User!';
					
				});
			$scope.email = "";
			$scope.pwd = "";
		};
	}]);

