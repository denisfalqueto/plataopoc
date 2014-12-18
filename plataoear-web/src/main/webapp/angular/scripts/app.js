'use strict';

angular.module('jbossforgehtml5',['ngRoute','ngResource', 'angular-loading-bar'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Cidades',{templateUrl:'views/Cidade/search.html',controller:'SearchCidadeController'})
      .when('/Cidades/new',{templateUrl:'views/Cidade/detail.html',controller:'NewCidadeController'})
      .when('/Cidades/edit/:CidadeId',{templateUrl:'views/Cidade/detail.html',controller:'EditCidadeController'})
      .when('/Servidores',{templateUrl:'views/Servidor/search.html',controller:'SearchServidorController'})
      .when('/Servidores/new',{templateUrl:'views/Servidor/detail.html',controller:'NewServidorController'})
      .when('/Servidores/edit/:ServidorId',{templateUrl:'views/Servidor/detail.html',controller:'EditServidorController'})
      .when('/Ufs',{templateUrl:'views/Uf/search.html',controller:'SearchUfController'})
      .when('/Ufs/new',{templateUrl:'views/Uf/detail.html',controller:'NewUfController'})
      .when('/Ufs/edit/:UfId',{templateUrl:'views/Uf/detail.html',controller:'EditUfController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
