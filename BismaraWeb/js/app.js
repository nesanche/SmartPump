var app = angular.module('app', [ 'ngRoute', 'app.controllers' ]);
/**
 * Configuración de rutas:
 * 
 * 1- Login
 */
app.config(function($routeProvider) {
	$routeProvider
	.when('/loginMedico', {
		templateUrl : 'templates/login.html',
		controller : 'loginMedicoController'
	})
	.when('/loginPaciente', {
		templateUrl : 'templates/login.html',
		controller : 'loginPacienteController'
	})
	.when('/index', {
		templateUrl : 'templates/home.html',
		controller : 'appController'
	})
    .otherwise({
        redirectTo: '/index'
      });
});


