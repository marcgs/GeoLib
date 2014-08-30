package com.mgomez.geolib.track.controller;

import com.mgomez.geolib.track.entity.Track;
import com.mgomez.geolib.track.entity.TrackMeta;

import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public interface TrackPersistenceController {

    List<TrackMeta> listTracks();

    void addTrack(Track track);

    Track getTrack(String trackName);

    List<Track> getTracks();

}
