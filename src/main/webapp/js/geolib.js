(function () {
    'use strict';

    var app = angular.module('geolib', []);

    app.factory('MapService', function () {
        return {
            loadMap: function (trackData) {
                var mapOptions = {
                    zoom: 8,
                    mapTypeId: google.maps.MapTypeId.TERRAIN
                };
                var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
                var xmlData = $.parseXML(trackData.content);
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
            loadTrack: function (trackMeta, callback) {
                $http({method: 'GET', url: '/geolib/resources/tracks/' + trackMeta.trackName}).
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
        var activeTrackMeta = undefined;
        $scope.loadTrack = function (trackMeta) {
            TrackService.loadTrack(trackMeta, function (trackData) {
                activeTrackMeta = trackData.trackMeta;
                MapService.loadMap(trackData);
            });
        };
        $scope.listTracks = function () {
            TrackService.listTracks(function (trackMetas) {
                $scope.trackMetas = trackMetas;
            });
        };
        $scope.isActiveTrack = function (trackMeta) {
            return activeTrackMeta !== undefined && trackMeta !== undefined && activeTrackMeta.trackName === trackMeta.trackName;
        };

        // TODO: clean up registration as callback for fileupload
        $('[data-js-selector="fileupload"]').fileupload({
            dataType: 'json',
            done: function (e, uploadedTrackMetas) {
                var trackMeta = uploadedTrackMetas.result[0];
                $scope.trackMetas.push(trackMeta);
                $scope.loadTrack(trackMeta);
            }
        });

        $scope.listTracks();
    });

})
();


