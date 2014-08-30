package com.mgomez.geolib.track.boundary;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.track.controller.BerkeleyDB;
import com.mgomez.geolib.track.controller.TrackPersistenceController;
import com.mgomez.geolib.track.entity.Track;

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

    public ImmutableList<Track> getTracks() {
        return ImmutableList.copyOf(trackPersistenceController.getTracks());
    }

    public void addTrack(Track file) {
        trackPersistenceController.addTrack(file);
    }

    public Optional<Track> getTrack(String trackName) {
        return Optional.ofNullable(trackPersistenceController.getTrack(trackName));
    }

    public Optional<Track> getMostRecentTrack() {
        return trackPersistenceController.getTracks().stream().max((o1, o2) -> (int) (o1.getUploadedDate().compareTo(o2.getUploadedDate())));
    }
}
