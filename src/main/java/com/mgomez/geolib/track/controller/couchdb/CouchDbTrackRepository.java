package com.mgomez.geolib.track.controller.couchdb;

import com.mgomez.geolib.track.entity.TrackDocument;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class CouchDbTrackRepository extends CouchDbRepositorySupport<TrackDocument> {
    public CouchDbTrackRepository(CouchDbConnector db) {
        super(TrackDocument.class, db);
    }
}
