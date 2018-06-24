var bookStoreApp = angular.module('bookStoreAppRoutes',['ngRoute']);

bookStoreApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		
		.when('/', {
			title:'Bookstore-app',
			templateUrl : '/app/html/home.html'
		})
		.when('/books', {
			title:'Books',
			templateUrl : '/app/html/books.html'
		})
	
		.when('/books/:id', {
			title:'edit_book',
			templateUrl : '/app/html/edit_book.html'
		})

		.when('/authors', {
			title:'Authors',
			templateUrl : '/app/html/authors.html'
		})

		.when('/authors/:id', {
			title: 'edit_author',
			templateUrl : '/app/html/edit_author.html'
		})

		.when('/publishers', {
			title:'Publishers',
			templateUrl : '/app/html/publishers.html'
		})

		
		.when('/publishers/:id', {
			title:'edit_publisher',
			templateUrl : '/app/html/edit_publisher.html'
		})

		.when('/votes', {
			title:'Top 10',
			templateUrl : '/app/html/top_10.html'
		})
		
 
		.otherwise({
			redirectTo: '/'
		});
}]);