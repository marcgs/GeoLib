(function () {
    'use strict';

    var app = angular.module('geolib', []);

    app.factory('MapService', function () {
        return {
            loadMap: function (trackMeta) {
                var mapOptions = {
                    zoom: 8,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
                var xmlData = $.parseXML(trackMeta.content);
                var parser = new GPXParser(xmlData, map);
                parser.setTrackColour("#ff0000");     // Set the track line colour
                parser.setTrackWidth(5);          // Set the track line width
                parser.setMinTrackPointDelta(0.001);      // Set the minimum distance between track points
                parser.centerAndZoom(xmlData);
                parser.addTrackpointsToMap();         // Add the trackpoints
                parser.addWaypointsToMap();           // Add the waypoints
            }
        };
    });

    app.factory('TrackService', function ($http) {
        return {
            loadMostRecentTrack: function (callback) {
                $http({method: 'GET', url: '/geolib/resources/tracks/mostRecent'}).
                    success(function (data, status, headers, config) {
                        callback(data);
                    });
            },
            loadTrack: function (trackName, callback) {
                $http({method: 'GET', url: '/geolib/resources/tracks/' + trackName}).
                    success(function (data, status, headers, config) {
                        callback(data);
                    });
            },
            loadAllTracks: function (callback) {
                $http({method: 'GET', url: '/geolib/resources/tracks'}).
                    success(function (data, status, headers, config) {
                        callback(data);
                    });
            }};
    });

    app.controller("GeoLibController", function ($scope, $http, TrackService, MapService) {
        var activeTrack = undefined;
        $scope.loadMostRecentTrack = function () {
            TrackService.loadMostRecentTrack(function (data) {
                activeTrack = data;
                MapService.loadMap(data);
            });
        };
        $scope.loadTrack = function (fileName) {
            TrackService.loadTrack(fileName, function (data) {
                activeTrack = data;
                MapService.loadMap(data);
            });
        };
        $scope.loadAllTracks = function () {
            TrackService.loadAllTracks(function (data) {
                $scope.tracks = data;
            });
        };
        $scope.isActiveTrack = function (track) {
            return activeTrack.fileName === track.fileName;
        };

        // TODO: clean up registration as callback for fileupload
        $('[data-js-selector="fileupload"]').fileupload({
            dataType: 'json',
            done: function (e, data) {
                $scope.loadAllTracks();
            }
        });

        $scope.loadAllTracks();
        $scope.loadMostRecentTrack();
    });

})
();


