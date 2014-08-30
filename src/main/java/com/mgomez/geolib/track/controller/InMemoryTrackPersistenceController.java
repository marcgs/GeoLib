package com.mgomez.geolib.track.controller;

import com.google.common.collect.Lists;
import com.mgomez.geolib.track.entity.Track;
import com.mgomez.geolib.track.entity.TrackMeta;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Singleton
public class InMemoryTrackPersistenceController implements TrackPersistenceController {

    private List<Track> tracks = Lists.newArrayList();

    @Override
    public List<TrackMeta> listTracks() {
        return tracks.stream().map(t -> t.getTrackMeta()).collect(Collectors.toList());
    }

    @Override
    public void addTrack(Track file) {
        tracks.add(file);
    }

    @Override
    public Track getTrack(String trackName) {
        return tracks.stream().filter(t -> t.getTrackMeta().getTrackName().equals(trackName)).findFirst().get();
    }

    @Override
    public List<Track> getTracks() {
        return tracks;
    }
}
