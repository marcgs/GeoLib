/*global $:false, spyOn:false */

'use strict';

describe('trackService', function() {
    var $httpBackend,
        TrackService;

    beforeEach(function() {
        module('geolibApp');

        inject(function (_$httpBackend_, _TrackService_) {
            $httpBackend = _$httpBackend_;
            TrackService = _TrackService_;
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

            TrackService.loadTrack(trackMock, function (data, track) {
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

            TrackService.listTracks(function (data) {
                expect(data).toBe(expectedData);
            });

            $httpBackend.flush();
        });
    });
});