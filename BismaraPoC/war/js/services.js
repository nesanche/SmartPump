
angular.module('app.services', ['ngResource', 'angular-loading-bar', 'ngAnimate'])

.config(function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = true;
  })

.factory('AuthService', function ($rootScope, $http, cfpLoadingBar) {

    var authService = {};
 
    authService.login = function (credentials) {
    cfpLoadingBar.start();
  	/*var sanitizeCredentials = function(credentials) {
    return {
      username: $sanitize(credentials.username),
      password: $sanitize(credentials.password),
      csrf_token: CSRF_TOKEN
    };
  	};*/
		   	$http.post("http://localhost:8888/rest/myresource/login",credentials).
			  success(function(data, status, headers, config) {
            cfpLoadingBar.complete();
				   return true;
			  }).
			  error(function(data, status, headers, config) {
             cfpLoadingBar.complete();
			   		return false;
			  });

  }

  authService.newUser = function (user) {
        $http.post("http://localhost:8888/rest/myresource/new",user).
        success(function(data, status, headers, config) {
            cfpLoadingBar.complete();
            alert("bien");
           return true;
        }).
        error(function(data, status, headers, config) {
             cfpLoadingBar.complete();
             alert("mal");
            return false;
        });
  }
  

  return authService;

 })