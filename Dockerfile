#FROM jboss/wildfly
FROM sillenttroll/wildfly-java-8
MAINTAINER Marc Gomez <marc.gomez82@gmail.com>
ADD target/geolib.war /opt/wildfly/standalone/deployments/
