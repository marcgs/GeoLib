package com.mgomez.geolib.track.controller;

import com.mgomez.geolib.track.entity.Track;

import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public interface TrackPersistenceController {

    List<Track> listTracks();

    void addTrack(Track track);

    Track getTrackById(String trackName);

}
