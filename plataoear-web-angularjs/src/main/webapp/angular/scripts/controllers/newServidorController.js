
angular.module('jbossforgehtml5').controller('NewServidorController', function ($scope, $location, locationParser, ServidorResource , CidadeResource, UfResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.servidor = $scope.servidor || {};
    
    $scope.ufList = UfResource.queryAll(function(items){
        $scope.ufSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.sigla
            });
        });
    });
//    $scope.cidadeList = CidadeResource.queryAll(function(items){
//        $scope.cidadeSelectionList = $.map(items, function(item) {
//            return ( {
//                value : item.id,
//                text : item.nome
//            });
//        });
//    });
    $scope.$watch("ufSelection", function(selection) {
        if ( typeof selection != 'undefined') {
        	var successCallback = function(data){
                self.original = data;
                $scope.servidor = new ServidorResource(self.original);
                CidadeResource.queryAll(function(items) {
                    $scope.cidadeSelectionList = $.map(items, function(item) {
                        var wrappedObject = {
                            id : item.id
                        };
                        var labelObject = {
                            value : item.id, 
                            text : item.sigla
                        };
                        if($scope.servidor.cidade && item.id == $scope.servidor.cidade.id) {
                            $scope.cidadeSelection = labelObject;
                            $scope.servidor.cidade = wrappedObject;
                            self.original.cidade = $scope.servidor.cidade;
                        }
                        return labelObject;
                    });
                });
            };
            var errorCallback = function() {
                $location.path("/servidores");
            };
        	console.log("UF"+ selection.value);
        	var cidades = CidadeResource.queryByUf({UfId:selection.value}, function (items) {
        		$scope.cidadeSelectionList = $.map(items, function(item) {
                  return ( {
                      value : item.id,
                      text : item.nome
                  });
              });
        	})
        }
    });
    
    $scope.$watch("cidadeSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.servidor.cidade = {};
            $scope.servidor.cidade.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/servidores/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ServidorResource.save($scope.servidor, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/servidores");
    };
});