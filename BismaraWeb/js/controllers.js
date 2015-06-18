//aca esta el controller que declara app.services ya que se comunicara con el services para hacer los llamados a la rest
//aca proceso los datos de las vistas y los mando a serivices.js
//como esta parte se comunica con la VISTA...necesito en la vista declarar etiquetas propias de Angular y todas empiezan
//con ng....por ejemplo ng-submit
//$scope es una variable global que voy utilizando...si te fijas en login.html en los inputs le agrego la etiqueta 
//ng-model="doctor.username" al usuario y  ng-model="doctor.password" al password.
//al submit del form lo hago con ng-submit="login(doctor)"..entonces mando el objeto doctor como parametro
//lo recibo y lo guardo en scope para manejarlo globalmente con $scope.doctor = doctor;
//osea...la ingenieria inversa seria desde el controller guardar $scope.doctor.username = 'franqui' y en la vista
//poniendo  <input type="text" ng-model="doctor.username"> ya muestro el valor.
//toda la documentacio de angular esta en https://docs.angularjs.org
//todo lo que empiece con $('# es Jquery


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

  //por ultimo, para conectar este controlador con el services, declaro el nombre del servicio en el parametro del function()
//como por ejemplo AuthService y llamo a sus metodos con AuthService.metodo(parametros)

.controller('loginController', function($scope , AuthService) {

        	$scope.login = function (doctor) {
      			$scope.doctor = doctor;
      			//al objeto doctor lo transformo en json para mandarlo a la rest despues
				var doctorJSON = angular.toJson(doctor);
      			AuthService.login(doctorJSON);
      		}

      		$scope.newUser = function (user) {    
      			//al objeto user lo transformo "manualmente" en JSON y llamo al metodo newUser
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