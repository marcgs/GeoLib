package com.mgomez.geolib.track.controller;

import com.google.common.collect.Lists;
import com.mgomez.geolib.track.entity.Track;

import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Singleton
public class InMemoryTrackPersistenceController implements TrackPersistenceController {

    private List<Track> tracks = Lists.newArrayList();
    private AtomicInteger counter = new AtomicInteger();

    @Override
    public List<Track> listTracks() {
        return tracks;
    }

    @Override
    public void addTrack(Track file) {
        final int id = counter.incrementAndGet();
        file.setId(Integer.toString(id));
        tracks.add(file);
    }

    @Override
    public Track getTrackById(String id) {
        return tracks.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }
}
