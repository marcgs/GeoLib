package com.mgomez.geolib.common.producer;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.inject.Produces;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class ObjectMapperProducer {
    @Produces
    public ObjectMapper produceObjectMapper() {
        return new ObjectMapper();
    }
}
