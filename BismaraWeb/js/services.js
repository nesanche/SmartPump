angular.module('app.services',[ 'ngResource', 'angular-loading-bar', 'ngAnimate','angular-md5' ])

/**
 * Factory con servicios:
 * 1- AuthService para login en general y registración de doctor.
 */

var pathLocal = "http://localhost:8080/";
var pathRemote = "http://bismara.elasticbeanstalk.com/";

.config(function(cfpLoadingBarProvider) {
			cfpLoadingBarProvider.includeSpinner = true;
})
		
.factory('AuthService',	function($rootScope, $http, cfpLoadingBar, md5) {
					var authService = {};		
					
					authService.login = function(user) {
						cfpLoadingBar.start();
						var passwordEncryt = md5.createHash(user.password || '');
						$http.get(pathRemote+"rest/doctors/getDoctor?username="+user.username+"&password="+passwordEncryt)
							.success(function(data, status, headers, config) {
											cfpLoadingBar.complete();
											if (data == null) {
												sweetAlert("Error!","No existe el Médico con los datos ingresados!","error");
											} else {
												if(data.user.state.id == 1){
													 sweetAlert("Pendiente de Autorización","Todavía no está autorizado para usar Bismara!","");
												}
												else{
															sweetAlert("Bienvenido a Bismara "+ user.username);
												}
											}
										})
								.error(function(data, status, headers, config) {
									cfpLoadingBar.complete();
											sweetAlert("Error de Servidor","Imposible conectar con el servidor.","error");
										});
					};
					
					authService.newDoctor = function(doctor) {
						doctor.user.password = md5.createHash(doctor.user.password || '');
						$http.post(pathRemote + "rest/doctors/new", doctor)
								.success(function(data, status, headers, config) {
											cfpLoadingBar.complete();
											if (data == "null") {
												sweetAlert("Oops..","Ya existe un Medico con esa matrícula!","error");
											} else {
												sweetAlert("Bienvenido!","Gracias por registrarse en Bismara "+ doctor.user.username,"success");
											}
										})
								.error(function(data, status, headers, config) {
											cfpLoadingBar.complete();
											sweetAlert("Imposible conectar con el servidor.","error");
										});
					}
					return authService;
})