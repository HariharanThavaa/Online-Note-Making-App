var LoginService =  app.service('LoginService', [ '$http', 'config', function($http, config) {
	this.login = function login( mail, password) {
	    return $http({
	        method : 'POST',
	        url : config.serverUrl + 'user/login',
	        data : {
	            mail: mail,
	            password: password
	        }
	    });
	}
} ]);
