var app = angular.module('crudApp',['ui.router','ngStorage', 'ng-currency']);

app.constant('urls', {
    BASE: '/',
    USER_SERVICE_API : '/api/user/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'ClienteController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, ClienteService) {
                        console.log('carregando todos os clientes');
                        var deferred = $q.defer();
                        ClienteService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);
