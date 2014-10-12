GeoLib
======

GeoLib is a web application for managing GPX files and displaying them on a Google map, which I use as a pet project for trying out new stuff.

The application consists of an AngularJS frontend interacting over JAX-RS REST Services with a Java Backend and a CouchDB. I've tried to TDD as much as I could, so the coverage is close to 100% for both Java and Javascript.

Deployment is automated with Docker and Vagrant. The resulting Docker container uses Wildfly 8.1 as application server.
