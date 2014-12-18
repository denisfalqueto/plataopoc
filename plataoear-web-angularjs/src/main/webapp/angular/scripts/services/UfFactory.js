angular.module('jbossforgehtml5').factory('UfResource', function($resource, APPCONFIG){
    var resource = $resource(APPCONFIG.REST_BASE_URL + 'rest/ufs/:UfId',{UfId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});