package com.mgomez.geolib.track.controller.couchdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.ektorp.CouchDbConnector;
import org.ektorp.impl.StdObjectMapperFactory;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class CouchDbGeoLibObjectMapperFactory extends StdObjectMapperFactory {

    private StdObjectMapperFactory delegate = new StdObjectMapperFactory();


    public synchronized ObjectMapper createObjectMapper() {
        final ObjectMapper om = delegate.createObjectMapper();
        om.registerModule(new JSR310Module());
        return om;
    }

    public ObjectMapper createObjectMapper(CouchDbConnector connector) {
        final ObjectMapper om = delegate.createObjectMapper(connector);
        om.registerModule(new JSR310Module());
        return om;
    }
}