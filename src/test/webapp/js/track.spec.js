/*global $:false, spyOn:false */

'use strict';

describe('trackService', function() {
    var $httpBackend,
        trackService;

    beforeEach(function() {
        module('geolibApp');

        inject(function (_$httpBackend_, _trackService_) {
            $httpBackend = _$httpBackend_;
            trackService = _trackService_;
        });
    });

    afterEach (function () {
        $httpBackend.verifyNoOutstandingExpectation ();
        $httpBackend.verifyNoOutstandingRequest ();
    });

    describe('when loadTrack is called', function() {
        it('should load tracks', function() {
            var trackMock = {_id: "id"},
                expectedData = 'trackData';

            $httpBackend
                .whenGET("/geolib/resources/tracks/id")
                .respond(expectedData);

            trackService.loadTrack(trackMock, function (data, track) {
                expect(data).toBe(expectedData);
                expect(track).toBe(trackMock);
            });

            $httpBackend.flush();
        });
    });

    describe('when listTrack is called', function() {
        it('should list tracks', function() {
            var expectedData = 'listTrackData';

            $httpBackend
                .whenGET("/geolib/resources/tracks")
                .respond(expectedData);

            trackService.listTracks(function (data) {
                expect(data).toBe(expectedData);
            });

            $httpBackend.flush();
        });
    });
});