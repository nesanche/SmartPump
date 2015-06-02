
angular.module('app.services', ['ngResource', 'angular-loading-bar', 'ngAnimate'])

.config(function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = true;
  })

.factory('AuthService', function ($rootScope, $http, cfpLoadingBar) {

    var authService = {};
    var pathLocal = "http://localhost:8888/";
    var pathRemote = "http://3-dot-smartpump-dev.appspot.com/";
 
    authService.login = function (credentials) {
        cfpLoadingBar.start();
      	/*var sanitizeCredentials = function(credentials) {
        return {
          username: $sanitize(credentials.username),
          password: $sanitize(credentials.password),
          csrf_token: CSRF_TOKEN
        };
      	};*/
		   	$http.post(pathRemote+"rest/myresource/login",credentials).
			  success(function(data, status, headers, config) {
            cfpLoadingBar.complete();
				    if(data == null){
              //alert("No existe el Medico con los datos ingresados! Imposible ingresar.")
			  sweetAlert("Error!", "No exite el Medico con los datos ingresados!", "error")
			}
            else{
              //alert("Bienvenido a Bismara " + data.username);
              sweetAlert("Bienvenido nuevamente a Bismara "+ data.username);
              location.reload();
            }
			  }).
			  error(function(data, status, headers, config) {
             cfpLoadingBar.complete();
			   		 //alert("Imposible conectar con el servidor.")
             		sweetAlert("Error de Servidor", "Imposible conectr con el servidor.", "error")
			  });

  }

  authService.newUser = function (user) {
        $http.post(pathRemote+"rest/myresource/new",user).
        success(function(data, status, headers, config) {
            cfpLoadingBar.complete();    
            if(data == "null"){
              //alert("Ya existe un medico con esa matricula! Imposible registrar.")
            	sweetAlert("Oops..", "Ya existe un Medico con esa matricula!", "error")
            }
            else{
              //alert("Bienvenido a Bismara " + data.username);
              sweetAlert("Bienvenido!", "Gracias por registrarse en Bismara " + data.username, "success");
              location.reload();
            }
        }).
        error(function(data, status, headers, config) {
             cfpLoadingBar.complete();
              //alert("Imposible conectar con el servidor.")
              sweetAlert("Imposible conectar con el servidor.", "error")
        });
  }
  

  return authService;

 })