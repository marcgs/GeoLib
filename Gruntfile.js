"use strict";

module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        concat: {
            options: {
                separator: ';'
            },
            dist: {
                src: [
                    'src/main/webapp/js/geolib/*.js'
                ],
                dest: 'target/js/geolib.js'
            }
        },

        copy: {
            js: {
                src: [
                    'node_modules/angular/lib/angular.min*',
                    'node_modules/bootstrap/dist/css/bootstrap.min.*',
                    'node_modules/bootstrap/dist/js/bootstrap.min.*'
                ],
                dest: 'src/main/webapp/deps/',
                expand: true,
                flatten: true
            }
        },

        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy") %> */\n',
                mangle: false
            },
            dist: {
                files: {
                    'src/main/webapp/js/geolib.min.js': ['<%= concat.dist.dest %>']
                }
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

    grunt.registerTask('default', ['concat', 'uglify']);
};