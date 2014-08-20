(function () {
    'use strict';

    var app = angular.module('geolib', []);

    function loadMap(trackMeta) {
        var mapOptions = {
            zoom: 8,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);
        var xmlData = $.parseXML(trackMeta.content);
        var parser = new GPXParser(xmlData, map);
        parser.setTrackColour("#ff0000");     // Set the track line colour
        parser.setTrackWidth(5);          // Set the track line width
        parser.setMinTrackPointDelta(0.001);      // Set the minimum distance between track points
        parser.centerAndZoom(xmlData);
        parser.addTrackpointsToMap();         // Add the trackpoints
        parser.addWaypointsToMap();           // Add the waypoints
    }

    app.factory('TrackFileService', function ($http) {
        return {
            loadMostRecentTrack: function () {
                $http({method: 'GET', url: '/geolib/resources/tracks/mostRecent'}).
                    success(loadMap);
            },
            loadTrack: function (trackName) {
                $http({method: 'GET', url: '/geolib/resources/tracks/' + trackName}).
                    success(loadMap);
            },
            loadTracks: function (callback) {
                $http({method: 'GET', url: '/geolib/resources/tracks'}).
                    success(function (data, status, headers, config) {
                        callback(data);
                    });
            }};
    });


    app.controller("GeoLibController", function ($scope, $http, TrackFileService) {
        var reload = function () {
            TrackFileService.loadTracks(function (data) {
                $scope.tracks = data;
            });
            TrackFileService.loadMostRecentTrack();
        }
        // TODO: clean up registration as callback for fileupload
        $('[data-js-selector="fileupload"]').fileupload({
            dataType: 'json',
            done: function (e, data) {
                reload();
            }
        });
        reload();
        $scope.loadTrack = TrackFileService.loadTrack;
    });

})
();


