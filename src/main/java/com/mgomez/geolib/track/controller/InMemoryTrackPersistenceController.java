package com.mgomez.geolib.track.controller;

import com.google.common.collect.Lists;
import com.mgomez.geolib.track.entity.TrackDocument;

import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Singleton
public class InMemoryTrackPersistenceController implements TrackPersistenceController {

    private List<TrackDocument> tracks = Lists.newArrayList();
    private AtomicInteger counter = new AtomicInteger();

    @Override
    public List<TrackDocument> listTracks() {
        return tracks;
    }

    @Override
    public void addTrack(TrackDocument file) {
        final int id = counter.incrementAndGet();
        file.setId(Integer.toString(id));
        tracks.add(file);
    }

    @Override
    public TrackDocument getTrackById(String id) {
        return tracks.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }
}
