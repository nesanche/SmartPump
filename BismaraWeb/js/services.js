var pathLocal = "http://localhost:8080/";
var pathRemote = "http://bismara.elasticbeanstalk.com/";
angular.module('app.services',[ 'ngResource', 'angular-loading-bar', 'ngAnimate','angular-md5' ])

/**
 * Factory con servicios:
 * 1- AuthService para login en general y registración de doctor.
 */

.config(function(cfpLoadingBarProvider) {
			cfpLoadingBarProvider.includeSpinner = true;
})

.directive('usernameValidator', function($http, $q) {
                    return {
                        require: 'ngModel',
                        link: function(scope, element, attrs, ngModel) {
                            ngModel.$asyncValidators.username = function(modelValue, viewValue) {
                                if (!viewValue) {
                                    return $q.when(true);
                                }
                                var deferred = $q.defer();                                  
                                $http.get(pathRemote+'rest/users/verifyUsername?username='+viewValue).then(
					                    function(response) {
					                        if (response.data == 'false') {
					                        	 deferred.reject();
					                        }
					                        deferred.resolve();                    
					                    }
					                );                                    
                                return deferred.promise;
                            };
                        }
                    };
})

.directive('emailValidator', function($http, $q) {
				    return {
				        require: 'ngModel',
				        link: function(scope, element, attrs, ngModel) {
				            ngModel.$asyncValidators.email = function(modelValue, viewValue) {
				                if (!viewValue) {
                                    return $q.when(true);
                                }
                                var deferred = $q.defer();                                  
                                $http.get(pathRemote+'rest/doctors/verifyEmail?email='+viewValue).then(
					                    function(response) {
					                        if (response.data == 'false') {
					                        	 deferred.reject();
					                        }
					                        deferred.resolve();                    
					                    }
					                );                                    
                                return deferred.promise;
				            };
				        }
				    };
})

.factory('AuthService',	function($rootScope, $http, cfpLoadingBar, md5) {
		
		var authService = {};		
					
					authService.login = function(user) {
						cfpLoadingBar.start();
						var passwordEncryt = md5.createHash(user.password || '');
						$http.get(pathRemote+"rest/doctors/getDoctor",{
							headers: {'username': user.username, 'password': passwordEncryt }
							})
							.success(function(data, status, headers, config) {
											cfpLoadingBar.complete();
											if (data == null) {
												sweetAlert("Oops...","No existe el Médico con los datos ingresados!","error");
											} else {
												if(data.user.state.id == 1){
													 sweetAlert("Pendiente de Autorización","Todavía no está autorizado para usar Bismara!","warning");
												}
												else{
															sweetAlert("Bienvenido a Bismara "+ user.username);
												}
											}
										})
								.error(function(data, status, headers, config) {
									cfpLoadingBar.complete();
											sweetAlert("Oops...","Imposible conectar con el servidor.","error");
										});
					}
					
					authService.newDoctor = function(doctor) {
						doctor.user.password = md5.createHash(doctor.user.password || '');
						console.log(JSON.stringify(doctor));
						console.log(pathRemote + "rest/doctors/new");
						$http.post(pathRemote + "rest/doctors/new", doctor)
								.success(function(data, status, headers, config) {
											cfpLoadingBar.complete();
											if (data == "null") {
												sweetAlert("Oops..","Ya existe un Medico con esa matrícula!","error");
											} else {
												sweetAlert("Bienvenido!","Gracias por registrarse en Bismara "+ data.user.username,"success");
											}
										})
								.error(function(data, status, headers, config) {
											cfpLoadingBar.complete();
											sweetAlert("Oops...","Imposible conectar con el servidor.","error");
										});
					}
					return authService;
})