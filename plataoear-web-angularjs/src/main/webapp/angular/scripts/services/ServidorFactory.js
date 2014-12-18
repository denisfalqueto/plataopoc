angular.module('jbossforgehtml5').factory('ServidorResource', function($resource, APPCONFIG){
    var resource = $resource(APPCONFIG.REST_BASE_URL + 'rest/servidores/:ServidorId',{ServidorId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
}); 