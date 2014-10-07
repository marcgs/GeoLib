(function () {
    'use strict';

    var app = angular.module('geolibApp', ['geolibApp.Map']);

    app.factory('TrackService', function ($http) {
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

    app.controller("GeoLibController", function ($scope, TrackService, mapService) {
        var activeTrack;
        $scope.loadTrack = function (track) {
            TrackService.loadTrack(track, function (data, track) {
                activeTrack = track;
                mapService.loadMap(data);
            });
        };
        $scope.listTracks = function () {
            TrackService.listTracks(function (tracks) {
                $scope.tracks = tracks;
            });
        };
        $scope.isActiveTrack = function (track) {
            return activeTrack !== undefined && track !== undefined && activeTrack._id === track._id;
        };

        $('[data-js-selector="fileupload"]').fileupload({
            dataType: 'json',
            done: function (e, uploadedTracks) {
                var track = uploadedTracks.result[0];
                $scope.loadTrack(track);
                $scope.tracks.push(track);
            }
        });

        $scope.listTracks();
    });

})
();


