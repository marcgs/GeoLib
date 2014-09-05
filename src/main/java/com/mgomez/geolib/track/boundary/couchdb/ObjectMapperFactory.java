package com.mgomez.geolib.track.boundary.couchdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.ektorp.CouchDbConnector;
import org.ektorp.impl.StdObjectMapperFactory;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class ObjectMapperFactory extends StdObjectMapperFactory {

    private StdObjectMapperFactory delegate = new StdObjectMapperFactory();

    @Override
    public synchronized ObjectMapper createObjectMapper() {
        final ObjectMapper om = delegate.createObjectMapper();
        om.registerModule(new JSR310Module());
        return om;
    }

    @Override
    public ObjectMapper createObjectMapper(CouchDbConnector connector) {
        final ObjectMapper om = delegate.createObjectMapper(connector);
        om.registerModule(new JSR310Module());
        return om;
    }
}