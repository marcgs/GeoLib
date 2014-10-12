package com.mgomez.geolib.track.boundary.inmemory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mgomez.geolib.common.mock.MockAlternative;
import com.mgomez.geolib.track.boundary.TrackService;
import com.mgomez.geolib.track.entity.TrackDocument;

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@MockAlternative
@Singleton
public class TrackServiceMockImpl implements TrackService {

    private Map<String, TrackDocument> trackDocuments = Maps.newHashMap();
    @Override
    public List<TrackDocument> listTracks() {
        return Lists.newArrayList(trackDocuments.values());
    }
    private Map<String, String> trackContents = Maps.newHashMap();

    private AtomicInteger counter = new AtomicInteger();

    @Override
    public void addTrack(TrackDocument track, String content) {
        String id = Integer.toString(counter.incrementAndGet());
        track.setId(id);
        trackDocuments.put(id, track);
        trackContents.put(id, content);
    }

    @Override
    public TrackDocument getTrack(String id) {
        return trackDocuments.get(id);
    }

    @Override
    public String getTrackContent(String id) {
        return trackContents.get(id);
    }

    @Override
    public void deleteTrack(TrackDocument track) {
        trackDocuments.remove(track.getId());
    }
}
