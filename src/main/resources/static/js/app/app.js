'use strict'
var demoApp = angular.module('demo', ['ui.bootstrap', 'demo.controllers',
    'demo.services'
]);
demoApp.constant("CONSTANTS", {
    getPredictByIdUrl: "/Predict/getPredict/",
    getAllPredicts: "/Predict/getAllPredicts",
    savePredict: "/Predict/savePredict"
});