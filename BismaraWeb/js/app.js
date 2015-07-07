var app = angular.module('app', [ 'ngRoute', 'app.controllers' ]);
/**
 * Configuración de rutas:
 */
app.config(function($routeProvider) {
	$routeProvider
	.when('/loginDoctor', {
		templateUrl : 'templates/login.html',
		controller : 'loginDoctorController'
	})
	.when('/loginPaciente', {
		templateUrl : 'templates/login.html',
		controller : 'loginPatientController'
	})
	.when('/index', {
		templateUrl : 'templates/home.html',
		controller : 'appController'
	})
	.when('/pacientes', {
		templateUrl : 'templates/viewPacientes.html',
		controller : 'doctorController'
	})
    .otherwise({
        redirectTo: '/index'
      });
});


