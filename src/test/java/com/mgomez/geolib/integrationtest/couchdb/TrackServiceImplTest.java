package com.mgomez.geolib.integrationtest.couchdb;

import com.google.common.collect.Lists;
import com.mgomez.geolib.config.GeoLibConfiguration;
import com.mgomez.geolib.config.GeoLibConfigurationKey;
import com.mgomez.geolib.integrationtest.IntegrationTest;
import com.mgomez.geolib.track.boundary.couchdb.TrackServiceImpl;
import com.mgomez.geolib.track.entity.TrackDocument;
import org.ektorp.support.CouchDbDocument;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Category(IntegrationTest.class)
public class TrackServiceImplTest {

    public static final String TEST_DB_URL = "http://192.168.33.33:5984/";
    @Mock
    private GeoLibConfiguration configurationMock;
    private TrackServiceImpl service;

    @Test
    public void addAndGetTrack() throws MalformedURLException {
        final String content1 = "Some sample content...";
        final String trackName1 = "trackName";
        final TrackDocument inputTrack = new TrackDocument(trackName1, LocalDateTime.now());
        final String trackName2 = "anotherTrackName";
        final String content2 = "Some other sample content...";
        final TrackDocument inputTrack2 = new TrackDocument(trackName2, LocalDateTime.now());

        service.addTrack(inputTrack, content1);
        final TrackDocument outputTrack = service.getTrack(inputTrack.getId());
        Assert.assertThat(outputTrack.toString(), CoreMatchers.equalTo(inputTrack.toString()));
        Assert.assertThat(service.getTrackContent(outputTrack.getId()), CoreMatchers.is(content1));
        Assert.assertThat(service.listTracks().size(), CoreMatchers.is(1));

        service.addTrack(inputTrack2, content2);
        final List<TrackDocument> trackList = service.listTracks();
        Assert.assertThat(trackList.size(), CoreMatchers.is(2));
        final List<String> ids = trackList.stream().map(CouchDbDocument::getId).collect(Collectors.toList());
        Assert.assertThat(ids, CoreMatchers.is(Lists.newArrayList(inputTrack.getId(), inputTrack2.getId())));
    }


    @Before
    public void setUp() throws MalformedURLException {
        MockitoAnnotations.initMocks(this);
        service = new TrackServiceImpl(configurationMock);

        Mockito.when(configurationMock.getConfigEntry(GeoLibConfigurationKey.DB_URL)).thenReturn(TEST_DB_URL);
        Mockito.when(configurationMock.getConfigEntry(GeoLibConfigurationKey.DB_USERNAME)).thenReturn(null);
        Mockito.when(configurationMock.getConfigEntry(GeoLibConfigurationKey.DB_PWD)).thenReturn(null);
        Mockito.when(configurationMock.getConfigEntry(GeoLibConfigurationKey.DB_NAME)).thenReturn("testDb");

        service.postConstruct();
        emptyDb();
    }

    @After
    public void tearDown() {
        emptyDb();
    }


    private void emptyDb() {
        final List<TrackDocument> trackDocuments = service.listTracks();
        trackDocuments.stream().forEach(service::deleteTrack);
    }
}