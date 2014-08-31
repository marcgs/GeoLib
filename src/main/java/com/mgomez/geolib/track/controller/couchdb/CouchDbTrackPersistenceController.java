package com.mgomez.geolib.track.controller.couchdb;

import com.mgomez.geolib.config.GeoLibConfiguration;
import com.mgomez.geolib.config.GeoLibConfigurationKey;
import com.mgomez.geolib.track.controller.TrackPersistenceController;
import com.mgomez.geolib.track.entity.Track;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.MalformedURLException;
import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@CouchDb
public class CouchDbTrackPersistenceController implements TrackPersistenceController {

    @Inject
    private GeoLibConfiguration config;
    private CouchDbTrackRepository trackRepository;

    @PostConstruct
    public void postConstruct() throws MalformedURLException {
        HttpClient httpClient = new StdHttpClient.Builder()
                .url(config.getConfigEntry(GeoLibConfigurationKey.DB_URL))
                .username(config.getConfigEntry(GeoLibConfigurationKey.DB_USERNAME))
                .password(config.getConfigEntry(GeoLibConfigurationKey.DB_PWD))
                .build();
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector db = new StdCouchDbConnector(config.getConfigEntry(GeoLibConfigurationKey.DB_NAME), dbInstance, new CouchDbGeoLibObjectMapperFactory());
        trackRepository = new CouchDbTrackRepository(db);
        trackRepository.initStandardDesignDocument();
    }

    @Override
    public List<Track> listTracks() {
        return trackRepository.getAll();
    }

    @Override
    public void addTrack(Track track) {
        trackRepository.add(track);
    }

    @Override
    public Track getTrackById(String id) {
        return trackRepository.get(id);
    }
}
