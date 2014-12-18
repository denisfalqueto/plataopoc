angular.module('jbossforgehtml5').factory('CidadeResource', function($resource, APPCONFIG){
    var resource = $resource(APPCONFIG.REST_BASE_URL + 'rest/ufs/:UfId/cidades',{UfId:'@id'},{'queryByUf':{method:'GET',isArray:true}});
    return resource;
});