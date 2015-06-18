//se pone 'app' ya que en el index declare ng-app="app".
//ngroute sirve para el manejo de rutas...e ir abriendo la vista que se me plazca segun lo que ponga en .when
//aca esta puesto el caso donde no pongo nada en la ruta (osea /index.html/) y me abrira el template del login.html
//esta capa App se comunica con la capa Controllers y por ello se declara el app.controllers
var app = angular.module('app', ['ngRoute', 'app.controllers']);

    // configure our routes
    app.config(function($routeProvider) {
        $routeProvider

            // route for the login page
            .when('/', {
                templateUrl : 'templates/login.html',
                controller  : 'appController'
            })
            
    })
