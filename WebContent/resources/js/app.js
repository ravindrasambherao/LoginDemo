var loginApp = angular.module('loginApp',['ngRoute','ngAnimate', 'ngSanitize', 'ui.bootstrap']);
//var vendorApp = angular.module("poemApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);

loginApp.config(['$routeProvider', function($routeProvider){	
	$routeProvider
	.when('/',{
		templateUrl:'resources/templates/indexloginhome.html',
		controller:'MainCtrl'
	})
	.when('/dashboard',{
		templateUrl:'resources/templates/dashboard.html',
		controller:'MainCtrl'
	})	
	.otherwise({
		redirectTo : '/'
	});

} ]);