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
