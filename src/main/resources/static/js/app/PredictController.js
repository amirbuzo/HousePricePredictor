'use strict'
var module = angular.module('demo.controllers', []);

module.controller("PredictController", ["$scope", "PredictService",
    function($scope, PredictService) {
     
        $scope.houseDto = {
        	sizeMeters: 100,
        	furnished: "NO",
            ownerType: "ESTATE",
            hasLift:"YES",
            legalized: "ME_HIPOTEKE",
            buildingYear: 2020,
            city:"Fresku",
            modelType:"TWO_PLUS_ONE",
            algorithm:"RANDOM_TREE"
        };
     
        $scope.savePredict = function() {
            var newhouseDto =PredictService.predictHouse($scope.houseDto).then(function(value){
                    console.log(value.data);
                    $scope.houseDto = value.data;
            });
        }
    }
]);


