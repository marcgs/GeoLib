package com.mgomez.geolib.track.boundary;

import com.mgomez.geolib.track.entity.TrackDocument;

import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public interface TrackService {

    List<TrackDocument> listTracks();

    void addTrack(TrackDocument track, String content);

    TrackDocument getTrack(String id);

    String getTrackContent(String id);

    void deleteTrack(TrackDocument track);

}
