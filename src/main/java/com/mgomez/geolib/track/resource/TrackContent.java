package com.mgomez.geolib.track.resource;

import com.google.common.base.Objects;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class TrackContent {

    private String id;
    private String content;

    public TrackContent(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("content", content)
                .toString();
    }

}
