(function () {
    'use strict';

    var app = angular.module('geolib', []);

    app.factory('MapService', function () {
        return {
            loadMap: function (track) {
                var mapOptions = {
                    zoom: 8,
                    mapTypeId: google.maps.MapTypeId.TERRAIN
                };
                var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
                var xmlData = $.parseXML(track.content);
                var parser = new GPXParser(xmlData, map);
                parser.setTrackColour("#ff0000");     // Set the track line colour
                parser.setTrackWidth(5);          // Set the track line width
                parser.setMinTrackPointDelta(0.001);      // Set the minimum distance between track points
                parser.centerAndZoom(xmlData);
                parser.addTrackpointsToMap();         // Add the trackpoints
                parser.addWaypointsToMap();           // Add the waypoints
            }
        };
    })
    ;

    app.factory('TrackService', function ($http) {
        return {
            loadTrack: function (track, callback) {
                $http({method: 'GET', url: '/geolib/resources/tracks/' + track._id}).
                    success(function (data, status, headers, config) {
                        callback(data);
                    });
            },
            listTracks: function (callback) {
                $http({method: 'GET', url: '/geolib/resources/tracks'}).
                    success(function (data, status, headers, config) {
                        callback(data);
                    });
            }};
    });

    app.controller("GeoLibController", function ($scope, $http, TrackService, MapService) {
        var activeTrack = undefined;
        $scope.loadTrack = function (track) {
            TrackService.loadTrack(track, function (track) {
                activeTrack = track;
                MapService.loadMap(track);
            });
        };
        $scope.listTracks = function () {
            TrackService.listTracks(function (tracks) {
                $scope.tracks = tracks;
            });
        };
        $scope.isActiveTrack = function (track) {
            return activeTrack !== undefined && track !== undefined && activeTrack.name === track.name;
        };

        // TODO: clean up registration as callback for fileupload
        $('[data-js-selector="fileupload"]').fileupload({
            dataType: 'json',
            done: function (e, uploadedTracks) {
                var track = uploadedTracks.result[0];
                $scope.tracks.push(track);
                $scope.loadTrack(track);
            }
        });

        $scope.listTracks();
    });

})
();


