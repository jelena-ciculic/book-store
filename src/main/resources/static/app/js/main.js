var bookStoreApp = angular.module('bookStoreApp',['bookStoreAppRoutes','bookStoreAppControllers','bookStoreAppFactories']);

bookStoreApp.run(['$rootScope', function($rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;
    });
}]);
