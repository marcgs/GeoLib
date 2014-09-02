package com.mgomez.geolib.track.boundary;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.track.controller.TrackPersistenceController;
import com.mgomez.geolib.track.entity.TrackDocument;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    @Mock
    private TrackPersistenceController controller;

    private TrackService trackService;

    @Test
    public void listTracks() {
        final TrackDocument track1 = mock(TrackDocument.class);
        final TrackDocument track2 = mock(TrackDocument.class);
        final ImmutableList<TrackDocument> tracksMock = ImmutableList.of(track1, track2);

        // train
        when(controller.listTracks()).thenReturn(tracksMock);

        // exercise
        final ImmutableList<TrackDocument> actual = trackService.listTracks();

        // assert
        assertThat(actual, equalTo(tracksMock));
    }

    @Test
    public void addTrack() {
        final TrackDocument track = mock(TrackDocument.class);

        // exercise
        trackService.addTrack(track);

        // assert
        verify(controller).addTrack(same(track));
    }

    @Test
    public void getTrack() {
        final String id = "id";
        final TrackDocument track = mock(TrackDocument.class);

        // train
        when(controller.getTrackById(id)).thenReturn(track);

        // exercise
        final Optional<TrackDocument> actual = trackService.getTrack(id);

        // assert
        assertTrue(actual.isPresent());
        assertThat(actual.get(), is(track));
    }

    @Test
    public void getTrack_notExisting() {
        final String id = "id";

        // train
        when(controller.getTrackById(id)).thenReturn(null);

        // exercise
        final Optional<TrackDocument> actual = trackService.getTrack(id);

        // assert
        assertFalse(actual.isPresent());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trackService = new TrackService(controller);
    }
}