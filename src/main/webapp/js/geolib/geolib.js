(function () {
    'use strict';

    var app = angular.module('geolibApp', ['geolibApp.Map', 'geolibApp.Track']);

    app.controller("GeoLibController", function ($scope, trackService, mapService) {
        var activeTrack;
        $scope.loadTrack = function (track) {
            trackService.loadTrack(track, function (data, track) {
                activeTrack = track;
                mapService.loadMap(data);
            });
        };
        $scope.listTracks = function () {
            trackService.listTracks(function (tracks) {
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


