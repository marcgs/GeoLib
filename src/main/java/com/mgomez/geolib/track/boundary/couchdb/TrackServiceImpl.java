package com.mgomez.geolib.track.boundary.couchdb;

import com.mgomez.geolib.config.GeoLibConfiguration;
import com.mgomez.geolib.config.GeoLibConfigurationKey;
import com.mgomez.geolib.track.boundary.TrackService;
import com.mgomez.geolib.track.entity.TrackDocument;
import org.apache.commons.io.IOUtils;
import org.ektorp.Attachment;
import org.ektorp.AttachmentInputStream;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class TrackServiceImpl implements TrackService {

    public static final String CONTENT_ATTACHMENT_KEY = "content";
    public static final String CONTENT_TYPE = "application/octet-stream";

    private GeoLibConfiguration config;
    private TrackRepository trackRepository;

    @Inject
    public TrackServiceImpl(GeoLibConfiguration config) {
        this.config = config;
    }

    @PostConstruct
    public void postConstruct() {
        initTrackRepository();
    }


    @Override
    public List<TrackDocument> listTracks() {
        return trackRepository.getAll();
    }

    @Override
    public void addTrack(TrackDocument track, String content) {
        track.addInlineAttachment(new Attachment(CONTENT_ATTACHMENT_KEY, Base64.getEncoder().encodeToString(content.getBytes()), CONTENT_TYPE));
        trackRepository.add(track);
    }

    @Override
    public TrackDocument getTrack(String id) {
        return trackRepository.get(id);
    }

    @Override
    public String getTrackContent(String id) {
        final AttachmentInputStream inputStream = trackRepository.getAttachment(id, CONTENT_ATTACHMENT_KEY);
        try {
            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTrack(TrackDocument track) {
        trackRepository.remove(track);
    }


    private void initTrackRepository() {
        HttpClient httpClient = null;
        try {
            httpClient = new StdHttpClient.Builder()
                    .url(config.getConfigEntry(GeoLibConfigurationKey.DB_URL))
                    .username(config.getConfigEntry(GeoLibConfigurationKey.DB_USERNAME))
                    .password(config.getConfigEntry(GeoLibConfigurationKey.DB_PWD))
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector db = new StdCouchDbConnector(config.getConfigEntry(GeoLibConfigurationKey.DB_NAME), dbInstance, new ObjectMapperFactory());
        trackRepository = new TrackRepository(db);
        trackRepository.initStandardDesignDocument();
    }
}
