package com.mgomez.geolib.track.controller.couchdb;

import com.mgomez.geolib.config.GeoLibConfiguration;
import com.mgomez.geolib.config.GeoLibConfigurationKey;
import com.mgomez.geolib.track.entity.TrackDocument;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.junit.Ignore;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class CouchDbIntegrationTest {

    @Ignore
    @Test
    public void couchDbIntegration() throws MalformedURLException {
        GeoLibConfiguration config = new GeoLibConfiguration();
        config.postConstruct();
        HttpClient httpClient = new StdHttpClient.Builder()
                .url(config.getConfigEntry(GeoLibConfigurationKey.DB_URL))
                .username(config.getConfigEntry(GeoLibConfigurationKey.DB_USERNAME))
                .password(config.getConfigEntry(GeoLibConfigurationKey.DB_PWD))
                .build();

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector db = new StdCouchDbConnector(config.getConfigEntry(GeoLibConfigurationKey.DB_NAME), dbInstance, new CouchDbGeoLibObjectMapperFactory());

        CouchDbTrackRepository repo = new CouchDbTrackRepository(db);
        repo.add(new TrackDocument("testFile", "some content abcdefghijk"));
        final List<TrackDocument> all = repo.getAll();
    }
}
