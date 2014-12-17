angular.module('jbossforgehtml5').factory('UfResource', function($resource){
    var resource = $resource('../rest/ufs/:UfId',{UfId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});