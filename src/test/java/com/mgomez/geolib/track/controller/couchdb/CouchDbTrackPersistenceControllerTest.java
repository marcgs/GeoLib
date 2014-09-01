package com.mgomez.geolib.track.controller.couchdb;

import com.google.common.collect.Lists;
import com.mgomez.geolib.config.GeoLibConfiguration;
import com.mgomez.geolib.config.GeoLibConfigurationKey;
import com.mgomez.geolib.track.entity.TrackDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class CouchDbTrackPersistenceControllerTest {

    public static final String TEST_DB_URL = "http://localhost:5984/";
    @Mock
    private GeoLibConfiguration configurationMock;
    private CouchDbTrackPersistenceController controller;

    @Test
    public void addAndGetTrack() throws MalformedURLException {
        when(configurationMock.getConfigEntry(GeoLibConfigurationKey.DB_URL)).thenReturn(TEST_DB_URL);
        when(configurationMock.getConfigEntry(GeoLibConfigurationKey.DB_USERNAME)).thenReturn(null);
        when(configurationMock.getConfigEntry(GeoLibConfigurationKey.DB_PWD)).thenReturn(null);
        when(configurationMock.getConfigEntry(GeoLibConfigurationKey.DB_NAME)).thenReturn("testDb");


        controller.postConstruct();

        emptyDb();

        final TrackDocument trackDocument = new TrackDocument("trackName", "Some sample content...");
        controller.addTrack(trackDocument);

        final TrackDocument retrievedTrackDocument = controller.getTrackById(trackDocument.getId());
        assertThat(retrievedTrackDocument.toString(), equalTo(trackDocument.toString()));

        final TrackDocument anotherTrackDocument = new TrackDocument("anotherTrackName", "Some other sample content...");
        controller.addTrack(anotherTrackDocument);

        final List<TrackDocument> trackDocuments = controller.listTracks();
        assertThat(trackDocuments.size(), is(2));
        final List<String> ids = trackDocuments.stream().map(t -> t.getId()).collect(Collectors.toList());
        assertThat(ids, is(Lists.newArrayList(trackDocument.getId(), anotherTrackDocument.getId())));
    }

    private void emptyDb() {
        final List<TrackDocument> trackDocuments = controller.listTracks();
        trackDocuments.stream().forEach(controller::deleteTrack);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new CouchDbTrackPersistenceController(configurationMock);
    }

    @After
    public void tearDown() {
        emptyDb();
    }
}