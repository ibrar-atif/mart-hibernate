var dirApp = angular.module('dirApp', ['ngRoute','ui.router']);

dirApp.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/app/productDetail');

	$stateProvider.state('app', {
		url: '/app',
		templateUrl: 'layout.html',
		abstract : true
	})
	
	.state('app.product', {
		url: '/productDetail',
		templateUrl: 'partials/product.html'
	})

		
});