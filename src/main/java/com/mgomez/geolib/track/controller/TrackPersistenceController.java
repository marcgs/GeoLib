package com.mgomez.geolib.track.controller;

import com.mgomez.geolib.track.entity.TrackDocument;

import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public interface TrackPersistenceController {

    List<TrackDocument> listTracks();

    void addTrack(TrackDocument track);

    TrackDocument getTrackById(String id);

    void deleteTrack(TrackDocument track);

}
