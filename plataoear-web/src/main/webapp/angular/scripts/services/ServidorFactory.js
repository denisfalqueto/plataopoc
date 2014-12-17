angular.module('jbossforgehtml5').factory('ServidorResource', function($resource){
    var resource = $resource('../rest/servidores/:ServidorId',{ServidorId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});