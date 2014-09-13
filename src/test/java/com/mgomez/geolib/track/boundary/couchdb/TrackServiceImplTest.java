package com.mgomez.geolib.track.boundary.couchdb;

import com.mgomez.geolib.config.GeoLibConfiguration;
import com.mgomez.geolib.track.entity.TrackDocument;
import org.ektorp.Attachment;
import org.ektorp.AttachmentInputStream;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.mgomez.geolib.track.boundary.couchdb.TrackServiceImpl.CONTENT_ATTACHMENT_KEY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class TrackServiceImplTest {

    public static final String ID = "id";
    public static final String CONTENT = "sample content";

    @Mock
    private GeoLibConfiguration configurationMock;
    @Mock
    private TrackRepository trackRepositoryMock;
    @Mock
    private TrackDocument trackMock;
    @Mock
    private Attachment attachmentMock;

    private TrackServiceImpl service;

    @Test
    public void getTrack() {
        assertThat(service.getTrack(ID), is(trackMock));
    }

    @Test
    public void getTrackContent() {
        assertThat(service.getTrackContent(ID), is(CONTENT));
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(trackRepositoryMock.get(ID)).thenReturn(trackMock);
        InputStream stream = new ByteArrayInputStream(CONTENT.getBytes(StandardCharsets.UTF_8));
        when(trackRepositoryMock.getAttachment(ID, CONTENT_ATTACHMENT_KEY)).thenReturn(new AttachmentInputStream("id", stream, "contentType"));


        service = new TrackServiceImpl(configurationMock, trackRepositoryMock);
    }


}