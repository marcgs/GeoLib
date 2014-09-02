package com.mgomez.geolib.track.boundary;

import com.google.common.annotations.VisibleForTesting;
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
    private TrackPersistenceController controller;

    public TrackService() {
    }

    @VisibleForTesting
    TrackService(TrackPersistenceController controller) {
        this.controller = controller;
    }

    public ImmutableList<TrackDocument> listTracks() {
        return ImmutableList.copyOf(controller.listTracks());
    }

    public void addTrack(TrackDocument file) {
        controller.addTrack(file);
    }

    public Optional<TrackDocument> getTrack(String id) {
        return Optional.ofNullable(controller.getTrackById(id));
    }

}
