(function () {
    'use strict';

    var app = angular.module('geolib', []);

    app.factory('TrackLoader', function ($http) {
        return {
            loadMostRecentTrack: function () {
                $http({method: 'GET', url: '/geolib/resources/geofiles/mostRecent'}).
                    success(function (data, status, headers, config) {
                        var mapOptions = {
                            zoom: 8,
                            mapTypeId: google.maps.MapTypeId.ROADMAP
                        };
                        var map = new google.maps.Map(document.getElementById("map-canvas"),
                            mapOptions);
                        var xmlData = $.parseXML(data);
                        var parser = new GPXParser(xmlData, map);
                        parser.setTrackColour("#ff0000");     // Set the track line colour
                        parser.setTrackWidth(5);          // Set the track line width
                        parser.setMinTrackPointDelta(0.001);      // Set the minimum distance between track points
                        parser.centerAndZoom(xmlData);
                        parser.addTrackpointsToMap();         // Add the trackpoints
                        parser.addWaypointsToMap();           // Add the waypoints
                    });
            }};
    });

    app.factory('FileLoader', function ($http) {
        return {
            loadFiles: function (callback) {
                $http({method: 'GET', url: '/geolib/resources/geofiles'}).
                    success(function (data, status, headers, config) {
                        callback(data);
                    });
            }};
    });

    app.controller("GeoLibController", function ($scope, $http, FileLoader, TrackLoader) {
        FileLoader.loadFiles(function (data) {
            $scope.geofiles = data;
        });
        TrackLoader.loadMostRecentTrack();
    });

})();


