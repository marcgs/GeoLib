package com.mgomez.geolib.track.entity;

import com.google.common.base.Objects;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class Track {

    private TrackMeta trackMeta;
    private String content;

    public Track() {
    }

    public Track(TrackMeta trackMeta, String content) {
        this.trackMeta = trackMeta;
        this.content = content;
    }

    public TrackMeta getTrackMeta() {
        return trackMeta;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("trackMeta", trackMeta)
                .add("content", content)
                .toString();
    }

}
