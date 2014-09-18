'use strict';

describe('GeoLib', function() {

    beforeEach(module('GeoLib'));

    describe('GeoLib Controller', function(){
        var scope,
            controller;

        it('should have a GeoLibController', inject(function($controller) {
            beforeEach(inject(function ($rootScope, $controller) {
                scope = $rootScope.$new();
                controller = $controller('GeoLibController', {
                    '$scope': scope
                });
            }));

            expect(controller).toBeDefined();
        }));

    });
});