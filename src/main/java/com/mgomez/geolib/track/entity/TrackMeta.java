package com.mgomez.geolib.track.entity;

import com.google.common.base.Objects;

import java.time.LocalDateTime;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class TrackMeta {

    private String trackName;
    private LocalDateTime uploadedDate = LocalDateTime.now();
    private String fileType = "text/xml";

    public TrackMeta() {
    }

    public TrackMeta(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackName() {
        return trackName;
    }

    public LocalDateTime getUploadedDate() {
        return uploadedDate;
    }

    public String getFileType() {
        return fileType;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("trackName", trackName)
                .add("uploadedDate", uploadedDate)
                .add("fileType", fileType)
                .toString();
    }


}
