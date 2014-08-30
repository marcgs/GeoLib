package com.mgomez.geolib.common.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import javax.enterprise.inject.Produces;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class ObjectMapperProducer {
    @Produces
    public ObjectMapper produceObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        return mapper;
    }
}
