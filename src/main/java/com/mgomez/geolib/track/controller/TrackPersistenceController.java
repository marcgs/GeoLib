package com.mgomez.geolib.track.controller;

import com.mgomez.geolib.track.entity.Track;

import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public interface TrackPersistenceController {

    List<Track> getTracks();

    void addTrack(Track track);

    Track getTrack(String track);

}
