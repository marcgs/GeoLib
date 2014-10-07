(function () {
    'use strict';

    angular.module('geolibApp.Track', [])

    .factory('trackService', function ($http) {
        return {
            loadTrack: function (track, callback) {
                $http({method: 'GET', url: '/geolib/resources/tracks/' + track._id}).
                    success(function (data) {
                        callback(data, track);
                    });
            },
            listTracks: function (callback) {
                $http({method: 'GET', url: '/geolib/resources/tracks'}).
                    success(function (data) {
                        callback(data);
                    });
            }};
    });

})();


