package com.mgomez.geolib.track.resource;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.track.boundary.TrackService;
import com.mgomez.geolib.track.entity.Track;
import com.mgomez.geolib.track.entity.TrackMeta;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class TrackResourceTest {

    @Mock
    private TrackService trackService;

    private TrackResource trackResource;

    @Test
    public void listFiles() {
        final ImmutableList<TrackMeta> expected = ImmutableList.<TrackMeta>builder().build();

        // train
        when(trackService.listTracks()).thenReturn(expected);

        // exercise
        final List<TrackMeta> actual = trackResource.listTracks();

        // assert
        assertThat(actual, is(expected));
    }

    @Test
    public void getMostRecentFile() {
        final Track expected = Mockito.mock(Track.class);

        // train
        when(trackService.getMostRecentTrack()).thenReturn(Optional.of(expected));

        // exercise
        final Track actual = trackResource.getMostRecentTrack();

        // assert
        assertThat(actual, is(expected));
    }

    @Test
    public void getMostRecentFile_null() {
        // train
        when(trackService.getMostRecentTrack()).thenReturn(Optional.empty());

        // exercise
        final Track actual = trackResource.getMostRecentTrack();

        // assert
        assertNull(actual);
    }

    @Test
    public void getTrack() {
        final String fileName = "fileName";
        final Track expected = Mockito.mock(Track.class);

        // train
        reset();
        when(trackService.getTrack(fileName)).thenReturn(Optional.of(expected));

        // exercise
        final Track actual = trackResource.getTrack(fileName);

        // assert
        assertThat(actual, is(expected));
    }

    @Test
    public void getTrack_null() {
        final String fileName = "fileName";

        // train
        when(trackService.getTrack(fileName)).thenReturn(Optional.empty());

        // exercise
        final Track actual = trackResource.getTrack(fileName);

        // assert
        assertNull(actual);
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trackResource = new TrackResource(trackService);
    }
}