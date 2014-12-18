angular.module('jbossforgehtml5').factory('CidadeResource', function($resource){
    var resource = $resource('../rest/ufs/:UfId/cidades',{UfId:'@id'},{'queryByUf':{method:'GET',isArray:true}});
    return resource;
});