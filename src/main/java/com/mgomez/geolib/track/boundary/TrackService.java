package com.mgomez.geolib.track.boundary;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.track.controller.TrackPersistenceController;
import com.mgomez.geolib.track.controller.couchdb.CouchDb;
import com.mgomez.geolib.track.entity.TrackDocument;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Stateless
public class TrackService {

    @CouchDb
    @Inject
    private TrackPersistenceController trackPersistenceController;

    public ImmutableList<TrackDocument> listTracks() {
        return ImmutableList.copyOf(trackPersistenceController.listTracks());
    }

    public void addTrack(TrackDocument file) {
        trackPersistenceController.addTrack(file);
    }

    public Optional<TrackDocument> getTrack(String id) {
        return Optional.ofNullable(trackPersistenceController.getTrackById(id));
    }

}
