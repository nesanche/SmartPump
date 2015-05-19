'use strict';
angular.module('app.controllers', ['app.services'])

  .controller('appController', function($scope) {

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

        	$scope.login = function (user) {
      			$scope.user = user;
				var userJSON = angular.toJson(user);
      			AuthService.login(userJSON);
      		}

      		$scope.newUser = function (user) {      			
      			var user = {
	          		username: user.username,
	          		password: user.password,
	          		matricula: user.matricula,
	          		first_name: user.firstName,
	          		last_name: user.lastName,
	          		phone: user.phone,
	          		location: user.location,
	          		email: user.email
      			};
      			$scope.user = user;

      			AuthService.newUser($scope.user);
      		}

    });