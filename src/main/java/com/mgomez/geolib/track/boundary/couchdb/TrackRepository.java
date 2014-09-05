package com.mgomez.geolib.track.boundary.couchdb;

import com.mgomez.geolib.track.entity.TrackDocument;
import org.ektorp.AttachmentInputStream;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class TrackRepository extends CouchDbRepositorySupport<TrackDocument> {
    public TrackRepository(CouchDbConnector db) {
        super(TrackDocument.class, db);
    }

    public AttachmentInputStream getAttachment(String documentId, String attachmentId) {
        return db.getAttachment(documentId, attachmentId);
    }
}
