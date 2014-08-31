package com.mgomez.geolib.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.mgomez.geolib.track.entity.Track;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class TrackSerializationTest {

    @Test
    public void trackSerialization() throws IOException {
        final Track track = new Track("fileName", "someContent lorem ipsum blah blah blah");

        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());

        final String serializedTrack = mapper.writeValueAsString(track);
        final Track deserializedTrack = mapper.readValue(serializedTrack, Track.class);

        assertThat(deserializedTrack.toString(), is(track.toString()));
    }

}
