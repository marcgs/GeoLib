(function () {
    'use strict';

    angular.module('geolib.Map', [])

    .factory('map', function() {
        return new google.maps.Map(
            document.getElementById("map-canvas"),
            {
                zoom: 8,
                mapTypeId: google.maps.MapTypeId.TERRAIN
            }
        );
        })

    .factory('mapService', function (map) {
        return {
            loadMap: function (data) {
                var xmlData = $.parseXML(data);
                var parser = new GPXParser(xmlData, map);
                parser.setTrackColour("#ff0000");       // Set the track line colour
                parser.setTrackWidth(5);                // Set the track line width
                parser.setMinTrackPointDelta(0.001);    // Set the minimum distance between track points
                parser.centerAndZoom(xmlData);
                parser.addTrackpointsToMap();           // Add the trackpoints
                parser.addWaypointsToMap();             // Add the waypoints
            }
        };
    });

})();


