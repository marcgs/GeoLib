package com.mgomez.geolib.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.mgomez.geolib.track.controller.BerkeleyDBTrackPersistenceController;
import com.mgomez.geolib.track.entity.Track;
import com.sleepycat.je.DatabaseException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class BerkeleyDBIntegrationTest {

    private BerkeleyDBTrackPersistenceController controller;

    @Test
    public void putAndGetTrack() throws DatabaseException {
        final Track track = new Track("trackName", "someContent lorem ipsum blah blah blah");
        controller.addTrack(track);
        final Track retrievedTrack = controller.getTrack("trackName");
        final List<Track> tracks = controller.getTracks();

        assertThat(retrievedTrack.toString(), is(track.toString()));
        //assertThat(tracks.size(), is(1));
        assertThat(tracks.get(0).getFileName(), is(track.getFileName()));
    }

    // TODO: Fix this test
    @Ignore
    @Test
    public void getNonExsistingTrack() throws DatabaseException {
        final Track retrievedTrack = controller.getTrack("nonExistingTrack");
    }

    @Before
    public void setUp() throws DatabaseException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        controller = new BerkeleyDBTrackPersistenceController(mapper);
        controller.postConstruct();
    }

    @After
    public void tearDown() throws DatabaseException {
        controller.preDestroy();
    }
}
