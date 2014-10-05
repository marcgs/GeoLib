/*global $:false, spyOn:false */

'use strict';

describe('GeoLib', function() {
    var $httpBackend,
        TrackService;

    beforeEach(function() {
        module('GeoLib');

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

            //$httpBackend.when("GET", "/geolib/resources/tracks/id").respond([{}, {}, {}]);
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
});