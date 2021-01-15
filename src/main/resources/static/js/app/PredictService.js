'use strict'
angular.module('demo.services', []).factory('PredictService', ["$http", "CONSTANTS", function($http, CONSTANTS) {
    var service = {};
    service.getPredictById = function(PredictId) {
        var url = CONSTANTS.getPredictByIdUrl + PredictId;
        return $http.get(url);
    }
    service.getAllPredicts = function() {
        return $http.get(CONSTANTS.getAllPredicts);
    }
    service.savePredict = function(PredictDto) {
        return $http.post(CONSTANTS.savePredict, PredictDto);
    }
    service.predictHouse = function(houseDto) {
        return $http.post('/v1/house', houseDto);
    }
    return service;
}]);