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

import static org.hamcrest.CoreMatchers.is;
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
        final List<TrackDocument> actual = trackResource.listTracks();

        // assert
        assertThat(actual, is(expected));
    }

    @Test
    public void getTrackContent() {
        final TrackDocument trackDocumentMock = Mockito.mock(TrackDocument.class);
        final String id = "id";
        final String content = "content";

        // train
        reset();
        when(trackService.getTrackContent(id)).thenReturn(content);

        // exercise
        final String actual = trackResource.getTrackContent(id);

        // assert
        assertThat(actual, is(content));
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trackResource = new TrackResource(trackService);
    }
}