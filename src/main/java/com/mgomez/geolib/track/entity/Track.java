package com.mgomez.geolib.track.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties({"id", "revision"})
public class Track {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_rev")
    private String revision;

    private TrackMeta trackMeta;
    private String content;

    public Track() {
    }

    public Track(TrackMeta trackMeta, String content) {
        this.trackMeta = trackMeta;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
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
