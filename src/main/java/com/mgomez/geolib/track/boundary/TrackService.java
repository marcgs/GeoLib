package com.mgomez.geolib.track.boundary;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.track.controller.TrackPersistenceController;
import com.mgomez.geolib.track.controller.couchdb.CouchDb;
import com.mgomez.geolib.track.entity.Track;

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

    public ImmutableList<Track> listTracks() {
        return ImmutableList.copyOf(trackPersistenceController.listTracks());
    }

    public void addTrack(Track file) {
        trackPersistenceController.addTrack(file);
    }

    public Optional<Track> getTrack(String id) {
        return Optional.ofNullable(trackPersistenceController.getTrackById(id));
    }

}
