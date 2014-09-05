package com.mgomez.geolib.track.boundary.inmemory;

import com.google.common.collect.Lists;
import com.mgomez.geolib.track.boundary.TrackService;
import com.mgomez.geolib.track.entity.TrackDocument;

import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Alternative
@Singleton
public class TrackServiceImpl implements TrackService {

    private List<TrackDocument> tracks = Lists.newArrayList();
    private AtomicInteger counter = new AtomicInteger();

    @Override
    public List<TrackDocument> listTracks() {
        return tracks;
    }

    @Override
    public void addTrack(TrackDocument track, String content) {
        final int id = counter.incrementAndGet();
        track.setId(Integer.toString(id));
        tracks.add(track);
    }

    @Override
    public TrackDocument getTrack(String id) {
        return tracks.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }

    @Override
    public String getTrackContent(String id) {
        return null;
    }

    @Override
    public void deleteTrack(TrackDocument track) {
        // TODO: fix remove
        tracks.remove(track);
    }
}
