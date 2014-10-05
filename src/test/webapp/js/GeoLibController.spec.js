/*global $:false, spyOn:false */

'use strict';

describe('GeoLib', function() {

    beforeEach(module('GeoLib'));

    describe('GeoLib Controller', function(){
        var fileUploadData,
            mapData,
            tracks = ['item1', 'item2'],
            trackServiceMock = {
                listTracks: function(callback) {callback(tracks);},
                loadTrack: function(track, callback) {callback(undefined, track);}
            },
            mapServiceMock = {
                loadMap: function (data) {mapData = data;}
            };

        $('<input data-js-selector="fileupload">').appendTo('body');
        $.fn.fileupload = function (input) { fileUploadData = input;};

        it('should initialize scope', inject(function($controller, $rootScope) {
            var scope = $rootScope.$new(),
                controller = $controller('GeoLibController', {
                '$scope': scope,
                TrackService: trackServiceMock
            });
            expect(controller).toBeDefined();
            expect(fileUploadData).toBeDefined();
            expect(fileUploadData.dataType).toBe('json');
            expect(scope.loadTrack).toBeDefined();
            expect(scope.listTracks).toBeDefined();
            expect(scope.isActiveTrack).toBeDefined();
            expect(scope.tracks).toBe(tracks);
        }));

        it('should load tracks', inject(function($controller, $rootScope) {
            var trackMock = { _id: "id"},
                scope = $rootScope.$new(),
                controller = $controller('GeoLibController', {
                '$scope': scope,
                TrackService: trackServiceMock,
                MapService:  mapServiceMock
            });

            expect(controller).toBeDefined();
            expect(scope.isActiveTrack(trackMock)).toBeFalsy();
            scope.loadTrack(trackMock);
            expect(scope.isActiveTrack(trackMock)).toBeTruthy();
        }));
    });
});