'use strict';
angular.module('app.controllers', ['app.services'])

  .controller('appController', function($scope) {
	  
	  //queda pendiente limpiar campos segun movimiento de pestañas

         $('#login-form-link').click(function(e) { 
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
 		$("#social-buttons").fadeIn(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
		});

		$('#register-form-link').click(function(e) {
			$("#register-form").delay(100).fadeIn(100);
	 		$("#login-form").fadeOut(100);
			$('#login-form-link').removeClass('active');
			$("#social-buttons").fadeOut(100);
			$(this).addClass('active');
			e.preventDefault();
		});


    })

.controller('loginController', function($scope , AuthService) {

        	$scope.login = function (doctor) {
      			$scope.doctor = doctor;
				var doctorJSON = angular.toJson(doctor);
      			AuthService.login(doctorJSON);
      		}

      		$scope.newUser = function (user) {      			
      			var user = {
	          		username: user.username,
	          		confirmPassword: user.confirmPassword,
	          		password: user.password,
	          		matricula: user.matricula,
	          		first_name: user.first_name,
	          		last_name: user.last_name,
	          		phone: user.phone,
	          		location: user.location,
	          		email: user.email
      			};
      			$scope.user = user;

      			AuthService.newUser($scope.user);
      		}

    });