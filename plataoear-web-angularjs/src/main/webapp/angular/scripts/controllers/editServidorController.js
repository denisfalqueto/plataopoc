

angular.module('jbossforgehtml5').controller('EditServidorController', function($scope, $routeParams, $location, ServidorResource , CidadeResource, UfResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;

    $scope.ufList = UfResource.queryAll(function(items){
        $scope.ufSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.sigla
            });
        });
    });

    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.servidor = new ServidorResource(self.original);
 
            if ($scope.servidor.cidade != undefined) {
	            CidadeResource.queryByUf({UfId:$scope.servidor.cidade.uf.id}, function (items) {
	                $scope.cidadeSelectionList = $.map(items, function(item) {
	                    var wrappedObject = item;
	                    wrappedObject.uf = $scope.servidor.cidade.uf;
	                    var labelObject = {
	                        value : item.id,
	                        text : item.nome
	                    };
	                    var labelObjectUf; 
	                    for (var i = 0, len = $scope.ufSelectionList.length; i < len; i++) {
	                    	if ($scope.servidor.cidade.uf.id == $scope.ufSelectionList[i].value) {
	                    		labelObjectUf = $scope.ufSelectionList[i];
	                    	}
	                    }
	                    if($scope.servidor.cidade && item.id == $scope.servidor.cidade.id) {
	                        $scope.cidadeSelection = labelObject;
	                        $scope.ufSelection = labelObjectUf;
	                        $scope.servidor.cidade = wrappedObject;
	                        self.original.cidade = $scope.servidor.cidade;
	                    }
	                    return labelObject;
	                });
	            }); 
        	}
        };
        var errorCallback = function() {
            $location.path("/servidores");
        };
        ServidorResource.get({ServidorId:$routeParams.ServidorId}, successCallback, errorCallback);
    };

    $scope.$watch("ufSelection", function(selection) {
        if ( typeof selection != 'undefined') {
        	if($scope.servidor.cidade != undefined && $scope.servidor.cidade.uf.id == selection.value) {
        		return;
        	}
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

    $scope.isClean = function() {
    	console.log("self.original", self.original);
    	console.log("$scope.servidor", $scope.servidor);
        return angular.equals(self.original, $scope.servidor);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.servidor.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/servidores");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/servidores");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.servidor.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("cidadeSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.servidor.cidade = jQuery.extend(true, {}, $scope.servidor.cidade);
            $scope.servidor.cidade.id = selection.value;
        }
    });
    
    $scope.get();
});