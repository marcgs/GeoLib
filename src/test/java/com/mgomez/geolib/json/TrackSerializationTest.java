package com.mgomez.geolib.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.mgomez.geolib.track.entity.TrackDocument;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class TrackSerializationTest {

    @Test
    public void trackSerialization() throws IOException {
        final TrackDocument track = new TrackDocument("fileName", LocalDateTime.now());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());

        final String serializedTrack = mapper.writeValueAsString(track);
        final TrackDocument deserializedTrack = mapper.readValue(serializedTrack, TrackDocument.class);

        assertThat(deserializedTrack.toString(), is(track.toString()));
    }

}
