//y aca consumo y envio a la rest
//aca tengo otro modulo
// .factory con el mismo nombre que habia declarado en el controller anteriormente.
// en las factorias puedo implementar librerias de funciones o almacenar datos. me devuelven un dato de cualquier tipo

//podes tener otras como .service o .provider  pero aca uso .factory 
//y para la comunicaciion con la rest usas $resource vs $http..en este caso opte por el ultimo
//http.post y http.get
//la diferencia aca: http://angularjs4u.com/http/angularjs-resource-http/

angular.module('app.services', ['ngResource', 'angular-loading-bar', 'ngAnimate'])

//para la barra de progreso
.config(function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = true;
  })

.factory('AuthService', function ($rootScope, $http, cfpLoadingBar) {
    var authService = {};
    var pathLocal = "http://localhost:8080/";
    var pathRemote = "http://bismara.elasticbeanstalk.com/";
 
    authService.login = function (credentials) {
        cfpLoadingBar.start();
      	/*var sanitizeCredentials = function(credentials) {
        return {
          username: $sanitize(credentials.username),
          password: $sanitize(credentials.password),
          csrf_token: CSRF_TOKEN
        };
      	};*/

        //la conexion va a dar .succes si es correcta pudieron ver el data, status, etc
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
             //aca podria ver tambien la data, status del error.
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
  
//y aca la factory retorna authService dependiendo el caso del metodo en el que entre...
  return authService;

 })