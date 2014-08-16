$(function () {

    $('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            var mapOptions = {
                zoom: 8,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map"),
                mapOptions);
            loadGPXFileIntoGoogleMap(map, "/geolib/resources/geofiles/mostRecent");
        }
    });

});