/*global $:false, spyOn:false */

'use strict';

describe('geolibApp', function() {

    var fileUploadData,
        mapData,
        tracksMock = ['item1', 'item2'],
        trackServiceMock = {
            listTracks: function(callback) {callback(tracksMock);},
            loadTrack: function(track, callback) {callback(undefined, track);}
        },
        mapServiceMock = {
            loadMap: function (data) {mapData = data;}
        };

    beforeEach(function() {
        module('geolibApp');
        $('<input data-js-selector="fileupload">').appendTo('body');
        $.fn.fileupload = function (input) { fileUploadData = input;};
    });

    describe('GeoLib Controller', function(){

        it('should initialize scope', inject(function($controller, $rootScope) {
            var scope = $rootScope.$new(),
                controller = initController($controller, scope);

            expect(controller).toBeDefined();
            expect(scope.loadTrack).toBeDefined();
            expect(scope.listTracks).toBeDefined();
            expect(scope.isActiveTrack).toBeDefined();
            expect(scope.tracks).toBe(tracksMock);
        }));

        it('should initialize fileuploader', inject(function($controller, $rootScope) {
            var scope = $rootScope.$new(),
                controller = initController($controller, scope);

            expect(controller).toBeDefined();
            expect(fileUploadData).toBeDefined();
            expect(fileUploadData.dataType).toBe('json');
            expect(fileUploadData.done).toBeDefined();

            var trackMock = 'track3';
            fileUploadData.done(null, {result: [ trackMock]});
            expect(scope.tracks.indexOf(trackMock)).toBe(2);
        }));

        it('should load tracks', inject(function($controller, $rootScope) {
            var trackMock = { _id: "id"},
                scope = $rootScope.$new(),
                controller = initController($controller, scope);

            expect(controller).toBeDefined();
            expect(scope.isActiveTrack(trackMock)).toBeFalsy();
            scope.loadTrack(trackMock);
            expect(scope.isActiveTrack(trackMock)).toBeTruthy();
        }));

    });

    function initController($controller, scope) {
        return $controller('GeoLibController', {
            '$scope': scope,
            TrackService: trackServiceMock,
            mapService: mapServiceMock
        });
    }
});