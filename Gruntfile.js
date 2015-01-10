"use strict";

module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        copy: {
            js: {
                src: [
                    'node_modules/angular/lib/angular.min*',
                    'node_modules/bootstrap/dist/css/bootstrap.min.*',
                    'node_modules/bootstrap/dist/js/bootstrap.min.*',
                    'node_modules/jquery/dist/jquery.min.*',
                    'src/main/frontend/js/vendor/*.js'
                ],
                dest: 'src/main/webapp/assets/js',
                expand: true,
                flatten: true
            }
        },

        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy") %> */\n',
                mangle: false,
                sourceMap: true,
                sourceMapIncludeSources : true
            },
            dist: {
                src: 'src/main/frontend/js/geolib/*.js',
                dest: 'src/main/webapp/assets/js/geolib.min.js'
            }
        },

        karma: {
            unit: {
                configFile: 'karma.conf.js'
            }
        },

        jshint: {
            files: ['Gruntfile.js', 'src/**/*.js', 'test/**/*.js', "!**/*.min.js", "!**/vendor/*"],
            options: {
                jshintrc: '.jshintrc'
            }
        },

        watch: {
            files: ['<%= jshint.files %>'],
            tasks: ['jshint']
        }

    });

    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-karma');

    grunt.registerTask('default', ['copy', 'uglify']);
};