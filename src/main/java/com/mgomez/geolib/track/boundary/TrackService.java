package com.mgomez.geolib.track.boundary;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.track.controller.BerkeleyDB;
import com.mgomez.geolib.track.controller.TrackPersistenceController;
import com.mgomez.geolib.track.entity.Track;
import com.mgomez.geolib.track.entity.TrackMeta;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Stateless
public class TrackService {

    @BerkeleyDB
    @Inject
    private TrackPersistenceController trackPersistenceController;

    public ImmutableList<TrackMeta> listTracks() {
        return ImmutableList.copyOf(trackPersistenceController.listTracks());
    }

    public void addTrack(Track file) {
        trackPersistenceController.addTrack(file);
    }

    public Optional<Track> getTrack(String trackName) {
        return Optional.ofNullable(trackPersistenceController.getTrack(trackName));
    }

}
