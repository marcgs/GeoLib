$(function () {

    function initialize() {
        var mapOptions = {
            center: new google.maps.LatLng(41.4, 2.2),
            zoom: 10
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);
    }

    google.maps.event.addDomListener(window, 'load', initialize);

    function loadGPXFileIntoGoogleMap(map, filename) {
        $.ajax({url: filename,
            dataType: "xml",
            success: function (data) {
                var parser = new GPXParser(data, map);
                parser.setTrackColour("#ff0000");     // Set the track line colour
                parser.setTrackWidth(5);          // Set the track line width
                parser.setMinTrackPointDelta(0.001);      // Set the minimum distance between track points
                parser.centerAndZoom(data);
                parser.addTrackpointsToMap();         // Add the trackpoints
                parser.addWaypointsToMap();           // Add the waypoints
            }
        });
    }


    $('[data-js-selector="fileupload"]').fileupload({
        dataType: 'json',
        done: function (e, data) {
            var mapOptions = {
                zoom: 8,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map-canvas"),
                mapOptions);
            loadGPXFileIntoGoogleMap(map, "/geolib/resources/geofiles/mostRecent");
        }
    });
});


