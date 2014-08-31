package com.mgomez.geolib.track.resource;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.track.boundary.TrackService;
import com.mgomez.geolib.track.entity.TrackDocument;
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
        final ImmutableList<TrackDocument> expected = ImmutableList.<TrackDocument>builder().build();

        // train
        when(trackService.listTracks()).thenReturn(expected);

        // exercise
        final List<TrackMeta> actual = trackResource.listTracks();

        // assert
        assertThat(actual, is(expected));
    }

    @Test
    public void getTrack() {
        final TrackDocument expected = Mockito.mock(TrackDocument.class);
        final String id = "id";
        final String content = "content";

        // train
        reset();
        when(trackService.getTrack(id)).thenReturn(Optional.of(expected));
        when(expected.getId()).thenReturn(id);
        when(expected.getContent()).thenReturn(content);

        // exercise
        final TrackContent actual = trackResource.getTrack(id);

        // assert
        assertThat(actual.toString(), is(new TrackContent(id, content).toString()));
    }

    @Test
    public void getTrack_null() {
        final String fileName = "fileName";

        // train
        when(trackService.getTrack(fileName)).thenReturn(Optional.empty());

        // exercise
        final TrackContent actual = trackResource.getTrack(fileName);

        // assert
        assertNull(actual);
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trackResource = new TrackResource(trackService);
    }
}