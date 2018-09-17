var UserService = app.service('UserService', [ '$http', 'config', function($http, config) {
	this.addUser = function addUser( alias, mail, password) {
	    return $http({
	        method : 'POST',
	        url : config.serverUrl + 'user/saveUser',
	        data : {
	            alias : alias,
	            mail: mail,
	            password: password
	        }
	    });
	}
} ]);

//https://www.baeldung.com/angularjs-crud-with-spring-data-rest