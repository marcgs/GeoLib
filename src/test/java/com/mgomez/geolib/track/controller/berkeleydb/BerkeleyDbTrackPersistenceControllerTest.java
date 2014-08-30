package com.mgomez.geolib.track.controller.berkeleydb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.mgomez.geolib.track.entity.Track;
import com.mgomez.geolib.track.entity.TrackMeta;
import com.sleepycat.je.DatabaseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BerkeleyDbTrackPersistenceControllerTest {

    private BerkeleyDbTrackPersistenceController controller;

    @Test
    public void putAndGetTrack() throws DatabaseException {
        final Track track = new Track(new TrackMeta("trackName"), "someContent lorem ipsum blah blah blah");
        controller.addTrack(track);
        final Track retrievedTrack = controller.getTrack("trackName");
        final List<TrackMeta> trackMetas = controller.listTracks();

        assertThat(retrievedTrack.toString(), is(track.toString()));
        //assertThat(tracks.size(), is(1));
        assertThat(trackMetas.get(0).getTrackName(), is(track.getTrackMeta().getTrackName()));
    }

    // TODO: Fix this test
    @Ignore
    @Test
    public void getNonExistingTrack() throws DatabaseException {
        final Track retrievedTrack = controller.getTrack("nonExistingTrack");
    }

    @Before
    public void setUp() throws DatabaseException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        controller = new BerkeleyDbTrackPersistenceController(mapper);
        controller.postConstruct();
    }

    @After
    public void tearDown() throws DatabaseException {
        controller.preDestroy();
    }
}