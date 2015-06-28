'use strict';
angular.module('app.controllers', [ 'app.services' ])

/**
 * 	Controladores:
 * 
 * 1- appController para la app en general.
 * 2- loginController para login.
 * 
 */

.controller('appController', function($scope) {
	$('#login-form-link').click(function(e) {
		$scope.user = "";
		$scope.doctor = "";
		$("#login-form").delay(100).fadeIn(100);
		$("#register-form").fadeOut(100);
		$("#social-buttons").fadeIn(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#register-form-link').click(function(e) {
		$scope.doctor = "";
		$scope.user = "";
		$("#register-form").delay(100).fadeIn(100);
		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$("#social-buttons").fadeOut(100);
		$(this).addClass('active');
		e.preventDefault();
	});
})

.controller('loginController', function($scope, AuthService) {
	$scope.login = function(user) {
		$scope.user = user;
		AuthService.login(user);
	}

	$scope.newUser = function(userRegistration, doctor) {
		var user = {
			username : userRegistration.username,
			password : userRegistration.password,
			state : {
				id : 1,
				description : "Pending"
			}
		};

		var doctor = {
			user : user,
			firstName : doctor.firstName,
			lastName : doctor.lastName,
			registrationNumber : doctor.registrationNumber,
			phone : doctor.phone,
			email : doctor.email
		};
		$scope.doctor = doctor;
		AuthService.newDoctor($scope.doctor);
	}
});

