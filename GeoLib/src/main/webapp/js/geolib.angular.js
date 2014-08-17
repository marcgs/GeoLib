(function () {
    'use strict';
    var app = angular.module('geolib', []);

    app.controller("GeoLibController", function ($scope, $http) {
        $http({method: 'GET', url: '/geolib/resources/geofiles'}).
            success(function (data, status, headers, config) {
                $scope.geofiles = data;
            }).
            error(function (data, status, headers, config) {
            });

    });

})();

