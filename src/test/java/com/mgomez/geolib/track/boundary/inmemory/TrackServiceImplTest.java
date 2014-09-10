package com.mgomez.geolib.track.boundary.inmemory;

import com.mgomez.geolib.track.entity.TrackDocument;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TrackServiceImplTest {

    private TrackServiceImpl trackService;

    @Test
    public void addAndGetTracks() {
        String name = "name";
        String content = "content";
        LocalDateTime time = LocalDateTime.now();

        assertThat(trackService.listTracks().size(), is(0));

        TrackDocument track1 = addTrack(name, content, time);
        assertThat(trackService.listTracks().size(), is(1));

        String track1Id = track1.getId();
        assertThat(trackService.getTrack(track1Id).toString(), is(track1.toString()));
        assertThat(trackService.getTrackContent(track1Id), is(content));

        TrackDocument track2 = addTrack(name, content, time);
        assertThat(trackService.listTracks().size(), is(2));

        trackService.deleteTrack(track1);
        assertThat(trackService.listTracks().size(), is(1));

        String track2Id = track2.getId();
        assertThat(trackService.getTrack(track2Id).toString(), is(track2.toString()));
        assertThat(trackService.getTrackContent(track2Id), is(content));
    }

    private TrackDocument addTrack(String name, String content, LocalDateTime time) {
        TrackDocument track = new TrackDocument(name, time);
        trackService.addTrack(track, content);
        return track;
    }

    @Before
    public void setUp() {
        trackService = new TrackServiceImpl();
    }


}