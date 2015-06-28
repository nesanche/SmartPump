var app = angular.module('app', [ 'ngRoute', 'app.controllers' ]);
/**
 * Configuración de rutas:
 * 
 * 1- Login
 */
app.config(function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl : 'templates/login.html',
		controller : 'appController'
	})
})
