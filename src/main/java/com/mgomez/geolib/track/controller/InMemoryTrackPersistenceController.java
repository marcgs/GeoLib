package com.mgomez.geolib.track.controller;

import com.google.common.collect.Lists;
import com.mgomez.geolib.track.entity.Track;

import javax.inject.Singleton;
import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Singleton
public class InMemoryTrackPersistenceController implements TrackPersistenceController {

    private List<Track> tracks = Lists.newArrayList();

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track file) {
        tracks.add(file);
    }

    public Track getTrack(String trackName) {
        return tracks.stream().filter(t -> t.getFileName().equals(trackName)).findFirst().get();
    }
}
