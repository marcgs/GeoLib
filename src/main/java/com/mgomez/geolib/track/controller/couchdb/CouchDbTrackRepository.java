package com.mgomez.geolib.track.controller.couchdb;

import com.mgomez.geolib.track.entity.Track;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;

import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class CouchDbTrackRepository extends CouchDbRepositorySupport<Track> {
    public CouchDbTrackRepository(CouchDbConnector db) {
        super(Track.class, db);
    }

    @GenerateView
    public List<Track> findByName(String name) {
        return queryView("byName", name);
    }
}
