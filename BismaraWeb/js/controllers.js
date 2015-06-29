'use strict';
var app = angular.module('app.controllers', ['app.services' , 'ngMessages']);

/**
 * 	Controladores:
 * 
 * 1- appController para la app en general.
 * 2- loginController para login y nuevo registro.
 * 
 */
var compareTo = function() {
	    return {
	        require: "ngModel",
	        scope: {
	            otherModelValue: "=compareTo"
	        },
	        link: function(scope, element, attributes, ngModel) {	        	
	            ngModel.$validators.compareTo = function(modelValue) {
	                return modelValue == scope.otherModelValue;
	            };	 
	            scope.$watch("otherModelValue", function() {
	                ngModel.$validate();
	            });
	        }
	    };
	};
app.directive("compareTo", compareTo)

.directive('passwordValidator', function($q) {
                    return {
                        require: 'ngModel',
                        link: function(scope, element, attrs, ngModel) {
                            ngModel.$asyncValidators.password = function(modelValue, viewValue) {
                                if (!viewValue) {
                                    return $q.when(true);
                                }
                                	var deferred = $q.defer(); 
                                	var passw = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$/;                                   
					                if (!viewValue.match(passw)) {
					                    deferred.reject();
					                }
					                deferred.resolve();                                                    
                                return deferred.promise;
                            };
                        }
                    };
})

app.controller('appController', function($scope) {

})

app.controller('loginPacienteController', function($scope) {
	$('#register-form-link').remove();
	$('#inicioSesion').css("width", "100%");
	$('#login-form-link').click(function(e) {
		$scope.user = "";
		$scope.doctor = "";
		e.preventDefault();
	});
})

app.controller('loginMedicoController', function($scope, AuthService) {

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

	$scope.login = function(user) {
		$scope.user = user;
		AuthService.login(user);
	}

	$scope.newUser = function(userRegistration, doctor) {
		
		if(userRegistration.password != userRegistration.confirmPassword){
			sweetAlert("Oops...","Debe confirmar correctamente la contrasenia!","error");
			return false;
		}

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
			address : doctor.address,
			email : doctor.email
		};
		$scope.doctor = doctor;
		AuthService.newDoctor($scope.doctor);
	}

});









