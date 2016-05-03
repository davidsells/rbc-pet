angular.module('hello', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home',
		controllerAs : 'controller'
	}).otherwise('/');

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('navigation',

function($rootScope, $http, $location, $route) {

	var self = this;

	self.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};

	$http.get('user').then(function(response) {
		if (response.data.name) {
			$rootScope.authenticated = true;
		} else {
			$rootScope.authenticated = false;
		}
	}, function() {
		$rootScope.authenticated = false;
	});

	self.credentials = {};

	self.logout = function() {
		$http.post('logout', {}).finally(function() {
			$rootScope.authenticated = false;
			$location.path("/");
		});
	}

}).controller('home', function($http) {
	var self = this;
	$http.get('resource/v1/message/').then(function(response) {
		self.greeting = response.data;
	});
	$http.get('resource/v1/pet/').then(function(response) {
		console.dir(response);
		self.pet = response.data;
	});
	$http.get('resource/v1/category/').then(function(response) {
		console.log("Acid Test....");
		console.dir(response);
		self.category = response.data;
	});
});
